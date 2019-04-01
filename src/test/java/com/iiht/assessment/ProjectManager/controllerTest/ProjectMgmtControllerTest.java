package com.iiht.assessment.ProjectManager.controllerTest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.iiht.assessment.ProjectManager.beans.ProjectOutput;
import com.iiht.assessment.ProjectManager.beans.TaskOutput;
import com.iiht.assessment.ProjectManager.controller.ProjectMgmtController;
import com.iiht.assessment.ProjectManager.entity.Users;
import com.iiht.assessment.ProjectManager.service.ProjectService;
import com.iiht.assessment.ProjectManager.service.TaskService;
import com.iiht.assessment.ProjectManager.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectMgmtControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	protected WebApplicationContext wac;
	
	@Autowired
	private ProjectMgmtController projectMgmtController;
	
	@MockBean
	private TaskService taskService;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private ProjectService projectService;
	
private List<TaskOutput> taskdets = new ArrayList<TaskOutput>();
private List<Users> userdets = new ArrayList<Users>(); 
private List<ProjectOutput> projectdets = new ArrayList<ProjectOutput>();
	
	@Before
	public void setUp() throws Exception {
		//MockitoAnnotations.initMocks(this);
		TaskOutput tio1 = getTaskOutput1();
		TaskOutput tio2 = getTaskOutput2();
		this.taskdets.add(tio1);
		this.taskdets.add(tio2);
		Users u1 = getUser1();
		Users u2 = getUser2();
		this.userdets.add(u1);
		this.userdets.add(u2);
		ProjectOutput po1 = getProjectOutput1();
		ProjectOutput po2 = getProjectOutput2();
		this.projectdets.add(po1);
		this.projectdets.add(po2);
		
	}
	
	@Test
	public void getallTasksTest() throws Exception{
		
		when(taskService.getallTasks()).thenReturn(taskdets);
		mockMvc.perform(get("/pmgmt/tsk")
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].tid", is(1)))
        .andExpect(jsonPath("$[1].tid", is(2)))
        .andDo(print());		
	}
	
	@Test
	public void getallUsersTest() throws Exception{
		
		when(userService.getallUsers()).thenReturn(userdets);
		mockMvc.perform(get("/pmgmt/usr")
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].tid", is(1)))
        .andExpect(jsonPath("$[1].tid", is(2)))
        .andDo(print());		
	}
	
	@Test
	public void getallProjectssTest() throws Exception{
		
		when(projectService.getallProjectDetails()).thenReturn(projectdets);
		mockMvc.perform(get("/pmgmt/pj")
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].empid", is(367834)))
        .andExpect(jsonPath("$[1].empid", is(367836)))
        .andDo(print());		
	}
	
	public TaskOutput getTaskOutput1(){
		TaskOutput to1 = new TaskOutput();
		to1.setTid(1);
		to1.setTask("UI Build Pages");
		to1.setPtask("UI Layer");
		to1.setProject("Capsule Project");
		to1.setEmpid(367834);
		to1.setStatus('N');
		LocalDate d1 = LocalDate.parse("2019-03-30", DateTimeFormatter.ISO_LOCAL_DATE);
		to1.setSdate(d1);
		LocalDate d2 = LocalDate.parse("2019-03-31", DateTimeFormatter.ISO_LOCAL_DATE);
		to1.setEdate(d2);		
		return to1;
	}
	public TaskOutput getTaskOutput2(){
		TaskOutput to1 = new TaskOutput();
		to1.setTid(2);
		to1.setTask("DB2 Scripts");
		to1.setPtask("DB2 Layer");
		to1.setProject("Capsule Project");
		to1.setEmpid(367836);
		to1.setStatus('N');
		LocalDate d1 = LocalDate.parse("2019-03-30", DateTimeFormatter.ISO_LOCAL_DATE);
		to1.setSdate(d1);
		LocalDate d2 = LocalDate.parse("2019-03-31", DateTimeFormatter.ISO_LOCAL_DATE);
		to1.setEdate(d2);		
		return to1;
	}
	
	public Users getUser1(){
		Users u1 = new Users();
		u1.setTid(1);
		u1.setEmpid(366122);
		u1.setFname("Renga Prasad");
		u1.setLname("Rajendran");
		u1.setProjectid(1);
		u1.setUserid(1);
		return u1;
	}
	public Users getUser2(){
		Users u1 = new Users();
		u1.setTid(2);
		u1.setEmpid(366123);
		u1.setFname("Uthra");
		u1.setLname("Kumarvel");
		u1.setProjectid(1);
		u1.setUserid(2);
		return u1;
	}
	
	public ProjectOutput getProjectOutput1(){
		ProjectOutput po1 = new ProjectOutput();
		po1.setProjectid(1);
		po1.setProject("Capsule Project");
		po1.setEmpid(367834);
		po1.setTottsk(2);
		po1.setComptsk(1);
		po1.setPriority(5);
		LocalDate d1 = LocalDate.parse("2019-03-30", DateTimeFormatter.ISO_LOCAL_DATE);
		po1.setSdate(d1);
		LocalDate d2 = LocalDate.parse("2019-04-30", DateTimeFormatter.ISO_LOCAL_DATE);
		po1.setEdate(d2);
		return po1;
	}
	
	public ProjectOutput getProjectOutput2(){
		ProjectOutput po1 = new ProjectOutput();
		po1.setProjectid(2);
		po1.setProject("Final Project");
		po1.setEmpid(367836);
		po1.setTottsk(2);
		po1.setComptsk(0);
		po1.setPriority(5);
		LocalDate d1 = LocalDate.parse("2019-03-30", DateTimeFormatter.ISO_LOCAL_DATE);
		po1.setSdate(d1);
		LocalDate d2 = LocalDate.parse("2019-04-30", DateTimeFormatter.ISO_LOCAL_DATE);
		po1.setEdate(d2);
		return po1;
	}

}
