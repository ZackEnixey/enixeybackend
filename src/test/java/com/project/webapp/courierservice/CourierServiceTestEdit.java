package com.project.webapp.courierservice;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

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
public class CourierServiceTestEdit {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	private String newUserId;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	CourierServiceRepository courierServiceRepository;
 
	@Before
	public void createNewCourierService() throws Exception {
		CourierServiceDto courierServiceDto = new CourierServiceDto("David", "imageURLimage", 3535, false);

		MvcResult result = mockMvc.perform( post("/courierservice/create").contentType(contentType).content(UtilTest.asJsonString(courierServiceDto)) )
								  .andDo(MockMvcResultHandlers.print())
								  .andExpect(status().isCreated())
								  .andReturn();

		String content = result.getResponse().getContentAsString();
		Object dataObject = JsonPath.parse(content).read("$.idCourierService");
		newUserId = dataObject.toString();
	}
	
	@Test 
	public void editCourierService() throws Exception {
		int id = Integer.parseInt(newUserId);
		CourierService courierService = courierServiceRepository.findById(id).orElseThrow(() -> new WebShopException(HttpStatus.BAD_REQUEST, "there is no CourierSerivice iwth ID: " + 1));
		CourierServiceDto courierServiceDto = new CourierServiceDto(courierService);		
		courierServiceDto.setName("CourierServiceTestEdit");
		
		mockMvc.perform(put("/courierservice/"+newUserId).contentType(contentType).content(UtilTest.asJsonString(courierServiceDto)))
				       .andExpect(status().isOk())
					   .andExpect(jsonPath("$.name", is("CourierServiceTestEdit")));
	}
}
