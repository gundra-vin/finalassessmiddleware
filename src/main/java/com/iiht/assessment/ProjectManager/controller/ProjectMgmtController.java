package com.iiht.assessment.ProjectManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.assessment.ProjectManager.beans.ProjectInput;
import com.iiht.assessment.ProjectManager.beans.ProjectOutput;
import com.iiht.assessment.ProjectManager.beans.TaskInput;
import com.iiht.assessment.ProjectManager.beans.TaskOutput;
import com.iiht.assessment.ProjectManager.entity.Project;
import com.iiht.assessment.ProjectManager.entity.Ptask;
import com.iiht.assessment.ProjectManager.entity.Task;
import com.iiht.assessment.ProjectManager.entity.Users;
import com.iiht.assessment.ProjectManager.service.ProjectService;
import com.iiht.assessment.ProjectManager.service.TaskService;
import com.iiht.assessment.ProjectManager.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/pmgmt")
public class ProjectMgmtController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/usr")
	public List<Users> getallUsers(){
		return userService.getallUsers();
	}
	
	@PostMapping("/usr")
	public Users addUsers(@RequestBody Users uio){
		
		return userService.addUser(uio);
		
	}
	
	@PutMapping("/usr")
	public Users updUsers(@RequestBody Users uio){
		
		return userService.updUser(uio);
		
	}
	@DeleteMapping("/usr/{delid}")
	public List<Users> delUsers(@PathVariable("delid") int delid){
		return userService.delUser(delid);
	}
	@GetMapping("/pj")
	public List<ProjectOutput> getallProjectDetails(){
		return projectService.getallProjectDetails();
	}
	
	@PostMapping("/pj")
	public Project addProject(@RequestBody ProjectInput pi){
		return projectService.addProject(pi);
	}
	
	@PostMapping("/ptsk")
	public Ptask addPTask(@RequestBody String ptask){
		return taskService.addParentTask(ptask);
	}
	@GetMapping("/tsk")
	public List<TaskOutput> getallTasks(){
		return taskService.getallTasks();
	}
	
	@PostMapping("/tsk")
	public Task addTask(@RequestBody TaskInput ti){
		return taskService.addTask(ti);
	}
	@PostMapping("/tsksts")
	public void updTaskStatus(@RequestBody TaskOutput ti){
		 taskService.updTaskStatus(ti);
	}
}
