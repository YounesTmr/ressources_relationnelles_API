package fr.cesi.cubes.resourceRelationnelles.servicesImpl.authentification;

import com.auth0.jwt.JWT;

import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.services.authentification.AuthenticationService;
import fr.cesi.cubes.resourceRelationnelles.services.member.MemberServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static fr.cesi.cubes.resourceRelationnelles.security.SecurityConstants.EXPIRATION_TIME;
import static fr.cesi.cubes.resourceRelationnelles.security.SecurityConstants.SECRET;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    public static final int SIZE_TMP_CODE = 10;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private MemberServices userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;



    @Override
    public Authentication authentication(String username, String password) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>())
        );
    }

    @Override
    public String login(Member user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
    }


	@Override
	public Member getCurrentUser() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.userService.getMemberByUserName((String) authentication.getPrincipal());
		
	}
}
