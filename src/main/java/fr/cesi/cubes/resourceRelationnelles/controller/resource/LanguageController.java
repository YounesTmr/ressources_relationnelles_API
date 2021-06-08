package fr.cesi.cubes.resourceRelationnelles.controller.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.cesi.cubes.resourceRelationnelles.entities.resource.Language;
import fr.cesi.cubes.resourceRelationnelles.request.resource.LanguageForm;
import fr.cesi.cubes.resourceRelationnelles.services.resource.LanguageServices;

@RestController
@CrossOrigin( "*" )
@RequestMapping( "/projectRE" )
public class LanguageController {

    @Autowired
    private LanguageServices languageServices;

    @PostMapping( value = "/resources/Languages" )
    public Language addLanguage( @Valid @RequestBody LanguageForm languageForm ) {
        return languageServices.addLanguage( languageForm.getName() );
    }

    @GetMapping( value = "/resources/Languages" )
    public List<Language> getLanguages() {
        return languageServices.getLanguages();
    }

    @DeleteMapping( value = "/resources/Language/{id}" )
    public void deleteLanguage( @PathVariable int id ) {
        languageServices.deleteLanguage( id );
    }

}