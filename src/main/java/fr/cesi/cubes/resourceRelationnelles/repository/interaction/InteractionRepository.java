package fr.cesi.cubes.resourceRelationnelles.repository.interaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.cesi.cubes.resourceRelationnelles.entities.interaction.Interaction;
import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;
import fr.cesi.cubes.resourceRelationnelles.request.statistics.CountKPI;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {

    Interaction findById( long id );

    List<Interaction> findByMember( Member member );

    List<Interaction> findByMemberAndCompleteTrue( Member member );

    List<Interaction> findByMemberAndFavoriteTrue( Member member );

    List<Interaction> findByResource( Resource resource );

    Interaction findByMemberAndResource( Member member, Resource resource );

    void deleteById( long id );

    void deleteByResource( Resource resource );

    void deleteByMember( Member member );

    @Modifying
    @Transactional
    @Query( nativeQuery = true, value = "UPDATE has_interacted_with SET is_favorite = :is_favorite WHERE Id_interaction = :id" )
    void updateFavorite( @Param( value = "id" ) long id,
            @Param( value = "is_favorite" ) boolean favorite );

    @Modifying
    @Transactional
    @Query( nativeQuery = true, value = "UPDATE has_interacted_with SET is_complete = :is_complete WHERE Id_interaction = :id" )
    void updateComplete( @Param( value = "id" ) long id,
            @Param( value = "is_complete" ) boolean complete );

    @Query( nativeQuery = true, value = "SELECT count(Id_interaction) AS total FROM has_interacted_with WHERE Id_Member = :idMember and is_favorite = true" )
    CountKPI countFavorites( @Param( value = "idMember" ) long idMember );

    @Query( nativeQuery = true, value = "SELECT count(Id_interaction) AS total FROM has_interacted_with WHERE Id_Member = :idMember and is_complete = true" )
    CountKPI countCompletes( @Param( value = "idMember" ) long idMember );

}
