package fr.cesi.cubes.resourceRelationnelles.services.share;

import java.util.Set;

import fr.cesi.cubes.resourceRelationnelles.entities.share.Share;
import fr.cesi.cubes.resourceRelationnelles.request.share.CreateShareForm;

public interface ShareServices {

    public void createShare( long idSender, CreateShareForm createShareForm );

    public Set<Share> getShareByRecipient( long id );

    public void deleteShareById( long idShare );

    public void deleteShareByResource( long idResource );

    public void deleteShareBySender( long idSender );

    public void deleteShareByRecipient( long idRecipient );

}
