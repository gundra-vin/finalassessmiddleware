package com.iiht.assessment.ProjectManager.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.iiht.assessment.ProjectManager.dao.UserDao;
import com.iiht.assessment.ProjectManager.entity.Ptask;
import com.iiht.assessment.ProjectManager.entity.Users;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@MockBean
	private UserDao userDao;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void TestfetchallUsers(){
		List<Users> ulst = new ArrayList<Users>();
		ulst.add(getUser1());
		ulst.add(getUser2());
		when(userDao.getallUsersDetails()).thenReturn(ulst);
		assertThat(userService.getallUsers(), hasSize(2));
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
