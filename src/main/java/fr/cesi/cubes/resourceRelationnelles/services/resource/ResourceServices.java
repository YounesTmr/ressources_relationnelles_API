package fr.cesi.cubes.resourceRelationnelles.services.resource;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import fr.cesi.cubes.resourceRelationnelles.entities.comment.Comment;
import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;
import fr.cesi.cubes.resourceRelationnelles.exceptions.resource.ResourceNotFoundException;
import fr.cesi.cubes.resourceRelationnelles.request.resource.CreateResourceForm;
import fr.cesi.cubes.resourceRelationnelles.request.resource.UpdateResourceForm;

public interface ResourceServices {

    public Resource createResource( CreateResourceForm createResourceForm, MultipartFile file, MultipartFile image );

    public Resource createDraft( CreateResourceForm createResourceForm, MultipartFile file, MultipartFile image );

    public Resource updateResource( long id, UpdateResourceForm updateResourceForm, MultipartFile file, MultipartFile image );

    public Resource updateDraft( long id, UpdateResourceForm updateResourceForm, MultipartFile file, MultipartFile image );

    public Resource getResourceById( long id ) throws ResourceNotFoundException;

    public List<Resource> getPostResourcesByCreator();
    
    public List<Resource> getDraftResourcesByCreator();
    
    public Set<Resource> getResourcesByExpression( String expression );

    public Set<Resource> getResourcesToValidate();

    void deleteResourceById( long id );

    public Map<String, List<Resource>> getResourcesByFilter( String filter, String sort, int limit, int page );

    public List<Resource> getResources();

    public List<Resource> getValidResources();

    public Resource validateResource( long id );

    public Resource switchVisibility( long id );

    public void addViews( long id );

    Resource addComment( Long id, Comment comment );

    public Resource removeComment( Long id, Comment comment );

    public List<Resource> getResourcesByFileType( int idFileType );

    public List<Resource> getResourcesByLanguage( int idLanguage );

    public List<Resource> getResourcesByRelationshipType( int idRelationshipType );

    public List<Resource> getResourcesByResourceCategory( int idResourceCategory );

    public List<Resource> getResourcesByResourceType( int idResourceType );

}
