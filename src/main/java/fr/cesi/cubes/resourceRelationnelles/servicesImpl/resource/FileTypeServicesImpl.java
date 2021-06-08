package fr.cesi.cubes.resourceRelationnelles.servicesImpl.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.FileType;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;
import fr.cesi.cubes.resourceRelationnelles.repository.resource.FileTypeRepository;
import fr.cesi.cubes.resourceRelationnelles.services.resource.FileTypeServices;
import fr.cesi.cubes.resourceRelationnelles.services.resource.ResourceServices;

@Service
public class FileTypeServicesImpl implements FileTypeServices {

    @Autowired
    FileTypeRepository fileTypeRepository;

    @Autowired
    ResourceServices   resourceServices;

    @Override
    public FileType addFileType( String FileTypeName ) {
        FileType fileType = new FileType();
        fileType.setName( FileTypeName );
        fileTypeRepository.save( fileType );
        return fileType;
    }

    @Override
    public List<FileType> getFileTypes() {
        return fileTypeRepository.findAll();
    }

    @Override
    public void deleteFileType( int id ) {
        List<Resource> resources = resourceServices.getResourcesByFileType( id );
        if ( resources.isEmpty() ) {
            fileTypeRepository.deleteById( id );
        } else {
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST,
                    String.format( "The type is still referenced by at least 1 resource" ) );
        }
    }

}
