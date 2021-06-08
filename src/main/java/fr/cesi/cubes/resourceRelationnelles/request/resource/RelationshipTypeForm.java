package fr.cesi.cubes.resourceRelationnelles.request.resource;

public class RelationshipTypeForm {

    private String name;

    public RelationshipTypeForm() {
    }

    public RelationshipTypeForm( String name ) {
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
