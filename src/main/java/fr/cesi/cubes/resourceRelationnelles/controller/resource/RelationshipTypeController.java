package fr.cesi.cubes.resourceRelationnelles.controller.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.RelationshipType;
import fr.cesi.cubes.resourceRelationnelles.request.resource.RelationshipTypeForm;
import fr.cesi.cubes.resourceRelationnelles.services.resource.RelationshipTypeServices;

@RestController
@CrossOrigin( "*" )
@RequestMapping( "/projectRE" )
public class RelationshipTypeController {

    @Autowired
    private RelationshipTypeServices relationshipTypeServices;

    // ******************************************
    // Ressource creation / editing
    // ******************************************

    @PostMapping( value = "/resources/RelationshipTypes" )
    public RelationshipType addRelationshipType( @Valid @RequestBody RelationshipTypeForm relationshipTypeForm ) {
        return relationshipTypeServices.addRelationshipType( relationshipTypeForm.getName() );
    }

    @GetMapping( value = "/resources/RelationshipTypes" )
    public List<RelationshipType> getRelationshipTypes() {
        return relationshipTypeServices.getRelationshipTypes();
    }

    @DeleteMapping( value = "/resources/RelationshipType/{id}" )
    public void deleteRelationshipType( @PathVariable int id ) {
        relationshipTypeServices.deleteRelationshipType( id );
    }

}