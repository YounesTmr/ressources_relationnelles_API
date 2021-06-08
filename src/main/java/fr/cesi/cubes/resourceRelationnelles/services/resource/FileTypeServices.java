package fr.cesi.cubes.resourceRelationnelles.services.resource;

import java.util.List;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.FileType;

public interface FileTypeServices {

    public FileType addFileType( String fileType );

    public List<FileType> getFileTypes();

    public void deleteFileType( int id );

}
