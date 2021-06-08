package fr.cesi.cubes.resourceRelationnelles.servicesImpl.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.Language;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;
import fr.cesi.cubes.resourceRelationnelles.repository.resource.LanguageRepository;
import fr.cesi.cubes.resourceRelationnelles.services.resource.LanguageServices;
import fr.cesi.cubes.resourceRelationnelles.services.resource.ResourceServices;

@Service
public class LanguageServicesImpl implements LanguageServices {

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    ResourceServices   resourceServices;

    @Override
    public Language addLanguage( String languageName ) {
        Language language = new Language();
        language.setName( languageName );
        languageRepository.save( language );
        return language;
    }

    @Override
    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }

    @Override
    public void deleteLanguage( int id ) {
        List<Resource> resources = resourceServices.getResourcesByLanguage( id );
        if ( resources.isEmpty() ) {
            languageRepository.deleteById( id );
        } else {
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST,
                    String.format( "The type is still referenced by at least 1 resource" ) );
        }
    }

}
