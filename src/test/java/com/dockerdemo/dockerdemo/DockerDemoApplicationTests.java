package com.dockerdemo.dockerdemo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
class DockerDemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	//@Test
	//void hiMarioTest() throws Exception {
	//	ResultActions res = mockMvc.perform(MockMvcRequestBuilders.get("/hi/Mario"));
	//	res.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
	//	res.andExpect(MockMvcResultMatchers.status().isOk());
	//	String response = res.andReturn().getResponse().getContentAsString();
	//	assertEquals("Hi Mario!!", response);
	//}

}
