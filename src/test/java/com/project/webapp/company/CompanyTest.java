package com.project.webapp.company;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.jayway.jsonpath.JsonPath;
import com.project.webapp.dto.request.LoginDTO;
import com.project.webapp.util.UtilTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private MockMvc mockMvc;
	
	private HttpHeaders adminHeader;
	
	@PostConstruct
	public void setUp() throws UnsupportedEncodingException, Exception {
		LoginDTO adminDto = new LoginDTO("admin@gmail.com","admin");
		RequestBuilder requestBuilder = post("/login").contentType(contentType)
													  .content(UtilTest.asJsonString(adminDto));
		String response = mockMvc.perform(requestBuilder)
								 .andExpect(status().isOk())
								 .andReturn()
								 .getResponse()
								 .getContentAsString();
		String token = JsonPath.parse(response)
							   .read("$.token")
							   .toString();
		adminHeader = new HttpHeaders();
		adminHeader.add("Authorization", "Bearer " + token);
	}
	
	@Test
	public void getCourierServiceById() throws Exception {
		mockMvc.perform(delete("/company/16").headers(adminHeader).contentType(contentType))
			.andExpect(status().isOk());
	}
}
