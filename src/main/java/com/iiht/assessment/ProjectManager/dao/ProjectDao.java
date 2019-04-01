package com.iiht.assessment.ProjectManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iiht.assessment.ProjectManager.entity.Project;

public interface ProjectDao extends JpaRepository<Project, Integer> {
	
	public Project findByProject(@Param("project") String project);
	public Project findByProjectid(@Param("projectid") int projectid);
	
	@Query("SELECT max(projectid) FROM Project")
	Integer getmaxProjectId();

}
