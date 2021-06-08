package fr.cesi.cubes.resourceRelationnelles.entities.resource;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.cesi.cubes.resourceRelationnelles.entities.comment.Comment;
import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;

@Entity
@Table( name = "resources" )
public class Resource {

    @Column( name = "id_resources" )
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long             id;

    @Column( name = "resource_name" )
    @NotNull( message = "La resource doit avoir un nom" )
    @NotBlank( message = "le nom ne peut pas être vide" )
    private String           name;

    @Column( name = "description" )
    @Lob
    private String           description;

    @Column( name = "creation_date" )
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Paris" )
    private Timestamp        creationDate;

    @ManyToOne( cascade = CascadeType.REFRESH )
    @JoinColumn( name = "id_creator" )
    @JsonIgnoreProperties( { "username", "email", "password", "cpassword", "birthDate", "county", "country",
            "dateInscription", "activatedAccount", "status", "friendships", "interactions", "authorities",
            "enabled", "accountNonExpired", "credentialsNonExpired", "accountNonLocked" } )
    private Member           creator;

    @Column( name = "modification_date" )
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Paris" )
    private Timestamp        modificationDate;

    @Column( name = "content" )
    @NotNull( message = "La resource doit comporter un contenu" )
    @NotBlank( message = "Le contenu de la ressource ne peut pas être vide" )
    private String           contentText;

    @Column( name = "image_url" )
    private String           imageUrl;

    @Column( name = "nb_views" )
    private long             nbViews;

    @Column( name = "is_validated" )
    private boolean          isValidated;

    @Column( name = "is_visible" )
    private boolean          isVisible;

    @Column( name = "is_draft" )
    private boolean          isDraft;

    @ManyToOne( cascade = CascadeType.REFRESH )
    @JoinColumn( name = "language" )
    @JsonIgnoreProperties( "id" )
    // @Enumerated( EnumType.STRING )
    private Language         language;

    @ManyToOne( cascade = CascadeType.REFRESH )
    @JoinColumn( name = "resource_type" )
    @JsonIgnoreProperties( "id" )
    // @Enumerated( EnumType.STRING )
    private ResourceType     resourceType;

    @ManyToOne( cascade = CascadeType.REFRESH )
    @JoinColumn( name = "resource_category" )
    @JsonIgnoreProperties( "id" )
    // @Enumerated( EnumType.STRING )
    private ResourceCategory resourceCategory;

    @ManyToOne( cascade = CascadeType.REFRESH )
    @JoinColumn( name = "relationship_type" )
    @JsonIgnoreProperties( "id" )
    // @Enumerated( EnumType.STRING )
    private RelationshipType relationshipType;

    @ManyToOne( cascade = CascadeType.REFRESH )
    @JoinColumn( name = "file_type" )
    @JsonIgnoreProperties( "id" )
    // @Enumerated( EnumType.STRING )
    private FileType         fileType;

    private String           fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName( String fileName ) {
        this.fileName = fileName;
    }

    @OneToMany
    private Set<Comment> comments = new HashSet<Comment>();

    public Resource() {
        this.creationDate = new Timestamp( System.currentTimeMillis() );
        this.modificationDate = this.creationDate;
        this.isVisible = false; // by default, the resource is not vsiible before moderation validation
        this.isValidated = false; // by default, the resource is not validated
        this.isDraft = true; // by default, the resource is a draft
        this.nbViews = 0; // for a new instance, the resource view is 0
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments( Set<Comment> comments ) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
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

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate( Timestamp creationDate ) {
        this.creationDate = creationDate;
    }

    public Member getCreator() {
        return creator;
    }

    public void setCreator( Member creator ) {
        this.creator = creator;
    }

    public Timestamp getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate( Timestamp modificationDate ) {
        this.modificationDate = modificationDate;
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

    public long getNbViews() {
        return nbViews;
    }

    public void setNbViews( long nbViews ) {
        this.nbViews = nbViews;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated( boolean isValidated ) {
        this.isValidated = isValidated;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible( boolean isVisible ) {
        this.isVisible = isVisible;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public void setDraft( boolean isDraft ) {
        this.isDraft = isDraft;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage( Language language ) {
        this.language = language;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType( ResourceType resourceType ) {
        this.resourceType = resourceType;
    }

    public ResourceCategory getResourceCategory() {
        return resourceCategory;
    }

    public void setResourceCategory( ResourceCategory resourceCategory ) {
        this.resourceCategory = resourceCategory;
    }

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType( RelationshipType relationshipType ) {
        this.relationshipType = relationshipType;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType( FileType fileType ) {
        this.fileType = fileType;
    }

}
