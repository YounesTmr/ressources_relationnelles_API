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

import fr.cesi.cubes.resourceRelationnelles.entities.resource.FileType;
import fr.cesi.cubes.resourceRelationnelles.request.resource.FileTypeForm;
import fr.cesi.cubes.resourceRelationnelles.services.resource.FileTypeServices;

@RestController
@CrossOrigin( "*" )
@RequestMapping( "/projectRE" )
public class FileTypeController {

    @Autowired
    private FileTypeServices fileTypeServices;

    @PostMapping( value = "/resources/FileTypes" )
    public FileType addFileType( @Valid @RequestBody FileTypeForm fileTypeForm ) {
        return fileTypeServices.addFileType( fileTypeForm.getName() );
    }

    @GetMapping( value = "/resources/FileTypes" )
    public List<FileType> getFileTypes() {
        return fileTypeServices.getFileTypes();
    }

    @DeleteMapping( value = "/resources/FileType/{id}" )
    public void deleteFileType( @PathVariable int id ) {
        fileTypeServices.deleteFileType( id );
    }

}