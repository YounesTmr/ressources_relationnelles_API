package fr.cesi.cubes.resourceRelationnelles.entities.resource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//public enum Language {
//    FR, EN, ES, IT, DE
//}

@Entity
@Table( name = "language" )
public class Language {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int    id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

}
