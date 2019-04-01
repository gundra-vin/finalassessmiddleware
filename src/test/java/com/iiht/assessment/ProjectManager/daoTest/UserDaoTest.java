package com.iiht.assessment.ProjectManager.daoTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Every.everyItem;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.iiht.assessment.ProjectManager.beans.TaskCount;
import com.iiht.assessment.ProjectManager.dao.UserDao;
import com.iiht.assessment.ProjectManager.entity.Ptask;
import com.iiht.assessment.ProjectManager.entity.Task;
import com.iiht.assessment.ProjectManager.entity.Users;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDaoTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void TestUserDetails(){
		
		Users u1 = getUser1();
		Users u2 = getUser2();
		this.entityManager.persist(u1);
		this.entityManager.persist(u2);
		List<Users> ulst = userDao.getallUsersDetails();	
		assertThat(ulst,hasSize(2));
	}
	@Test
	public void TestgetmaxUserId(){
		
		Users u1 = getUser1();
		Users u2 = getUser2();
		this.entityManager.persist(u1);
		this.entityManager.persist(u2);		
		int usrid = userDao.getmaxusrid();
		assertThat(usrid,equalTo(2));		
	}
	@Test
	public void TestUpdateProjectId(){
		
		Users u1 = getUser1();
		Users u2 = getUser2();
		this.entityManager.persist(u1);
		this.entityManager.persist(u2);		
		int rowupd = userDao.updateProjectID(1, 366123);
		assertThat(rowupd, equalTo(1));		
	}
	@Test
	public void TestUpdateTaskId(){
		
		Users u1 = getUser1();
		Users u2 = getUser2();
		this.entityManager.persist(u1);
		this.entityManager.persist(u2);		
		int rowupd = userDao.updateTaskID(2, 366123);
		assertThat(rowupd, equalTo(1));		
	}
	@Test
	public void TestfindByTaskId(){
		
		Users u1 = getUser1();
		Users u2 = getUser2();
		this.entityManager.persist(u1);
		this.entityManager.persist(u2);		
		Users u = userDao.findByTid(2);
		assertThat(u,hasProperty("empid", equalTo(366123)));
			
	}
	@After
	public void cleanup() {
		this.entityManager.clear();
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

}
