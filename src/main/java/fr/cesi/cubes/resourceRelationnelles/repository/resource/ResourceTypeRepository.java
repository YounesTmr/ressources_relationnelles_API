package fr.cesi.cubes.resourceRelationnelles.repository.resource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.ResourceType;

@Repository
public interface ResourceTypeRepository extends JpaRepository<ResourceType, Long> {

    public List<ResourceType> findAll();

    public ResourceType findByName( String name );

    public ResourceType findById( int id );

    @Transactional
    public void deleteById( int id );

}
