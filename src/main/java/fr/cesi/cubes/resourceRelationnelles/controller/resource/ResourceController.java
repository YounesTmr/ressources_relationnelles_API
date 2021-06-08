package fr.cesi.cubes.resourceRelationnelles.controller.resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;
import fr.cesi.cubes.resourceRelationnelles.exceptions.ConstraintViolationException;
import fr.cesi.cubes.resourceRelationnelles.request.resource.CreateResourceForm;
import fr.cesi.cubes.resourceRelationnelles.request.resource.UpdateResourceForm;
import fr.cesi.cubes.resourceRelationnelles.services.resource.ResourceServices;

@RestController
@CrossOrigin("*")
@RequestMapping("/projectRE")
public class ResourceController {

	@Value("${app.upload.dir:${user.home}}")
	public String uploadDir;

	@Autowired
	private ResourceServices resourceService;

	// ******************************************
	// Ressource creation / editing
	// ******************************************

	// create a new resource, but kept it in draft mode for further modification
	@PostMapping(value = "/resource/createInDraft")
	public Resource createDraft(@RequestPart("file") MultipartFile file, @RequestPart("image") MultipartFile image,
			@RequestPart("register") CreateResourceForm createResourceForm, Errors errors) {
		constraintViolationCheck(errors);
		Path copyLocationFile = Paths
				.get("C:\\Users\\Temar\\OneDrive\\Bureau\\api1\\ressourcesRelationnelles\\RessourceRelationnelleFront\\src\\assets\\FileAsset"
						+ File.separator + StringUtils.cleanPath(
								"file de " + createResourceForm.getName() + " - " + file.getOriginalFilename()));
		try {
			Files.copy(file.getInputStream(), copyLocationFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Path copyLocationImage = Paths
				.get("C:\\Users\\Temar\\OneDrive\\Bureau\\api1\\ressourcesRelationnelles\\RessourceRelationnelleFront\\src\\assets\\FileAsset"
						+ File.separator + StringUtils.cleanPath(
								"image de " + createResourceForm.getName() + " - " + image.getOriginalFilename()));
		try {
			Files.copy(image.getInputStream(), copyLocationImage, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resourceService.createDraft(createResourceForm, file, image);
	}

	// create a new resource, but kept it in draft mode for further modification
	@PostMapping(value = "/resource/createInDraft/text")
	public Resource createDraft(@RequestBody CreateResourceForm createResourceForm, Errors errors) {
		constraintViolationCheck(errors);
		return resourceService.createDraft(createResourceForm, null, null);
	}

	// create a new resource, ready for validation by moderator
	@PostMapping(value = "/resource/createToPost")
	public Resource createResource(@RequestPart("file") MultipartFile file, @RequestPart("image") MultipartFile image,
			@RequestPart("register") CreateResourceForm createResourceForm, Errors errors) {
		constraintViolationCheck(errors);
		Path copyLocationFile = Paths
				.get("C:\\Users\\Temar\\OneDrive\\Bureau\\api1\\ressourcesRelationnelles\\RessourceRelationnelleFront\\src\\assets\\FileAsset"
						+ File.separator + StringUtils.cleanPath(
								"file de " + createResourceForm.getName() + " - " + file.getOriginalFilename()));
		try {
			Files.copy(file.getInputStream(), copyLocationFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Path copyLocationImage = Paths
				.get("C:\\Users\\Temar\\OneDrive\\Bureau\\api1\\ressourcesRelationnelles\\RessourceRelationnelleFront\\src\\assets\\FileAsset"
						+ File.separator + StringUtils.cleanPath(
								"image de " + createResourceForm.getName() + " - " + image.getOriginalFilename()));
		try {
			Files.copy(image.getInputStream(), copyLocationImage, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resourceService.createResource(createResourceForm, file, image);
	}

	// create a new resource, ready for validation by moderator
	@PostMapping(value = "/resource/createToPost/text")
	public Resource createResource(@RequestBody CreateResourceForm createResourceForm, Errors errors) {
		constraintViolationCheck(errors);
		return resourceService.createResource(createResourceForm, null, null);
	}

	// update a drafted resource or existing resource and get ready for validation
	// by moderator
	@PutMapping(value = "/resource/{id}/updateToPost")
	public Resource postDraft(@PathVariable long id, @RequestPart("file") MultipartFile file,
			@RequestPart("image") MultipartFile image, @RequestPart("register") UpdateResourceForm updateResourceForm,
			Errors errors) {
		constraintViolationCheck(errors);
		Path copyLocationFile = Paths.get(uploadDir + File.separator + StringUtils
				.cleanPath("file de " + updateResourceForm.getName() + " - " + file.getOriginalFilename()));
		try {
			Files.copy(file.getInputStream(), copyLocationFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Path copyLocationImage = Paths.get(uploadDir + File.separator + StringUtils
				.cleanPath("image de " + updateResourceForm.getName() + " - " + image.getOriginalFilename()));
		try {
			Files.copy(image.getInputStream(), copyLocationImage, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resourceService.updateResource(id, updateResourceForm, file, image);
	}

	// update a drafted resource or existing resource and get ready for validation
	// by moderator
	@PutMapping(value = "/resource/{id}/updateToPost/text")
	public Resource postDraft(@PathVariable long id, @Valid @RequestBody UpdateResourceForm updateResourceForm,
			Errors errors) {
		constraintViolationCheck(errors);
		return resourceService.updateResource(id, updateResourceForm, null, null);
	}

	// update a drafted resource or existing resource and let it in draft mode for
	// further modification
	@PutMapping(value = "/resource/{id}/updateInDraft")
	public Resource saveDraft(@PathVariable long id, @RequestPart("file") MultipartFile file,
			@RequestPart("image") MultipartFile image, @RequestPart("register") UpdateResourceForm updateResourceForm,
			Errors errors) {
		constraintViolationCheck(errors);
		Path copyLocationFile = Paths.get(uploadDir + File.separator + StringUtils
				.cleanPath("file de " + updateResourceForm.getName() + " - " + file.getOriginalFilename()));
		try {
			Files.copy(file.getInputStream(), copyLocationFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Path copyLocationImage = Paths.get(uploadDir + File.separator + StringUtils
				.cleanPath("image de " + updateResourceForm.getName() + " - " + image.getOriginalFilename()));
		try {
			Files.copy(image.getInputStream(), copyLocationImage, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resourceService.updateDraft(id, updateResourceForm, file, image);
	}

	// update a drafted resource or existing resource and let it in draft mode for
	// further modification
	@PutMapping(value = "/resource/{id}/updateInDraft/text")
	public Resource saveDraft(@PathVariable long id, @Valid @RequestBody UpdateResourceForm updateResourceForm,
			Errors errors) {
		constraintViolationCheck(errors);
		return resourceService.updateDraft(id, updateResourceForm, null, null);
	}

	// ********************************************
	// Show resources in detail or for home listing
	// ********************************************

	// Get a Resource by its Id
	@GetMapping(value = "/resource/{id}")
	public Resource getResourceById(@PathVariable long id) {
		resourceService.addViews(id);
		return resourceService.getResourceById(id);
	}

	@GetMapping(value = "/resource/posts")
	public List<Resource> getPostResourceForCurrentUser() {
		return resourceService.getPostResourcesByCreator();
	}

	@GetMapping(value = "/resource/drafts")
	public List<Resource> getDraftResourceForCurrentUser() {
		return resourceService.getDraftResourcesByCreator();
	}

	// Get a list of ressources containing an expression
	@GetMapping(value = "/resource/find/{expression}")
	public Set<Resource> getResourcesByExpression(@PathVariable String expression) {
		return resourceService.getResourcesByExpression(expression);
	}

	// Get all resources sorted and categorized
	@GetMapping(value = "/home/resources/{filter}/{sort}/{limit}/{page}")
	public Map<String, List<Resource>> getResourcesFiltered(@PathVariable String filter, @PathVariable String sort,
			@PathVariable int limit, @PathVariable int page) {
		return resourceService.getResourcesByFilter(filter, sort, limit, page);
	}

	// Get all resources
	@GetMapping(value = "/resources")
	public List<Resource> getResources() {
		return resourceService.getResources();
	}

	// Get all validated resources
	@GetMapping(value = "/resources/valid")
	public List<Resource> getValidResources() {
		return resourceService.getValidResources();
	}

	// ********************************************
	// Delete resource
	// ********************************************

	// Delete a Resource by its Id
	@DeleteMapping(value = "/resource/{id}/delete")
	public void deleteResourceById(@PathVariable long id) {
		resourceService.deleteResourceById(id);
	}

	// *************************
	// Moderator actions
	// *************************

	// validate ressource
	@PutMapping(value = "/admin/resources/{id}/validate")
	public Resource validateResource(@PathVariable long id) {
		Resource resourceUpdated = resourceService.validateResource(id);
		return resourceUpdated;
	}

	// switch resource visibility
	@PutMapping(value = "/admin/resources/{id}/visibility")
	public Resource switchVisibility(@PathVariable long id) {
		return resourceService.switchVisibility(id);
	}

	// Get all ressources not validated by moderator
	@GetMapping(value = "/admin/resources/toValidate")
	public Set<Resource> getResourcesToValidate() {
		return resourceService.getResourcesToValidate();
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