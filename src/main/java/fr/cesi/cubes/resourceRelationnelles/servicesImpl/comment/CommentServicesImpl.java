package fr.cesi.cubes.resourceRelationnelles.servicesImpl.comment;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.cesi.cubes.resourceRelationnelles.entities.comment.Comment;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;
import fr.cesi.cubes.resourceRelationnelles.repository.comment.CommentRepository;
import fr.cesi.cubes.resourceRelationnelles.services.authentification.AuthenticationService;
import fr.cesi.cubes.resourceRelationnelles.services.comment.CommentServices;
import fr.cesi.cubes.resourceRelationnelles.services.member.MemberServices;
import fr.cesi.cubes.resourceRelationnelles.services.resource.ResourceServices;

@Service( value = "commentService" )
public class CommentServicesImpl implements CommentServices {

    @Autowired
    private CommentRepository     repository;

    @Autowired
    private ResourceServices      resourceService;
    @Autowired
    private MemberServices        memberService;
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Comment createComment( String body, Long idPost ) {
        final Comment comment = new Comment( body, new Timestamp( System.currentTimeMillis() ),
                authenticationService.getCurrentUser() );
        final Resource post = this.resourceService.addComment( idPost, comment );
        comment.setResource( post );
        return this.repository.save( comment );
    }

    @Override
    public List<Comment> getAllComments() {
        return this.repository.findAll();
    }

    @Override
    public List<Comment> getAllCommentsByCommentId( Long id ) {
        return this.repository.findAllByCommentId( id );
    }

    @Override
    public List<Comment> getAllCommentsByPostId( Long id ) {
        return this.repository.findAllByResourceId( id );
    }

    @Override
    public Comment updateComment( long id, String newBody ) {
        final Comment comment = this.repository.findById( id )
                .orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND,
                        String.format( "None Comment could be found with the id %d", id ) ) );
        comment.setCommentBodyText( newBody );
        comment.setDateOfComment( new Timestamp( System.currentTimeMillis() ) );
        repository.saveAndFlush( comment );
        return comment;
    }

    @Override
    public void deleteCommentById( Long id ) {
        final Comment comment = this.repository.findById( id )
                .orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND,
                        String.format( "None Comment could be found with the id %d", id ) ) );
        this.resourceService.removeComment( comment.getResource().getId(), comment );
        this.repository.deleteById( id );
    }

    @Override
    public void deleteCommentByResource( Long idResource ) {
        Resource resource = this.resourceService.getResourceById( idResource );
        this.repository.deleteByResource( resource );
    }

    @Override
    public void removeMemberFromComment( Long idMember ) {
        this.repository.removeMember( idMember );
    }

}
