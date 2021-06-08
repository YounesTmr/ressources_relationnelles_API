package fr.cesi.cubes.resourceRelationnelles.repository.resource;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.FileType;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Language;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.RelationshipType;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.ResourceCategory;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.ResourceType;
import fr.cesi.cubes.resourceRelationnelles.request.statistics.CountGroup;
import fr.cesi.cubes.resourceRelationnelles.request.statistics.CountKPI;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

	public Resource findById(long id);

	public Set<Resource> findByNameContaining(String expression);

	public Set<Resource> findByIsValidatedFalseAndIsDraftFalse();

	public List<Resource> findByIsValidatedTrue();

	@Override
	public List<Resource> findAll();

	@Query(nativeQuery = true, value = "SELECT * FROM resources WHERE is_validated = true AND file_type = :fieldId ORDER BY :sort DESC LIMIT :limit OFFSET :offset ")
	public List<Resource> findByFileType(@Param(value = "fieldId") int fieldId, @Param(value = "sort") String sort,
			@Param(value = "limit") int limit, @Param(value = "offset") int offset);

	@Query(nativeQuery = true, value = "SELECT * FROM resources WHERE is_validated = true AND relationship_type = :fieldId ORDER BY :sort DESC LIMIT :limit OFFSET :offset ")
	public List<Resource> findByRelationshipType(@Param(value = "fieldId") int fieldId,
			@Param(value = "sort") String sort, @Param(value = "limit") int limit, @Param(value = "offset") int offset);

	@Query(nativeQuery = true, value = "SELECT * FROM resources WHERE is_validated = true AND resource_type = :fieldId ORDER BY :sort DESC LIMIT :limit OFFSET :offset ")
	public List<Resource> findByResourceType(@Param(value = "fieldId") int fieldId, @Param(value = "sort") String sort,
			@Param(value = "limit") int limit, @Param(value = "offset") int offset);

	@Query(nativeQuery = true, value = "SELECT * FROM resources WHERE is_validated = true AND resource_category = :fieldId ORDER BY :sort DESC LIMIT :limit OFFSET :offset ")
	public List<Resource> findByResourceCategory(@Param(value = "fieldId") int fieldId,
			@Param(value = "sort") String sort, @Param(value = "limit") int limit, @Param(value = "offset") int offset);

	@Query(nativeQuery = true, value = "SELECT * FROM resources WHERE is_validated = true AND id_creator = :id")
	public List<Resource> getPostResourcesByCreator(long id);

	@Query(nativeQuery = true, value = "SELECT * FROM resources WHERE is_draft = true AND id_creator = :id")
	public List<Resource> getDraftResourcesByCreator(long id);

	public List<Resource> findByFileType(FileType fileType);

	public List<Resource> findByRelationshipType(RelationshipType relationshipType);

	public List<Resource> findByResourceType(ResourceType resourceType);

	public List<Resource> findByResourceCategory(ResourceCategory resourceCategory);

	public List<Resource> findByLanguage(Language language);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE resources SET is_validated = :is_validated WHERE Id_Resources = :id")
	void updateValidated(@Param(value = "id") long id, @Param(value = "is_validated") boolean isValidated);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE resources SET is_visible = :is_visible WHERE Id_Resources = :id")
	void updateVisibility(@Param(value = "id") long id, @Param(value = "is_visible") boolean isVisible);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE resources SET nb_views = :nb_views WHERE Id_Resources = :id")
	void updateViews(@Param(value = "id") long id, @Param(value = "nb_views") long nbViews);

	@Query(nativeQuery = true, value = "SELECT T.name AS name, count(R.Id_Resources) AS total FROM `resources` AS R "
			+ "INNER JOIN resource_type AS T ON R.resource_type=T.id GROUP BY R.resource_type")
	List<CountGroup> countResourceTypes();

	@Query(nativeQuery = true, value = "SELECT C.name AS name, count(R.Id_Resources) AS total FROM `resources` AS R "
			+ "INNER JOIN resource_category AS C ON R.resource_category=C.id GROUP BY R.resource_category")
	List<CountGroup> countResourceCategories();

	@Query(nativeQuery = true, value = "SELECT T.name AS name, count(R.Id_Resources) AS total FROM `resources` AS R "
			+ "INNER JOIN relationship_type AS T ON R.relationship_type=T.id GROUP BY R.relationship_type")
	List<CountGroup> countRelationshipTypes();

	@Query(nativeQuery = true, value = "SELECT T.name AS name, count(R.Id_Resources) AS total FROM `resources` AS R "
			+ "INNER JOIN file_type AS T ON R.file_type=T.id GROUP BY R.file_type")
	List<CountGroup> countFileTypes();

	@Query(nativeQuery = true, value = "SELECT count(Id_Resources) AS total FROM `resources`")
	CountKPI countResources();

	@Query(nativeQuery = true, value = "SELECT sum(nb_views) AS total FROM `resources`")
	CountKPI countResourceViews();

	@Query(nativeQuery = true, value = "SELECT count(Id_Resources) AS total FROM `resources` WHERE id_creator = :idMember and is_validated = true")
	CountKPI countCreatedResources(@Param(value = "idMember") long idMember);
}
