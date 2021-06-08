package fr.cesi.cubes.resourceRelationnelles.request.resource;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateResourceForm {

    @NotNull( message = "La resource doit avoir un nom" )
    @NotBlank( message = "le nom ne peut pas être vide" )
    private String name;

    @Column( name = "description" )
    private String description;

    @NotNull( message = "La resource doit comporter un contenu" )
    @NotBlank( message = "Le contenu de la ressource ne peut pas être vide" )
    private String contentText;

    @Column( name = "image_url" )
    private String imageUrl;

    private String language;

    private String resourceType;

    private String resourceCategory;

    private String relationshipType;

    private String fileType;

    public UpdateResourceForm() {
    }

    public UpdateResourceForm(
            @NotNull( message = "La resource doit avoir un nom" ) @NotBlank( message = "le nom ne peut pas être vide" ) String name,
            String description,
            @NotNull( message = "La resource doit comporter un contenu" ) @NotBlank( message = "Le contenu de la ressource ne peut pas être vide" ) String contentText,
            String imageUrl, String language, String resourceType, String resourceCategory, String relationshipType,
            String fileType ) {
        super();
        this.name = name;
        this.description = description;
        this.contentText = contentText;
        this.imageUrl = imageUrl;
        this.language = language;
        this.resourceType = resourceType;
        this.resourceCategory = resourceCategory;
        this.relationshipType = relationshipType;
        this.fileType = fileType;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText( String contentText ) {
        this.contentText = contentText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl( String imageUrl ) {
        this.imageUrl = imageUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage( String language ) {
        this.language = language;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType( String resourceType ) {
        this.resourceType = resourceType;
    }

    public String getResourceCategory() {
        return resourceCategory;
    }

    public void setResourceCategory( String resourceCategory ) {
        this.resourceCategory = resourceCategory;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType( String relationshipType ) {
        this.relationshipType = relationshipType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType( String fileType ) {
        this.fileType = fileType;
    }

}
