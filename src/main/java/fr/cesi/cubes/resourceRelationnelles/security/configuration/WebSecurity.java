package fr.cesi.cubes.resourceRelationnelles.security.configuration;

import static fr.cesi.cubes.resourceRelationnelles.security.SecurityConstants.LOG_IN_URL;
import static fr.cesi.cubes.resourceRelationnelles.security.SecurityConstants.REGISTER_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fr.cesi.cubes.resourceRelationnelles.security.filter.JWTAuthorizationFilter;
import fr.cesi.cubes.resourceRelationnelles.services.member.MemberServices;

@EnableWebSecurity
public class WebSecurity extends AbstractConfiguration {

	// ###########################################################################
	// WebSecurity boolean toggle for HTTP Pattern Matcher enablement.
	// To be modified in application.properties file :
	// - for security enabled (default or empty/null/commented):
	// api.security.httpPatternMatcher.disabled=false
	// - for security disabled :
	// api.security.httpPatternMatcher.disabled=true
	// ###########################################################################

	@Value("${api.security.httpPatternMatcher.disabled:false}")
	private boolean httpPatternMatcherDisabled;

	@Autowired
	private MemberServices userService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
		if (!httpPatternMatcherDisabled) { // http pattern matcher enabled
			http.authorizeRequests()
					.antMatchers(HttpMethod.POST, REGISTER_URL, LOG_IN_URL, "/projectRE/whoami", "/projectRE/register",
							"/projectRE/*/*", "/projectRE/home/resources/*/*/*/*")
					.permitAll()
					.antMatchers(HttpMethod.GET, "/favicon.ico", "/v2/api-docs", "/configuration/ui",
							"/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**",
							"/h2/**", "/projectRE/home/resources/*/*/*", "/projectRE/*/*",
							"/projectRE/home/resources/*/*/*/*", "/projectRE/resource/find/*")
					.permitAll().antMatchers(HttpMethod.PUT, "/projectRE/*/*", "/projectRE/*/*/*").permitAll()
					.anyRequest().authenticated();
		} else { // http pattern matcher disabled
			http.authorizeRequests().anyRequest().permitAll(); // toutes les pages/requêtes sont accessibles
		}

		http.authorizeRequests().and().addFilter(new JWTAuthorizationFilter(authenticationManager()))
				// this disables session creation on Spring Security
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().headers()
				.frameOptions().disable();
		http.headers()
        .contentSecurityPolicy("script-src 'self' https://trustedscripts.example.com; object-src https://trustedplugins.example.com; report-uri /csp-report-endpoint/");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(s -> this.userService.getMemberByUserName(s))
				.passwordEncoder(this.bCryptPasswordEncoder());
	}

}