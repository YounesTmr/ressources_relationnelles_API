package fr.cesi.cubes.resourceRelationnelles.entities.share;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;

@Entity
@Table( name = "share" )
public class Share {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long      id;

    @ManyToOne
    @JoinColumn( name = "sender_id" )
    @JsonIgnoreProperties( { "username", "email", "password", "cpassword", "birthDate", "county", "country",
            "dateInscription", "activatedAccount", "status", "friendships", "interactions" } )
    private Member    sender;

    @ManyToOne
    @JoinColumn( name = "recipient_id" )
    @JsonIgnore
    private Member    recipient;

    @Column( name = "share_date" )
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Paris" )
    private Timestamp shareDate;

    @ManyToOne
    @JoinColumn( name = "resource_id" )
    @JsonIgnoreProperties( { "name", "description", "creationDate", "modificationDate", "contentText", "nbViews",
            "validated", "draft", "visible", "language", "resourceType", "resourceCategory", "relationshipType",
            "fileType", "imageUrl", "creator", "comments" } )
    private Resource  resource;

    public Share() {
        this.shareDate = new Timestamp( System.currentTimeMillis() );
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public Member getSender() {
        return sender;
    }

    public void setSender( Member sender ) {
        this.sender = sender;
    }

    public Member getRecipient() {
        return recipient;
    }

    public void setRecipient( Member recipient ) {
        this.recipient = recipient;
    }

    public Timestamp getShareDate() {
        return shareDate;
    }

    public void setShareDate( Timestamp shareDate ) {
        this.shareDate = shareDate;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource( Resource resource ) {
        this.resource = resource;
    }

}
