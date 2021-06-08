package fr.cesi.cubes.resourceRelationnelles.servicesImpl.interaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cesi.cubes.resourceRelationnelles.entities.historic.Action;
import fr.cesi.cubes.resourceRelationnelles.entities.interaction.Interaction;
import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;
import fr.cesi.cubes.resourceRelationnelles.repository.interaction.InteractionRepository;
import fr.cesi.cubes.resourceRelationnelles.request.interaction.CreateInteractionForm;
import fr.cesi.cubes.resourceRelationnelles.request.interaction.UpdateInteractionForm;
import fr.cesi.cubes.resourceRelationnelles.services.historic.HistoricServices;
import fr.cesi.cubes.resourceRelationnelles.services.interaction.InteractionServices;
import fr.cesi.cubes.resourceRelationnelles.services.member.MemberServices;
import fr.cesi.cubes.resourceRelationnelles.services.resource.ResourceServices;

@Service( value = "interaction" )
public class InteractionServicesImpl implements InteractionServices {

    @Autowired
    private InteractionRepository interactionRepository;
    @Autowired
    private MemberServices        memberServices;
    @Autowired
    private ResourceServices      resourceServices;
    @Autowired
    private HistoricServices      historicServices;

    // contructor
    public InteractionServicesImpl() {
    }

    @Override
    public Interaction newInteraction( long idMember, CreateInteractionForm createInteractionForm ) {

        Interaction interaction = new Interaction();

        // get attributes of the interaction
        Member member = memberServices.getMemberById( idMember );
        Resource resource = resourceServices.getResourceById( createInteractionForm.getResourceId() );
        interaction.setMember( member );
        interaction.setResource( resource );
        interaction.setFavorite( createInteractionForm.getFavorite() );
        interaction.setComplete( createInteractionForm.getComplete() );

        // Does this relation already exist ?
        boolean exists = false;
        int index = 0;
        // research in interactions list of the member
        if ( member.getInteractions() != null ) {
            for ( Interaction interactionOfMember : member.getInteractions() ) {
                if ( interactionOfMember.getResource().getId() == resource.getId() ) {
                    exists = true;
                    index = member.getInteractions().indexOf( interactionOfMember );
                    break;
                }
            }
        }
        if ( !exists ) {

            // if not, action record saved in database
            interactionRepository.save( interaction );
            if ( interaction.isFavorite() != null ) {
                historicServices.saveAction( interaction.getMember(), null, interaction.getResource(),
                        Action.ADD_FAVORITE );
            }
            if ( interaction.isComplete() != null ) {
                historicServices.saveAction( interaction.getMember(), null, interaction.getResource(),
                        Action.COMPLETE );
            }
            return interaction;

        } else {

            // is exists, get old informations and replace by new informations if they exist in JSON
            Interaction existedInteraction = member.getInteractions().get( index );
            if ( interaction.isFavorite() != null ) {
                existedInteraction.setFavorite( interaction.isFavorite() );
                historicServices.saveAction( existedInteraction.getMember(), null, existedInteraction.getResource(),
                        Action.ADD_FAVORITE );
            }
            if ( interaction.isComplete() != null ) {
                existedInteraction.setComplete( interaction.isComplete() );
                historicServices.saveAction( existedInteraction.getMember(), null, existedInteraction.getResource(),
                        Action.COMPLETE );
            }
            interactionRepository.save( existedInteraction );
            return existedInteraction;
        }
    }

    @Override
    public Interaction getInteractionById( long id ) {
        return interactionRepository.findById( id );
    }

    @Override
    public List<Interaction> getFavoritesByMember( long idMember ) {
        Member member = memberServices.getMemberById( idMember );
        return interactionRepository.findByMemberAndFavoriteTrue( member );
    }

    @Override
    public List<Interaction> getCompletedByMember( long idMember ) {
        Member member = memberServices.getMemberById( idMember );
        return interactionRepository.findByMemberAndCompleteTrue( member );
    }

    @Override
    public List<Interaction> getInteractionByResource( long idResource ) {
        Resource resource = resourceServices.getResourceById( idResource );
        return interactionRepository.findByResource( resource );
    }

    @Override
    public Interaction updateInteraction( long idMember, long idResource,
            UpdateInteractionForm updateInteractionForm ) throws Exception {

        // get attributes of the interaction
        Member member = memberServices.getMemberById( idMember );
        Resource resource = resourceServices.getResourceById( idResource );
        Interaction interaction = interactionRepository.findByMemberAndResource( member, resource );

        if ( interaction == null ) {
            throw new Exception(
                    "the member n°" + idMember + " has no interaction with resource n°" + idResource + " yet." );
        }

        // following the action (favorite or completion), the database element is updated and a new historic record is created
        if ( updateInteractionForm.getFavorite() != null ) {
            interaction.setFavorite( updateInteractionForm.getFavorite() );
            interactionRepository.updateFavorite( interaction.getId(), interaction.isFavorite() );
            historicServices.saveAction( interaction.getMember(), null, interaction.getResource(),
                    Action.ADD_FAVORITE );
        }
        if ( updateInteractionForm.getComplete() != null ) {
            interaction.setComplete( updateInteractionForm.getComplete() );
            interactionRepository.updateComplete( interaction.getId(), interaction.isComplete() );
            historicServices.saveAction( interaction.getMember(), null, interaction.getResource(),
                    Action.COMPLETE );
        }

        return interaction;
    }

    @Override
    public void deleteInteractionByResource( long idResource ) {
        Resource resource = resourceServices.getResourceById( idResource );
        interactionRepository.deleteByResource( resource );
    }

    @Override
    public void deleteInteractionByMember( long idMember ) {
        Member member = memberServices.getMemberById( idMember );
        interactionRepository.deleteByMember( member );

    }

    // ***********************
    // DATA PROCESSING
    // ***********************

    // ***********************
    // DATA VALIDATION METHODS
    // ***********************

}
