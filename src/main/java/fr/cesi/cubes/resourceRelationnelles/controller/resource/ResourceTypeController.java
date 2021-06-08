package fr.cesi.cubes.resourceRelationnelles.controller.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.ResourceType;
import fr.cesi.cubes.resourceRelationnelles.request.resource.ResourceTypeForm;
import fr.cesi.cubes.resourceRelationnelles.services.resource.ResourceTypeServices;

@RestController
@CrossOrigin("*")
@RequestMapping("/projectRE")
public class ResourceTypeController {

	@Autowired
	private ResourceTypeServices resourceTypeService;

	// ******************************************
	// Ressource creation / editing
	// ******************************************

	@PostMapping(value = "/resources/ResourceTypes")
	public ResourceType addResourceType(@Valid @RequestBody ResourceTypeForm resourceTypeForm) {
		return resourceTypeService.addResourceType(resourceTypeForm.getName());
	}

	@GetMapping(value = "/resources/ResourceTypes")
	public List<ResourceType> getResourceTypes() {
		return resourceTypeService.getResourceTypes();
	}

	@DeleteMapping(value = "/resources/ResourceType/{id}")
	public void deleteResourceType(@PathVariable int id) {
		resourceTypeService.deleteResourceType(id);
	}

}