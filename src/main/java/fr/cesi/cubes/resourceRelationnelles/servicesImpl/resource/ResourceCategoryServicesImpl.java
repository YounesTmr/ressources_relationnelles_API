package fr.cesi.cubes.resourceRelationnelles.servicesImpl.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.ResourceCategory;
import fr.cesi.cubes.resourceRelationnelles.repository.resource.ResourceCategoryRepository;
import fr.cesi.cubes.resourceRelationnelles.services.resource.ResourceCategoryServices;
import fr.cesi.cubes.resourceRelationnelles.services.resource.ResourceServices;

@Service
public class ResourceCategoryServicesImpl implements ResourceCategoryServices {

    @Autowired
    ResourceCategoryRepository resourceCategoryRepository;

    @Autowired
    ResourceServices           resourceServices;

    @Override
    public ResourceCategory addResourceCategory( String resourceCategoryName ) {
        ResourceCategory resourceCategory = new ResourceCategory();
        resourceCategory.setName( resourceCategoryName );
        resourceCategoryRepository.save( resourceCategory );
        return resourceCategory;
    }

    @Override
    public List<ResourceCategory> getResourceCategories() {
        return resourceCategoryRepository.findAll();
    }

    @Override
    public void deleteResourceCategory( int id ) {
        List<Resource> resources = resourceServices.getResourcesByResourceCategory( id );
        if ( resources.isEmpty() ) {
            resourceCategoryRepository.deleteById( id );
        } else {
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST,
                    String.format( "The type is still referenced by at least 1 resource" ) );
        }
    }

}
