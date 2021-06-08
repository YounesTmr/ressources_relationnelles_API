package fr.cesi.cubes.resourceRelationnelles.repository.friendship;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.cesi.cubes.resourceRelationnelles.entities.friendship.Friendship;
import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.RelationshipType;
import fr.cesi.cubes.resourceRelationnelles.request.statistics.CountKPI;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    Friendship findById( long id );

    List<Friendship> findByMember1AndAreFriendsTrue( Member member1 );

    List<Friendship> findByMember1AndGroupAndAreFriendsTrue( Member member1, RelationshipType type );

    Friendship findByMember1AndMember2AndAreFriendsTrue( Member member1, Member member2 );

    void deleteById( long id );

    void deleteByMember1( Member member1 );

    void deleteByMember2( Member member2 );

    Member findByAreFriends( boolean areFriends );

    @Modifying
    @Transactional
    @Query( nativeQuery = true, value = "UPDATE is_friend_with SET are_friends = :are_friends WHERE Id_Friendship = :id" )
    void updateAreFriends( @Param( value = "id" ) long id,
            @Param( value = "are_friends" ) boolean areFriends );

    @Modifying
    @Transactional
    @Query( nativeQuery = true, value = "UPDATE is_friend_with SET friendship_type = :groupId WHERE Id_Friendship = :id" )
    void updateGroup( @Param( value = "id" ) long id,
            @Param( value = "groupId" ) int groupId );

    @Query( nativeQuery = true, value = "SELECT count(Id_Friendship) AS total FROM is_friend_with WHERE Id_Member_1 = :idMember and are_friends = true" )
    CountKPI countFriends( @Param( value = "idMember" ) long idMember );

}
