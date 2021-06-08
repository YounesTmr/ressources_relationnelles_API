package fr.cesi.cubes.resourceRelationnelles.servicesImpl.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.RelationshipType;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;
import fr.cesi.cubes.resourceRelationnelles.repository.resource.RelationshipTypeRepository;
import fr.cesi.cubes.resourceRelationnelles.services.resource.RelationshipTypeServices;
import fr.cesi.cubes.resourceRelationnelles.services.resource.ResourceServices;

@Service
public class RelationshipTypeServicesImpl implements RelationshipTypeServices {

    @Autowired
    RelationshipTypeRepository relationshipTypeRepository;

    @Autowired
    ResourceServices           resourceServices;

    @Override
    public RelationshipType addRelationshipType( String relationshipTypeName ) {
        RelationshipType relationshipType = new RelationshipType();
        relationshipType.setName( relationshipTypeName );
        relationshipTypeRepository.save( relationshipType );
        return relationshipType;
    }

    @Override
    public List<RelationshipType> getRelationshipTypes() {
        return relationshipTypeRepository.findAll();
    }

    @Override
    public void deleteRelationshipType( int id ) {
        List<Resource> resources = resourceServices.getResourcesByRelationshipType( id );
        if ( resources.isEmpty() ) {
            relationshipTypeRepository.deleteById( id );
        } else {
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST,
                    String.format( "The type is still referenced by at least 1 resource" ) );
        }
    }

}
