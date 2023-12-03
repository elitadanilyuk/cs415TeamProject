package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Set;
import edu.colostate.cs415.dto.WorkerDTO;

public class Worker {

	public static final int MAX_WORKLOAD = 12;

	private String name;
	private double salary;
	private Set<Project> projects;
	private Set<Qualification> qualifications;

	public Worker(String name, Set<Qualification> qualifications, double salary) {
		if(name == null || name.trim().isEmpty() || qualifications == null || qualifications.size() < 1 || salary < 0.0){
			throw new IllegalArgumentException();
		}
		this.name = name;
		Set<Qualification> qualClone = new HashSet<>(qualifications);
		this.qualifications = qualClone;
		this.salary = salary;
		this.projects = new HashSet<Project>();
	}

	@Override
	public boolean equals(Object other) {
		if(other == null || other.getClass() != this.getClass()){
			return false;
		}
		return this.name.equals(((Worker)other).getName());
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return this.name + ":" + this.projects.size() + ":" + this.qualifications.size() + ":" + ((int)this.salary);
	}

	public String getName() {
		return this.name;
	}

	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
		if(salary < 0){
			throw new IllegalArgumentException();
		}
		this.salary = salary;
	}

	public Set<Qualification> getQualifications() {
		Set<Qualification> copy = new HashSet<Qualification>(qualifications);
		return copy;
	}

	// NOTE: caller's responsibility to ensure that this qualification is from the company's set of qualifications
	public void addQualification(Qualification qualification) {
		if(qualification == null){
			throw new IllegalArgumentException();
		}
		qualifications.add(qualification);
	}

	public Set<Project> getProjects() {
		Set<Project> projectsClone = new HashSet<>(projects);
		return projectsClone;
	}
	
	// NOTE: caller's responsibility to check if the project can be added to the worker and also to ensure that the worker is added to the project
	public void addProject(Project project) {
		if(project == null){
			throw new IllegalArgumentException();
		}
		projects.add(project);
	}

	public void removeProject(Project project) {
		if(project == null){
			throw new IllegalArgumentException();
		}
		projects.remove(project);
	}

	public int getWorkload() {
		int workload = 0;
		for(Project project: projects){
			if(project.getStatus() != ProjectStatus.FINISHED){
				workload += project.getSize().getValue();
			}
		}
		return workload;
	}

	public boolean willOverload(Project project) {
		if(project == null || this.projects.contains(project)){
			return false;
		}
		return (project.getSize().getValue() + getWorkload()) > MAX_WORKLOAD;
	}

	public boolean isAvailable() {
		return getWorkload() < MAX_WORKLOAD;
	}

	public WorkerDTO toDTO() {
		Set<String> projectNames = new HashSet<String>();
		Set<String> qualNames = new HashSet<String>();

		for(Project p : projects){
			projectNames.add(p.getName());
		}
		for(Qualification q : qualifications){
			qualNames.add(q.toString());
		}
		
		String[] pName = projectNames.toArray(new String[0]);
		String[] qNames = qualNames.toArray(new String[0]);
		WorkerDTO dto = new WorkerDTO(name, salary, getWorkload(), pName, qNames);
		return dto;
	}
}
