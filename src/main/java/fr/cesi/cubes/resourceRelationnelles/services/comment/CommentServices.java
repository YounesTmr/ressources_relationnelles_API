package fr.cesi.cubes.resourceRelationnelles.services.comment;

import java.util.List;

import fr.cesi.cubes.resourceRelationnelles.entities.comment.Comment;

public interface CommentServices {
    Comment createComment( String body, Long idPost );

    List<Comment> getAllComments();

    List<Comment> getAllCommentsByCommentId( Long id );

    List<Comment> getAllCommentsByPostId( Long id );

    Comment updateComment( long id, String newBody );

    void deleteCommentById( Long id );

    void deleteCommentByResource( Long idResource );

    void removeMemberFromComment( Long idMember );
}
