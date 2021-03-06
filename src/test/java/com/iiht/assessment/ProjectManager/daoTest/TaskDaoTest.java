package com.iiht.assessment.ProjectManager.daoTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Every.everyItem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.iiht.assessment.ProjectManager.beans.TaskCount;
import com.iiht.assessment.ProjectManager.dao.PtaskDao;
import com.iiht.assessment.ProjectManager.dao.TaskDao;
import com.iiht.assessment.ProjectManager.entity.Ptask;
import com.iiht.assessment.ProjectManager.entity.Task;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskDaoTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	private PtaskDao ptaskDao;
	
	@Test
	public void TestfetchTaskDetails(){
		
		Ptask p1 = getPtask1();
		Ptask p2 = getPtask2();
		this.entityManager.persist(p1);
		this.entityManager.persist(p2);
		
		Task t1 = getTask1();
		Task t2 = getTask2();
		this.entityManager.persist(t1);
		this.entityManager.persist(t2);
		List<TaskCount> totlst = taskDao.getTotalTasks();
		List<TaskCount> comlst = taskDao.getTotalCompletedTasks();
		assertThat(totlst,hasSize(2));
		assertThat(comlst,hasSize(0));
	}
	@Test
	public void TestupdateTaskStatus(){
		
		Ptask p1 = getPtask1();
		Ptask p2 = getPtask2();
		this.entityManager.persist(p1);
		this.entityManager.persist(p2);
		
		Task t1 = getTask1();
		Task t2 = getTask2();
		this.entityManager.persist(t1);
		this.entityManager.persist(t2);
		taskDao.updateTaskStatus(1);
		List<TaskCount> comlst = taskDao.getTotalCompletedTasks();	
	
		assertThat(comlst,hasSize(1));
	}
	
	@Test
	public void TestPropertyPTask(){
		/* This method tests whether the result fetched by join query has property 
		 * parent task and it's value.
		 */
		
		
		Ptask p1 = getPtask1();
		this.entityManager.persist(p1);
		
		
		Task t1 = getTask1();
		Task t2 = getTask2();
		this.entityManager.persist(t1);
		this.entityManager.persist(t2);
		
		
		List<Task> tlst = taskDao.findAll();
		
		System.out.println(tlst);
		assertThat(tlst,everyItem(hasProperty("priority", equalTo(6))));
	}
	
	@Test
	public void TestMaxIdPtask(){
		Ptask p1 = getPtask1();
		Ptask p2 = getPtask2();
		this.entityManager.persist(p1);
		this.entityManager.persist(p2);
		
		int id = ptaskDao.getmaxParentTaskID();
		assertThat(id,equalTo(2));
	}
	@Test
	public void TestMaxIdTask(){
		Ptask p1 = getPtask1();
		Ptask p2 = getPtask2();
		this.entityManager.persist(p1);
		this.entityManager.persist(p2);
		Task t1 = getTask1();
		Task t2 = getTask2();
		this.entityManager.persist(t1);
		this.entityManager.persist(t2);
		
		int id = taskDao.getmaxTaskId();
		assertThat(id,equalTo(2));
	}
	@After
	public void cleanup() {
		this.entityManager.clear();
	}
	
	public Ptask getPtask1(){
		Ptask p1 = new Ptask();
		p1.setPid(1);
		p1.setPtask("UI Tasks");
		return p1;
	}
	public Ptask getPtask2(){
		Ptask p1 = new Ptask();
		p1.setPid(2);
		p1.setPtask("Middleware");
		return p1;
	}

	public Task getTask1(){
		
		Task t1 = new Task();
		t1.setTid(1);
		t1.setPid(1);
		t1.setTask("UI Build Pages");
		t1.setPriority(6);
		LocalDate d1 = LocalDate.parse("2019-01-18", DateTimeFormatter.ISO_LOCAL_DATE);
		t1.setSdate(d1);
		LocalDate d2 = LocalDate.parse("2019-01-19", DateTimeFormatter.ISO_LOCAL_DATE);
		t1.setEdate(d2);
		t1.setStatus('N');
		t1.setProjectid(1);		
		return t1;
		
	}
	
	public Task getTask2(){
		Task t2 = new Task();
		t2.setTid(2);
		t2.setPid(1);
		t2.setTask("UI Jasmine Unit Testing");
		t2.setPriority(6);
		LocalDate d3 = LocalDate.parse("2019-01-18", DateTimeFormatter.ISO_LOCAL_DATE);
		t2.setSdate(d3);
		LocalDate d4 = LocalDate.parse("2019-01-19", DateTimeFormatter.ISO_LOCAL_DATE);
		t2.setEdate(d4);
		t2.setStatus('N');
		t2.setProjectid(2);
		return t2;
		
	}


}
