package fr.cesi.cubes.resourceRelationnelles.exceptions.resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException {

    public ResourceNotFoundException( String s ) {
        super( HttpStatus.NOT_FOUND, s );
    }

}
