package com.iiht.assessment.ProjectManager.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.CoreMatchers.isA;
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

import com.iiht.assessment.ProjectManager.beans.TaskOutput;
import com.iiht.assessment.ProjectManager.dao.ProjectDao;
import com.iiht.assessment.ProjectManager.dao.PtaskDao;
import com.iiht.assessment.ProjectManager.dao.TaskDao;
import com.iiht.assessment.ProjectManager.dao.UserDao;
import com.iiht.assessment.ProjectManager.entity.Project;
import com.iiht.assessment.ProjectManager.entity.Ptask;
import com.iiht.assessment.ProjectManager.entity.Task;
import com.iiht.assessment.ProjectManager.entity.Users;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {
	
	@MockBean
	private PtaskDao ptaskDao;
	
	@MockBean
	private TaskDao taskDao;
	
	@MockBean
	private ProjectDao projectDao;
	
	@MockBean
	private UserDao userDao;
	
	@Autowired
	private TaskService taskService;
	
	@Test
	public void TestfetchallParentTasks(){
		List<Ptask> ptlst = new ArrayList<Ptask>();
		ptlst.add(getPtask1());
		ptlst.add(getPtask2());
		when(ptaskDao.findAll()).thenReturn(ptlst);
		assertThat(taskService.getallParentTasks(), hasSize(2));
	}
	
	@Test
	public void TestfetchallTasks(){
		List<Task> tlst = new ArrayList<Task>();
		tlst.add(getTask1());
		tlst.add(getTask2());
		when(taskDao.findAll()).thenReturn(tlst);
		when(ptaskDao.findByPid(getTask1().getPid())).thenReturn(getPtask1());
		when(projectDao.findByProjectid(getTask1().getProjectid())).thenReturn(getProject1());
		when(userDao.findByTid(getTask1().getTid())).thenReturn(getUser1());
		when(userDao.findByTid(getTask2().getTid())).thenReturn(getUser2());
		assertThat(taskService.getallTasks(), hasSize(2));
		assertThat(taskService.getallTasks(), hasItems(isA(TaskOutput.class)));
	}
	
	public Ptask getPtask1(){
		Ptask p1 = new Ptask();
		p1.setPid(1);
		p1.setPtask("UI Layer");
		return p1;
	}
	public Ptask getPtask2(){
		Ptask p1 = new Ptask();
		p1.setPid(2);
		p1.setPtask("Middleware Layer");
		return p1;
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
	public Task getTask1(){
		Task t1 = new Task();
		
		t1.setTid(1);
		t1.setTask("UI Build pages");
		t1.setPid(1);
		t1.setPriority(10);
		t1.setProjectid(1);		
		t1.setStatus('N');
		LocalDate d1 = LocalDate.parse("2019-03-30", DateTimeFormatter.ISO_LOCAL_DATE);
	    t1.setSdate(d1);
		LocalDate d2 = LocalDate.parse("2019-03-31", DateTimeFormatter.ISO_LOCAL_DATE);
        t1.setEdate(d2);
		return t1;
	}
	public Task getTask2(){
		Task t1 = new Task();		
		t1.setTid(2);
		t1.setTask("UI Bootstrap pages");
		t1.setPid(1);
		t1.setPriority(10);
		t1.setProjectid(1);		
		t1.setStatus('N');
		LocalDate d1 = LocalDate.parse("2019-03-30", DateTimeFormatter.ISO_LOCAL_DATE);
	    t1.setSdate(d1);
		LocalDate d2 = LocalDate.parse("2019-03-31", DateTimeFormatter.ISO_LOCAL_DATE);
        t1.setEdate(d2);
        
		return t1;
	}
}
