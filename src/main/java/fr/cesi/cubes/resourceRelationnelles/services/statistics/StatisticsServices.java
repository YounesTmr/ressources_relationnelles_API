package fr.cesi.cubes.resourceRelationnelles.services.statistics;

import java.util.List;

import fr.cesi.cubes.resourceRelationnelles.entities.historic.Action;
import fr.cesi.cubes.resourceRelationnelles.request.statistics.CountGroup;
import fr.cesi.cubes.resourceRelationnelles.request.statistics.CountGroupEnum;
import fr.cesi.cubes.resourceRelationnelles.request.statistics.CountKPI;

public interface StatisticsServices {

    public CountKPI getCountResources();

    public List<CountGroup> getCountResourceTypes();

    public List<CountGroup> getCountResourceCategories();

    public List<CountGroup> getCountRelationshipTypes();

    public List<CountGroup> getCountFileTypes();

    public CountKPI getCountResourceViews();

    public CountKPI getCountMembers();

    public List<CountGroupEnum> getCountCounties();

    public List<CountGroupEnum> getCountStatus();

    public List<CountGroupEnum> getCountAges();

    public CountKPI getCountFavorites( long idMember );

    public CountKPI getCountCompletes( long idMember );

    public CountKPI getCountCreatedResources( long idMember );

    public CountKPI getCountFriends( long idMember );

    public List<CountGroupEnum> getCountActionsPerDay( Action action );
}
