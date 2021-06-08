package fr.cesi.cubes.resourceRelationnelles.services.interaction;

import java.util.List;

import fr.cesi.cubes.resourceRelationnelles.entities.interaction.Interaction;
import fr.cesi.cubes.resourceRelationnelles.request.interaction.CreateInteractionForm;
import fr.cesi.cubes.resourceRelationnelles.request.interaction.UpdateInteractionForm;

public interface InteractionServices {

    public Interaction newInteraction( long id, CreateInteractionForm createInteractionForm );

    public Interaction getInteractionById( long id );

    public List<Interaction> getFavoritesByMember( long idMember );

    public List<Interaction> getCompletedByMember( long idMember );

    public List<Interaction> getInteractionByResource( long idResource );

    public Interaction updateInteraction( long idMember, long idResource, UpdateInteractionForm updateInteractionForm )
            throws Exception;

    public void deleteInteractionByResource( long idResource );

    public void deleteInteractionByMember( long idMember );

}
