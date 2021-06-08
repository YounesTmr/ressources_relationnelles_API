package fr.cesi.cubes.resourceRelationnelles.servicesImpl.statistics;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cesi.cubes.resourceRelationnelles.entities.historic.Action;
import fr.cesi.cubes.resourceRelationnelles.entities.historic.Historic;
import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.repository.friendship.FriendshipRepository;
import fr.cesi.cubes.resourceRelationnelles.repository.historic.HistoricRepository;
import fr.cesi.cubes.resourceRelationnelles.repository.interaction.InteractionRepository;
import fr.cesi.cubes.resourceRelationnelles.repository.member.MemberRepository;
import fr.cesi.cubes.resourceRelationnelles.repository.resource.ResourceRepository;
import fr.cesi.cubes.resourceRelationnelles.request.statistics.CountGroup;
import fr.cesi.cubes.resourceRelationnelles.request.statistics.CountGroupEnum;
import fr.cesi.cubes.resourceRelationnelles.request.statistics.CountKPI;
import fr.cesi.cubes.resourceRelationnelles.services.statistics.StatisticsServices;

@Service
public class StatisticsServicesImpl implements StatisticsServices {

    @Autowired
    private ResourceRepository    resourceRepository;
    @Autowired
    private MemberRepository      memberRepository;
    @Autowired
    private InteractionRepository interactionRepository;
    @Autowired
    private FriendshipRepository  friendshipRepository;
    @Autowired
    private HistoricRepository    historicRepository;

    @Override
    public CountKPI getCountResources() {
        return resourceRepository.countResources();
    }

    @Override
    public List<CountGroup> getCountResourceTypes() {
        return resourceRepository.countResourceTypes();
    }

    @Override
    public List<CountGroup> getCountResourceCategories() {
        return resourceRepository.countResourceCategories();
    }

    @Override
    public List<CountGroup> getCountRelationshipTypes() {
        return resourceRepository.countRelationshipTypes();
    }

    @Override
    public List<CountGroup> getCountFileTypes() {
        return resourceRepository.countFileTypes();
    }

    @Override
    public CountKPI getCountResourceViews() {
        return resourceRepository.countResourceViews();
    }

    @Override
    public CountKPI getCountMembers() {
        return memberRepository.countMembers();
    }

    @Override
    public List<CountGroupEnum> getCountCounties() {
        List<Member> members = memberRepository.findAll();
        List<CountGroupEnum> resultList = new ArrayList<CountGroupEnum>();

        for ( Member member : members ) {

            if ( member.getCounty() != null ) {

                boolean found = false;
                for ( CountGroupEnum result : resultList ) {

                    if ( !resultList.isEmpty() && result.getName().equals( member.getCounty().name() ) ) {
                        result.setTotal( result.getTotal() + 1 );
                        found = true;
                        break;
                    }

                }
                if ( !found ) {
                    resultList.add( new CountGroupEnum( member.getCounty().name(), 1 ) );
                }
            }
        }

        return resultList;

    }

    @Override
    public List<CountGroupEnum> getCountStatus() {
        List<Member> members = memberRepository.findAll();
        List<CountGroupEnum> resultList = new ArrayList<CountGroupEnum>();

        for ( Member member : members ) {

            boolean found = false;
            for ( CountGroupEnum result : resultList ) {

                if ( !resultList.isEmpty() && result.getName().equals( member.getStatus().name() ) ) {
                    result.setTotal( result.getTotal() + 1 );
                    found = true;
                    break;
                }

            }
            if ( !found ) {
                resultList.add( new CountGroupEnum( member.getStatus().name(), 1 ) );
            }
        }

        return resultList;
    }

