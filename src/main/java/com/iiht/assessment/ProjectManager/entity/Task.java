package com.iiht.assessment.ProjectManager.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Task")
public class Task {
	

	@Id
	@Column(name="Task_ID")
	private int tid;
	
	@Column(name="Parent_ID")
	private int pid;
	
	@Column(name="Project_ID")
	private int projectid;
	
	@Column(name="Task")
	private String task;
	
	@Column(name="Start_Date")
	private LocalDate sdate;
	
	@Column(name="End_Date")
	private LocalDate edate;
	
	@Column(name="Priority")
	private int priority;
	
	@Column(name="Status")
	private char status;
	


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Parent_ID", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Ptask ptask;
	
	public Ptask getPtask() {
		return ptask;
	}

	public void setPtask(Ptask ptask) {
		this.ptask = ptask;
	}
	public Task(){
		
	}
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Project_ID", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Project project;
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}*/

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public LocalDate getSdate() {
		return sdate;
	}

	public void setSdate(LocalDate sdate) {
		this.sdate = sdate;
	}

	public LocalDate getEdate() {
		return edate;
	}

	public void setEdate(LocalDate edate) {
		this.edate = edate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	
	public Task(int tid, int pid, int projectid, String task, LocalDate sdate, LocalDate edate, int priority,
			char status, Ptask ptask) {
		super();
		this.tid = tid;
		this.pid = pid;
		this.projectid = projectid;
		this.task = task;
		this.sdate = sdate;
		this.edate = edate;
		this.priority = priority;
		this.status = status;
		this.ptask = ptask;
	}

	

}

