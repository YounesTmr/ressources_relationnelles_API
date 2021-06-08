package fr.cesi.cubes.resourceRelationnelles.repository.resource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.FileType;

@Repository
public interface FileTypeRepository extends JpaRepository<FileType, Long> {

    public List<FileType> findAll();

    public FileType findByName( String name );

    public FileType findById( int id );

    @Transactional
    public void deleteById( int id );

}
