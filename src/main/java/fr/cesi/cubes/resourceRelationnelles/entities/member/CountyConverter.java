package fr.cesi.cubes.resourceRelationnelles.entities.member;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter( autoApply = true )
public class CountyConverter implements AttributeConverter<County, String> {

    @Override
    public String convertToDatabaseColumn( County county ) {
        if ( county == null ) {
            return null;
        }
        return county.getName();
    }

    @Override
    public County convertToEntityAttribute( String name ) {
        if ( name == null ) {
            return null;
        }

        return Stream.of( County.values() )
                .filter( c -> c.getName().equals( name ) )
                .findFirst()
                .orElseThrow( IllegalArgumentException::new );
    }

}