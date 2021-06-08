package fr.cesi.cubes.resourceRelationnelles.repository.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.cesi.cubes.resourceRelationnelles.entities.comment.Comment;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByCommentId( Long id );

    List<Comment> findAllByResourceId( Long id );

    List<Comment> deleteByResource( Resource resource );

    @Modifying
    @Transactional
    @Query( nativeQuery = true, value = "UPDATE comment SET member_id_member = null WHERE member_id_member = :id" )
    void removeMember( @Param( value = "id" ) long id );
}
