package fr.cesi.cubes.resourceRelationnelles.servicesImpl.resource;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import fr.cesi.cubes.resourceRelationnelles.entities.comment.Comment;
import fr.cesi.cubes.resourceRelationnelles.entities.historic.Action;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.FileType;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Language;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.RelationshipType;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.ResourceCategory;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.ResourceType;
import fr.cesi.cubes.resourceRelationnelles.exceptions.resource.ResourceNotFoundException;
import fr.cesi.cubes.resourceRelationnelles.exceptions.resource.ResourceValidationException;
import fr.cesi.cubes.resourceRelationnelles.repository.comment.CommentRepository;
import fr.cesi.cubes.resourceRelationnelles.repository.resource.FileTypeRepository;
import fr.cesi.cubes.resourceRelationnelles.repository.resource.LanguageRepository;
import fr.cesi.cubes.resourceRelationnelles.repository.resource.RelationshipTypeRepository;
import fr.cesi.cubes.resourceRelationnelles.repository.resource.ResourceCategoryRepository;
import fr.cesi.cubes.resourceRelationnelles.repository.resource.ResourceRepository;
import fr.cesi.cubes.resourceRelationnelles.repository.resource.ResourceTypeRepository;
import fr.cesi.cubes.resourceRelationnelles.request.resource.CreateResourceForm;
import fr.cesi.cubes.resourceRelationnelles.request.resource.UpdateResourceForm;
import fr.cesi.cubes.resourceRelationnelles.services.authentification.AuthenticationService;
import fr.cesi.cubes.resourceRelationnelles.services.historic.HistoricServices;
import fr.cesi.cubes.resourceRelationnelles.services.interaction.InteractionServices;
import fr.cesi.cubes.resourceRelationnelles.services.member.MemberServices;
import fr.cesi.cubes.resourceRelationnelles.services.resource.ResourceServices;
import fr.cesi.cubes.resourceRelationnelles.services.share.ShareServices;

@Service(value = "resource")
public class ResourceServicesImpl implements ResourceServices {

	@Autowired
	private ResourceRepository resourceRepository;
	@Autowired
	private FileTypeRepository fileTypeRepository;
	@Autowired
	private LanguageRepository languageRepository;
	@Autowired
	private RelationshipTypeRepository relationshipTypeRepository;
	@Autowired
	private ResourceCategoryRepository resourceCategoryRepository;
	@Autowired
	private ResourceTypeRepository resourceTypeRepository;

	@Autowired
	private MemberServices memberServices;
	@Autowired
	private InteractionServices interactionServices;
	@Autowired
	private ShareServices shareServices;
	@Autowired
	private HistoricServices historicServices;
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private CommentRepository commentRepository;

	// contructor
	public ResourceServicesImpl() {
	}

	// ***********************
	// DATA PROCESSING
	// ***********************

	// save a resource ready to post (no draft)
	@Override
	public Resource createResource(CreateResourceForm createResourceForm, MultipartFile file, MultipartFile image) {

		Resource resource = new Resource();

		try {

			resource.setName(createResourceForm.getName());
			resource.setDescription(createResourceForm.getDescription());
			resource.setCreator(authenticationService.getCurrentUser());
			resource.setContentText(createResourceForm.getContentText());
			resource.setLanguage(languageRepository.findByName(createResourceForm.getLanguage()));
			resource.setResourceType(resourceTypeRepository.findByName(createResourceForm.getResourceType()));
			resource.setResourceCategory(
					resourceCategoryRepository.findByName(createResourceForm.getResourceCategory()));
			resource.setRelationshipType(
					relationshipTypeRepository.findByName(createResourceForm.getRelationshipType()));
			resource.setFileType(fileTypeRepository.findByName(createResourceForm.getFileType()));

			resource.setDraft(false); // by default, creation in draft mode. false -> ready to be validated by
			// moderator
			if (file != null) {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				resource.setFileName("file de " + createResourceForm.getName() + " - " + file.getOriginalFilename() );
			} else {
				resource.setFileName(null);
			}
			if (image != null) {
				String imageURL = StringUtils.cleanPath(image.getOriginalFilename());
				resource.setImageUrl("image de " + createResourceForm.getName() + " - " + image.getOriginalFilename());
			} else {
				resource.setImageUrl(null);
			}
			resourceRepository.save(resource);
			historicServices.saveAction(resource.getCreator(), null, resource, Action.CREATE_RESOURCE);

		} catch (Exception e) {
			e.getMessage();
			return null;
		}
		return resource;

	}

