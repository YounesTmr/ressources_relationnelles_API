package fr.cesi.cubes.resourceRelationnelles.controllerTest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ResponseStatusException;

import static org.hamcrest.CoreMatchers.is;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.cesi.cubes.resourceRelationnelles.controller.member.MemberController;
import fr.cesi.cubes.resourceRelationnelles.entities.member.Country;
import fr.cesi.cubes.resourceRelationnelles.entities.member.County;
import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.request.jwt.JwtResponse;
import fr.cesi.cubes.resourceRelationnelles.request.member.LoginForm;
import fr.cesi.cubes.resourceRelationnelles.request.member.UpdateMemberForm;
import fr.cesi.cubes.resourceRelationnelles.services.authentification.AuthenticationService;
import fr.cesi.cubes.resourceRelationnelles.services.comment.CommentServices;
import fr.cesi.cubes.resourceRelationnelles.services.member.MemberServices;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private MemberController memberController;

	@Autowired
	private AuthenticationService service;
	 private ObjectMapper  mapper = new ObjectMapper();
	Member member1;
	Member member2;
	Member superAdmin;
	String tokenMember1;
	String tokenMember2;
	String tokenSuperAdmin;

	@BeforeEach
	public void setUp() {
		LoginForm loginformMember1 = new LoginForm("titi", "truc");
		member1 = memberController.login(loginformMember1).getBody().getUser();
		tokenMember1 = memberController.login(loginformMember1).getBody().getToken();
		LoginForm loginformMember2 = new LoginForm("toto", "truc");
		member2 = memberController.login(loginformMember2).getBody().getUser();
		tokenMember2 = memberController.login(loginformMember2).getBody().getToken();
		LoginForm loginformSuperAdmin = new LoginForm("Super Admin", "truc");
		superAdmin = memberController.login(loginformSuperAdmin).getBody().getUser();
		tokenSuperAdmin = memberController.login(loginformSuperAdmin).getBody().getToken();
	}

	@Test
	public void testGetMembers() throws Exception {
		mockMvc.perform(get("/projectRE/profile")).andExpect(status().isForbidden());
	}

	@Test
	public void testGetMemberByUsername() throws Exception {

		String username = "titi";
//		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
//				requestParams.add("username", "titi");
//		mockMvc.perform(get("/projectRE/profile/search").params(requestParams).header("Authorization", "Bearer " + tokenSuperAdmin)).andExpect(status().isOk());

		mockMvc.perform(get("/projectRE/profile/search/" + username).header("Authorization",
				"Bearer " + tokenMember1).accept("application/json;charset=UTF-8")).andExpect(status().isOk());
	}

	@Test
	public void testGetProfileListBeingConnectedAsSuperAdmin() throws Exception {
		long id = superAdmin.getId();
		mockMvc.perform(get("/projectRE/profile").header("Authorization", "Bearer " + tokenSuperAdmin))
				.andExpect(status().is(200)).andExpect(jsonPath("$[0].id", is((int) id)));
	}

	@Test
	public void testGetMemberById() throws Exception {
		long id = 1;
		mockMvc.perform(get("/projectRE/profile/" + id).header("Authorization", "Bearer " + tokenSuperAdmin)
				.accept("application/json;charset=UTF-8")).andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateMemberByIdWithGoodCredentials() throws Exception {
		long id = member1.getId();
		Member member = new Member();
		UpdateMemberForm form = new UpdateMemberForm( member1.getUsername() , member.getBirthDate(), County.ARDECHE , Country.ESPAGNE);
		mockMvc.perform(put("/projectRE/profile/"+ id).contentType( MediaType.APPLICATION_JSON ).content( mapper.writeValueAsString( form ) ).header("Authorization", "Bearer " + tokenMember1)
				).andExpect(status().isOk());
	}
	
	@Test
	public void testGetAllComment() throws Exception {
		mockMvc.perform(get("/projectRE/comments").header("Authorization", "Bearer " + tokenSuperAdmin))
				.andExpect(status().isOk());

	}

}
