package fr.cesi.cubes.resourceRelationnelles.services.authentification;

import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface AuthenticationService {

    Member getCurrentUser();

    Authentication authentication(String username, String password);

    String login(Member user);


}
