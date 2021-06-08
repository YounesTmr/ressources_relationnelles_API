package fr.cesi.cubes.resourceRelationnelles.exceptions.member;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MemberNotFoundException extends ResponseStatusException {

    public MemberNotFoundException( String s ) {
        super( HttpStatus.NOT_FOUND, s );
    }

}
