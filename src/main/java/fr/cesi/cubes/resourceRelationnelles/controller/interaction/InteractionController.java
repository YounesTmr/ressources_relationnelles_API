package fr.cesi.cubes.resourceRelationnelles.controller.interaction;

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

import fr.cesi.cubes.resourceRelationnelles.entities.interaction.Interaction;
import fr.cesi.cubes.resourceRelationnelles.exceptions.ConstraintViolationException;
import fr.cesi.cubes.resourceRelationnelles.request.interaction.CreateInteractionForm;
import fr.cesi.cubes.resourceRelationnelles.request.interaction.UpdateInteractionForm;
import fr.cesi.cubes.resourceRelationnelles.services.interaction.InteractionServices;

@RestController
@CrossOrigin( "*" )
@RequestMapping( "/projectRE" )
public class InteractionController {

    @Autowired
    private InteractionServices interactionService;

    // get the favorite resources list from a member profile
    @GetMapping( value = "/profile/{id}/favorites" )
    public List<Interaction> getFavoritesResources( @PathVariable long id ) {
        return interactionService.getFavoritesByMember( id );
    }

    // get the completed resources list from a member profile
    @GetMapping( value = "/profile/{id}/completed" )
    public List<Interaction> getCompletedResources( @PathVariable long id ) {
        return interactionService.getCompletedByMember( id );
    }

    // create a new interaction (favorite or completion) from a member profile
    @PostMapping( value = "/profile/{id}/resources" )
    public Interaction registerInteracton( @PathVariable long id,
            @RequestBody CreateInteractionForm createInteractionForm,
            Errors errors ) {

        // check if entities constraints are respected
        constraintViolationCheck( errors );

        return interactionService.newInteraction( id, createInteractionForm );
    }

    // update if a resource is favorite or completed (only the corresponding attribute is needed in JSON)
    @PutMapping( value = "/profile/{idMember}/resources/{idResource}/update" )
    public Interaction updateInteracton( @PathVariable long idMember, @PathVariable long idResource,
            @RequestBody UpdateInteractionForm updateInteractionForm ) throws Exception {
        return interactionService.updateInteraction( idMember, idResource, updateInteractionForm );
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