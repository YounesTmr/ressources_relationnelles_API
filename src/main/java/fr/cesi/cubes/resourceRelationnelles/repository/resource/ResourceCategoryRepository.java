package fr.cesi.cubes.resourceRelationnelles.repository.resource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.ResourceCategory;

@Repository
public interface ResourceCategoryRepository extends JpaRepository<ResourceCategory, Long> {

    public List<ResourceCategory> findAll();

    public ResourceCategory findByName( String name );

    public ResourceCategory findById( int id );

    @Transactional
    public void deleteById( int id );

}
