package fr.cesi.cubes.resourceRelationnelles.request.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateMemberForm {

    @NotNull( message = "Un pseudo est requis" )
    @NotBlank( message = "Un pseudo ne peut être vide" )
    private String username;

    @NotBlank( message = "l'adresse mail ne peut être vide" )
    @NotNull( message = "l'adresse mail est requise" )
    @Email( message = "L'adresse n'est pas valide" )
    private String email;

    @NotNull( message = "le mot de passe est requis" )
    private String password;

    @NotNull( message = "le mot de passe est requis" )
    private String cpassword;

    public CreateMemberForm() {

    }

    public CreateMemberForm( String username, String email, String password, String cpassword ) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
        this.cpassword = cpassword;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword( String cpassword ) {
        this.cpassword = cpassword;
    }

}
