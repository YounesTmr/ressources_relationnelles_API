package fr.cesi.cubes.resourceRelationnelles.entities.friendship;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.RelationshipType;

@Entity
@Table( name = "is_friend_with" )
public class Friendship {

    @Column( name = "Id_friendship" )
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long             id;

    @ManyToOne
    @JoinColumn( name = "Id_Member_1" )
    @JsonBackReference
    private Member           member1;

    @ManyToOne
    @JoinColumn( name = "Id_Member_2" )
    @JsonIgnoreProperties( { "username", "email", "password", "cpassword", "birthDate", "county", "country",
            "dateInscription",
            "activatedAccount", "status", "friendships", "interactions" } )
    private Member           member2;

    @Column( name = "are_friends" )
    @NotNull
    private boolean          areFriends;

    // @Enumerated( EnumType.STRING )
    @ManyToOne( cascade = CascadeType.REFRESH )
    @JoinColumn( name = "friendship_type" )
    @JsonIgnoreProperties( "id" )
    private RelationshipType group;

    public Friendship() {
        this.areFriends = true;
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

    public boolean isAreFriends() {
        return areFriends;
    }

    public void setAreFriends( boolean areFriends ) {
        this.areFriends = areFriends;
    }

    public RelationshipType getGroup() {
        return group;
    }

    public void setGroup( RelationshipType group ) {
        this.group = group;
    }

}
