package fr.cesi.cubes.resourceRelationnelles.entities.historic;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;

@Entity
@Table( name = "historic" )
public class Historic {

    @Column( name = "Id_historic" )
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long      id;

    @ManyToOne
    @JoinColumn( name = "Id_Member_1" )
    @JsonIgnoreProperties( { "username", "email", "password", "cpassword", "birthDate", "county", "country",
            "dateInscription", "activatedAccount", "status", "friendships", "interactions" } )
    private Member    member1;

    @ManyToOne
    @JoinColumn( name = "Id_Member_2" )
    @JsonIgnoreProperties( { "username", "email", "password", "cpassword", "birthDate", "county", "country",
            "dateInscription", "activatedAccount", "status", "friendships", "interactions" } )
    private Member    member2;

    @ManyToOne
    @JoinColumn( name = "Id_Resources" )
    @JsonIgnoreProperties( { "name", "description", "creationDate", "modificationDate", "contentText", "nbViews",
            "validated", "draft", "visible", "language", "resourceType", "resourceCategory", "relationshipType",
            "fileType", "imageUrl", "creator", "comments" } )
    private Resource  resource;

    @Column( name = "action" )
    @Enumerated( EnumType.STRING )
    private Action    action;

    @Column( name = "action_date" )
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Paris" )
    private Timestamp actionDate;

    public Historic() {
        actionDate = new Timestamp( System.currentTimeMillis() );
    }

    public Historic( Member member1, Member member2, Resource resource, Action action ) {
        this.member1 = member1;
        this.member2 = member2;
        this.resource = resource;
        this.action = action;
        this.actionDate = new Timestamp( System.currentTimeMillis() );
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Member getMember1() {
        return member1;
    }

    public void setMember1( Member member1 ) {
        this.member1 = member1;
    }

    public Member getMember2() {
        return member2;
    }

    public void setMember2( Member member2 ) {
        this.member2 = member2;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource( Resource resource ) {
        this.resource = resource;
    }

    public Action getAction() {
        return action;
    }

    public void setAction( Action action ) {
        this.action = action;
    }

    public Timestamp getActionDate() {
        return actionDate;
    }

    public void setActionDate( Timestamp actionDate ) {
        this.actionDate = actionDate;
    }

}
