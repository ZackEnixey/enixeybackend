package com.project.webapp.courierservice;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import com.jayway.jsonpath.JsonPath;
import com.project.webapp.dto.response.CourierServiceDto;
import com.project.webapp.model.CourierService;
import com.project.webapp.repository.CourierServiceRepository;
import com.project.webapp.util.UtilTest;
import com.project.webapp.util.WebShopException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("file:${C:/Users/korisnik/Desktop/testapplication.properties}")
public class CourierServiceTestCreateAndDelete {
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	private String newUserId;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	CourierServiceRepository courierServiceRepository;

	@Before
	public void createCourierService() throws Exception {
		CourierServiceDto courierServiceDto = new CourierServiceDto("CreatedToDelete", "imageURLimage", 3535, false);

		MvcResult result = mockMvc.perform( post("/courierservice/create").contentType(contentType).content(UtilTest.asJsonString(courierServiceDto)) )
								  .andDo(MockMvcResultHandlers.print())
								  .andExpect(status().isCreated())
								  .andReturn();

		String content = result.getResponse().getContentAsString();
		Object dataObject = JsonPath.parse(content).read("$.idCourierService");
		newUserId = dataObject.toString();
	}

	@Test
	public void deleteCountryById() throws Exception {
		mockMvc.perform(delete("/courierservice/" + newUserId).contentType(contentType)).andExpect(status().isOk())
				.andExpect(jsonPath("$.deleted", is(true)));
		System.out.println("deleteNewCourierService");
	}

}
