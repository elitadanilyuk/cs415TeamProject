package edu.colostate.cs415.dto;

import java.util.Objects;

public class AssignmentDTO {
    private String worker;
    private String project;

    public AssignmentDTO() {
    }

    public AssignmentDTO(String worker, String project) {
        this.worker = worker;
        this.project = project;
    }

    public String getWorker() {
        return this.worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getProject() {
        return this.project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public AssignmentDTO worker(String worker) {
        setWorker(worker);
        return this;
    }

    public AssignmentDTO project(String project) {
        setProject(project);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AssignmentDTO)) {
            return false;
        }
        AssignmentDTO assignmentDTO = (AssignmentDTO) o;
        return Objects.equals(worker, assignmentDTO.worker) && Objects.equals(project, assignmentDTO.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(worker, project);
    }

    @Override
    public String toString() {
        return "{" +
                " worker='" + getWorker() + "'" +
                ", project='" + getProject() + "'" +
                "}";
    }
}
