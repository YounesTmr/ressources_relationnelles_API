package fr.cesi.cubes.resourceRelationnelles.entities.resource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//public enum ResourceType {
//    ACTIVITE, ARTICLE, DEFI, COURS, EXERCICE, FICHE_DE_LECTURE, JEU_EN_LIGNE, VIDEO
//
//}

@Entity
@Table( name = "resource_type" )
public class ResourceType {

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
