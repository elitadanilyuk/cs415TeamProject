package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Set;

import edu.colostate.cs415.dto.ProjectDTO;
import spark.utils.StringUtils;

public class Project {

	private String name;
	private ProjectSize size;
	private ProjectStatus status;
	private Set<Worker> workers;
	private Set<Qualification> qualifications;

	public Project(String name, Set<Qualification> qs, ProjectSize size) throws IllegalArgumentException {
		if (name == null || qs == null || size == null || name.isEmpty() || qs.isEmpty() || StringUtils.isBlank(name)) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		Set<Qualification> qualClone = new HashSet<>(qs);
		this.qualifications = qualClone;
		this.size = size;
		this.status = ProjectStatus.PLANNED;
		this.workers = new HashSet<>();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || other.getClass() != this.getClass()) {
			return false;
		}
		return this.name.equals(((Project)other).getName());
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return this.name + ":" + this.workers.size() + ":" + this.status;
	}

	public String getName() {
		return this.name;
	}

	public ProjectSize getSize() {
		return this.size;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		if (status == null) {
			throw new IllegalArgumentException();
		}
		this.status = status;
	}

	public void addWorker(Worker worker) {
		if(worker == null){
			throw new IllegalArgumentException();
		}
		this.workers.add(worker);
	}

	public void removeWorker(Worker worker) {
		if(worker == null){
			throw new IllegalArgumentException();
		}
		workers.remove(worker);
	}

	public Set<Worker> getWorkers() {
		Set<Worker> workersClone = new HashSet<>(workers);
		return workersClone;
	}

	public void removeAllWorkers() {
		workers.clear();
	}

	public Set<Qualification> getRequiredQualifications() {
		Set<Qualification> copy = new HashSet<Qualification>(qualifications);
		return copy;
	}

	public void addQualification(Qualification qualification) {
		if(qualification == null){
			throw new IllegalArgumentException();
		}
		if(status == ProjectStatus.ACTIVE){
			qualifications.add(qualification);
			if(!getMissingQualifications().isEmpty()){
				status = ProjectStatus.SUSPENDED;
			}
		}
		else{
			qualifications.add(qualification);
		}
	}

	public Set<Qualification> getMissingQualifications() {
		Set<Qualification> presentQualifications = new HashSet<Qualification>();
		Set<Qualification> missingQualifications = new HashSet<Qualification>(qualifications);
		for(Worker w : workers){
			presentQualifications.addAll(w.getQualifications());
		}
		missingQualifications.removeAll(presentQualifications);
		return missingQualifications;
	}

	public boolean isHelpful(Worker worker) {
		if(worker == null){
			return false;
		}
		for(Qualification mq : getMissingQualifications()){
			if(worker.getQualifications().contains(mq)){
				return true;
			} else {
				continue;
			}
		}
		return false;
	}

	public ProjectDTO toDTO() {
		Set<String> workerStrings = new HashSet<String>();
		Set<String> qualStrings = new HashSet<String>();

		for (Worker w : workers) {
			workerStrings.add(w.getName());
		}

		for (Qualification q : qualifications) {
			qualStrings.add(q.toString());
		}

		String[] workersArray = workerStrings.toArray(new String[0]);
		String[] qualArray = qualStrings.toArray(new String[0]);
		Set<Qualification> quals = getMissingQualifications();
		
		String[] missingQualArray = new String[quals.size()];
		int index = 0;
		for(Qualification qual: quals){
				missingQualArray[index] = qual.toString();
				index++;
		}
		
		ProjectDTO pdto = new ProjectDTO(name, size, status, workersArray, qualArray, missingQualArray);
		
		return pdto;
	}
}
