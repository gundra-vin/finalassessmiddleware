package com.iiht.assessment.ProjectManager.daoTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.iiht.assessment.ProjectManager.dao.ProjectDao;
import com.iiht.assessment.ProjectManager.entity.Project;
import com.iiht.assessment.ProjectManager.entity.Users;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectDaoTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Test
	public void TestgetmaxProjectId(){
		
		Project p1 = getProject1();
		Project p2 = getProject2();
		this.entityManager.persist(p1);
		this.entityManager.persist(p2);		
		int pjid = projectDao.getmaxProjectId();
		assertThat(pjid,equalTo(2));		
	}
	@Test
	public void TestfindByProject(){
		
		Project p1 = getProject1();
		Project p2 = getProject2();
		this.entityManager.persist(p1);
		this.entityManager.persist(p2);		
		Project pj = projectDao.findByProject("Capsule Project");
		assertThat(pj,hasProperty("priority",equalTo(10)));		
	}
	@Test
	public void TestfindByProjectId(){
		
		Project p1 = getProject1();
		Project p2 = getProject2();
		this.entityManager.persist(p1);
		this.entityManager.persist(p2);		
		Project pj = projectDao.findByProjectid(1);
		assertThat(pj,hasProperty("project",equalTo("Capsule Project")));		
	}
	@After
	public void cleanup() {
		this.entityManager.clear();
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

}
