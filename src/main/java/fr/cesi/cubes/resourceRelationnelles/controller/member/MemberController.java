package fr.cesi.cubes.resourceRelationnelles.controller.member;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.exceptions.ConstraintViolationException;
import fr.cesi.cubes.resourceRelationnelles.request.jwt.JwtResponse;
import fr.cesi.cubes.resourceRelationnelles.request.member.CreateMemberForm;
import fr.cesi.cubes.resourceRelationnelles.request.member.LoginForm;
import fr.cesi.cubes.resourceRelationnelles.request.member.UpdateMemberForm;
import fr.cesi.cubes.resourceRelationnelles.request.member.UpdateMemberPasswordForm;
import fr.cesi.cubes.resourceRelationnelles.request.member.UpdateMemberStatusForm;
import fr.cesi.cubes.resourceRelationnelles.services.authentification.AuthenticationService;
import fr.cesi.cubes.resourceRelationnelles.services.member.MemberServices;

@RestController
@CrossOrigin("*")
@RequestMapping("/projectRE")
public class MemberController {

	@Autowired
	private MemberServices memberService;

	@Autowired
	private AuthenticationService service;

	@PostMapping(value = "/register")
	public Member registerMember(@Valid @RequestBody CreateMemberForm createMemberForm, Errors errors) {

		constraintViolationCheck(errors);

		return memberService.registerMember(createMemberForm);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginForm credentials) {
		final Authentication authenticate;
		try {
			authenticate = this.service.authentication(credentials.getUsername(), credentials.getPassword());
		} catch (AuthenticationException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
					"Wrong credentials, please try again or contact an administrator.");
		}
		final Member user = (Member) authenticate.getPrincipal();
		final String token = this.service.login(user);
		return new ResponseEntity<>(new JwtResponse(user, token, authenticate.getAuthorities()), HttpStatus.OK);
	}

	// Get a Member by his/her Id
	@GetMapping(value = "/profile/{id}")
	public Member getMemberById(@PathVariable long id) {
		return memberService.getMemberById(id);
	}

	// Get a Member by its username
	@GetMapping(value = "/profile/search/{username}")
	public Member getMemberByUsername(@PathVariable String username) {
		return memberService.getMemberByUserName(username);
	}

	// Get all Members
	@GetMapping(value = "/profile")
	public List<Member> getMembers() {
		return memberService.getMembers();
	}

	// Delete member
	@DeleteMapping(value = "/profile/{id}")
	public void deleteMemberById(@PathVariable long id) {
		memberService.deleteById(id);
	}

	// modify profile member attributes
	@PutMapping(value = "/profile/{id}")
	public Member updateMemberById(@PathVariable long id, @RequestBody UpdateMemberForm updateMemberForm) {
		return memberService.updateMemberProfile(id, updateMemberForm);

	}

	// modify profile password
	@PutMapping(value = "/profile/{id}/password")
	public Member updateMemberPassword(@PathVariable long id,
			@RequestBody UpdateMemberPasswordForm updateMemberPasswordForm) {
		return memberService.updatePassword(id, updateMemberPasswordForm);
	}

	// switch member account visibility
	@PutMapping(value = "/admin/members/{id}/account")
	public Member switchValidAccount(@PathVariable long id) {
		return memberService.switchAccountStatus(id);
	}

	// modidy member status
	@PutMapping(value = "/admin/members/{id}/status")
	public Member updateMemberStatus(@PathVariable long id,
			@RequestBody UpdateMemberStatusForm updateMemberStatusForm) {
		return memberService.updateStatus(id, updateMemberStatusForm);
	}

	@GetMapping("/member")
	public ResponseEntity<Member> getCurrentUser() {
		return new ResponseEntity<>(this.service.getCurrentUser(), HttpStatus.OK);
	}

	// ***************
	// ERROR MANAGEMENT
	// ***************

	private void constraintViolationCheck(Errors errors) {

		if (errors.hasErrors()) {
			List<ConstraintViolation<?>> violationsList = new ArrayList<>();
			for (ObjectError e : errors.getAllErrors()) {
				violationsList.add(e.unwrap(ConstraintViolation.class));
			}
			String exceptionMessage = "";
			for (ConstraintViolation<?> violation : violationsList) {
				if (violationsList.indexOf(violation) > 0) {
					exceptionMessage += " | ";
				}
				exceptionMessage += violation.getMessage();
			}
			throw new ConstraintViolationException(exceptionMessage);
		}
	}

}