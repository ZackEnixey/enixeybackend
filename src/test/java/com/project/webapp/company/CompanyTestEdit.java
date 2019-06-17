package com.project.webapp.company;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.jayway.jsonpath.JsonPath;
import com.project.webapp.dto.request.LoginDTO;
import com.project.webapp.dto.response.AppUserDto;
import com.project.webapp.dto.response.CompanyDto;
import com.project.webapp.dto.response.CourierServiceDto;
import com.project.webapp.model.AppUser;
import com.project.webapp.model.Company;
import com.project.webapp.model.CourierService;
import com.project.webapp.repository.AppUserRepository;
import com.project.webapp.repository.CompanyRepository;
import com.project.webapp.repository.CourierServiceRepository;
import com.project.webapp.util.UtilTest;
import com.project.webapp.util.WebShopException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("file:${C:/Users/korisnik/Desktop/testapplication.properties}")
public class CompanyTestEdit {
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	private String newUserId;
	private String token;

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	CompanyRepository companyRepository;
	
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
		token = JsonPath.parse(response)
							   .read("$.token")
							   .toString();
		adminHeader = new HttpHeaders();
		adminHeader.add("Authorization", "Bearer " + token);
	}
	
	@Before
	public void createAppUser() throws Exception {
		byte[] array = new byte[8];
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
	    
		CompanyDto companyDto = new CompanyDto();
		companyDto.setName(generatedString);
		companyDto.setCountry("Serbia");
		companyDto.setCity("Belgrade");
		companyDto.setAddress("some address");
		companyDto.setBankAccount("12365411111");
		
		MvcResult result = mockMvc.perform( post("/company/create").contentType(contentType).content(UtilTest.asJsonString(companyDto)) )
								  .andDo(MockMvcResultHandlers.print())
								  .andExpect(status().isCreated())
								  .andReturn();

		String content = result.getResponse().getContentAsString();
		Object dataObject = JsonPath.parse(content).read("$.idCompany");
		newUserId = dataObject.toString();
		System.out.println("This is a new user ID: " + newUserId);
	}

	@Test
	public void editCompanyById() throws Exception {
		int id = Integer.parseInt(newUserId);
		Company company = companyRepository.findById(id).orElseThrow(() -> new WebShopException(HttpStatus.BAD_REQUEST, "there is no CourierSerivice iwth ID: " + 1));
		CompanyDto companyDto= new CompanyDto(company);		
		companyDto.setName("CompanyEdit");
		
		mockMvc.perform(put("/company/"+newUserId).contentType(contentType).content(UtilTest.asJsonString(companyDto)))
				       .andExpect(status().isOk())
					   .andExpect(jsonPath("$.name", is("CompanyEdit")));
	}
}
