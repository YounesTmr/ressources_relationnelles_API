package fr.cesi.cubes.resourceRelationnelles.request.resource;

public class LanguageForm {

    private String name;

    public LanguageForm() {

    }

    public LanguageForm( String name ) {
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
