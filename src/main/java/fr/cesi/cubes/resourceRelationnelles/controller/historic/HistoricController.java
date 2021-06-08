package fr.cesi.cubes.resourceRelationnelles.controller.historic;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.cesi.cubes.resourceRelationnelles.entities.historic.Historic;
import fr.cesi.cubes.resourceRelationnelles.exceptions.ConstraintViolationException;
import fr.cesi.cubes.resourceRelationnelles.request.historic.CreateHistoricForm;
import fr.cesi.cubes.resourceRelationnelles.services.historic.HistoricServices;

@RestController
@CrossOrigin( "*" )
@RequestMapping( "/projectRE" )
public class HistoricController {

    @Autowired
    private HistoricServices historicService;

    // get the actions historic from an unique member
    @GetMapping( value = "/profile/{id}/historic" )
    public List<Historic> getHistoricByMember( @PathVariable long id ) {
        return historicService.getHistoricByMember1( id );
    }

    // get the actions historic from all users
    @GetMapping( value = "/admin/historic" )
    public List<Historic> getAllHistoric() {
        return historicService.getHistoric();
    }

    // add a new action record
    @PostMapping( value = "/admin/historic" )
    public Historic saveAction( @RequestBody CreateHistoricForm createHistoricForm, Errors errors ) {

        // check if entities constraints are respected
        constraintViolationCheck( errors );

        return historicService.saveAction( createHistoricForm );
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