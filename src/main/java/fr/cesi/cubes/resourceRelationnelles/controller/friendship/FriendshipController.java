package fr.cesi.cubes.resourceRelationnelles.controller.friendship;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.cesi.cubes.resourceRelationnelles.entities.friendship.Friendship;
import fr.cesi.cubes.resourceRelationnelles.exceptions.ConstraintViolationException;
import fr.cesi.cubes.resourceRelationnelles.request.friendship.CreateFriendshipForm;
import fr.cesi.cubes.resourceRelationnelles.request.friendship.UpdateFriendshipGroupForm;
import fr.cesi.cubes.resourceRelationnelles.services.friendship.FriendshipServices;

@RestController
@CrossOrigin( "*" )
@RequestMapping( "/projectRE" )
public class FriendshipController {

    @Autowired
    private FriendshipServices friendshipService;

    @GetMapping( value = "/profile/{id}/friends" )
    public List<Friendship> getFriendships( @PathVariable long id ) {
        return friendshipService.getFriendshipByMember1( id );
    }

    @GetMapping( value = "/profile/{id}/friends/{group}" )
    public List<Friendship> getFriendshipsByType( @PathVariable long id, @PathVariable String group ) {
        return friendshipService.getFriendshipByMember1AndGroup( id, group );
    }

    // add new friendship from member profile
    @PostMapping( value = "/profile/{id}/friends" )
    public Friendship registerFriendship( @PathVariable long id, @RequestBody CreateFriendshipForm createFriendshipForm,
            Errors errors ) {

        // check if entities constraints are respected
        constraintViolationCheck( errors );

        return friendshipService.newFriendship( id, createFriendshipForm );
    }

    // update the relationship type (group) from member profile (only the attribute
    // group is needed in JSON)
    @PutMapping( value = "/profile/{id1}/friends/{id2}/update" )
    public Friendship updateGroup( @PathVariable long id1, @PathVariable long id2,
            @RequestBody UpdateFriendshipGroupForm updateFriendshipGroupForm ) throws Exception {
        return friendshipService.updateGroup( id1, id2, updateFriendshipGroupForm.getGroup() );
    }

    // cancel the friendship with a member, from member profile
    @PutMapping( value = "/profile/{id1}/friends/{id2}/delete" )
    public Friendship cancelFriendship( @PathVariable long id1, @PathVariable long id2 ) {
        return friendshipService.cancelFriendship( id1, id2 );
    }

    // ***************
    // ERROR MANAGEMENT
    // ***************

    private void constraintViolationCheck( Errors errors ) {

        if ( errors.hasErrors() ) {
            List<ConstraintViolation<?>> violationsList = new ArrayList<>();
            for ( ObjectError e : errors.getAllErrors() ) {
                violationsList.add( e.unwrap( ConstraintViolation.class ) );
            }
            String exceptionMessage = "";
            for ( ConstraintViolation<?> violation : violationsList ) {
                if ( violationsList.indexOf( violation ) > 0 ) {
                    exceptionMessage += " | ";
                }
                exceptionMessage += violation.getMessage();
            }
            throw new ConstraintViolationException( exceptionMessage );
        }
    }

}