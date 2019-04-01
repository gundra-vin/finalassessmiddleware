package com.iiht.assessment.ProjectManager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iiht.assessment.ProjectManager.beans.ProjectInput;
import com.iiht.assessment.ProjectManager.beans.ProjectOutput;
import com.iiht.assessment.ProjectManager.beans.TaskCount;
import com.iiht.assessment.ProjectManager.dao.ProjectDao;
import com.iiht.assessment.ProjectManager.dao.TaskDao;
import com.iiht.assessment.ProjectManager.dao.UserDao;
import com.iiht.assessment.ProjectManager.entity.Project;
import com.iiht.assessment.ProjectManager.entity.Users;

@Service
@Transactional
public class ProjectService {

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private TaskDao taskDao;

	@Autowired
	private UserDao userDao;

	public List<Users> getallUsers() {
		return userDao.findAll();
	}

	public List<Project> getallProjects() {
		return projectDao.findAll();
	}

	public List<ProjectOutput> getallProjectDetails() {
		List<Project> pl = getallProjects();
		List<TaskCount> tottsk = taskDao.getTotalTasks();
		List<TaskCount> comptsk = taskDao.getTotalCompletedTasks();
		List<Users> usrs = getallUsers();
		List<ProjectOutput> pol = new ArrayList<ProjectOutput>();

		for (Project pj : pl) {
			System.out.println(pj);
			ProjectOutput po = new ProjectOutput();
			po.setComptsk(0);
			po.setTottsk(0);
			po.setEmpid(0);
			po.setProjectid(pj.getProjectid());
			po.setProject(pj.getProject());
			po.setSdate(pj.getSdate());
			po.setEdate(pj.getEdate());
			po.setPriority(pj.getPriority());

			TaskCount tc1 = tottsk.stream().filter(tskcnt -> pj.getProjectid() == tskcnt.getProjectid()).findAny()
					.orElse(null);
			if (tc1 != null) {
				po.setTottsk(tc1.getCount());
			}
			TaskCount tc2 = comptsk.stream().filter(tskcnt -> pj.getProjectid() == tskcnt.getProjectid()).findAny()
					.orElse(null);
			if (tc2 != null) {
				po.setComptsk(tc2.getCount());
			}
			Users usr = usrs.stream().filter(u -> pj.getProjectid() == u.getProjectid()).findAny().orElse(null);
			if (usr != null) {
				po.setEmpid(usr.getEmpid());
			}
			pol.add(po);
		}
		
		return pol;
	}

	public Project addProject(ProjectInput pi) {

		Project p = new Project();
		p.setProjectid(projectDao.getmaxProjectId() + 1);
		p.setProject(pi.getProject());
		p.setSdate(pi.getSdate());
		p.setEdate(pi.getEdate());
		p.setPriority(pi.getPriority());
		Project np = projectDao.saveAndFlush(p);
		int rowsupdated = userDao.updateProjectID(np.getProjectid(), pi.getEmpid());
		System.out.println("Rows Updated:" +rowsupdated);
		return np;
	}

	public Project updProject(ProjectInput pu) {

		Project p = new Project();
		p.setProjectid(pu.getProjectid());
		p.setProject(pu.getProject());
		p.setSdate(pu.getSdate());
		p.setEdate(pu.getEdate());
		p.setPriority(pu.getPriority());
		Project np = projectDao.saveAndFlush(p);
		return np;
	}

	public String delProject(int delproid) {

		projectDao.deleteById(delproid);

		return "deleted";
	}
}
