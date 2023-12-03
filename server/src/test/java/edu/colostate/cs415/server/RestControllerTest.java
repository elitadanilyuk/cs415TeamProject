package edu.colostate.cs415.server;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.hc.client5.http.HttpResponseException;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.gson.Gson;

import edu.colostate.cs415.db.DBConnector;
import edu.colostate.cs415.dto.*;
import edu.colostate.cs415.model.*;

public class RestControllerTest {

    private Gson gson = new Gson();
    private static DBConnector dbConnector = mock(DBConnector.class);
    private static RestController restController = new RestController(4567, dbConnector);
    private static Company company;

    @Rule public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void init(){
        when(dbConnector.loadCompanyData()).thenAnswer((i) -> company);
    }

    @Test
    public void testGetProjects1() throws IOException {
        // No workers assigned to project, has missing qualifications
            company = new Company("Company 1");
            Qualification java = company.createQualification("Java");
            Set<Qualification> quals = new HashSet<Qualification>();
            quals.add(java);
            company.createProject("Moon mission", quals, ProjectSize.BIG);
            restController.start();
            ProjectDTO[] projects = gson.fromJson(
                            Request.get("http://localhost:4567/api/projects").execute().returnContent().asString(),
                            ProjectDTO[].class);

            assertEquals(1, projects.length);
            assertEquals("Moon mission", projects[0].getName());
            assertEquals(ProjectSize.BIG, projects[0].getSize());
            assertEquals(ProjectStatus.PLANNED, projects[0].getStatus());
            assertEquals("Java", projects[0].getMissingQualifications()[0]);
            assertEquals("Java", projects[0].getQualifications()[0]);
            assertEquals(0, projects[0].getWorkers().length);
    }

    @Test
    public void testGetProjects2() throws IOException {
        // No projects returns empty list
            company = new Company("Company 1");
            restController.start();
            ProjectDTO[] projects = gson.fromJson(
                            Request.get("http://localhost:4567/api/projects").execute().returnContent().asString(),
                            ProjectDTO[].class);
            
            assertEquals(0, projects.length);
    }

    @Test
    public void testGetProjects3() throws IOException {
        // Project with worker, no missing qualifications
            company = new Company("Company 1");
            Qualification java = company.createQualification("Java");
            Set<Qualification> quals = new HashSet<Qualification>();
            quals.add(java);
            company.createProject("Moon mission", quals, ProjectSize.BIG);
            Worker worker = new Worker("w1", quals, 10);
            Project project =company.getProjects().iterator().next();
            company.start(project);
            company.createWorker("w1", quals, 10);
            company.assign(worker, project);
            restController.start();
            ProjectDTO[] projects = gson.fromJson(
                            Request.get("http://localhost:4567/api/projects").execute().returnContent().asString(),
                            ProjectDTO[].class);
            
            assertEquals(1, projects.length);
            assertEquals("Moon mission", projects[0].getName());
            assertEquals(ProjectSize.BIG, projects[0].getSize());
            assertEquals(ProjectStatus.PLANNED, projects[0].getStatus());
            assertEquals(0, projects[0].getMissingQualifications().length);
            assertEquals("Java", projects[0].getQualifications()[0]);
            assertEquals("w1", projects[0].getWorkers()[0]);
    }


    @Test
    public void testGetProjects4() throws IOException {
        // Workers assigned to project, has missing qualifications, multiple projects
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Qualification python = company.createQualification("Python");
        Qualification c = company.createQualification("C");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        quals.add(python);
        quals.add(c);
        company.createProject("Moon mission", quals, ProjectSize.BIG);
        company.createProject("Teleportation", quals, ProjectSize.BIG);
        Worker worker = new Worker("w1", new HashSet<Qualification>(Arrays.asList(java)), 10);
        Worker worker2 = new Worker("w2", new HashSet<Qualification>(Arrays.asList(python)), 10);
        Project project =company.getProjects().iterator().next();
        company.start(project);
        company.createWorker("w1", new HashSet<Qualification>(Arrays.asList(java)), 10);
        company.createWorker("w2", new HashSet<Qualification>(Arrays.asList(python)), 10);
        company.assign(worker, project);
        company.assign(worker2, project);
        restController.start();
        ProjectDTO[] projects = gson.fromJson(
                        Request.get("http://localhost:4567/api/projects").execute().returnContent().asString(),
                        ProjectDTO[].class);
        
        assertEquals(2, projects.length);
        assertEquals("Moon mission", projects[0].getName());
        assertEquals("Teleportation", projects[1].getName());
        assertEquals(ProjectSize.BIG, projects[0].getSize());
        assertEquals(ProjectStatus.PLANNED, projects[0].getStatus());
        assertEquals("C", projects[0].getMissingQualifications()[0]);
        assertEquals(3, projects[0].getQualifications().length);
        assertEquals(2, projects[0].getWorkers().length);
    }

