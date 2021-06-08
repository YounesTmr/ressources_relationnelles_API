package fr.cesi.cubes.resourceRelationnelles.services.friendship;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.cesi.cubes.resourceRelationnelles.entities.friendship.Friendship;
import fr.cesi.cubes.resourceRelationnelles.request.friendship.CreateFriendshipForm;

public interface FriendshipServices {

    public Friendship newFriendship( long id, CreateFriendshipForm createFriendshipForm );

    public Friendship getFriendshipById( long id );

    public List<Friendship> getFriendshipByMember1( long idMember1 );

    public List<Friendship> getFriendshipByMember1AndGroup( long idMember1, String group );

    public Friendship updateGroup( long idMember1, long idMember2, String group ) throws Exception;

    public Friendship cancelFriendship( long idMember1, long idMember2 );

    @Transactional
    public void deleteFriendshipByMember1( long idMember1 );

    @Transactional
    public void deleteFriendshipByMember2( long idMember2 );

}
