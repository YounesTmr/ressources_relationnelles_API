package fr.cesi.cubes.resourceRelationnelles.entities.member;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

import fr.cesi.cubes.resourceRelationnelles.entities.friendship.Friendship;
import fr.cesi.cubes.resourceRelationnelles.entities.interaction.Interaction;

@Entity
@Table( name = "members" )
public class Member implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Column( name = "id_member" )
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long              id;

    @Column( name = "username" )
    @NotNull( message = "Un pseudo est requis" )
    @NotBlank( message = "Un pseudo ne peut être vide" )
    private String            username;

    @Column( name = "email" )
    @NotBlank( message = "l'adresse mail ne peut être vide" )
    @NotNull( message = "l'adresse mail est requise" )
    @Email( message = "L'adresse n'est pas valide" )
    private String            email;

    @Column( name = "password" )
    @NotNull( message = "le mot de passe est requis" )
    private String            password;

    @Column( name = "birth_date" )
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris" )
    private Timestamp         birthDate;

    @Column( name = "county" )
    @Convert( converter = CountyConverter.class )
    private County            county;

    @Column( name = "country" )
    @Enumerated( EnumType.STRING )
    private Country           country;

    @Column( name = "creation_date" )
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Paris" )
    private Timestamp         dateInscription;

    @Column( name = "activated_account" )
    private boolean           activatedAccount;

    @Column( name = "status" )
    @Enumerated( EnumType.STRING )
    private Status            status;

    @OneToMany( mappedBy = "member1" )
    private List<Friendship>  friendships      = new ArrayList<Friendship>();

    @OneToMany( mappedBy = "member" )
    private List<Interaction> interactions     = new ArrayList<Interaction>();

    @Transient
	private Collection<? extends GrantedAuthority> authorities;
    
    public Member() {
        this.dateInscription = new Timestamp( System.currentTimeMillis() );
        this.activatedAccount = true;
        this.status = Status.MEMBER;
    }

    public Member(String Usename) {
		// TODO Auto-generated constructor stub
    	this.username = username;
	}

	public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate( Timestamp birthDate ) {
        this.birthDate = birthDate;
    }

    public County getCounty() {
        return this.county;
    }

    public void setCounty( County county ) {
        this.county = county;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry( Country country ) {
        this.country = country;
    }

    public Timestamp getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription( Timestamp dateInscription ) {
        this.dateInscription = dateInscription;
    }

    public boolean isActivatedAccount() {
        return activatedAccount;
    }

    public void setActivatedAccount( boolean activatedAccount ) {
        this.activatedAccount = activatedAccount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus( Status status ) {
        this.status = status;
    }

    public List<Friendship> getFriendships() {
        return friendships;
    }

    public void setFriendships( List<Friendship> friendships ) {
        this.friendships = friendships;
    }

    public List<Interaction> getInteractions() {
        return interactions;
    }

    public void setInteractions( List<Interaction> interactions ) {
        this.interactions = interactions;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
