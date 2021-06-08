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

import fr.cesi.cubes.resourceRelationnelles.entities.resource.ResourceCategory;
import fr.cesi.cubes.resourceRelationnelles.request.resource.ResourceCategoryForm;
import fr.cesi.cubes.resourceRelationnelles.services.resource.ResourceCategoryServices;

@RestController
@CrossOrigin( "*" )
@RequestMapping( "/projectRE" )
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryServices resourceCategoryService;

    // ******************************************
    // Ressource creation / editing
    // ******************************************

    @PostMapping( value = "/resources/ResourceCategories" )
    public ResourceCategory addResourceCategory( @Valid @RequestBody ResourceCategoryForm resourceCategoryForm ) {
        return resourceCategoryService.addResourceCategory( resourceCategoryForm.getName() );
    }

    @GetMapping( value = "/resources/ResourceCategories" )
    public List<ResourceCategory> getResourceCategorys() {
        return resourceCategoryService.getResourceCategories();
    }

    @DeleteMapping( value = "/resources/ResourceCategory/{id}" )
    public void deleteResourceCategory( @PathVariable int id ) {
        resourceCategoryService.deleteResourceCategory( id );
    }

}