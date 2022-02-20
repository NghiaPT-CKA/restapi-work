package com.work;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.common.BaseResponse;
import com.work.enums.StatusResponse;
import com.work.model.WorkModel;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = WorkApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WorkControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	private static final String URL_REGISTER = "/work/add";
	private static final String URL_LIST = "/work/list";
	private static final String URL_UPDATE = "/work/edit";
	private static final String URL_DELETE = "/work/delete";
	
	private static Map<String, String> input;
	private static List<Integer> idList;
	
	@BeforeAll
	public static void setup() {
		input = new HashMap<String, String>();
		input.put("name", "test0001");
		input.put("startingDate", "20220220");
		input.put("endingDate", "20220320");
		input.put("status", "Planning");
	}
	
	
	/**
	 * Test add work
	 * 
	 * Normal case
	 * 
	 * @throws Exception
	 */
	@Test
	public void test001() throws Exception {
		// Convert input to json
		String jsonRequest = objectMapper.writeValueAsString(input);
		
		// Execute the request get list
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL_REGISTER)
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		BaseResponse<?> baseResponse = objectMapper
				.readValue(result.getResponse().getContentAsString(), BaseResponse.class);
		
		// Compare results
		assertEquals(StatusResponse.SUCCESS.toString(), baseResponse.getStatus());
	}
	
	/**
	 * Test add work
	 * 
	 * Abnormal case
	 * 
	 * @throws Exception
	 */
	@Test
	public void test002() throws Exception {
		input.put("status", "abnomal");
		
		// Convert input to json
		String jsonRequest = objectMapper.writeValueAsString(input);
		
		// Execute the request register
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL_REGISTER)
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		BaseResponse<?> baseResponse = objectMapper
				.readValue(result.getResponse().getContentAsString(), BaseResponse.class);
		
		// Compare results
		assertEquals(StatusResponse.FAIL.toString(), baseResponse.getStatus());
	}
	
	/**
	 * Test get list work
	 * 
	 * @throws Exception
	 */
	@Test
	public void test003() throws Exception {
		// Execute the request get list
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL_LIST)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		BaseResponse<WorkModel> baseResponse = objectMapper
				.readValue(result.getResponse().getContentAsString(), new TypeReference<BaseResponse<WorkModel>>() {});
		List<WorkModel> workList = baseResponse.getData();
		idList = workList.stream()
				.filter(w -> input.get("name").equals(w.getName()))
				.map(w -> w.getId())
				.collect(Collectors.toList());
		
		// Compare results
		assertEquals(StatusResponse.SUCCESS.toString(), baseResponse.getStatus());
	}
	
	/**
	 * Test update work
	 * 
	 * Normal case
	 * 
	 * @throws Exception
	 */
	@Test
	public void test004() throws Exception {
		// Prepare input update
		Map<String, String> inputUpdate = new HashMap<String, String>();
		inputUpdate.put("name", "test0002");
		inputUpdate.put("startingDate", "20220320");
		inputUpdate.put("endingDate", "20220420");
		inputUpdate.put("status", "Complete");
		
		// Convert input to json
		String jsonRequest = objectMapper.writeValueAsString(inputUpdate);
		
		if (CollectionUtils.isEmpty(idList)) {
			
			// Execute the request get list
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL_LIST)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andReturn();
			
			BaseResponse<WorkModel> baseResponse = objectMapper
					.readValue(result.getResponse().getContentAsString(), new TypeReference<BaseResponse<WorkModel>>() {});
			List<WorkModel> workList = baseResponse.getData();
			idList = workList.stream()
					.filter(w -> input.get("name").equals(w.getName()))
					.map(w -> w.getId())
					.collect(Collectors.toList());
		}
		
		for (Integer id : idList) {
			
			// Execute the request update
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put((URL_UPDATE + "/" + id))
					.content(jsonRequest)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andReturn();
			
			BaseResponse<?> baseResponse = objectMapper
					.readValue(result.getResponse().getContentAsString(), BaseResponse.class);
			
			// Compare results
			assertEquals(StatusResponse.SUCCESS.toString(), baseResponse.getStatus());
		}
	}
	
	/**
	 * Test update work
	 * 
	 * Abnormal case
	 * 
	 * @throws Exception
	 */
	@Test
	public void test005() throws Exception {
		// Prepare input update
		Map<String, String> inputUpdate = new HashMap<String, String>();
		inputUpdate.put("name", "test0003");
		inputUpdate.put("status", "Abnomal");
		
		// Convert input to json
		String jsonRequest = objectMapper.writeValueAsString(inputUpdate);
		
		if (CollectionUtils.isEmpty(idList)) {
			
			// Execute the request get list
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL_LIST)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andReturn();
			
			BaseResponse<WorkModel> baseResponse = objectMapper
					.readValue(result.getResponse().getContentAsString(), new TypeReference<BaseResponse<WorkModel>>() {});
			List<WorkModel> workList = baseResponse.getData();
			idList = workList.stream()
					.filter(w -> input.get("name").equals(w.getName()))
					.map(w -> w.getId())
					.collect(Collectors.toList());
		}
		
		for (Integer id : idList) {
			
			// Execute the request update
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put((URL_UPDATE + "/" + id))
					.content(jsonRequest)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andReturn();
			
			BaseResponse<?> baseResponse = objectMapper
					.readValue(result.getResponse().getContentAsString(), BaseResponse.class);
			
			// Compare results
			assertEquals(StatusResponse.FAIL.toString(), baseResponse.getStatus());
		}
	}
	
	/**
	 * Test delete work
	 * 
	 * Normal case
	 * 
	 * @throws Exception
	 */
	@Test
	public void test006() throws Exception {
		if (CollectionUtils.isEmpty(idList)) {
			
			// Execute the request get list
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL_LIST)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andReturn();
			
			BaseResponse<WorkModel> baseResponse = objectMapper
					.readValue(result.getResponse().getContentAsString(), new TypeReference<BaseResponse<WorkModel>>() {});
			List<WorkModel> workList = baseResponse.getData();
			idList = workList.stream()
					.filter(w -> "test0002".equals(w.getName()))
					.map(w -> w.getId())
					.collect(Collectors.toList());
		}
		
		for (Integer id : idList) {
			
			// Execute the request delete
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete((URL_DELETE + "/" + id))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andReturn();
			
			BaseResponse<?> baseResponse = objectMapper
					.readValue(result.getResponse().getContentAsString(), BaseResponse.class);
			
			// Compare results
			assertEquals(StatusResponse.SUCCESS.toString(), baseResponse.getStatus());
		}
	}
	
	/**
	 * Test delete work
	 * 
	 * Abnormal
	 * 
	 * @throws Exception
	 */
	@Test
	public void test007() throws Exception {
		if (CollectionUtils.isEmpty(idList)) {
			
			// Execute the request get list
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL_LIST)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andReturn();
			
			BaseResponse<WorkModel> baseResponse = objectMapper
					.readValue(result.getResponse().getContentAsString(), new TypeReference<BaseResponse<WorkModel>>() {});
			List<WorkModel> workList = baseResponse.getData();
			idList = workList.stream()
					.filter(w -> "test0002".equals(w.getName()))
					.map(w -> w.getId())
					.collect(Collectors.toList());
		}
		
		for (Integer id : idList) {
			
			// Execute the request delete
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete((URL_DELETE + "/" + id))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andReturn();
			
			BaseResponse<?> baseResponse = objectMapper
					.readValue(result.getResponse().getContentAsString(), BaseResponse.class);
			
			// Compare results
			assertEquals(StatusResponse.FAIL.toString(), baseResponse.getStatus());
		}
	}
}
