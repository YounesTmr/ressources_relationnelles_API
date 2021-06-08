package fr.cesi.cubes.resourceRelationnelles.controller.share;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.cesi.cubes.resourceRelationnelles.entities.share.Share;
import fr.cesi.cubes.resourceRelationnelles.exceptions.ConstraintViolationException;
import fr.cesi.cubes.resourceRelationnelles.request.share.CreateShareForm;
import fr.cesi.cubes.resourceRelationnelles.services.share.ShareServices;

@RestController
@CrossOrigin("*")
@RequestMapping("/projectRE")
public class ShareController {

	@Autowired
	private ShareServices shareServices;

	// get the ressources shared by other for a specific member
	@GetMapping(value = "/profile/{idRecipient}/shared")
	public Set<Share> getSharedResourcesByRecipient(@PathVariable long idRecipient) {
		return shareServices.getShareByRecipient(idRecipient);
	}

	// share a new resource to another member
	@PostMapping(value = "/profile/{idSender}/share")
	public void share(@PathVariable long idSender, @RequestBody CreateShareForm createShareForm, Errors errors) {

		// check if entities constraints are respected
		constraintViolationCheck(errors);

		shareServices.createShare(idSender, createShareForm);
	}

	// delete a share record
	@DeleteMapping(value = "/profile/{idRecipient}/shared/{idShare}")
	public void deleteShare(@PathVariable long idRecipient, @PathVariable long idShare) {
		shareServices.deleteShareById(idShare);
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