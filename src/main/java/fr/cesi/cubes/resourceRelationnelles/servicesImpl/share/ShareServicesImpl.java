package fr.cesi.cubes.resourceRelationnelles.servicesImpl.share;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cesi.cubes.resourceRelationnelles.entities.friendship.Friendship;
import fr.cesi.cubes.resourceRelationnelles.entities.historic.Action;
import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;
import fr.cesi.cubes.resourceRelationnelles.entities.share.Share;
import fr.cesi.cubes.resourceRelationnelles.repository.share.ShareRepository;
import fr.cesi.cubes.resourceRelationnelles.request.share.CreateShareForm;
import fr.cesi.cubes.resourceRelationnelles.services.friendship.FriendshipServices;
import fr.cesi.cubes.resourceRelationnelles.services.historic.HistoricServices;
import fr.cesi.cubes.resourceRelationnelles.services.member.MemberServices;
import fr.cesi.cubes.resourceRelationnelles.services.resource.ResourceServices;
import fr.cesi.cubes.resourceRelationnelles.services.share.ShareServices;

@Service
public class ShareServicesImpl implements ShareServices {

    @Autowired
    ShareRepository    shareRepository;

    @Autowired
    MemberServices     memberServices;
    @Autowired
    ResourceServices   resourceServices;

    @Autowired
    HistoricServices   historicServices;

    @Autowired
    FriendshipServices friendShipService;

    @Override
    public void createShare( long idSender, CreateShareForm createShareForm ) {

        Member sender = memberServices.getMemberById( idSender );

        if ( createShareForm.getGroup() != null ) {
            List<Friendship> friendShips = friendShipService.getFriendshipByMember1AndGroup( sender.getId(),
                    createShareForm.getGroup() );

            for ( Friendship friendShip : friendShips ) {
                Share share = new Share();

                share.setSender( memberServices.getMemberById( idSender ) );
                share.setRecipient( friendShip.getMember2() );
                share.setResource( resourceServices.getResourceById( createShareForm.getResourceId() ) );

                historicServices.saveAction( share.getSender(), share.getRecipient(), share.getResource(),
                        Action.SHARE );

                shareRepository.save( share );

            }
        } else {
            Share share = new Share();

            share.setSender( memberServices.getMemberById( idSender ) );
            share.setRecipient( memberServices.getMemberById( createShareForm.getRecipientId() ) );
            share.setResource( resourceServices.getResourceById( createShareForm.getResourceId() ) );

            historicServices.saveAction( share.getSender(), share.getRecipient(), share.getResource(), Action.SHARE );

            shareRepository.save( share );

        }

    }

    @Override
    public Set<Share> getShareByRecipient( long id ) {
        Member recipient = memberServices.getMemberById( id );
        return shareRepository.findByRecipient( recipient );
    }

    @Override
    public void deleteShareById( long idShare ) {
        shareRepository.deleteById( idShare );
    }

    @Override
    public void deleteShareByResource( long idResource ) {
        Resource resource = resourceServices.getResourceById( idResource );
        shareRepository.deleteByResource( resource );
    }

    @Override
    public void deleteShareBySender( long idSender ) {
        Member sender = memberServices.getMemberById( idSender );
        shareRepository.deleteBySender( sender );

    }

    @Override
    public void deleteShareByRecipient( long idRecipient ) {
        Member recipient = memberServices.getMemberById( idRecipient );
        shareRepository.deleteByRecipient( recipient );

    }

}
