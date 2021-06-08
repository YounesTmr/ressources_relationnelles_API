package fr.cesi.cubes.resourceRelationnelles.services.historic;

import java.util.List;

import fr.cesi.cubes.resourceRelationnelles.entities.historic.Action;
import fr.cesi.cubes.resourceRelationnelles.entities.historic.Historic;
import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;
import fr.cesi.cubes.resourceRelationnelles.request.historic.CreateHistoricForm;

public interface HistoricServices {

    public Historic saveAction( long idMember1, Long idMember2, Long idResource, Action action );

    public Historic saveAction( CreateHistoricForm createHistoricForm );

    public Historic saveAction( Member member1, Member member2, Resource resource, Action action );

    public List<Historic> getHistoricByMember1( long idMember1 );

    public List<Historic> getHistoricByMember2( long idMember1 );

    public List<Historic> getHistoricByResource( long idResource );

    public List<Historic> getHistoric();

    public void deleteByResource( long idResource );

    public void deleteByMember1( long idMember );

    public void deleteByMember2( long idMember );

}
