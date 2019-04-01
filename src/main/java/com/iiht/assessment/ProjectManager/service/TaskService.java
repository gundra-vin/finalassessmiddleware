package com.iiht.assessment.ProjectManager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iiht.assessment.ProjectManager.beans.TaskInput;
import com.iiht.assessment.ProjectManager.beans.TaskOutput;
import com.iiht.assessment.ProjectManager.dao.ProjectDao;
import com.iiht.assessment.ProjectManager.dao.PtaskDao;
import com.iiht.assessment.ProjectManager.dao.TaskDao;
import com.iiht.assessment.ProjectManager.dao.UserDao;
import com.iiht.assessment.ProjectManager.entity.Project;
import com.iiht.assessment.ProjectManager.entity.Ptask;
import com.iiht.assessment.ProjectManager.entity.Task;
import com.iiht.assessment.ProjectManager.entity.Users;

@Service
@Transactional
public class TaskService {
	
	@Autowired
	private PtaskDao ptaskDao;
	
	@Autowired 
	private TaskDao taskDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private UserDao userDao;
	
	public List<Ptask> getallParentTasks(){
		return ptaskDao.findAll();
	}
	
	public List<TaskOutput> getallTasks(){
		List<TaskOutput> tos = new ArrayList<TaskOutput>();
		List<Task> tsks = taskDao.findAll();
		for(Task tsk: tsks){
			TaskOutput to = new TaskOutput();
			to.setTid(tsk.getTid());
			to.setTask(tsk.getTask());
			to.setPriority(tsk.getPriority());
			to.setEdate(tsk.getEdate());
			to.setSdate(tsk.getSdate());
			to.setStatus(tsk.getStatus());
			Ptask pt = ptaskDao.findByPid(tsk.getPid());
			to.setPtask(pt.getPtask());
			Project pj = projectDao.findByProjectid(tsk.getProjectid());
			to.setProject(pj.getProject());
			Users usr = userDao.findByTid(tsk.getTid());
			to.setEmpid(usr.getEmpid());
			tos.add(to);
		}
		return tos;
	}
	
	public Ptask addParentTask(String ptask){
		Ptask np = new Ptask();
		np.setPid(ptaskDao.getmaxParentTaskID() + 1);
		np.setPtask(ptask);
		Ptask ptsk = ptaskDao.saveAndFlush(np);
		return ptsk;		
		
	}
	
	public Task addTask(TaskInput ti){
		
		Task nt = new Task();
		nt.setTid(taskDao.getmaxTaskId() + 1);
		Ptask pt = ptaskDao.findByPtask(ti.getParenttask().trim());
		Project p = projectDao.findByProject(ti.getProject().trim());
		nt.setPid(pt.getPid());
		nt.setProjectid(p.getProjectid());
		nt.setTask(ti.getTask());
		nt.setSdate(ti.getSdate());
		nt.setEdate(ti.getEdate());
		nt.setPriority(ti.getPriority());
		nt.setStatus('N');
		Task ntt = taskDao.saveAndFlush(nt);
		int rowsupd = userDao.updateTaskID(ntt.getTid(), ti.getEmpid());
		System.out.println("Rows updated:" +rowsupd);
		return ntt;
			
	}
	
	public void updTaskStatus(TaskOutput ti){
		System.out.println(ti.getTid());
		int rowsupd = taskDao.updateTaskStatus(ti.getTid());
		System.out.println("Rows Updated:" +rowsupd);
	}

}
