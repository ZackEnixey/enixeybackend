package com.project.webapp.util;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UtilTest {
	
	public static String asJsonString(final Object obj) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonContent = objectMapper.writeValueAsString(obj);
			System.out.println(jsonContent);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}