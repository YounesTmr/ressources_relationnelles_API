package fr.cesi.cubes.resourceRelationnelles.request.member;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import fr.cesi.cubes.resourceRelationnelles.entities.member.Country;
import fr.cesi.cubes.resourceRelationnelles.entities.member.County;

public class UpdateMemberForm {

    private String    username;

    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris" )
    private Timestamp birthDate;

    private County    county;

    private Country   country;

    public UpdateMemberForm() {

    }

    public UpdateMemberForm( String username, Timestamp birthDate, County county, Country country ) {
        super();
        this.username = username;
        this.birthDate = birthDate;
        this.county = county;
        this.country = country;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate( Timestamp birthDate ) {
        this.birthDate = birthDate;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty( County county ) {
        this.county = county;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry( Country country ) {
        this.country = country;
    }

}