    @Test
    public void testGetProjects5() throws IOException {
        // Multiple workers assigned to project, no missing qualifications
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Qualification python = company.createQualification("Python");
        Qualification c = company.createQualification("C");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        quals.add(python);
        quals.add(c);
        company.createProject("Moon mission", quals, ProjectSize.BIG);
        company.createProject("Teleportation", quals, ProjectSize.BIG);
        Worker worker = new Worker("w1", new HashSet<Qualification>(Arrays.asList(java, c)), 10);
        Worker worker2 = new Worker("w2", new HashSet<Qualification>(Arrays.asList(python)), 10);
        Project project =company.getProjects().iterator().next();
        company.start(project);
        company.createWorker("w1", new HashSet<Qualification>(Arrays.asList(java, c)), 10);
        company.createWorker("w2", new HashSet<Qualification>(Arrays.asList(python)), 10);
        company.assign(worker, project);
        company.assign(worker2, project);
        restController.start();
        ProjectDTO[] projects = gson.fromJson(
                        Request.get("http://localhost:4567/api/projects").execute().returnContent().asString(),
                        ProjectDTO[].class);
        
        assertEquals(2, projects.length);
        assertEquals("Moon mission", projects[0].getName());
        assertEquals("Teleportation", projects[1].getName());
        assertEquals(ProjectSize.BIG, projects[0].getSize());
        assertEquals(ProjectStatus.PLANNED, projects[0].getStatus());
        assertEquals(0, projects[0].getMissingQualifications().length);
        assertEquals(3, projects[0].getQualifications().length);
        assertEquals(2, projects[0].getWorkers().length);
    }

    @Test
    public void testGetProject1() throws IOException {
        //Get a project with one worker and one qualification
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        
        Project project = company.createProject("P1", quals, ProjectSize.BIG);
        Worker worker = company.createWorker("W1", quals, 10);
        company.start(project);
        company.assign(worker, project);
        
        restController.start();
        ProjectDTO response = gson.fromJson(
                        Request.get("http://localhost:4567/api/projects/P1").execute().returnContent().asString(),
                        ProjectDTO.class);
        
        assertEquals("P1", response.getName());
        assertEquals(ProjectSize.BIG, response.getSize());
        assertEquals(0, response.getMissingQualifications().length);
        assertEquals(1, response.getQualifications().length);
        assertEquals(1, response.getWorkers().length);
    }

    @Test
    public void testGetProject2() throws IOException {
        //Return null when the company doesn't have the requested project
        company = new Company("Company 1");
        
        restController.start();
        ProjectDTO response = gson.fromJson(
                        Request.get("http://localhost:4567/api/projects/P1").execute().returnContent().asString(),
                        ProjectDTO.class);
        
        assertEquals(null, response);
    }

    @Test
    public void testPostProject1() throws IOException {;
        // Valid project returns OK
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        String body = "{ \"name\": \"P1\", \"qualifications\": [\"Java\"]," + 
        "\"size\": \"BIG\"}";
        restController.start();
        String response = gson.fromJson(
        Request.post("http://localhost:4567/api/projects/P1")
        .bodyByteArray(body.getBytes())
        .execute().returnContent().asString(), String.class);
        
        assertEquals("OK", response);
        assertEquals("P1", company.getProjects().iterator().next().getName());
    }

