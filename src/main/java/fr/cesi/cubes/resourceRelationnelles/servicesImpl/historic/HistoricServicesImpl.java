package fr.cesi.cubes.resourceRelationnelles.servicesImpl.historic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cesi.cubes.resourceRelationnelles.entities.historic.Action;
import fr.cesi.cubes.resourceRelationnelles.entities.historic.Historic;
import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;
import fr.cesi.cubes.resourceRelationnelles.repository.historic.HistoricRepository;
import fr.cesi.cubes.resourceRelationnelles.request.historic.CreateHistoricForm;
import fr.cesi.cubes.resourceRelationnelles.services.historic.HistoricServices;
import fr.cesi.cubes.resourceRelationnelles.services.member.MemberServices;
import fr.cesi.cubes.resourceRelationnelles.services.resource.ResourceServices;

@Service( value = "historic" )
public class HistoricServicesImpl implements HistoricServices {

    @Autowired
    private HistoricRepository historicRepository;
    @Autowired
    private MemberServices     memberServices;
    @Autowired
    private ResourceServices   resourceServices;

    // contructor
    public HistoricServicesImpl() {
    }

    // ***********************
    // DATA PROCESSING
    // ***********************

    @Override
    public Historic saveAction( long idMember1, Long idMember2, Long idResource, Action action ) {

        // get attributes
        Member member1 = memberServices.getMemberById( idMember1 );

        Member member2 = memberServices.getMemberById( idMember2 );

        Resource resource = resourceServices.getResourceById( idResource );

        // instanciate a new historic
        Historic historic = new Historic( member1, member2, resource, action );

        // add a new record in data base
        return historicRepository.save( historic );
    }

    @Override
    public Historic saveAction( CreateHistoricForm createHistoricForm ) {

        // get attribute
        Member member1 = memberServices.getMemberById( createHistoricForm.getMainMemberId() );
        Member member2;
        Resource resource;
        if ( createHistoricForm.getFriendId() != null ) {
            member2 = memberServices.getMemberById( createHistoricForm.getFriendId() );
        } else {
            member2 = null;
        }

        if ( createHistoricForm.getResourceId() != null ) {
            resource = resourceServices.getResourceById( createHistoricForm.getResourceId() );
        } else {
            resource = null;
        }

        // instanciate a new historic
        Historic historic = new Historic( member1, member2, resource, createHistoricForm.getAction() );

        // add a new record in data base
        return historicRepository.save( historic );
    }

    @Override
    public Historic saveAction( Member member1, Member member2, Resource resource, Action action ) {
        Historic historic = new Historic( member1, member2, resource, action );
        return historicRepository.save( historic );
    }

    @Override
    public List<Historic> getHistoricByMember1( long idMember1 ) {
        Member member1 = memberServices.getMemberById( idMember1 );
        return historicRepository.findByMember1( member1 );
    }

    @Override
    public List<Historic> getHistoricByMember2( long idMember1 ) {
        Member member1 = memberServices.getMemberById( idMember1 );
        return historicRepository.findByMember2( member1 );
    }

    @Override
    public List<Historic> getHistoricByResource( long idResource ) {
        Resource resource = resourceServices.getResourceById( idResource );
        return historicRepository.findByResource( resource );
    }

    @Override
    public List<Historic> getHistoric() {
        return historicRepository.findAll();
    }

    @Override
    public void deleteByResource( long idResource ) {
        Resource resource = resourceServices.getResourceById( idResource );
        historicRepository.deleteByResource( resource );
    }

    @Override
    public void deleteByMember1( long idMember ) {
        Member member = memberServices.getMemberById( idMember );
        historicRepository.deleteByMember1( member );
    }

    @Override
    public void deleteByMember2( long idMember ) {
        Member member = memberServices.getMemberById( idMember );
        historicRepository.deleteByMember2( member );
    }

    // ***********************
    // DATA VALIDATION METHODS
    // ***********************

}
