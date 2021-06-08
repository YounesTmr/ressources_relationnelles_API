package fr.cesi.cubes.resourceRelationnelles.repository.resource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    public List<Language> findAll();

    public Language findByName( String name );

    public Language findById( int id );

    @Transactional
    public void deleteById( int id );

}
