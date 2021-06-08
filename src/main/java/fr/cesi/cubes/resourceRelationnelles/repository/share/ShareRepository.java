package fr.cesi.cubes.resourceRelationnelles.repository.share;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;
import fr.cesi.cubes.resourceRelationnelles.entities.share.Share;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {

    Share findById( long id );

    Set<Share> findByRecipient( Member recipient );

    void deleteByResource( Resource resource );

    void deleteBySender( Member sender );

    void deleteByRecipient( Member recipient );

}
