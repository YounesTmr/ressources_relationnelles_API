package fr.cesi.cubes.resourceRelationnelles.controllerTest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.cesi.cubes.resourceRelationnelles.controller.member.MemberController;
import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.request.comment.CreateCommentForm;
import fr.cesi.cubes.resourceRelationnelles.request.member.LoginForm;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
		  locations = "classpath:application.properties")
public class CommentControllerTest {

    private ObjectMapper mapper = new ObjectMapper();
//init la classe objectMapper
    @Autowired
    MemberController     memberController;

    String               tokenSuperAdmin;
    String               tokenMember1;
    @BeforeEach
    public void setUp() {
    	LoginForm loginformMember1 = new LoginForm("titi", "truc");
		tokenMember1 = memberController.login(loginformMember1).getBody().getToken();
		
        LoginForm loginform = new LoginForm( "Super Admin", "truc" );

        tokenSuperAdmin = memberController.login( loginform).getBody().getToken();
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllComment() throws Exception {
        mockMvc.perform( get( "/projectRE/comments" ).header( "Authorization", "Bearer " + tokenSuperAdmin ) )
                .andExpect( status().isOk() );

    }

    @Test
    public void testGetCommentByPostId() throws Exception {
        mockMvc.perform( get( "/projectRE/comments/resource/42" ).header( "Authorization", "Bearer " + tokenSuperAdmin ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$[0].id", is( 1 ) ) );
    }

    

    @Test
    public void testCreateComment() throws Exception {
        CreateCommentForm createCommentForm = new CreateCommentForm( "BodyComment", 33 );
        mockMvc.perform( post( "/projectRE/comments" ).header( "Authorization", "Bearer " + tokenSuperAdmin )
                .contentType( MediaType.APPLICATION_JSON )
                .content( mapper.writeValueAsString( createCommentForm ) ) )
                .andExpect( status().is( 200 ) )
                .andExpect( jsonPath( "$.commentBodyText", is( "BodyComment" ) ) );
    }

    @Test
    public void testErrorCreateComment() throws Exception {
        CreateCommentForm createCommentForm = new CreateCommentForm( "BodyComment", -1 );
        mockMvc.perform( post( "/projectRE/comments" ).header( "Authorization", "Bearer " + tokenSuperAdmin )
                .contentType( MediaType.APPLICATION_JSON )
                .content( mapper.writeValueAsString( createCommentForm ) ) )
                .andExpect( status().is( 404 ) );
    }


}
