package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Set;

import edu.colostate.cs415.dto.QualificationDTO;

public class Qualification {

	private String description;
	private Set<Worker> workers;

	public Qualification(String description) {
		if(description ==null  ||  description.trim().isEmpty()){
			throw new IllegalArgumentException();
		}
		this.description = description;
		this.workers = new HashSet<Worker>();
	}

	@Override
	public boolean equals(Object other) {
		if(other == null || other.getClass() != this.getClass()){
			return false;
		}
		return this.description.equals(((Qualification)other).toString());
	}
 
	@Override
	public int hashCode() {
		return description.hashCode();
	}

	@Override
	public String toString() {
		return description;
	}

	public Set<Worker> getWorkers() {
		Set<Worker> workersClone = new HashSet<>(workers);
		return workersClone;
	}

	public void addWorker(Worker worker) {
		if(worker == null){
			throw new IllegalArgumentException();
		}
		workers.add(worker);
	}

	public void removeWorker(Worker worker) {
		if(worker == null){
			throw new IllegalArgumentException();
		}
		workers.remove(worker);
	}

	public QualificationDTO toDTO() {
		Set<String> workerStrings = new HashSet<String>();
		for (Worker w : workers) {
			workerStrings.add(w.getName());
		}
		String[] workerArray = workerStrings.toArray(new String[0]);
		QualificationDTO qdto = new QualificationDTO(this.description, workerArray);
		return qdto;
	}
 
}
