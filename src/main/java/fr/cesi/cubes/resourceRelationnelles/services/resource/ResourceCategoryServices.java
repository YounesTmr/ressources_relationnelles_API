package fr.cesi.cubes.resourceRelationnelles.services.resource;

import java.util.List;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.ResourceCategory;

public interface ResourceCategoryServices {

    public ResourceCategory addResourceCategory( String resourceCategory );

    public List<ResourceCategory> getResourceCategories();

    public void deleteResourceCategory( int id );

}
