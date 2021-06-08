package fr.cesi.cubes.resourceRelationnelles.exceptions.resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceValidationException extends ResponseStatusException {

    public ResourceValidationException( String s ) {
        super( HttpStatus.BAD_REQUEST, s );
    }

}