    @Override
    public List<CountGroupEnum> getCountAges() {
        List<Member> members = memberRepository.findAll();
        List<CountGroupEnum> resultList = new ArrayList<CountGroupEnum>();

        resultList.add( new CountGroupEnum( "< 10 ans", 0 ) );
        resultList.add( new CountGroupEnum( "10 à 20 ans", 0 ) );
        resultList.add( new CountGroupEnum( "20 à 30 ans", 0 ) );
        resultList.add( new CountGroupEnum( "30 à 40 ans", 0 ) );
        resultList.add( new CountGroupEnum( "40 à 50 ans", 0 ) );
        resultList.add( new CountGroupEnum( "50 à 60 ans", 0 ) );
        resultList.add( new CountGroupEnum( "60 à 70 ans", 0 ) );
        resultList.add( new CountGroupEnum( "70 à 80 ans", 0 ) );
        resultList.add( new CountGroupEnum( "> 80 ans", 0 ) );

        for ( Member member : members ) {

            if ( member.getBirthDate() != null ) {
                LocalDateTime birthDate = LocalDateTime.fromDateFields( member.getBirthDate() );
                LocalDateTime currentDate = new LocalDateTime();
                int age = Years.yearsBetween( birthDate, currentDate ).getYears();

                if ( age < 10 ) {
                    resultList.get( 0 ).setTotal( resultList.get( 0 ).getTotal() + 1 );
                } else if ( age >= 10 && age < 20 ) {
                    resultList.get( 1 ).setTotal( resultList.get( 1 ).getTotal() + 1 );
                } else if ( age >= 20 && age < 30 ) {
                    resultList.get( 2 ).setTotal( resultList.get( 2 ).getTotal() + 1 );
                } else if ( age >= 30 && age < 40 ) {
                    resultList.get( 3 ).setTotal( resultList.get( 3 ).getTotal() + 1 );
                } else if ( age >= 40 && age < 50 ) {
                    resultList.get( 4 ).setTotal( resultList.get( 4 ).getTotal() + 1 );
                } else if ( age >= 50 && age < 60 ) {
                    resultList.get( 5 ).setTotal( resultList.get( 5 ).getTotal() + 1 );
                } else if ( age >= 60 && age < 70 ) {
                    resultList.get( 6 ).setTotal( resultList.get( 6 ).getTotal() + 1 );
                } else if ( age >= 70 && age < 80 ) {
                    resultList.get( 7 ).setTotal( resultList.get( 7 ).getTotal() + 1 );
                } else if ( age >= 80 ) {
                    resultList.get( 8 ).setTotal( resultList.get( 8 ).getTotal() + 1 );
                }
            }
        }
        return resultList;
    }

    @Override
    public CountKPI getCountFavorites( long idMember ) {
        return interactionRepository.countFavorites( idMember );
    }

    @Override
    public CountKPI getCountCompletes( long idMember ) {
        return interactionRepository.countCompletes( idMember );
    }

    @Override
    public CountKPI getCountCreatedResources( long idMember ) {
        return resourceRepository.countCreatedResources( idMember );
    }

    @Override
    public CountKPI getCountFriends( long idMember ) {
        return friendshipRepository.countFriends( idMember );
    }

    @Override
    public List<CountGroupEnum> getCountActionsPerDay( Action action ) {
        List<Historic> historics = historicRepository.findByAction( action );
        List<CountGroupEnum> resultList = new ArrayList<CountGroupEnum>();
        String datestr;

        for ( Historic historic : historics ) {

            LocalDate date = Instant.ofEpochMilli( historic.getActionDate().getTime() )
                    .atZone( ZoneId.systemDefault() ).toLocalDate();
            datestr = date.format( DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) );

            boolean found = false;
            for ( CountGroupEnum result : resultList ) {

                if ( !resultList.isEmpty() && result.getName().equals( datestr ) ) {
                    result.setTotal( result.getTotal() + 1 );
                    found = true;
                    break;
                }

            }
            if ( !found ) {
                resultList.add( new CountGroupEnum( datestr, 1 ) );
            }
        }

        return resultList;
    }

}