	// save a resource in draft (no post)
	@Override
	public Resource createDraft(CreateResourceForm createResourceForm, MultipartFile file, MultipartFile image) {

		Resource resource = new Resource();

		try {

			resource.setName(createResourceForm.getName());
			resource.setDescription(createResourceForm.getDescription());
			resource.setCreator(authenticationService.getCurrentUser());
			resource.setContentText(createResourceForm.getContentText());
			resource.setLanguage(languageRepository.findByName(createResourceForm.getLanguage()));
			resource.setResourceType(resourceTypeRepository.findByName(createResourceForm.getResourceType()));
			resource.setResourceCategory(
					resourceCategoryRepository.findByName(createResourceForm.getResourceCategory()));
			resource.setRelationshipType(
					relationshipTypeRepository.findByName(createResourceForm.getRelationshipType()));
			resource.setFileType(fileTypeRepository.findByName(createResourceForm.getFileType()));
			if (file != null) {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				resource.setFileName("file de " + createResourceForm.getName() + " - " + file.getOriginalFilename() );
			} else {
				resource.setFileName(null);
			}
			if (image != null) {
				String imageURL = StringUtils.cleanPath(image.getOriginalFilename());
				resource.setImageUrl("image de " + createResourceForm.getName() + " - " + image.getOriginalFilename());
			} else {
				resource.setImageUrl(null);
			}
			resourceRepository.save(resource);

		} catch (Exception e) {
			e.getMessage();
			return null;
		}

		return resource;

	}

	// save a resource ready to post from draft or resource updating
	@Override
	public Resource updateResource(long id, UpdateResourceForm updateResourceForm, MultipartFile file,
			MultipartFile image) {

		Resource resource = getResourceById(id);

		try {

			resource.setName(updateResourceForm.getName());
			resource.setDescription(updateResourceForm.getDescription());
			resource.setCreator(authenticationService.getCurrentUser());
			resource.setContentText(updateResourceForm.getContentText());
			resource.setImageUrl(updateResourceForm.getImageUrl());
			resource.setLanguage(languageRepository.findByName(updateResourceForm.getLanguage()));
			resource.setResourceType(resourceTypeRepository.findByName(updateResourceForm.getResourceType()));
			resource.setResourceCategory(
					resourceCategoryRepository.findByName(updateResourceForm.getResourceCategory()));
			resource.setRelationshipType(
					relationshipTypeRepository.findByName(updateResourceForm.getRelationshipType()));
			resource.setFileType(fileTypeRepository.findByName(updateResourceForm.getFileType()));

			// modification date is udpated
			resource.setModificationDate(new Timestamp(System.currentTimeMillis()));

			resource.setDraft(false); // non-draft -> ready to post
			resource.setVisible(false); // after a modification, new validation required by moderator => non visible for
										// public

			if (file != null) {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				resource.setFileName("file de " + updateResourceForm.getName() + " - " + file.getOriginalFilename());
			} else {
				resource.setFileName(null);
			}
			if (image != null) {
				String imageURL = StringUtils.cleanPath(image.getOriginalFilename());
				resource.setImageUrl("image de " + updateResourceForm.getName() + " - " + image.getOriginalFilename());
			} else {
				resource.setImageUrl(null);
			}

			// if the resource was previously validated, save action as edit resource, else
			// save as create resource
			if (resource.isValidated()) {
				resource.setValidated(false); // after a modification, new validation required by moderator
				resourceRepository.save(resource);
				historicServices.saveAction(resource.getCreator(), null, resource, Action.EDIT_RESOURCE);
			} else {
				resourceRepository.save(resource);
				historicServices.saveAction(resource.getCreator(), null, resource, Action.CREATE_RESOURCE);
			}

		} catch (Exception e) {
			e.getMessage();
			return null;
		}

		return resource;

	}

