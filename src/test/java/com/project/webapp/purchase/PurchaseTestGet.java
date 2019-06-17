package com.project.webapp.purchase;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.jayway.jsonpath.JsonPath;
import com.project.webapp.dto.request.LoginDTO;
import com.project.webapp.repository.CourierServiceRepository;
import com.project.webapp.util.UtilTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("file:${C:/Users/korisnik/Desktop/testapplication.properties}")
public class PurchaseTestGet {
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Autowired
	private MockMvc mockMvc;	
	
	@Test
	public void getItemById() throws Exception {
		mockMvc.perform(get("/purchase/1").contentType(contentType))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.deleted", is(false)));
	}
}
