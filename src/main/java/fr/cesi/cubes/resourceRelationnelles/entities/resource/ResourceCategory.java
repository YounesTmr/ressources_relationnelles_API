package fr.cesi.cubes.resourceRelationnelles.entities.resource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//public enum ResourceCategory {
//    COMMUNICATION, CULTURES, DEVELOPPEMENT_PERSONNEL, INTELLIGENCE_EMOTIONNELLE, LOISIRS, MONDE_PROFESSIONNEL, PARENTALITE, QUALITE_DE_VIE, RECHERCHE_DE_SENS, SANTE_PHYSIQUE, SANTE_PSYCHIQUE, SPIRITUALITE, VIE_AFFECTIVE
//
//}

@Entity
@Table( name = "resource_category" )
public class ResourceCategory {

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
