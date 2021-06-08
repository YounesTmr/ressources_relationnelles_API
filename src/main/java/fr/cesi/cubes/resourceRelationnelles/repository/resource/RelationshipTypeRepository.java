package fr.cesi.cubes.resourceRelationnelles.repository.resource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.RelationshipType;

@Repository
public interface RelationshipTypeRepository extends JpaRepository<RelationshipType, Long> {

    public List<RelationshipType> findAll();

    public RelationshipType findByName( String name );

    public RelationshipType findById( int id );

    @Transactional
    public void deleteById( int id );

}
