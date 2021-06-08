package fr.cesi.cubes.resourceRelationnelles.servicesImpl.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.ResourceType;
import fr.cesi.cubes.resourceRelationnelles.repository.resource.ResourceTypeRepository;
import fr.cesi.cubes.resourceRelationnelles.services.resource.ResourceServices;
import fr.cesi.cubes.resourceRelationnelles.services.resource.ResourceTypeServices;

@Service
public class ResourceTypeServicesImpl implements ResourceTypeServices {

    @Autowired
    ResourceTypeRepository resourceTypeRepository;

    @Autowired
    ResourceServices       resourceServices;

    @Override
    public ResourceType addResourceType( String resourceTypeName ) {
        ResourceType resourceType = new ResourceType();
        resourceType.setName( resourceTypeName );
        resourceTypeRepository.save( resourceType );
        return resourceType;
    }

    @Override
    public List<ResourceType> getResourceTypes() {
        return resourceTypeRepository.findAll();
    }

    @Override
    public void deleteResourceType( int id ) {
        List<Resource> resources = resourceServices.getResourcesByResourceType( id );
        if ( resources.isEmpty() ) {
            resourceTypeRepository.deleteById( id );
        } else {
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST,
                    String.format( "The type is still referenced by at least 1 resource" ) );
        }
    }

}
