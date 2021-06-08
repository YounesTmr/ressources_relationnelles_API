package fr.cesi.cubes.resourceRelationnelles.services.resource;

import java.util.List;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.Language;

public interface LanguageServices {

    public Language addLanguage( String language );

    public List<Language> getLanguages();

    public void deleteLanguage( int id );

}
