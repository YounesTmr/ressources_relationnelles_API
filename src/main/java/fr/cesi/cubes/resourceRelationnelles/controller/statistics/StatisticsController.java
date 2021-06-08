package fr.cesi.cubes.resourceRelationnelles.controller.statistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.cesi.cubes.resourceRelationnelles.entities.historic.Action;
import fr.cesi.cubes.resourceRelationnelles.request.statistics.CountGroup;
import fr.cesi.cubes.resourceRelationnelles.request.statistics.CountGroupEnum;
import fr.cesi.cubes.resourceRelationnelles.request.statistics.CountKPI;
import fr.cesi.cubes.resourceRelationnelles.services.statistics.StatisticsServices;

@RestController
@CrossOrigin( "*" )
@RequestMapping( "/projectRE" )
public class StatisticsController {

    @Autowired
    private StatisticsServices statisticsServices;

    // *************************************************
    // Resources
    // *************************************************

    // get the count of resources
    @GetMapping( value = "/admin/stats/count/resources" )
    public CountKPI getCountResources() {
        return statisticsServices.getCountResources();
    }

    // get the count of resources by Resource Types
    @GetMapping( value = "/admin/stats/count/ResourceTypes" )
    public List<CountGroup> getCountResourceTypes() {
        return statisticsServices.getCountResourceTypes();
    }

    // get the count of resources by Resource Categories
    @GetMapping( value = "/admin/stats/count/ResourceCategories" )
    public List<CountGroup> getCountResourceCategories() {
        return statisticsServices.getCountResourceCategories();
    }

    // get the count of resources by Relationship types
    @GetMapping( value = "/admin/stats/count/RelationshipTypes" )
    public List<CountGroup> getCountRelationshipTypes() {
        return statisticsServices.getCountRelationshipTypes();
    }

    // get the count of resources by file types
    @GetMapping( value = "/admin/stats/count/FileTypes" )
    public List<CountGroup> getCountFileTypes() {
        return statisticsServices.getCountFileTypes();
    }

    // get the count of views for all resources
    @GetMapping( value = "/admin/stats/count/resourceViews" )
    public CountKPI getCountResourceViews() {
        return statisticsServices.getCountResourceViews();
    }

    // *************************************************
    // Members
    // *************************************************

    // get the count of members
    @GetMapping( value = "/admin/stats/count/members" )
    public CountKPI getCountMembers() {
        return statisticsServices.getCountMembers();
    }

    // get the count of members by counties
    @GetMapping( value = "/admin/stats/count/counties" )
    public List<CountGroupEnum> getCountCounties() {
        return statisticsServices.getCountCounties();
    }

    // get the count of members by status
    @GetMapping( value = "/admin/stats/count/status" )
    public List<CountGroupEnum> getCountStatus() {
        return statisticsServices.getCountStatus();
    }

    // get the count of members age
    @GetMapping( value = "/admin/stats/count/ages" )
    public List<CountGroupEnum> getCountAges() {
        return statisticsServices.getCountAges();
    }

    // *************************************************
    // individual statistics
    // *************************************************

    // get the count of favorite resources for a specific member
    @GetMapping( value = "/profile/{id}/stats/count/favorites" )
    public CountKPI getCountFavorites( @PathVariable long id ) {
        return statisticsServices.getCountFavorites( id );
    }

    // get the count of completed resources for a specific member
    @GetMapping( value = "/profile/{id}/stats/count/completed" )
    public CountKPI getCountCompletes( @PathVariable long id ) {
        return statisticsServices.getCountCompletes( id );
    }

    // get the count of created resources for a specific member
    @GetMapping( value = "/profile/{id}/stats/count/creations" )
    public CountKPI getCountCreatedResources( @PathVariable long id ) {
        return statisticsServices.getCountCreatedResources( id );
    }

    // get the count of created resources for a specific member
    @GetMapping( value = "/profile/{id}/stats/count/friends" )
    public CountKPI getCountFriends( @PathVariable long id ) {
        return statisticsServices.getCountFriends( id );
    }

    // *************************************************
    // Historic
    // *************************************************

    // get the count of action per day
    @GetMapping( value = "/admin/stats/count/action/{action}" )
    public List<CountGroupEnum> getCountMembers( @PathVariable Action action ) {
        return statisticsServices.getCountActionsPerDay( action );
    }

}