	// update a resource in draft from draft or resource updating
	@Override
	public Resource updateDraft(long id, UpdateResourceForm updateResourceForm, MultipartFile file,
			MultipartFile image) {

		Resource resource = getResourceById(id);

		// saved resource attributes are overwritten by new resources (except for
		// creation date and nb of views)

		try {

			resource.setName(updateResourceForm.getName());
			resource.setDescription(updateResourceForm.getDescription());
			resource.setCreator(authenticationService.getCurrentUser());
			resource.setContentText(updateResourceForm.getContentText());
			resource.setImageUrl(updateResourceForm.getImageUrl());
			resource.setLanguage(languageRepository.findByName(updateResourceForm.getLanguage()));
			resource.setResourceType(resourceTypeRepository.findByName(updateResourceForm.getResourceType()));
			resource.setResourceCategory(
					resourceCategoryRepository.findByName(updateResourceForm.getResourceCategory()));
			resource.setRelationshipType(
					relationshipTypeRepository.findByName(updateResourceForm.getRelationshipType()));
			resource.setFileType(fileTypeRepository.findByName(updateResourceForm.getFileType()));

			// modification date is udpated
			resource.setModificationDate(new Timestamp(System.currentTimeMillis()));

			resource.setDraft(true); // writing in draft
			resource.setVisible(false); // after a modification, new validation required by moderator => non visible for
										// public
			resource.setValidated(false); // after a modification, new validation required by moderator

			if (file != null) {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				resource.setFileName("file de " + updateResourceForm.getName() + " - " + file.getOriginalFilename());
			} else {
				resource.setFileName(null);
			}
			if (image != null) {
				String imageURL = StringUtils.cleanPath(image.getOriginalFilename());
				resource.setImageUrl("image de " + updateResourceForm.getName() + " - " + image.getOriginalFilename());
			} else {
				resource.setImageUrl(null);
			}

			resourceRepository.save(resource);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}

		return resource;

	}

	@Override
	public Resource getResourceById(long id) throws ResourceNotFoundException {

		Resource resource = resourceRepository.findById(id);

		if (resource == null) {
			throw new ResourceNotFoundException("La ressource n° " + id + " est introuvable");
		}

		return resource;
	}

	@Override
	public List<Resource> getResources() {
		return resourceRepository.findAll();
	}

	@Override
	public List<Resource> getValidResources() {
		return resourceRepository.findByIsValidatedTrue();
	}

	@Override
	public Set<Resource> getResourcesByExpression(String expression) {
		return resourceRepository.findByNameContaining(expression);
	}

	@Override
	public Set<Resource> getResourcesToValidate() {
		return resourceRepository.findByIsValidatedFalseAndIsDraftFalse();
	}

	@Override
	@Transactional
	public void deleteResourceById(long id) {

		// update of historic records referencing the resource
		historicServices.deleteByResource(id);
		System.out.println("historics deleted !");

		// deletion of interaction records referencing the resource
		interactionServices.deleteInteractionByResource(id);
		System.out.println("interactions deleted !");

		// deletion of share records referencing the resource
		shareServices.deleteShareByResource(id);
		System.out.println("shares deleted !");

		// BUG !!! Si la ressource existe dans un commentaire, elle est supprimée en
		// même temps que le commentaire

		// deletion of all comments referencing the resource
		List<Comment> commentList = commentRepository.findAllByResourceId(id);
		if (!commentList.isEmpty()) {
			for (Comment comment : commentList) {
				commentRepository.deleteById(comment.getId());
				System.out.println("comment deleted !");
			}
		} else {
			Resource resource = this.getResourceById(id);
			if (resource != null) {
				resourceRepository.deleteById(id);
				System.out.println("resource deleted !");
			}
		}

	}

