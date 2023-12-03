package edu.colostate.cs415.dto;

import java.util.Objects;

import edu.colostate.cs415.model.ProjectSize;
import edu.colostate.cs415.model.ProjectStatus;

public class ProjectDTO {
    private String name;
    private ProjectSize size;
    private ProjectStatus status;
    private String[] workers;
    private String[] qualifications;
    private String[] missingQualifications;

    public ProjectDTO() {
    }

    public ProjectDTO(String name, ProjectSize size, ProjectStatus status, String[] workers, String[] qualifications,
            String[] missingQualifications) {
        this.name = name;
        this.size = size;
        this.status = status;
        this.workers = workers;
        this.qualifications = qualifications;
        this.missingQualifications = missingQualifications;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectSize getSize() {
        return this.size;
    }

    public void setSize(ProjectSize size) {
        this.size = size;
    }

    public ProjectStatus getStatus() {
        return this.status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public String[] getWorkers() {
        return this.workers;
    }

    public void setWorkers(String[] workers) {
        this.workers = workers;
    }

    public String[] getQualifications() {
        return this.qualifications;
    }

    public void setQualifications(String[] qualifications) {
        this.qualifications = qualifications;
    }

    public String[] getMissingQualifications() {
        return this.missingQualifications;
    }

    public void setMissingQualifications(String[] missingQualifications) {
        this.missingQualifications = missingQualifications;
    }

    public ProjectDTO name(String name) {
        setName(name);
        return this;
    }

    public ProjectDTO size(ProjectSize size) {
        setSize(size);
        return this;
    }

    public ProjectDTO status(ProjectStatus status) {
        setStatus(status);
        return this;
    }

    public ProjectDTO workers(String[] workers) {
        setWorkers(workers);
        return this;
    }

    public ProjectDTO qualifications(String[] qualifications) {
        setQualifications(qualifications);
        return this;
    }

    public ProjectDTO missingQualifications(String[] missingQualifications) {
        setMissingQualifications(missingQualifications);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProjectDTO)) {
            return false;
        }
        ProjectDTO projectDTO = (ProjectDTO) o;
        return Objects.equals(name, projectDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", size='" + getSize() + "'" +
                ", status='" + getStatus() + "'" +
                ", workers='" + getWorkers() + "'" +
                ", qualifications='" + getQualifications() + "'" +
                ", missingQualifications='" + getMissingQualifications() + "'" +
                "}";
    }
}
