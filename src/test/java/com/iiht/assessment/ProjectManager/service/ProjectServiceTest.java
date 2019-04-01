package com.iiht.assessment.ProjectManager.service;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.iiht.assessment.ProjectManager.beans.ProjectOutput;
import com.iiht.assessment.ProjectManager.beans.TaskCount;
import com.iiht.assessment.ProjectManager.dao.TaskDao;
import com.iiht.assessment.ProjectManager.dao.UserDao;
import com.iiht.assessment.ProjectManager.entity.Project;
import com.iiht.assessment.ProjectManager.entity.Users;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceTest {
	
	@MockBean
	private UserDao userDao;
	@MockBean
	private TaskDao taskDao;
	
	@Autowired
	private ProjectService projectService;
	
	@Test
	public void TestfetchallProjects(){
		List<Project> pjlst = new ArrayList<Project>();
		pjlst.add(getProject1());
		pjlst.add(getProject1());
		List<Users> ulst = new ArrayList<Users>();
		ulst.add(getUser1());
		ulst.add(getUser2());
		when(userDao.findAll()).thenReturn(ulst);
		when(taskDao.getTotalTasks()).thenReturn(getTotalTasks());
		when(taskDao.getTotalCompletedTasks()).thenReturn(getCompletedTasks());
		assertThat(projectService.getallProjectDetails(), hasSize(2));
		assertThat(projectService.getallProjectDetails(), hasItems(isA(ProjectOutput.class)));
	}
	public Project getProject1(){
		Project p1 = new Project();
		p1.setProjectid(1);
		p1.setProject("Capsule Project");
		p1.setPriority(10);
		LocalDate d1 = LocalDate.parse("2019-03-30", DateTimeFormatter.ISO_LOCAL_DATE);
		p1.setSdate(d1);
		LocalDate d2 = LocalDate.parse("2019-04-30", DateTimeFormatter.ISO_LOCAL_DATE);
        p1.setEdate(d2);
		return p1;
	}
	public Project getProject2(){
		Project p1 = new Project();
		p1.setProjectid(2);
		p1.setProject("Final Project");
		p1.setPriority(10);
		LocalDate d1 = LocalDate.parse("2019-03-30", DateTimeFormatter.ISO_LOCAL_DATE);
		p1.setSdate(d1);
		LocalDate d2 = LocalDate.parse("2019-04-30", DateTimeFormatter.ISO_LOCAL_DATE);
        p1.setEdate(d2);
		return p1;
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
		u1.setProjectid(2);
		u1.setUserid(2);
		return u1;
	}
	public List<TaskCount> getTotalTasks(){
		List<TaskCount> tcl = new ArrayList<TaskCount>();
		TaskCount tc1 = new TaskCount(1,2);
		TaskCount tc2 = new TaskCount(2,1);
		tcl.add(tc1);
		tcl.add(tc2);
		return tcl;
	}
	public List<TaskCount> getCompletedTasks(){
		List<TaskCount> tcl = new ArrayList<TaskCount>();
		TaskCount tc1 = new TaskCount(1,1);
		TaskCount tc2 = new TaskCount(2,0);
		tcl.add(tc1);
		tcl.add(tc2);
		return tcl;
	}
}
