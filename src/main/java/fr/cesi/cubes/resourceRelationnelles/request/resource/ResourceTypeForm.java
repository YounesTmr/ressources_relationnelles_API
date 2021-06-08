package fr.cesi.cubes.resourceRelationnelles.request.resource;

public class ResourceTypeForm {

    private String name;

    public ResourceTypeForm() {
    }

    public ResourceTypeForm( String name ) {
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
