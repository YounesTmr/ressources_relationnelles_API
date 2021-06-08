package fr.cesi.cubes.resourceRelationnelles.repository.historic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.cesi.cubes.resourceRelationnelles.entities.historic.Action;
import fr.cesi.cubes.resourceRelationnelles.entities.historic.Historic;
import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;

@Repository
public interface HistoricRepository extends JpaRepository<Historic, Long> {

    List<Historic> findByMember1( Member member );

    List<Historic> findByMember2( Member member );

    List<Historic> findByResource( Resource resource );

    List<Historic> findAll();

    List<Historic> findByAction( Action action );

    @Transactional
    void deleteByResource( Resource resource );

    @Transactional
    void deleteByMember1( Member member );

    @Transactional
    void deleteByMember2( Member member );

}
