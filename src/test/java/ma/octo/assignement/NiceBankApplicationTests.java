package ma.octo.assignement;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NiceBankApplicationTests {

	@Autowired
	MockMvc mvc;

	@Test
	void usersTest() throws Exception {
		String username = "admin";
		String password = "admin";

//		String body = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/auth")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .param("username", username)
            .param("password", password))
            .andDo(print())
			.andExpect(status().isOk()).andReturn();

		String response = result.getResponse().getContentAsString();
		Tokens tokens = new ObjectMapper().readValue(response, Tokens.class);

		mvc.perform(MockMvcRequestBuilders.get("/api/users/" + username)
		.header("Authorization", "Bearer " + tokens.access_token))
		.andExpect(status().isOk());

	}

	static class Tokens {
		String access_token;
		String refresh_token;
	}

}
