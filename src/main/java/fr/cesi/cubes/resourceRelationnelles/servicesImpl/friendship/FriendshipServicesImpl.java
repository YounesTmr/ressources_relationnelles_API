package fr.cesi.cubes.resourceRelationnelles.servicesImpl.friendship;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cesi.cubes.resourceRelationnelles.entities.friendship.Friendship;
import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.RelationshipType;
import fr.cesi.cubes.resourceRelationnelles.repository.friendship.FriendshipRepository;
import fr.cesi.cubes.resourceRelationnelles.repository.resource.RelationshipTypeRepository;
import fr.cesi.cubes.resourceRelationnelles.request.friendship.CreateFriendshipForm;
import fr.cesi.cubes.resourceRelationnelles.services.friendship.FriendshipServices;
import fr.cesi.cubes.resourceRelationnelles.services.member.MemberServices;

@Service( value = "friendship" )
public class FriendshipServicesImpl implements FriendshipServices {

    @Autowired
    private FriendshipRepository       friendshipRepository;
    @Autowired
    private RelationshipTypeRepository relationshipTypeRepository;
    @Autowired
    private MemberServices             memberServices;

    // contructor
    public FriendshipServicesImpl() {
    }

    @Override
    public Friendship newFriendship( long id, CreateFriendshipForm createFriendshipForm ) {

        Friendship friendship = new Friendship();

        // get members of the friendship
        Member member1 = memberServices.getMemberById( id );
        Member member2 = memberServices.getMemberById( createFriendshipForm.getMemberId() );

        friendship.setMember1( member1 );
        friendship.setMember2( member2 );
        friendship.setGroup( relationshipTypeRepository.findByName( createFriendshipForm.getGroup() ) );

        // Does this friendship already exist ?
        boolean exists = false;
        int index = 0;
        // research in friendships list of the member
        if ( member1.getFriendships() != null ) {
            for ( Friendship friends : member1.getFriendships() ) {
                if ( friends.getMember2().getId() == member2.getId() ) {
                    exists = true;
                    index = member1.getFriendships().indexOf( friends );
                    break;
                }
            }
        }
        if ( !exists ) {
            // if not, friendship saved in database
            friendshipRepository.save( friendship );
        } else {
            // is exists, replace informations at the old ID (but forces the friendship boolean to true
            friendship.setId( member1.getFriendships().get( index ).getId() );
            friendship.setAreFriends( true );
            friendshipRepository.save( friendship );
        }
        return friendship;
    }

    @Override
    public Friendship getFriendshipById( long id ) {
        return friendshipRepository.findById( id );
    }

    @Override
    public List<Friendship> getFriendshipByMember1( long idMember1 ) {
        Member member1 = memberServices.getMemberById( idMember1 );
        return friendshipRepository.findByMember1AndAreFriendsTrue( member1 );
    }

    @Override
    public List<Friendship> getFriendshipByMember1AndGroup( long idMember1, String group ) {
        Member member1 = memberServices.getMemberById( idMember1 );
        RelationshipType relationshipType = relationshipTypeRepository.findByName( group );
        return friendshipRepository.findByMember1AndGroupAndAreFriendsTrue( member1, relationshipType );
    }

    @Override
    public Friendship updateGroup( long idMember1, long idMember2, String group ) throws Exception {

        // get members
        Member member1 = memberServices.getMemberById( idMember1 );
        Member member2 = memberServices.getMemberById( idMember2 );

        // get the existing friendship
        Friendship friendship = friendshipRepository.findByMember1AndMember2AndAreFriendsTrue( member1, member2 );

        if ( friendship == null ) {
            throw new Exception(
                    "The member n°" + member2.getId() + " is not in friendship list of member n°" + member1.getId() );
        }

        // update the group
        friendship.setGroup( relationshipTypeRepository.findByName( group ) );
        friendshipRepository.updateGroup( friendship.getId(), friendship.getGroup().getId() ); // SQL query needs strings

        return friendship;
    }

    @Override
    public Friendship cancelFriendship( long idMember1, long idMember2 ) {

        // get members
        Member member1 = memberServices.getMemberById( idMember1 );
        Member member2 = memberServices.getMemberById( idMember2 );

        // get the existing friendship
        Friendship friendship = friendshipRepository.findByMember1AndMember2AndAreFriendsTrue( member1, member2 );

        // put the friendship boolean to false
        friendship.setAreFriends( false );
        friendshipRepository.updateAreFriends( friendship.getId(), false );
        return friendship;

    }

    @Override
    public void deleteFriendshipByMember1( long idMember1 ) {
        Member member1 = memberServices.getMemberById( idMember1 );
        friendshipRepository.deleteByMember1( member1 );
    }

    @Override
    public void deleteFriendshipByMember2( long idMember2 ) {
        Member member2 = memberServices.getMemberById( idMember2 );
        friendshipRepository.deleteByMember2( member2 );
    }

    // ***********************
    // DATA PROCESSING
    // ***********************

    // ***********************
    // DATA VALIDATION METHODS
    // ***********************

}