	// get resources filtered by user choice, to post in home screen
	@Override
	public Map<String, List<Resource>> getResourcesByFilter(String filter, String sort, int limit, int page) {

		Map<String, List<Resource>> resources = new HashMap<String, List<Resource>>();

		if (sort.contentEquals("latest") || sort.contentEquals("most_viewed")) {

			// selection de la colonne utilisée pour le tri
			switch (sort) {
			case "latest":
				sort = "modification_date";
				break;
			case "most_viewed":
				sort = "nb_views";
				break;
			}

			// suivant le filtre sélectionné, on récupère, pour chaque catégorie du filtre,
			// la liste des ressources correspondantes.
			switch (filter) {

			case "FileType":
				for (FileType fileType : fileTypeRepository.findAll()) {
					resources.put(fileType.getName(),
							resourceRepository.findByFileType(fileType.getId(), sort, limit, ((page - 1) * limit)));
				}
				break;

			case "RelationshipType":
				for (RelationshipType relationshipType : relationshipTypeRepository.findAll()) {
					resources.put(relationshipType.getName(), resourceRepository
							.findByRelationshipType(relationshipType.getId(), sort, limit, ((page - 1) * limit)));
				}
				break;

			case "ResourceType":
				for (ResourceType resourceType : resourceTypeRepository.findAll()) {
					resources.put(resourceType.getName(), resourceRepository.findByResourceType(resourceType.getId(),
							sort, limit, ((page - 1) * limit)));
				}
				break;

			case "ResourceCategory":
				for (ResourceCategory resourceCategory : resourceCategoryRepository.findAll()) {
					resources.put(resourceCategory.getName(), resourceRepository
							.findByResourceCategory(resourceCategory.getId(), sort, limit, ((page - 1) * limit)));
				}
				break;

			default:

			}

		} else {
			new ResourceValidationException("Le filtre " + sort + " n'est pas reconnu");
		}

		return resources;
	}

	// validate resource by moderator
	@Override
	public Resource validateResource(long id) {

		Resource resource = this.getResourceById(id);
		resource.setValidated(true);
		resource.setVisible(true);
		resourceRepository.updateValidated(id, resource.isValidated());
		resourceRepository.updateVisibility(id, resource.isVisible());
		return resource;
	}

	// make the resource visible or not for users
	@Override
	public Resource switchVisibility(long id) {

		Resource resource = this.getResourceById(id);
		resource.setVisible(!resource.isVisible());
		resourceRepository.updateVisibility(id, resource.isVisible());
		return resource;
	}

	// increments the number of views
	@Override
	public void addViews(long id) {

		Resource resource = this.getResourceById(id);
		resource.setNbViews(resource.getNbViews() + 1);
		resourceRepository.updateViews(id, resource.getNbViews());
	}

	@Override
	public Resource addComment(Long id, Comment comment) {
		final Resource post = this.getResourceById(id);
		post.getComments().add(comment);
		return post;
	}

	@Override
	public Resource removeComment(Long id, Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resource> getResourcesByFileType(int idFileType) {
		FileType fileType = fileTypeRepository.findById(idFileType);
		return resourceRepository.findByFileType(fileType);
	}

	@Override
	public List<Resource> getResourcesByLanguage(int idLanguage) {
		Language language = languageRepository.findById(idLanguage);
		return resourceRepository.findByLanguage(language);
	}

	@Override
	public List<Resource> getResourcesByRelationshipType(int idRelationshipType) {
		RelationshipType relationshipType = relationshipTypeRepository.findById(idRelationshipType);
		return resourceRepository.findByRelationshipType(relationshipType);
	}

	@Override
	public List<Resource> getResourcesByResourceCategory(int idResourceCategory) {
		ResourceCategory resourceCategory = resourceCategoryRepository.findById(idResourceCategory);
		return resourceRepository.findByResourceCategory(resourceCategory);
	}

	@Override
	public List<Resource> getResourcesByResourceType(int idResourceType) {
		ResourceType resourceType = resourceTypeRepository.findById(idResourceType);
		return resourceRepository.findByResourceType(resourceType);
	}

	@Override
	public List<Resource> getPostResourcesByCreator() {

		return resourceRepository.getPostResourcesByCreator(authenticationService.getCurrentUser().getId());
	}

	@Override
	public List<Resource> getDraftResourcesByCreator() {
		// TODO Auto-generated method stub
		return resourceRepository.getDraftResourcesByCreator(authenticationService.getCurrentUser().getId());
	}

	// ***********************
	// DATA VALIDATION METHODS
	// ***********************

}
