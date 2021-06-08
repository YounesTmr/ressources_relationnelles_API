package fr.cesi.cubes.resourceRelationnelles.entities.interaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;

@Entity
@Table( name = "has_interacted_with" )
public class Interaction {

    @Column( name = "Id_interaction" )
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long     id;

    @ManyToOne
    @JoinColumn( name = "Id_Member" )
    @JsonIgnoreProperties( { "username", "email", "password", "cpassword", "birthDate", "county", "country",
            "dateInscription", "activatedAccount", "status", "friendships", "interactions" } )
    private Member   member;

    @ManyToOne
    @JoinColumn( name = "Id_Resources" )
    @JsonIgnoreProperties( { "name", "description", "creationDate", "modificationDate", "contentText", "nbViews",
            "validated", "draft", "visible", "language", "resourceType", "resourceCategory", "relationshipType",
            "fileType", "imageUrl", "creator", "comments" } )
    private Resource resource;

    @Column( name = "is_favorite" )
    private Boolean  favorite;

    @Column( name = "is_complete" )
    private Boolean  complete;

    public Interaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember( Member member ) {
        this.member = member;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource( Resource resource ) {
        this.resource = resource;
    }

    public Boolean isFavorite() {
        return favorite;
    }

    public void setFavorite( Boolean favorite ) {
        this.favorite = favorite;
    }

    public Boolean isComplete() {
        return complete;
    }

    public void setComplete( Boolean complete ) {
        this.complete = complete;
    }

}
