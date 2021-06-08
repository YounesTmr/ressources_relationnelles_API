package fr.cesi.cubes.resourceRelationnelles.request.resource;

public class ResourceCategoryForm {

    private String name;

    public ResourceCategoryForm() {
    }

    public ResourceCategoryForm( String name ) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

}
