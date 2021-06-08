package fr.cesi.cubes.resourceRelationnelles.services.resource;

import java.util.List;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.ResourceType;

public interface ResourceTypeServices {

    public ResourceType addResourceType( String resourceType );

    public List<ResourceType> getResourceTypes();

    public void deleteResourceType( int id );

}
