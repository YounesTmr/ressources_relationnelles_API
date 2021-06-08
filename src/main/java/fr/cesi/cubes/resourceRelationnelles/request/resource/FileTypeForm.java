package fr.cesi.cubes.resourceRelationnelles.request.resource;

public class FileTypeForm {

    private String name;

    public FileTypeForm() {

    }

    public FileTypeForm( String name ) {
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
