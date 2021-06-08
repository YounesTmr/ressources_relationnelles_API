package fr.cesi.cubes.resourceRelationnelles.exceptions.member;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MemberValidationException extends ResponseStatusException {

    public MemberValidationException( String s ) {
        super( HttpStatus.BAD_REQUEST, s );
    }

}
