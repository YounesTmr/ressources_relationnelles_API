package fr.cesi.cubes.resourceRelationnelles.services.resource;

import java.util.List;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.RelationshipType;

public interface RelationshipTypeServices {

    public RelationshipType addRelationshipType( String relationshipType );

    public List<RelationshipType> getRelationshipTypes();

    public void deleteRelationshipType( int id );

}
