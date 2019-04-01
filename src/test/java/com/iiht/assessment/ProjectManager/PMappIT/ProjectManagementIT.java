package com.iiht.assessment.ProjectManager.PMappIT;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.assessment.ProjectManager.bean.TaskInput;
import com.iiht.assessment.ProjectManager.controller.ProjectMgmtController;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectManagementIT {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext wac;

	@Autowired
	private ProjectMgmtController PmgmtController;

	@Before
	public void setUp() throws Exception {

	}
	
	@Test
	public void getallTasksTest() throws Exception {

		mockMvc.perform(get("/pmgmt/tsk").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].tid", is(1))).andExpect(jsonPath("$[1].tid", is(2))).andDo(print());

	}
	@Test
	public void getallProjectsTest() throws Exception {

		mockMvc.perform(get("/pmgmt/pj").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].projectid", is(1))).andExpect(jsonPath("$[1].projectid", is(2))).andDo(print());

	}
	@Test
	public void getallUsersTest() throws Exception {

		mockMvc.perform(get("/pmgmt/usr").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].userid", is(1))).andExpect(jsonPath("$[1].userid", is(2))).andDo(print());

	}
	@Test
	public void addTaskTest() throws Exception {

		TaskInput tio = getTaskInput1();

		mockMvc.perform(post("/pmgmt/tsk").content(asJsonString(tio)).contentType(MediaType.APPLICATION_JSON))

				.andExpect(status().isOk());

	}
	
	
	public TaskInput getTaskInput1(){
		TaskInput t1 = new TaskInput();
		t1.setTaskid(5);
		t1.setTask("AWS Cloud Lambada microservices");
		t1.setParenttask("UI Build");
		t1.setEmployeeid(367837);
		t1.setPriority(6);
		
		t1.setSdate("2019-01-18");
	
		t1.setEdate("2019-01-19");
		t1.setProject("Capsule Project");
		t1.setEmpid(367837);
		return t1;
	}
	
	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
