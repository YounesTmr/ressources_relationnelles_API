package fr.cesi.cubes.resourceRelationnelles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ResourceRelationnellesApplication {

    public static void main( String[] args ) {
    	//commentaire
        SpringApplication.run( ResourceRelationnellesApplication.class, args );
    }

}