    @Test
    public void testPostProject2() throws IOException {
        // If a 0-length name is provided, a project isn't created
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        String body = "{ \"name\": \"\", \"qualifications\": [\"Java\"]," + 
         "\"size\": \"BIG\"}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        String response = gson.fromJson(
                        Request.post("http://localhost:4567/api/projects/P1")
                        .bodyByteArray(body.getBytes())
                        .execute().returnContent().asString(), String.class);
        
        
    }

    @Test
    public void testPostProject3() throws IOException {
        // If name is null, a project isn't created
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        String body = "{\"qualifications\": [\"Java\"]," + 
         "\"size\": \"BIG\"}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        String response = gson.fromJson(
                        Request.post("http://localhost:4567/api/projects/P1")
                        .bodyByteArray(body.getBytes())
                        .execute().returnContent().asString(), String.class);  
    }

    @Test
    public void testPostProject4() throws IOException {
        // If qualifications is empty, a project isn't created
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        String body = "{ \"name\": \"P1\", \"qualifications\": []," + 
         "\"size\": \"BIG\"}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        String response = gson.fromJson(
                        Request.post("http://localhost:4567/api/projects/P1")
                        .bodyByteArray(body.getBytes())
                        .execute().returnContent().asString(), String.class);  
    }

    @Test
    public void testPostProject5() throws IOException {
        // If qualifications is null, a project isn't created
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        String body = "{ \"name\": \"P1\"," + 
         "\"size\": \"BIG\"}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        String response = gson.fromJson(
                        Request.post("http://localhost:4567/api/projects/P1")
                        .bodyByteArray(body.getBytes())
                        .execute().returnContent().asString(), String.class);  
    }

    @Test
    public void testPostProject6() throws IOException {
        // If size is null, a project isn't created
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        String body = "{ \"name\": \"P1\", \"qualifications\": [\"Java\"]}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        String response = gson.fromJson(
                        Request.post("http://localhost:4567/api/projects/P1")
                        .bodyByteArray(body.getBytes())
                        .execute().returnContent().asString(), String.class);  
    }

    @Test
    public void testPostProject7() throws IOException {;
        // If the param name and the body name don't match, a project isn't created
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        String body = "{ \"name\": \"P1\", \"qualifications\": [\"Java\"]," + 
        "\"size\": \"BIG\"}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        String response = gson.fromJson(
                        Request.post("http://localhost:4567/api/projects/P2")
                        .bodyByteArray(body.getBytes())
                        .execute().returnContent().asString(), String.class);
    }

    @Test
    public void testPostWorker1() throws IOException {
        // Valid worker returns OK
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        company.createProject("Moon mission", quals, ProjectSize.BIG);
        String body = "{ \"name\": \"Daisy\", \"salary\": 150000.0," +
            "\"qualifications\": [\"Java\"]}";
        restController.start();
        String response = gson.fromJson(
                        Request.post("http://localhost:4567/api/workers/Daisy")
                        .bodyByteArray(body.getBytes())
                        .execute().returnContent().asString(), String.class);
        
        assertEquals("OK", response);
        assertEquals("Daisy", company.getEmployedWorkers().iterator().next().getName());
    }


    @Test
    public void testPostWorker2() throws IOException {
        // Param name doesn't match body name
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        company.createProject("Moon mission", quals, ProjectSize.BIG);
        String body = "{ \"name\": \"Daisy\", \"salary\": 150000.0," +
            "\"qualifications\": [\"Java\"]}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        Request.post("http://localhost:4567/api/workers/aisy")
                .bodyByteArray(body.getBytes())
                .execute().returnContent().asString();
    }

    @Test
    public void testPostWorker3() throws IOException {
        // Salary not supplied, no worker created
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        company.createProject("Moon mission", quals, ProjectSize.BIG);
        String body = "{ \"name\": \"Daisy\"," +
            "\"qualifications\": [\"Java\"]}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        Request.post("http://localhost:4567/api/workers/Daisy")
                .bodyByteArray(body.getBytes())
                .execute().returnContent().asString();
    }


    @Test
    public void testPostWorker4() throws IOException {
        // Quals not supplied, no worker created
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        company.createProject("Moon mission", quals, ProjectSize.BIG);
        String body = "{ \"name\": \"Daisy\", \"salary\": 150000.0";
        thrown.expect(HttpResponseException.class);
        restController.start();
        Request.post("http://localhost:4567/api/workers/Daisy")
                .bodyByteArray(body.getBytes())
                .execute().returnContent().asString();
    }

    @Test
    public void testPostWorker5() throws IOException {
        // Quals supplied but empty, no worker created
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        company.createProject("Moon mission", quals, ProjectSize.BIG);
        String body = "{ \"name\": \"Daisy\", \"salary\": 150000.0," +
            "\"qualifications\": []}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        Request.post("http://localhost:4567/api/workers/Daisy")
                    .bodyByteArray(body.getBytes())
                    .execute().returnContent().asString();

        assertEquals(0, company.getEmployedWorkers().size());
    }

    @Test
    public void testPostWorker6() throws IOException {
        // Salary negative, no worker created
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        company.createProject("Moon mission", quals, ProjectSize.BIG);
        String body = "{ \"name\": \"Daisy\", \"salary\": -150000.0," +
            "\"qualifications\": [\"Java\"]}";
        restController.start();
        Request.post("http://localhost:4567/api/workers/Daisy")
                .bodyByteArray(body.getBytes())
                .execute().returnContent().asString();

        assertEquals(0, company.getEmployedWorkers().size());
    }

    @Test
    public void testPostWorker7() throws IOException {
        // null name, no worker created
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        company.createProject("Moon mission", quals, ProjectSize.BIG);
        String body = "{ \"name\": \"null\", \"salary\": -150000.0," +
            "\"qualifications\": [\"Java\"]}";
        restController.start();
        Request.post("http://localhost:4567/api/workers/null")
                .bodyByteArray(body.getBytes())
                .execute().returnContent().asString();

        assertEquals(0, company.getEmployedWorkers().size());
    }

    @Test
    public void testPostWorker8() throws IOException {
        // null quals, no worker created
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        company.createProject("Moon mission", quals, ProjectSize.BIG);
        String body = "{ \"name\": \"Daisy\", \"salary\": 150000.0," +
            "\"qualifications\": null}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        Request.post("http://localhost:4567/api/workers/Daisy")
                .bodyByteArray(body.getBytes())
                .execute().returnContent().asString();
    }

    @Test
    public void testPostWorker9() throws IOException {
        // null salary
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        company.createProject("Moon mission", quals, ProjectSize.BIG);
        String body = "{ \"name\": \"Daisy\", \"salary\": null," +
            "\"qualifications\": null}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        Request.post("http://localhost:4567/api/workers/Daisy")
                .bodyByteArray(body.getBytes())
                .execute().returnContent().asString();
    }

    @Test
    public void testPostWorker10() throws IOException {
        // null salary
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        company.createProject("Moon mission", quals, ProjectSize.BIG);
        String body = "{ \"name\": \"Daisy\", \"salary\": \"null\"," +
            "\"qualifications\": null}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        Request.post("http://localhost:4567/api/workers/Daisy")
                .bodyByteArray(body.getBytes())
                .execute().returnContent().asString();
    }

    @Test
    public void testPutAssign() throws IOException {
        // Valid project and worker returns OK
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);
        Project project = company.createProject("Joe's Java", quals, ProjectSize.SMALL);

        assertFalse(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO(worker.getName(), project.getName());
        String bodyString = gson.toJson(body);
        
        restController.start();
        String response = Request.put("http://localhost:4567/api/assign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();

        assertEquals("OK", response);
        assertTrue(project.getWorkers().contains(worker));
    }

    @Test
    public void testPutAssign1() throws IOException {
        // project name does not match
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);

        assertFalse(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO(worker.getName(), "Joe's");
        String bodyString = gson.toJson(body);
        
        restController.start();
        thrown.expect(HttpResponseException.class);
        Request.put("http://localhost:4567/api/assign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();
    }

    @Test
    public void testPutAssign2() throws IOException {
        // project name is blank
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);

        assertFalse(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO(worker.getName(), "   ");
        String bodyString = gson.toJson(body);
        
        restController.start();
        thrown.expect(HttpResponseException.class);
        Request.put("http://localhost:4567/api/assign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();
    }

    @Test
    public void testPutAssign3() throws IOException {
        // project name is empty
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);

        assertFalse(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO(worker.getName(), "");
        String bodyString = gson.toJson(body);
        
        restController.start();
        thrown.expect(HttpResponseException.class);
        Request.put("http://localhost:4567/api/assign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();
    }

    @Test
    public void testPutAssign4() throws IOException {
        // project name is null
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);

        assertFalse(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO(worker.getName(), "null");
        String bodyString = gson.toJson(body);
        
        restController.start();
        thrown.expect(HttpResponseException.class);
        Request.put("http://localhost:4567/api/assign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();
    }

    @Test
    public void testPutAssign5() throws IOException {
        // worker name does not match
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);
        Project project = company.createProject("Joe's Java", quals, ProjectSize.SMALL);

        assertFalse(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO("Joseph", project.getName());
        String bodyString = gson.toJson(body);
        
        restController.start();
        thrown.expect(HttpResponseException.class);
        Request.put("http://localhost:4567/api/assign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();
    }

    @Test
    public void testPutAssign6() throws IOException {
        // worker name is blank
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);
        Project project = company.createProject("Joe's Java", quals, ProjectSize.SMALL);

        assertFalse(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO("   ", project.getName());
        String bodyString = gson.toJson(body);
        
        restController.start();
        thrown.expect(HttpResponseException.class);
        Request.put("http://localhost:4567/api/assign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();
    }

    @Test
    public void testPutAssign7() throws IOException {
        // worker name is empty
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);
        Project project = company.createProject("Joe's Java", quals, ProjectSize.SMALL);

        assertFalse(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO("", project.getName());
        String bodyString = gson.toJson(body);
        
        restController.start();
        thrown.expect(HttpResponseException.class);
        Request.put("http://localhost:4567/api/assign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();
    }

    @Test
    public void testPutAssign8() throws IOException {
        // worker name null
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);
        Project project = company.createProject("Joe's Java", quals, ProjectSize.SMALL);

        assertFalse(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO("null", project.getName());
        String bodyString = gson.toJson(body);
        
        restController.start();
        thrown.expect(HttpResponseException.class);
        Request.put("http://localhost:4567/api/assign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();
    }

    @Test
    public void testGetQuals1() throws IOException {
        company = new Company("Company 1");
        assertEquals(0, company.getQualifications().size());
        Qualification java = company.createQualification("Java");
        restController.start();
        String response = Request.get("http://localhost:4567/api/qualifications")
                            .execute().returnContent().asString();
        assertEquals(1, company.getQualifications().size());
        assertEquals(java.toString(), company.getQualifications().iterator().next().toString());
        assertEquals("[{\"description\":\"Java\",\"workers\":[]}]", response);
    }

    @Test
    public void testGetQuals2() throws IOException {
        company = new Company("Company 1");
        restController.start();
        String response = Request.get("http://localhost:4567/api/qualifications")
                            .execute().returnContent().asString();

        assertEquals(0, company.getQualifications().size());
        assertEquals("[]", response);
    }

    @Test
    public void testGetQuals3() throws IOException {
        company = new Company("Company 1");
        restController.start();
        String response = Request.get("http://localhost:4567/api/qualifications/q1")
                            .execute().returnContent().asString();

                            System.out.println(response);
        assertEquals(0, company.getQualifications().size());
        assertEquals("null", response);
    }

    @Test
    public void testGetQuals4() throws IOException {
        company = new Company("Company 1");
        assertEquals(0, company.getQualifications().size());
        Qualification java = company.createQualification("Java");
        restController.start();
        String response = Request.get("http://localhost:4567/api/qualifications/Java")
                            .execute().returnContent().asString();
        System.out.println(response);
        assertEquals(1, company.getQualifications().size());
        assertEquals(java.toString(), company.getQualifications().iterator().next().toString());
        assertEquals("{\"description\":\"Java\",\"workers\":[]}", response);
    }
    
    @Test
    public void testPutUnassign() throws IOException {
        // Valid project and worker returns OK
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);
        Project project = company.createProject("Joe's Java", quals, ProjectSize.SMALL);
        
        company.assign(worker, project);
        assertTrue(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO(worker.getName(), project.getName());
        String bodyString = gson.toJson(body);
        
        restController.start();
        String response = Request.put("http://localhost:4567/api/unassign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();

        assertEquals("OK", response);
        assertFalse(project.getWorkers().contains(worker));
    }

    @Test
    public void testPutUnassign1() throws IOException {
        // project name does not match
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);
        Project project = company.createProject("Joe's Java", quals, ProjectSize.SMALL);
        
        company.assign(worker, project);
        assertTrue(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO(worker.getName(), "Java");
        String bodyString = gson.toJson(body);
        
        restController.start();
        thrown.expect(HttpResponseException.class);
        Request.put("http://localhost:4567/api/unassign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();
    }

    @Test
    public void testPutUnssign2() throws IOException {
        // project name is blank
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);
        Project project = company.createProject("Joe's Java", quals, ProjectSize.SMALL);
        
        company.assign(worker, project);
        assertTrue(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO(worker.getName(), "   ");
        String bodyString = gson.toJson(body);
        
        restController.start();
        thrown.expect(HttpResponseException.class);
        Request.put("http://localhost:4567/api/unassign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();
    }

    @Test
    public void testPutUnassign3() throws IOException {
        // project name is empty
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);
        Project project = company.createProject("Joe's Java", quals, ProjectSize.SMALL);
        
        company.assign(worker, project);
        assertTrue(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO(worker.getName(), "");
        String bodyString = gson.toJson(body);
        
        restController.start();
        thrown.expect(HttpResponseException.class);
        Request.put("http://localhost:4567/api/unassign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();
    }

    @Test
    public void testPutUnassign4() throws IOException {
        // project name is null
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);
        Project project = company.createProject("Joe's Java", quals, ProjectSize.SMALL);
        
        company.assign(worker, project);
        assertTrue(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO(worker.getName(), "null");
        String bodyString = gson.toJson(body);
        
        restController.start();
        thrown.expect(HttpResponseException.class);
        Request.put("http://localhost:4567/api/unassign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();
    }

    @Test
    public void testPutUnassign5() throws IOException {
        // worker name does not match
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);
        Project project = company.createProject("Joe's Java", quals, ProjectSize.SMALL);
        
        company.assign(worker, project);
        assertTrue(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO("Joseph", project.getName());
        String bodyString = gson.toJson(body);
        
        restController.start();
        thrown.expect(HttpResponseException.class);
        Request.put("http://localhost:4567/api/unassign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();
    }

    @Test
    public void testPutUnassign6() throws IOException {
        // worker name is blank
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);
        Project project = company.createProject("Joe's Java", quals, ProjectSize.SMALL);
        
        company.assign(worker, project);
        assertTrue(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO("   ", project.getName());
        String bodyString = gson.toJson(body);
        
        restController.start();
        thrown.expect(HttpResponseException.class);
        Request.put("http://localhost:4567/api/unassign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();
    }

    @Test
    public void testPutUnassign7() throws IOException {
        // worker name is empty
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);
        Project project = company.createProject("Joe's Java", quals, ProjectSize.SMALL);
        
        company.assign(worker, project);
        assertTrue(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO("", project.getName());
        String bodyString = gson.toJson(body);
        
        restController.start();
        thrown.expect(HttpResponseException.class);
        Request.put("http://localhost:4567/api/unassign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();
    }

    @Test
    public void testPutUnassign8() throws IOException {
        // worker name null
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker worker = company.createWorker("Joe", quals, 10);
        Project project = company.createProject("Joe's Java", quals, ProjectSize.SMALL);
        
        company.assign(worker, project);
        assertTrue(company.getAssignedWorkers().contains(worker));

        AssignmentDTO body = new AssignmentDTO("null", project.getName());
        String bodyString = gson.toJson(body);
        
        restController.start();
        thrown.expect(HttpResponseException.class);
        Request.put("http://localhost:4567/api/unassign")
            .bodyString(bodyString, ContentType.APPLICATION_JSON)
            .execute().returnContent().asString();
    }

    @Test
    public void testPutStart() throws IOException {
        // Valid project returns OK
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker w1 = company.createWorker("w", quals, 10);
        Project p1 = company.createProject("Moon mission", quals, ProjectSize.SMALL);
        company.assign(w1, p1);
        String body = "{ \"name\": \"Moon mission\"}";
        restController.start();
        String response = gson.fromJson(
                        Request.put("http://localhost:4567/api/start")
                        .bodyByteArray(body.getBytes())
                        .execute().returnContent().asString(), String.class);
        
        assertEquals("OK", response);
        assertEquals(ProjectStatus.ACTIVE, company.getProjects().iterator().next().getStatus());
        
    }

    @Test
    public void testPutStart1() throws IOException {
        // project names dont match
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker w1 = company.createWorker("w", quals, 10);
        Project p1 = company.createProject("Moon mission", quals, ProjectSize.SMALL);
        company.assign(w1, p1);
        String body = "{ \"name\": \"Moon\"}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        Request.put("http://localhost:4567/api/start")
        .bodyByteArray(body.getBytes())
        .execute().returnContent();
    }

    @Test
    public void testPutStart2() throws IOException {
        // blank project name
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker w1 = company.createWorker("w", quals, 10);
        Project p1 = company.createProject("Moon mission", quals, ProjectSize.SMALL);
        company.assign(w1, p1);
        String body = "{ \"name\": \"\"}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        Request.put("http://localhost:4567/api/start")
        .bodyByteArray(body.getBytes())
        .execute().returnContent();
    }

    @Test
    public void testPutStart3() throws IOException {
        // null project
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker w1 = company.createWorker("w", quals, 10);
        Project p1 = company.createProject("Moon mission", quals, ProjectSize.SMALL);
        company.assign(w1, p1);
        String body = "{ \"name\": null}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        Request.put("http://localhost:4567/api/start")
        .bodyByteArray(body.getBytes())
        .execute().returnContent();
    }

    @Test
    public void testPutStart4() throws IOException {
        // company has no projects
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        String body = "{ \"name\": \"Moon mission\"}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        Request.put("http://localhost:4567/api/start")
        .bodyByteArray(body.getBytes())
        .execute().returnContent();
    }

    @Test
    public void testPutStart5() throws IOException {
        // project already started 
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker w1 = company.createWorker("w", quals, 10);
        Project p1 = company.createProject("Moon mission", quals, ProjectSize.SMALL);
        company.assign(w1, p1);
        company.start(p1);
        String body = "{ \"name\": \"Moon mission\"}";
        restController.start();
        Request.put("http://localhost:4567/api/start");
        String response = gson.fromJson(
                        Request.put("http://localhost:4567/api/start")
                        .bodyByteArray(body.getBytes())
                        .execute().returnContent().asString(), String.class);
        assertEquals("OK", response);
        assertEquals(ProjectStatus.ACTIVE, company.getProjects().iterator().next().getStatus());
    }

    @Test
    public void testPutStart6() throws IOException {
        // missing quals
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Qualification python = company.createQualification("Python");
        Set<Qualification> qualsWorker = new HashSet<Qualification>();
        qualsWorker.add(java);
        Set<Qualification> qualsProj = new HashSet<Qualification>();
        qualsProj.add(java);
        qualsProj.add(python);
        Worker w1 = company.createWorker("w", qualsWorker, 10);
        Project p1 = company.createProject("Moon mission", qualsProj, ProjectSize.SMALL);
        company.assign(w1, p1);
        String body = "{ \"name\": \"Moon mission\"}";
        restController.start();
        String response = gson.fromJson(
                        Request.put("http://localhost:4567/api/start")
                        .bodyByteArray(body.getBytes())
                        .execute().returnContent().asString(), String.class);
        assertEquals("OK", response);
        assertEquals(ProjectStatus.PLANNED, company.getProjects().iterator().next().getStatus());
    }

    @Test
    public void testPutStart7() throws IOException {
        // suspended project
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Qualification python = company.createQualification("Python");
        Set<Qualification> qualsW1 = new HashSet<Qualification>();
        qualsW1.add(java);
        Set<Qualification> qualsW2 = new HashSet<Qualification>();
        qualsW2.add(python);
        Set<Qualification> qualsProj = new HashSet<Qualification>();
        qualsProj.add(python);
        qualsProj.add(java);
        Worker w1 = company.createWorker("w1", qualsW1, 10);
        Worker w2 = company.createWorker("w2", qualsW2, 10);
        Project p1 = company.createProject("Moon mission", qualsProj, ProjectSize.SMALL);
        company.assign(w1, p1);
        company.assign(w2, p1);
        company.start(p1);
        company.unassign(w2, p1);
        String body = "{ \"name\": \"Moon mission\"}";
        restController.start();
        String response = gson.fromJson(
                        Request.put("http://localhost:4567/api/start")
                        .bodyByteArray(body.getBytes())
                        .execute().returnContent().asString(), String.class);
        assertEquals("OK", response);
        assertEquals(ProjectStatus.SUSPENDED, company.getProjects().iterator().next().getStatus());
    }

    @Test
    public void testGetWorkers1() throws IOException {
        //One worker with project and qualification
        company = new Company("Jedi Order");
        Qualification q1 = company.createQualification("jedi master");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(q1);
        Worker Obi_Wan = company.createWorker("Obi-Wan Kenobi", quals, 8);
        Project impossible = company.createProject("Save Anikan", quals, ProjectSize.BIG);
        Obi_Wan.addProject(impossible);
        restController.start();
        WorkerDTO[] workers = gson.fromJson(
                        Request.get("http://localhost:4567/api/workers").execute().returnContent().asString(),
                        WorkerDTO[].class);
        assertEquals(1, workers.length);
        assertEquals("Obi-Wan Kenobi", workers[0].getName());
        assertEquals(8, workers[0].getSalary(), 0.0001);
        assertEquals("jedi master", workers[0].getQualifications()[0]);
        assertEquals("Save Anikan", workers[0].getProjects()[0]);
    }

    @Test
    public void testGetWorkers2() throws IOException{
        //No workers returns empy list
        company = new Company("The Daily Planet");
        restController.start();
        WorkerDTO[] workers = gson.fromJson(
                        Request.get("http://localhost:4567/api/workers").execute().returnContent().asString(),
                        WorkerDTO[].class);
        assertEquals(0, workers.length);
    }

    @Test
    public void testGetWorkers3() throws IOException{
        //Worker with no projects
        company = new Company("Tesla");
        Qualification q1 = company.createQualification("born rich");
        Set<Qualification> quals = new HashSet<>();
        quals.add(q1);
        company.createWorker("Elon", quals, 100000);
        restController.start();
        WorkerDTO[] workers = gson.fromJson(
                        Request.get("http://localhost:4567/api/workers").execute().returnContent().asString(),
                        WorkerDTO[].class);
        assertEquals(1, workers.length);
        assertEquals(0, workers[0].getProjects().length);
    }
    
    public void testPutFinish() throws IOException {
        // Valid project returns OK
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker w1 = company.createWorker("w", quals, 10);
        Project p1 = company.createProject("Moon mission", quals, ProjectSize.SMALL);
        company.assign(w1, p1);
        company.start(p1);
        String body = "{ \"name\": \"Moon mission\"}";
        company.start(p1);
        restController.start();
        String response = gson.fromJson(
                        Request.put("http://localhost:4567/api/finish")
                        .bodyByteArray(body.getBytes())
                        .execute().returnContent().asString(), String.class);
        
        assertEquals("OK", response);
        assertEquals(ProjectStatus.FINISHED, company.getProjects().iterator().next().getStatus());
        assertEquals(0, company.getAssignedWorkers().size());
        assertEquals(0, company.getProjects().iterator().next().getWorkers().size());
    }

    @Test
    public void testPutFinish1() throws IOException {
        // project names dont match
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker w1 = company.createWorker("w", quals, 10);
        Project p1 = company.createProject("Moon mission", quals, ProjectSize.SMALL);
        company.assign(w1, p1);
        company.start(p1);
        String body = "{ \"name\": \"Moon\"}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        Request.put("http://localhost:4567/api/finish")
        .bodyByteArray(body.getBytes())
        .execute().returnContent();
    }

    @Test
    public void testPutFinish2() throws IOException {
        // blank project name
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker w1 = company.createWorker("w", quals, 10);
        Project p1 = company.createProject("Moon mission", quals, ProjectSize.SMALL);
        company.assign(w1, p1);
        company.start(p1);
        String body = "{ \"name\": \"\"}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        Request.put("http://localhost:4567/api/finish")
        .bodyByteArray(body.getBytes())
        .execute().returnContent();
    }

    @Test
    public void testPutFinish3() throws IOException {
        // null project
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker w1 = company.createWorker("w", quals, 10);
        Project p1 = company.createProject("Moon mission", quals, ProjectSize.SMALL);
        company.assign(w1, p1);
        company.start(p1);
        String body = "{ \"name\": null}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        Request.put("http://localhost:4567/api/finish")
        .bodyByteArray(body.getBytes())
        .execute().returnContent();
    }

    @Test
    public void testPutFinish4() throws IOException {
        // company has no projects
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        String body = "{ \"name\": \"Moon mission\"}";
        thrown.expect(HttpResponseException.class);
        restController.start();
        Request.put("http://localhost:4567/api/finish")
        .bodyByteArray(body.getBytes())
        .execute().returnContent();
    }

    @Test
    public void testPutFinished5() throws IOException {
        // project already finished
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Set<Qualification> quals = new HashSet<Qualification>();
        quals.add(java);
        Worker w1 = company.createWorker("w", quals, 10);
        Project p1 = company.createProject("Moon mission", quals, ProjectSize.SMALL);
        company.assign(w1, p1);
        company.start(p1);
        company.finish(p1);
        String body = "{ \"name\": \"Moon mission\"}";
        restController.start();
        Request.put("http://localhost:4567/api/start");
        String response = gson.fromJson(
                        Request.put("http://localhost:4567/api/finish")
                        .bodyByteArray(body.getBytes())
                        .execute().returnContent().asString(), String.class);
        assertEquals("OK", response);
        assertEquals(ProjectStatus.FINISHED, company.getProjects().iterator().next().getStatus());
    }

    @Test
    public void testPutFinish6() throws IOException {
        // suspended project
        company = new Company("Company 1");
        Qualification java = company.createQualification("Java");
        Qualification python = company.createQualification("Python");
        Set<Qualification> qualsW1 = new HashSet<Qualification>();
        qualsW1.add(java);
        Set<Qualification> qualsW2 = new HashSet<Qualification>();
        qualsW2.add(python);
        Set<Qualification> qualsProj = new HashSet<Qualification>();
        qualsProj.add(python);
        qualsProj.add(java);
        Worker w1 = company.createWorker("w1", qualsW1, 10);
        Worker w2 = company.createWorker("w2", qualsW2, 10);
        Project p1 = company.createProject("Moon mission", qualsProj, ProjectSize.SMALL);
        company.assign(w1, p1);
        company.assign(w2, p1);
        company.start(p1);
        company.unassign(w2, p1);
        String body = "{ \"name\": \"Moon mission\"}";
        restController.start();
        String response = gson.fromJson(
                        Request.put("http://localhost:4567/api/finish")
                        .bodyByteArray(body.getBytes())
                        .execute().returnContent().asString(), String.class);
        assertEquals("OK", response);
        assertEquals(ProjectStatus.SUSPENDED, company.getProjects().iterator().next().getStatus());
    }

    @Test
    public void testGetNameWorker1() throws IOException{
        company = new Company("Ford");
        restController.start();
        WorkerDTO[] workers= gson.fromJson(
            Request.get("http://localhost:4567/api/workers").execute().returnContent().asString(),
            WorkerDTO[].class);
        assertEquals(0, workers.length);
    }

    @Test
    public void testGetNameWorker2() throws IOException{
        company = new Company("NSA");
        Qualification q1 = company.createQualification("Knowledge Giver");
        Set<Qualification> quals = new HashSet<>();
        quals.add(q1);
        company.createWorker("EdwardSnowden", quals, 10);
        restController.start();
        WorkerDTO[] worker = gson.fromJson(
                                Request.get("http://localhost:4567/api/workers").execute().returnContent().asString(),
                       WorkerDTO[].class);
        assertEquals("EdwardSnowden", worker[0].getName());
    }

    @Test 
    public void testGetNameWorker3() throws IOException{
        company = new Company("CSU");
        Qualification q1 = company.createQualification("President");
        Set<Qualification> quals = new HashSet<>();
        quals.add(q1);
        Set<Qualification> quals2 = new HashSet<>();
        quals2.add(new Qualification("Unknown"));
        company.createWorker("Tony Frank", quals, 10);
        Worker joyce = new Worker("Joyce McConnell", quals2, 0);
        company.createWorker("Amy Parsons", quals, 10);
        restController.start();
        WorkerDTO[] workers = gson.fromJson(
                                Request.get("http://localhost:4567/api/workers").execute().returnContent().asString(),
                       WorkerDTO[].class);
        assertEquals(2, workers.length);
        WorkerDTO joyceDTO = joyce.toDTO();
        for(WorkerDTO w : workers){
            assertFalse(w.equals(joyceDTO));
        }
    }




}
