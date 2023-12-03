package edu.colostate.cs415.model;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CompanyTest {
	@Rule public ExpectedException thrown = ExpectedException.none();

	private Company company;
	private Set<Qualification> qualifications;
	private Qualification q = new Qualification("q1");
	private String equalCompQual = "q1"; 

	@Before
	public void setUp() {
		company = new Company("test co");
		qualifications = new HashSet<>();
		qualifications.add(q);
	}

/****** Company ******/
	@Test
	public void testCompanyConstructor() {
		assertEquals("test co", company.getName());
	}

	@Test
	public void testCompanyConstructorThrowsExceptionWithNullName() {
		thrown.expect(IllegalArgumentException.class);
		company = new Company(null);
	}

	@Test
	public void testCompanyConstructorThrowsExceptionWithEmptyName() {
		thrown.expect(IllegalArgumentException.class);
		company = new Company("");
	}

	@Test
	public void testCompanyConstructorThrowsExceptionWithSpacesName() {
		thrown.expect(IllegalArgumentException.class);
		company = new Company("  ");
	}

/****** equals ******/
	@Test
	public void testEqualsAllValidAndEqual() {
		Company company2 = new Company("test co");
		assertTrue(company.equals(company2));
	}

	@Test
	public void testEqualsNull() {
		//thrown.expect(IllegalArgumentException.class);
		assertFalse(company.equals(null));
	}

	@Test
	public void testEqualsSelfNameEmpty() {
		thrown.expect(IllegalArgumentException.class);
		Company c1 = new Company("");
		c1.equals(company);
	}

	@Test
	public void testEqualsOtherNameNull() {
		thrown.expect(IllegalArgumentException.class);
		Company c2 = new Company("");
		company.equals(c2);
	}

	@Test
	public void testEqualsOtherNameEmpty() {
		thrown.expect(IllegalArgumentException.class);
		Company c2 = new Company(null);
		company.equals(c2);
	}

	@Test
	public void testEqualsObjectNotCompany() {
		String stringCompany = "test co";
		assertFalse(company.equals(stringCompany));
	}

	@Test
	public void testEqualsAllValidNotEqual() {
		Company company2 = new Company("test co 2");
		assertFalse(company.equals(company2));
	}

	/* hashCode */

	@Test
	public void testHashCode() {
		assertEquals("test co".hashCode(), company.hashCode());
	}

	/****** toString ******/
	@Test
	public void testToStringAllValid() {
		company.createQualification("q1");
		company.createWorker("w1", qualifications, 10);
		company.createProject("p1", qualifications, ProjectSize.SMALL);
		assertEquals(company.toString(), "test co:1:1");
	}

	@Test
	public void testToStringNoWorkers() {
		company.createQualification("q1");
		company.createProject("p1", qualifications, ProjectSize.SMALL);
		assertEquals(company.toString(), "test co:0:1");
	}

	@Test
	public void testToStringNoProjects() {
	company.createQualification("q1");
	company.createWorker("w1", qualifications, 10);
	assertEquals(company.toString(), "test co:1:0");
	}

/* getEmployedWorkers */
	@Test
	public void testGetEmployedWorkersDoesNotChangeThroughReference() {
		Set<Worker> employeesClone = company.getEmployedWorkers();
		Worker w1 = new Worker("One Worker", qualifications, 1.0);
		employeesClone.add(w1);
		assertFalse(company.getEmployedWorkers().contains(w1));
	}

	@Test
	public void testGetEmployedWorkers() {
		company.createQualification("q1");
		company.createWorker("w1", qualifications, 10);
		company.createWorker("w2", qualifications, 10);
		assertEquals(2, company.getEmployedWorkers().size());
	}

	@Test
	public void testGetEmployedWorkersEmpty() {
		assertTrue( company.getEmployedWorkers().isEmpty());
	}

/****** getAvailableWorkers ******/
	@Test
	public void testGetAvailableWorkerDoesNotChangeThroughReference() {
		Set<Worker> availableClone = company.getAvailableWorkers();
		Worker w1 = new Worker("One Worker", qualifications, 1.0);
		availableClone.add(w1);
		assertFalse(company.getAssignedWorkers().contains(w1));
	}
	
	@Test
	public void testGetAvailableWorkersMoreThanOneWorker() {
		Worker w1 = new Worker("Worker 1", qualifications, 10);
		Worker w2 = new Worker("Worker 2", qualifications, 10);
		Worker w3 = new Worker("Worker 3", qualifications, 10);
		
		for(Qualification q: qualifications){
			company.createQualification(q.toString());
		}
		
		assertEquals(qualifications.size(), 1);
		assertEquals(company.getAvailableWorkers().size(), 0);
		assertEquals(company.createWorker("Worker 1", qualifications, 10), w1);
		assertEquals(company.createWorker("Worker 2", qualifications, 10), w2);
		assertEquals(company.createWorker("Worker 3", qualifications, 10), w3);
		assertEquals(company.getAvailableWorkers().size(), 3);
	}
	
	@Test
	public void testGetAvailableWorkersOneWorker() {
		Worker worker = new Worker("Worker", qualifications, 10);
		
		for(Qualification q: qualifications){
			company.createQualification(q.toString());
		}
		
		assertEquals(qualifications.size(), 1);
		assertEquals(company.getAvailableWorkers().size(), 0);
		assertEquals(company.createWorker("Worker", qualifications, 10), worker);
		assertEquals(company.getAvailableWorkers().size(), 1);
	}
	
	@Test
	public void testGetAvailableWorkersEmpty() {
		assertTrue(company.getAvailableWorkers().isEmpty());		
	}

/****** getUnavailableWorkers ******/
	@Test
	public void testGetUnavailableWorkersSomeUnavailable(){
		company.createQualification("q1");
		Worker w1 = company.createWorker("w1", qualifications, 10);
		company.createWorker("w2", qualifications, 10);
		Project p1 = company.createProject("p1", qualifications, ProjectSize.BIG);
		Project p2 = company.createProject("p2", qualifications, ProjectSize.BIG);
		Project p3 = company.createProject("p3", qualifications, ProjectSize.BIG);
		Project p4 = company.createProject("p4", qualifications, ProjectSize.BIG);
		company.assign(w1, p1);
		company.assign(w1, p2);
		company.assign(w1, p3);
		company.assign(w1, p4);
		assertEquals(company.getUnavailableWorkers().size(), 1);
	}

	@Test
	public void testGetUnavailableWorkersNoneAvailable(){
		company.createQualification("q1");
		Worker w1 = company.createWorker("w1", qualifications, 10);
		Worker w2 = company.createWorker("w2", qualifications, 10);
		Project p1 = company.createProject("p1", qualifications, ProjectSize.BIG);
		Project p2 = company.createProject("p2", qualifications, ProjectSize.BIG);
		Project p3 = company.createProject("p3", qualifications, ProjectSize.BIG);
		Project p4 = company.createProject("p4", qualifications, ProjectSize.BIG);
		Project p5 = company.createProject("p5", qualifications, ProjectSize.BIG);
		Project p6 = company.createProject("p6", qualifications, ProjectSize.BIG);
		Project p7 = company.createProject("p7", qualifications, ProjectSize.BIG);
		Project p8 = company.createProject("p8", qualifications, ProjectSize.BIG);
		company.assign(w1, p1);
		company.assign(w1, p2);
		company.assign(w1, p3);
		company.assign(w1, p4);
		company.assign(w2, p5);
		company.assign(w2, p6);
		company.assign(w2, p7);
		company.assign(w2, p8);
		assertEquals(company.getUnavailableWorkers().size(), 2);
	}

	@Test
	public void testGetUnavailableWorkersAllAvailable(){
		company.createQualification("q1");
		company.createWorker("w1", qualifications, 10);
		company.createWorker("w2", qualifications, 10);
		company.createWorker("w3", qualifications, 10);
		company.createWorker("w4", qualifications, 10);
		assertTrue(company.getUnavailableWorkers().isEmpty());
	}

/****** getName ******/
	@Test
	public void testGetName(){
		//Constructor tests for nullness/emptiness.
		assertEquals(company.getName(), "test co");
	}

/***** createQualification */
	@Test
	public void testCreateQualification(){
		Qualification q1 = new Qualification("valid");
		assertEquals(company.createQualification("valid"), q1);
		// add assert to check qual was added to companies quals
	}

	@Test
	public void testCreateQualificationEmptyName(){
		assertEquals(company.createQualification(""),null);
		// add assert to check qual was not added to companies quals
	}

	@Test
	public void testCreateQualificationNullName(){
		assertEquals(company.createQualification(null),null);
		// add assert to check qual was not added to companies quals
	}

	@Test
	public void testCreateQualificationWithAlreadyPresentQualName(){
		Qualification q1 = new Qualification("valid");
		assertEquals(company.createQualification("valid"),q1);
		assertEquals(company.createQualification("valid"),null);
		// add assert to check qual was not added to companies quals
	}

/***** createProject ******/
@Test
public void testCreateProjectAllValidSingleQual() {
	Project project = new Project("Manhattan Project", qualifications, ProjectSize.SMALL);
	// make sure the project quals is a subset of the company
	for(Qualification q: qualifications){
		company.createQualification(q.toString());
	}
	assertEquals(qualifications.size(), 1);
	assertEquals(company.getProjects().size(), 0);
	assertEquals(company.createProject("Manhattan Project", qualifications, ProjectSize.SMALL), project);
	assertEquals(company.getProjects().size(), 1);
}

@Test
public void testCreateProjectNameNull() {
	assertEquals(qualifications.size(), 1);
	assertEquals(company.createProject(null, qualifications, ProjectSize.SMALL), null);
	assertEquals(company.getProjects().size(), 0);
}

@Test
public void testCreateProjectNameEmpty() {
	assertEquals(qualifications.size(), 1);
	assertEquals(company.createProject("", qualifications, ProjectSize.SMALL), null);
	assertEquals(company.getProjects().size(), 0);
}

@Test
public void testCreateProjectNameBlankSpaces() {
	assertEquals(qualifications.size(), 1);
	assertEquals(company.createProject("   ", qualifications, ProjectSize.SMALL), null);
	assertEquals(company.getProjects().size(), 0);
}

@Test
public void testCreateProjectQualNull() {
	assertEquals(company.createProject("Project 4", null, ProjectSize.SMALL), null);
	assertEquals(company.getProjects().size(), 0);
}

@Test
public void testCreateProjectQualEmpty() {
	HashSet<Qualification> quals = new HashSet<Qualification>();
	assertEquals(company.createProject("Project 5", quals, ProjectSize.SMALL), null);
	assertTrue(quals.isEmpty());
	assertEquals(company.getProjects().size(), 0);
}

@Test
public void testCreateProjectAllValidMultiQual() {
	Qualification q1 = new Qualification("Valid Qual 1");
	Qualification q2 = new Qualification("Valid Qual 2");
	qualifications.add(q1);
	qualifications.add(q2);
	for(Qualification q: qualifications){
		company.createQualification(q.toString());
	}
	Project project = new Project("GitHub Projects", qualifications, ProjectSize.SMALL);
	assertEquals(qualifications.size(), 3);
	assertEquals(company.getProjects().size(), 0);
	assertEquals(company.createProject("GitHub Projects", qualifications, ProjectSize.SMALL), project);
	assertEquals(company.getProjects().size(), 1);
}

@Test
public void testCreateProjectSizeNull() {
	assertEquals(qualifications.size(), 1);
	assertEquals(company.createProject("Project", qualifications, null), null);
	assertEquals(company.getProjects().size(), 0);
}

@Test
public void testCreateProjectSizeMEDIUM() {
	Project project = new Project("Project X", qualifications, ProjectSize.MEDIUM);
	for(Qualification q: qualifications){
		company.createQualification(q.toString());
	}
	assertEquals(qualifications.size(), 1);
	assertEquals(company.getProjects().size(), 0);
	assertEquals(company.createProject("Project X", qualifications, ProjectSize.MEDIUM), project);
	assertEquals(company.getProjects().size(), 1);
}

@Test
public void testCreateProjectSizeBIG() {
	Project project = new Project("Project Y", qualifications, ProjectSize.BIG);
	for(Qualification q: qualifications){
		company.createQualification(q.toString());
	}
	assertEquals(qualifications.size(), 1);
	assertEquals(company.getProjects().size(), 0);
	assertEquals(company.createProject("Project Y", qualifications, ProjectSize.BIG), project);
	assertEquals(company.getProjects().size(), 1);
}

@Test
public void testCreateProjectQualificationNotSubsetOfCompany() {
	company.createProject("Project Y", qualifications, ProjectSize.BIG);
	assertEquals(qualifications.size(), 1);
	assertEquals(company.getProjects().size(), 0);
	assertEquals(company.createProject("Project Y", qualifications, ProjectSize.BIG), null);
}

@Test
public void testCreateProjectOneExistingProject() {
	Project project = new Project("Manhattan Project", qualifications, ProjectSize.SMALL);
	// make sure the project quals is a subset of the company
	for(Qualification q: qualifications){
		company.createQualification(q.toString());
	}
	assertEquals(qualifications.size(), 1);
	assertEquals(company.getProjects().size(), 0);
	assertEquals(company.createProject("Manhattan Project", qualifications, ProjectSize.SMALL), project);
	assertEquals(company.getProjects().size(), 1);

	Project project2 = new Project("Project X", qualifications, ProjectSize.SMALL);
	assertEquals(company.createProject("Project X", qualifications, ProjectSize.SMALL), project2);
	assertEquals(company.getProjects().size(), 2);
}

@Test
public void testCreateProjectMultipleExistingProjects() {
	Project project = new Project("Project X", qualifications, ProjectSize.SMALL);
	Project project2 = new Project("Project Y", qualifications, ProjectSize.SMALL);
	// make sure the project quals is a subset of the company
	for(Qualification q: qualifications){
		company.createQualification(q.toString());
	}
	assertEquals(qualifications.size(), 1);
	assertEquals(company.getProjects().size(), 0);
	assertEquals(company.createProject("Project X", qualifications, ProjectSize.SMALL), project);
	assertEquals(company.createProject("Project Y", qualifications, ProjectSize.SMALL), project2);
	assertEquals(company.getProjects().size(), 2);
	
	Project project3 = new Project("Project Z", qualifications, ProjectSize.SMALL);
	assertEquals(company.createProject("Project Z", qualifications, ProjectSize.SMALL), project3);
	assertEquals(company.getProjects().size(), 3);

}

/***** start ******/
@Test
public void testStartAllValid() {
	ProjectSize size = ProjectSize.SMALL;
	ProjectStatus activeStatus = ProjectStatus.ACTIVE;
	company.createQualification(equalCompQual);
	Project testProject = company.createProject("testProj", qualifications, size);
	Worker testWorker = company.createWorker("testW", qualifications, 10);
	testProject.addWorker(testWorker);
	company.start(testProject);
	assertEquals(testProject.getStatus(), activeStatus);
}

@Test
public void testStartNullProject() {
	thrown.expect(IllegalArgumentException.class);
	company.start(null);
}

@Test
public void testStartStatusActive() {
	ProjectSize size = ProjectSize.SMALL;
	ProjectStatus activeStatus = ProjectStatus.ACTIVE;
	company.createQualification(equalCompQual);
	Project testProject = company.createProject("testProj", qualifications, size);
	Worker testWorker = company.createWorker("testW", qualifications, 10);
	testProject.addWorker(testWorker);
	testProject.setStatus(activeStatus);
	company.start(testProject);
	assertEquals(testProject.getStatus(), activeStatus);
}

@Test
public void testStartStatusSuspended() {
	ProjectSize size = ProjectSize.SMALL;
	ProjectStatus activeStatus = ProjectStatus.ACTIVE;
	company.createQualification(equalCompQual);
	Project testProject = company.createProject("testProj", qualifications, size);
	Worker testWorker = company.createWorker("testW", qualifications, 10);
	testProject.addWorker(testWorker);
	testProject.setStatus(ProjectStatus.SUSPENDED);
	company.start(testProject);
	assertEquals(testProject.getStatus(), activeStatus);
}

@Test
public void testStartStatusFinished() {
	ProjectSize size = ProjectSize.SMALL;
	company.createQualification(equalCompQual);
	Project testProject = company.createProject("testProj", qualifications, size);
	Worker testWorker = new Worker("testW", qualifications, 10);
	testProject.addWorker(testWorker);
	testProject.setStatus(ProjectStatus.FINISHED);
	company.start(testProject);
	assertEquals(testProject.getStatus(), ProjectStatus.FINISHED);
}

@Test
public void testStartRequirementsNotMet() {
	ProjectSize size = ProjectSize.SMALL;
	company.createQualification(equalCompQual);
	Project testProject = company.createProject("testProj", qualifications, size);
	company.start(testProject);
	assertEquals(testProject.getStatus(), ProjectStatus.PLANNED);
}

@Test	
public void testStartWithProjectNotInProject(){	
	thrown.expect(IllegalArgumentException.class);	
	Company c1 = new Company("c1");	
	Project p1 = new Project("p1", qualifications, ProjectSize.BIG);	
	c1.start(p1);	
}

/***** getQualifications  *****/
	@Test
	public void testGetQualificationsEmpty() {
		assertTrue(company.getQualifications().isEmpty());
	}

	@Test
	public void testGetQualificationsNotEmpty() {
		company.createQualification("q1");
		company.createQualification("q2");
		assertTrue(!company.getQualifications().isEmpty());
		assertEquals(company.getQualifications().size(), 2);
	}

	@Test
	public void testRemoveQualifications() {
		company.createQualification("q1");
		Set<Qualification> attemptDamage = company.getQualifications();
		int qualSize = attemptDamage.size();

		attemptDamage.remove(q);
		assertEquals(0, attemptDamage.size());

		Set<Qualification> checkUnchanged = company.getQualifications();
		assertEquals(qualSize, checkUnchanged.size());
	}

/***** getUnassignedWorkers  */

	@Test
	public void testGetUnassignedWorkersEmpty() {
		assertTrue( company.getUnassignedWorkers().isEmpty());
	}

	@Test
	public void testGetEmployedWorkersEmptyDoesntReturnNull() {
		assertTrue( company.getUnassignedWorkers() != null);
	}

	@Test
	public void testGetUnassignedWorkersEmployeesEqualsAssigned() {
		// add workers and assign all of them
		Set<Qualification> w1Quals = new HashSet<Qualification>();
		Qualification q1 = new Qualification("q1");
		w1Quals.add(q1);

		Set<Qualification> w2Quals = new HashSet<Qualification>();
		Qualification q2 = new Qualification("q2");
		w2Quals.add(q2);

		company.createQualification("q1");
		company.createQualification("q2");

		company.createWorker("w1", w1Quals, 10);
		company.createWorker("w2", w2Quals, 10);

		Set<Qualification> projectQuals = new HashSet<>();
		projectQuals.add(q1);
		projectQuals.add(q2);

		Set<Worker> workers = company.getUnassignedWorkers();
		Project p1 = company.createProject("p1", projectQuals, ProjectSize.SMALL);
		for(Worker w : workers){
			company.assign(w, p1);
		}
		assertTrue( company.getUnassignedWorkers().isEmpty());
	}

	@Test
	public void testGetUnassignedWorkersCorrectNumberAssigned() {
		company.createQualification("q1");
		company.createWorker("w1", qualifications, 10);
		company.createWorker("w2", qualifications, 10);
		assertTrue( company.getUnassignedWorkers().size() == 2);
	}

	@Test
	public void testGetUnassignedWorkersEmployeesMoreThanAssigned() {
		// add workers and assign all but one
		// assertTrue( company.getUnassignedWorkers().size() == 1);
	}

/***** getProjects  */
	@Test
	public void testGetProjectsDoesNotChangeThroughReference() {
		Set<Project> projectClone = company.getProjects();
		Project p1 = new Project("Project Z", qualifications, ProjectSize.SMALL);
		projectClone.add(p1);
		assertFalse(company.getProjects().contains(p1));
	}
	
	@Test
	public void testGetProjectsMoreThanOneProject() {;
		Project p1 = new Project("Project 1", qualifications, ProjectSize.MEDIUM);
		Project p2 = new Project("Project 2", qualifications, ProjectSize.MEDIUM);
		Project p3 = new Project("Project 3", qualifications, ProjectSize.MEDIUM);
		
		for(Qualification q: qualifications){
			company.createQualification(q.toString());
		}
		
		assertEquals(qualifications.size(), 1);
		assertEquals(company.getProjects().size(), 0);
		assertEquals(company.createProject("Project 1", qualifications, ProjectSize.MEDIUM), p1);
		assertEquals(company.createProject("Project 2", qualifications, ProjectSize.MEDIUM), p2);
		assertEquals(company.createProject("Project 3", qualifications, ProjectSize.MEDIUM), p3);
		assertEquals(company.getProjects().size(), 3);
	}
	
	@Test
	public void testGetProjectsOneProject() {
		Project project = new Project("Project 1", qualifications, ProjectSize.MEDIUM);
		for(Qualification q: qualifications){
			company.createQualification(q.toString());
		}
		assertEquals(qualifications.size(), 1);
		assertEquals(company.getProjects().size(), 0);
		assertEquals(company.createProject("Project 1", qualifications, ProjectSize.BIG), project);
		assertEquals(company.getProjects().size(), 1);
	}
	
	@Test
	public void testProjectsEmpty() {
		assertTrue(company.getProjects().isEmpty());		
	}

	/****** createWorker ******/
	@Test
	public void testCreateWorkerWithValidInput(){
		/*This test covers inputs for valid name, non-empty & non-null qulifications, salary > 0.0, and worker's
			qulifications contained in company qualifications */
		company.createQualification("q1");
		company.createQualification("q2");
		company.createQualification("q3");
		Qualification wq1 = new Qualification("q1");
		Qualification wq2 = new Qualification("q3");
		Set<Qualification> workerQuals = new HashSet<>();
		workerQuals.add(wq1);
		workerQuals.add(wq2);
		Worker w1 = company.createWorker("Barry Allen", workerQuals, 1.0);
		assertTrue(company.getEmployedWorkers().contains(w1));
	}

	@Test
	public void testCreateWorkerWithEmptyName(){
		assertNull(company.createWorker("", qualifications, 1.0));
	}

	@Test
	public void testCreateWorkerWithNullName(){
		assertNull(company.createWorker(null, qualifications, 1.0));
	}

	@Test
	public void testCreateWorkerWithEmptyQualifications(){
		Set<Qualification> emptySet = new HashSet<>(); 
		assertNull(company.createWorker("George Costanza", emptySet, 0));
	}

	@Test
	public void testCreateWorkerWithNullQualifications(){
		Worker w1 = company.createWorker("Dwight Schrute", null, 1.0);
		assertNull(w1);
	}

	@Test
	public void testCreateWorkerWithSalaryLessThanZero(){
		assertNull(company.createWorker("SpongeBob", qualifications, -0.1));

	}

	@Test
	public void testCreateWorkerWithQualificationsNotInCompanyQualifications(){
		company.createQualification("q1");
		company.createQualification("q2");
		company.createQualification("q3");
		Qualification wq1 = new Qualification("q2");
		Qualification wq2 = new Qualification("q3");
		Qualification wq3 = new Qualification("q4");
		Set<Qualification> workerQuals = new HashSet<>();
		workerQuals.add(wq1);
		workerQuals.add(wq2);
		workerQuals.add(wq3);
		assertNull(company.createWorker("Rick Snip", workerQuals, 1.0));
	}

	@Test
	public void testCreateWorkerWithSalaryEqualToZero(){
		company.createQualification(equalCompQual);
		Worker w1 = company.createWorker("test", qualifications, 0);
		assertEquals(w1.getSalary(), 0, .01);
	}

	@Test
	public void testCreateWorkerCheckThatWorkerWasAddedToQualification(){
		Company c1 = new Company("c1");
		Qualification q1 = c1.createQualification("q1");
		Set<Qualification> wQuals = new HashSet<>();
		wQuals.add(q1);
		c1.createProject("q1", wQuals, ProjectSize.BIG);
		Worker w1 = c1.createWorker("w1", wQuals, 0);
		assertTrue(q1.getWorkers().contains(w1));

	}

	/***** getAssignedWorkers *****/
	@Test
	public void testGetAssignedWorkersEmpty(){
		assertTrue(company.getAssignedWorkers().isEmpty());
	}
	
	@Test
	public void testGetAssignedWorkersNotEmpty(){
		Company cia = new Company("CIA");
		Qualification spy = cia.createQualification("Cat Spy");
		Set<Qualification> spyQuals = new HashSet<>();
		spyQuals.add(spy);
		Worker milo = cia.createWorker("Milo", spyQuals, 1000);
		Project coldWarEspionage = cia.createProject("Cold War Espionage", spyQuals, ProjectSize.BIG);
		cia.assign(milo, coldWarEspionage);
		assertTrue(cia.getAssignedWorkers().contains(milo));
	}

	@Test
	public void testGetAssignedWorkerDoesNotChangeThroughReference(){
		Set<Worker> assignedClone = company.getAssignedWorkers();
		Worker w1 = new Worker("Max Verstappen", qualifications, 1.0);
		assignedClone.add(w1);
		assertFalse(company.getAssignedWorkers().contains(w1));
	}

	/***** unassign *****/
		@Test
		public void testUnassignValidBaseTest() {
			Company thisCompany = new Company("Company Test");
			Qualification q1 = thisCompany.createQualification("Qual Test");
	
			Set<Qualification> quals = new HashSet<>();
			quals.add(q1);

			Worker worker = thisCompany.createWorker("Test Worker", quals, 10);
			Project project = thisCompany.createProject("Project 1", quals, ProjectSize.MEDIUM);
			
			thisCompany.assign(worker, project);
			assertTrue(worker.isAvailable());
			assertEquals(thisCompany.getAssignedWorkers().size(), 1);
			assertEquals(worker.getProjects().size(), 1);
			assertEquals(thisCompany.getAvailableWorkers().size(), 1);
			assertEquals(project.getStatus(), ProjectStatus.PLANNED);
			assertTrue(project.getMissingQualifications().isEmpty()); //project does NOT have missing qual

			thisCompany.unassign(worker, project);
			assertEquals(thisCompany.getAssignedWorkers().size(), 0);
			assertEquals(worker.getProjects().size(), 0);
			assertEquals(thisCompany.getAvailableWorkers().size(), 1);
			assertEquals(project.getStatus(), ProjectStatus.PLANNED);
		}

		@Test
		public void testUnassignNullProject() {
			thrown.expect(IllegalArgumentException.class);
			Worker w1 = new Worker("Max Verstappen", qualifications, 1.0);
			company.unassign(w1, null);
		}

		@Test
		public void testUnassignProjectStatusFINISHED() {
			Company thisCompany = new Company("Company Test");
			Qualification q1 = thisCompany.createQualification("Qual Test");
	
			Set<Qualification> quals = new HashSet<>();
			quals.add(q1);

			Worker worker1 = thisCompany.createWorker("Test Worker", quals, 10);
			Project project1 = thisCompany.createProject("Project 1", quals, ProjectSize.MEDIUM);
			
			thisCompany.assign(worker1, project1);

			project1.setStatus(ProjectStatus.FINISHED);
			assertEquals(project1.getStatus(), ProjectStatus.FINISHED);

			thrown.expect(IllegalArgumentException.class);
			thisCompany.unassign(worker1, project1);
		}

		@Test
		public void testUnassignProjectStatusSUSPENDED() {
			Company thisCompany = new Company("Company Test");
			Qualification q1 = thisCompany.createQualification("Qual Test");
	
			Set<Qualification> quals = new HashSet<>();
			quals.add(q1);

			Worker worker = thisCompany.createWorker("Test Worker", quals, 10);
			Project project = thisCompany.createProject("Project 1", quals, ProjectSize.MEDIUM);
			project.setStatus(ProjectStatus.SUSPENDED);
			
			thisCompany.assign(worker, project);
			assertTrue(worker.isAvailable());
			assertEquals(thisCompany.getAssignedWorkers().size(), 1);
			assertEquals(worker.getProjects().size(), 1);
			assertEquals(thisCompany.getAvailableWorkers().size(), 1);
			assertEquals(project.getStatus(), ProjectStatus.SUSPENDED);
			assertTrue(project.getMissingQualifications().isEmpty()); //project does NOT have missing qual
			
			thisCompany.unassign(worker, project);
			assertEquals(thisCompany.getAssignedWorkers().size(), 0);
			assertEquals(worker.getProjects().size(), 0);
			assertEquals(thisCompany.getAvailableWorkers().size(), 1);
			assertEquals(project.getStatus(), ProjectStatus.SUSPENDED);
		}

		@Test
		public void testUnassignProjectStatusACTIVE() {
			Company thisCompany = new Company("Company Test");
			Qualification q1 = thisCompany.createQualification("Qual Test");
	
			Set<Qualification> quals = new HashSet<>();
			quals.add(q1);

			Worker worker = thisCompany.createWorker("Test Worker", quals, 10);
			Project project = thisCompany.createProject("Project 1", quals, ProjectSize.MEDIUM);
			
			thisCompany.assign(worker, project);
			assertTrue(worker.isAvailable());
			assertEquals(thisCompany.getAssignedWorkers().size(), 1);
			assertEquals(worker.getProjects().size(), 1);
			assertEquals(thisCompany.getAvailableWorkers().size(), 1);
			assertEquals(project.getStatus(), ProjectStatus.PLANNED);
			assertTrue(project.getMissingQualifications().isEmpty()); //project does NOT have missing qual
			
			project.setStatus(ProjectStatus.ACTIVE);

			thisCompany.unassign(worker, project);
			assertEquals(thisCompany.getAssignedWorkers().size(), 0);
			assertEquals(worker.getProjects().size(), 0);
			assertEquals(thisCompany.getAvailableWorkers().size(), 1);
			assertEquals(project.getStatus(), ProjectStatus.SUSPENDED);
		}

		@Test
		public void testUnassignProjectWithMissingQualifications() {
			Company thisCompany = new Company("Company Test");
			Qualification q1 = thisCompany.createQualification("Qual 1 Test");
			Qualification q2 = thisCompany.createQualification("Qual 2 Test");
			Qualification q3 = thisCompany.createQualification("Qual 3 Test");
	
			Set<Qualification> requiredQuals = new HashSet<>();
			requiredQuals.add(q1);
			requiredQuals.add(q2);
			requiredQuals.add(q3);

			Set<Qualification> wQuals = new HashSet<Qualification>();
			wQuals.add(q1);

			Worker worker = thisCompany.createWorker("Worker Test", wQuals, 10);
			Project project = thisCompany.createProject("Project", requiredQuals, ProjectSize.MEDIUM);

			assertFalse(project.getMissingQualifications().isEmpty());

			thisCompany.assign(worker, project);
			assertTrue(worker.isAvailable());
			assertEquals(thisCompany.getAssignedWorkers().size(), 1);
			assertEquals(worker.getProjects().size(), 1);
			assertEquals(thisCompany.getAvailableWorkers().size(), 1);
			assertEquals(project.getStatus(), ProjectStatus.PLANNED);
			assertFalse(project.getMissingQualifications().isEmpty()); //project does have missing qual
			
			thisCompany.unassign(worker, project);
			assertEquals(thisCompany.getAssignedWorkers().size(), 0);
			assertEquals(worker.getProjects().size(), 0);
			assertEquals(thisCompany.getAvailableWorkers().size(), 1);
			assertEquals(project.getStatus(), ProjectStatus.PLANNED);
		}

		@Test
		public void testUnassignProjectWithMissingQualificationsAndACTIVE() {
			Company thisCompany = new Company("Company Test");
			Qualification q1 = thisCompany.createQualification("Qual 1 Test");
			Qualification q2 = thisCompany.createQualification("Qual 2 Test");
			Qualification q3 = thisCompany.createQualification("Qual 3 Test");
	
			Set<Qualification> requiredQuals = new HashSet<>();
			requiredQuals.add(q1);
			requiredQuals.add(q2);
			requiredQuals.add(q3);

			Set<Qualification> wQuals = new HashSet<Qualification>();
			wQuals.add(q1);

			Worker worker = thisCompany.createWorker("Worker Test", wQuals, 10);
			Project project = thisCompany.createProject("Project", requiredQuals, ProjectSize.MEDIUM);

			assertFalse(project.getMissingQualifications().isEmpty());

			thisCompany.assign(worker, project);
			assertTrue(worker.isAvailable());
			assertEquals(thisCompany.getAssignedWorkers().size(), 1);
			assertEquals(worker.getProjects().size(), 1);
			assertEquals(thisCompany.getAvailableWorkers().size(), 1);

			project.setStatus(ProjectStatus.ACTIVE);

			assertEquals(project.getStatus(), ProjectStatus.ACTIVE);
			assertFalse(project.getMissingQualifications().isEmpty()); //project does have missing qual
			
			thisCompany.unassign(worker, project);
			assertEquals(thisCompany.getAssignedWorkers().size(), 0);
			assertEquals(worker.getProjects().size(), 0);
			assertEquals(thisCompany.getAvailableWorkers().size(), 1);
			assertEquals(project.getStatus(), ProjectStatus.SUSPENDED);
		}

		@Test
		public void testUnassignNullWorker() {
			thrown.expect(IllegalArgumentException.class);
			Project project = new Project("Project 1", qualifications, ProjectSize.SMALL);
			company.unassign(null, project);
		}

		@Test
		public void testUnassignWorkerAssignedMultipleProjects() {
			Company thisCompany = new Company("Company Test");
			Qualification q1 = thisCompany.createQualification("Qual Test");
	
			Set<Qualification> quals = new HashSet<>();
			quals.add(q1);

			Worker worker = thisCompany.createWorker("Test Worker", quals, 10);
			Project project = thisCompany.createProject("Project 1", quals, ProjectSize.MEDIUM);
			Project project2 = thisCompany.createProject("Project 2", quals, ProjectSize.MEDIUM);
			
			thisCompany.assign(worker, project);
			thisCompany.assign(worker, project2);
			assertTrue(worker.isAvailable());
			assertEquals(thisCompany.getAssignedWorkers().size(), 1);
			assertEquals(worker.getProjects().size(), 2);
			assertEquals(thisCompany.getAvailableWorkers().size(), 1);
			assertEquals(project.getStatus(), ProjectStatus.PLANNED);
			assertTrue(project.getMissingQualifications().isEmpty()); //project does NOT have missing qual

			thisCompany.unassign(worker, project);
			assertEquals(thisCompany.getAssignedWorkers().size(), 1);
			assertEquals(worker.getProjects().size(), 1);
			assertEquals(thisCompany.getAvailableWorkers().size(), 1);
			assertEquals(project.getStatus(), ProjectStatus.PLANNED);
		}

		@Test
		public void testUnassignWorkerDoesNotContainProject() {
			Company thisCompany = new Company("Company Test");
			Qualification q1 = thisCompany.createQualification("Qual Test");
	
			Set<Qualification> quals = new HashSet<>();
			quals.add(q1);

			Worker worker = thisCompany.createWorker("Test Worker", quals, 10);
			Project project = thisCompany.createProject("Project 1", quals, ProjectSize.MEDIUM);
			
			assertTrue(worker.isAvailable());
			assertEquals(thisCompany.getAssignedWorkers().size(), 0);
			assertEquals(worker.getProjects().size(), 0);
			assertEquals(thisCompany.getAvailableWorkers().size(), 1);
			assertEquals(project.getStatus(), ProjectStatus.PLANNED);
			
			// nothing should change if worker is not assigned to a project 
			thisCompany.unassign(worker, project);
			assertEquals(thisCompany.getAssignedWorkers().size(), 0);
			assertEquals(worker.getProjects().size(), 0);
			assertEquals(thisCompany.getAvailableWorkers().size(), 1);
			assertEquals(project.getStatus(), ProjectStatus.PLANNED);
		}

		@Test
		public void testUnassignWorkerIsNotAssigned() {
			Company thisCompany = new Company("Company Test");
			Qualification q1 = thisCompany.createQualification("Qual Test");
	
			Set<Qualification> quals = new HashSet<>();
			quals.add(q1);

			Worker worker = thisCompany.createWorker("Test Worker", quals, 10);
			Project project = thisCompany.createProject("Project 1", quals, ProjectSize.MEDIUM);
			worker.addProject(project);

			assertTrue(worker.isAvailable());
			assertEquals(thisCompany.getAssignedWorkers().size(), 0);
			assertEquals(worker.getProjects().size(), 1);
			assertEquals(thisCompany.getAvailableWorkers().size(), 1);
			assertEquals(project.getStatus(), ProjectStatus.PLANNED);

			thisCompany.unassign(worker, project);
			assertEquals(thisCompany.getAssignedWorkers().size(), 0);
			assertEquals(worker.getProjects().size(), 1);
			assertEquals(thisCompany.getAvailableWorkers().size(), 1);
			assertEquals(project.getStatus(), ProjectStatus.PLANNED);
		}

		@Test
		public void testUnassignProjectsDoesNotContainProject() {
			Company thisCompany = new Company("Company Test");
			Qualification q1 = thisCompany.createQualification("Qual Test");
	
			Set<Qualification> quals = new HashSet<>();
			quals.add(q1);

			Worker worker = thisCompany.createWorker("Test Worker", quals, 10);
			Project project = new Project("Project 1", quals, ProjectSize.MEDIUM);

			thrown.expect(IllegalArgumentException.class);
			thisCompany.unassign(worker, project);
		}

		@Test
		public void testUnassignWorkerNotCompanyEmployee() {
			Company thisCompany = new Company("Company Test");
			Qualification q1 = thisCompany.createQualification("Qual Test");
	
			Set<Qualification> quals = new HashSet<>();
			quals.add(q1);

			Worker worker = new Worker("Test Worker", quals, 10);
			Project project = thisCompany.createProject("Project 1", quals, ProjectSize.MEDIUM);
			
			thrown.expect(IllegalArgumentException.class);
			thisCompany.unassign(worker, project);
		}

	/***** unassignAll *****/
	@Test
	public void testUnassignAllBaseTest(){
		company.createQualification("q1");
		Worker w1 = company.createWorker("w1", qualifications, 10);
		Project p1 = company.createProject("p1", qualifications, ProjectSize.BIG);
		company.assign(w1, p1);
		company.start(p1);
		company.unassignAll(w1);
		assertEquals(p1.getStatus(), ProjectStatus.SUSPENDED);
	}

	@Test
	public void testUnassignAllProjectRemainsActive(){
		Set<Qualification> w1Quals = new HashSet<Qualification>();
		Qualification q1 = new Qualification("q1");
		w1Quals.add(q1);
		Set<Qualification> w2Quals = new HashSet<Qualification>();
		Qualification q2 = new Qualification("q2");
		w2Quals.add(q1);
		w2Quals.add(q2);
		company.createQualification("q1");
		company.createQualification("q2");
		Worker w1 = company.createWorker("w1", w1Quals, 10);
		Worker w2 = company.createWorker("w2", w2Quals, 10);
		Project p1 = company.createProject("p1", w2Quals, ProjectSize.BIG);
		company.assign(w1, p1);
		company.assign(w2, p1);
		company.start(p1);
		company.unassignAll(w1);
		assertEquals(p1.getStatus(), ProjectStatus.ACTIVE);
	}

	@Test
	public void testUnassignAllNullWorker(){
		thrown.expect(IllegalArgumentException.class);
		company.unassignAll(null);
	}

	@Test
	public void testUnassignAllWorkerHasNoProjects(){
		company.createQualification("q1");
		Worker w1 = company.createWorker("w1", qualifications, 10);
		company.unassignAll(w1);
		assertEquals(company.getUnassignedWorkers().size(), 1);
	}

	@Test
	public void testUnassignAllWorkerWithSuspendedProject(){
		company.createQualification("q1");
		Worker w1 = company.createWorker("w1", qualifications, 10);
		Project p1 = company.createProject("p1", qualifications, ProjectSize.BIG);
		company.assign(w1, p1);
		company.start(p1);
		p1.setStatus(ProjectStatus.SUSPENDED);
		company.unassignAll(w1);
		assertEquals(company.getUnassignedWorkers().size(), 1);
		assertEquals(p1.getStatus(), ProjectStatus.SUSPENDED);
	}

	@Test
	public void testUnassignAllWorkerWithPlannedProject(){
		company.createQualification("q1");
		Worker w1 = company.createWorker("w1", qualifications, 10);
		Project p1 = company.createProject("p1", qualifications, ProjectSize.BIG);
		Project p2 = company.createProject("p2", qualifications, ProjectSize.BIG);
		company.assign(w1, p1);
		company.assign(w1, p2);
		company.unassignAll(w1);
		assertEquals(company.getUnassignedWorkers().size(), 1);
	}

	@Test
	public void testUnassignWorkerNotEmployee() {
		Project project = company.createProject("Project Test", qualifications, ProjectSize.MEDIUM);
		Worker worker = new Worker("Worker Test", qualifications, 10);
		thrown.expect(IllegalArgumentException.class);
		company.unassign(worker, project);
	}

	@Test	
	public void testUnassignAllWithWorkerNotInEmployees(){	
		thrown.expect(IllegalArgumentException.class);	
		Company c1 = new Company("c1");	
		Qualification q1 = new Qualification("test");	
		Set<Qualification> wQuals = new HashSet<>();	
		wQuals.add(q1);	
		Worker w1 = new Worker("w1", qualifications, 10);	
		c1.unassignAll(w1);	
		assertTrue(w1.getProjects().isEmpty());	
	}

	@Test
	public void testThatWorkerWasRemovedFromProjectsSetOfWorkers(){
		Company c1 = new Company("c1");
		Qualification q1 = c1.createQualification("q1");
		Set<Qualification> quals = new HashSet<>();
		quals.add(q1);
		Project p1 = c1.createProject("p1", quals, ProjectSize.BIG);
		Worker w1 = c1.createWorker("w1", quals, 0);
		c1.assign(w1, p1);
		c1.unassign(w1, p1);
		assertFalse(p1.getWorkers().contains(w1));
	}

	@Test
	public void testUnassignAllProjectIsRemovedFromWorkersSetOfProjects(){
		Company c1 = new Company("c1");
		Qualification q1 = c1.createQualification("q1");
		Set<Qualification> quals = new HashSet<>();
		quals.add(q1);
		Project p1 = c1.createProject("p1", quals, ProjectSize.BIG);
		Worker w1 = c1.createWorker("w1", quals, 0);
		c1.assign(w1, p1);
		c1.unassignAll(w1);
		assertTrue(w1.getProjects().isEmpty());
	}

	/****** finish ******/
	@Test
	public void testFinishValidInputs(){
		/*This test covers ProjectStatus = Active, Project != null, 
			Project.getWorkers != null, Worker.getProjects != null*/

		Company aboveEarth = new Company("Above Earth");

		//create quals to add into company::qualifications, create project, worker::qualifications, and create workers
		Qualification q1 = aboveEarth.createQualification("space walker");
		Qualification q2 = aboveEarth.createQualification("shuttle pilot");
		Qualification q3 = aboveEarth.createQualification("commander");

		Set<Qualification> spaceQuals = new HashSet<>();
		spaceQuals.add(q1);
		spaceQuals.add(q2);
		spaceQuals.add(q3);

		//create project to add into company::projects, worker::projects
		Project moonMission = aboveEarth.createProject("Moon Mission", spaceQuals, ProjectSize.BIG);

		//create worker to add into company::employees, company::available
		Set<Qualification> felicetteQuals = new HashSet<>(spaceQuals);
		felicetteQuals.remove(q3);
		Worker felicette = aboveEarth.createWorker("Felicette", felicetteQuals, 50);

		Set<Qualification> albertIIQuals = new HashSet<>();
		albertIIQuals.add(q3);
		Worker albertII = aboveEarth.createWorker("Albert II", albertIIQuals, 50);

		//assign workers to project
		aboveEarth.assign(felicette, moonMission);
		aboveEarth.assign(albertII, moonMission);

		aboveEarth.start(moonMission);
		aboveEarth.finish(moonMission);

		assertEquals(moonMission.getStatus(), ProjectStatus.FINISHED);
		assertTrue(felicette.getProjects().isEmpty());
		assertTrue(albertII.getProjects().isEmpty());
		assertEquals(aboveEarth.getAvailableWorkers().size(), 2);
		assertTrue(aboveEarth.getAssignedWorkers().isEmpty());
		assertTrue(moonMission.getWorkers().isEmpty());
	}

	@Test
	public void testFinishWithProjectsNotContainingProject(){
		thrown.expect(IllegalArgumentException.class);
		Project p1 = new Project("p1", qualifications, ProjectSize.BIG);
		company.finish(p1);
	}

	@Test
	public void testFinishProjectWithWorkerOnlyHavingCurrentProject(){
		company.createQualification(equalCompQual);
		Project p1 = company.createProject("p1", qualifications, ProjectSize.BIG);
		Project p2 = company.createProject("p2", qualifications, ProjectSize.BIG);
		Worker w1 = company.createWorker("w1", qualifications, 10);
		company.assign(w1, p1);
		company.assign(w1, p2);
		company.start(p1);
		company.finish(p1);
		assertFalse(w1.getProjects().isEmpty());
	}

	@Test
	public void testFinishWithWrongStatus(){
		company.createQualification(equalCompQual);
		Project p1 = company.createProject("p1", qualifications, ProjectSize.BIG);
		company.finish(p1);
		assertEquals(p1.getStatus(), ProjectStatus.PLANNED);
		Project p2 = company.createProject("p2", qualifications, ProjectSize.BIG);
		p2.setStatus(ProjectStatus.SUSPENDED);
		company.finish(p2);
		assertEquals(p2.getStatus(), ProjectStatus.SUSPENDED);
		Project p3 = company.createProject("p3", qualifications, ProjectSize.BIG);
		p3.setStatus(ProjectStatus.FINISHED);
		company.finish(p3);
		assertEquals(p3.getStatus(), ProjectStatus.FINISHED);
	}

	@Test
	public void testFinishWithNullProject(){
		thrown.expect(IllegalArgumentException.class);
		Project p1 = null;
		company.finish(p1);
	}

	/****** assign ******/
	@Test
	public void testAssignWithAllValidInputs_BCC(){
		Company believers = new Company("Believers Inc.");
		Qualification wldB = believers.createQualification("Primatologist");
		Qualification surS = believers.createQualification("Survival Specialist");
		Qualification camO = believers.createQualification("Camera Operator");
		//At this point, the company only has 3 qualifications

		Set<Qualification> janeQuals = new HashSet<>();
		janeQuals.add(wldB);
		Set<Qualification> bearQuals = new HashSet<>();
		bearQuals.add(surS);
		Set<Qualification> joeQuals = new HashSet<>();
		joeQuals.add(camO);

		Worker janeGoodall = believers.createWorker("Jane Goodall", janeQuals, 10);
		Worker bearGrylls = believers.createWorker("Bear Grylls", bearQuals, 10);
		Worker joeRogan = believers.createWorker("Joe Rogan", joeQuals, 10);

		Set<Qualification> prjQuals = new HashSet<>();
		prjQuals.add(wldB);
		prjQuals.add(surS);
		prjQuals.add(camO);
		Project bigfootHunt = believers.createProject("Find Bigfoot", prjQuals, ProjectSize.BIG);

		believers.assign(janeGoodall, bigfootHunt);
		believers.assign(bearGrylls, bigfootHunt);
		believers.assign(joeRogan, bigfootHunt);

		//company tests
		assertTrue(believers.getEmployedWorkers().contains(janeGoodall));
		assertTrue(believers.getEmployedWorkers().contains(bearGrylls));
		assertTrue(believers.getEmployedWorkers().contains(joeRogan));

		assertTrue(believers.getAvailableWorkers().contains(janeGoodall));
		assertTrue(believers.getAvailableWorkers().contains(bearGrylls));
		assertTrue(believers.getAvailableWorkers().contains(joeRogan));

		assertTrue(believers.getAssignedWorkers().contains(janeGoodall));
		assertTrue(believers.getAssignedWorkers().contains(bearGrylls));
		assertTrue(believers.getAssignedWorkers().contains(joeRogan));

		//project tests
		assertTrue(bigfootHunt.getWorkers().contains(janeGoodall));
		assertTrue(bigfootHunt.getWorkers().contains(bearGrylls));
		assertTrue(bigfootHunt.getWorkers().contains(joeRogan));

		//worker tests
		assertTrue(janeGoodall.getProjects().contains(bigfootHunt));
		assertTrue(bearGrylls.getProjects().contains(bigfootHunt));
		assertTrue(joeRogan.getProjects().contains(bigfootHunt));
	}

	@Test
	public void testAssignWithNullCompany(){
		thrown.expect(NullPointerException.class);
		company = null;
		company.createProject("p1", qualifications, ProjectSize.BIG);
	}

	@Test
	public void testAssignWithWorkerNotInAvailable(){
		company.createQualification(equalCompQual);
		Worker w1 = company.createWorker("w1", qualifications, 10);
		Project p1 = company.createProject("p1", qualifications, ProjectSize.BIG);
		Project p2 = company.createProject("p2", qualifications, ProjectSize.BIG);
		Project p3 = company.createProject("p3", qualifications, ProjectSize.BIG);
		Project p4 = company.createProject("p4", qualifications, ProjectSize.BIG);
		Project p5 = company.createProject("p5", qualifications, ProjectSize.BIG);
		company.assign(w1, p1);
		company.assign(w1, p2);
		company.assign(w1, p3);
		company.assign(w1, p4);
		assertFalse(company.getAvailableWorkers().contains(w1));
		company.assign(w1, p5);
	}

	@Test
	public void testAssignWithProjectNotInCompanyProjects(){
		thrown.expect(IllegalArgumentException.class);
		company.createQualification(equalCompQual);
		Project p1 = new Project("p1", qualifications, ProjectSize.BIG);
		Worker w1 = company.createWorker("w1", qualifications, 0);
		company.assign(w1, p1);
	}

	@Test
	public void testAssignWithInvalidName(){
		thrown.expect(IllegalArgumentException.class);
		company = new Company("");
	}

	@Test
	public void testAssignWithWorkerInAssignedSet(){
		company.createQualification(equalCompQual);
		Worker w1 = company.createWorker("w1", qualifications, 10);
		Project p1 = company.createProject("p1", qualifications, ProjectSize.BIG);
		Project p2 = company.createProject("p2", qualifications, ProjectSize.BIG);
		company.assign(w1, p1);
		company.assign(w1, p2);
	}

	@Test
	public void testAssignWithSuspendedProjectStatus(){
		for(Qualification q: qualifications){
			company.createQualification(q.toString());
		}
		Project p1 = company.createProject("p1", qualifications, ProjectSize.BIG);
		p1.setStatus(ProjectStatus.SUSPENDED);
		company.createQualification(equalCompQual);
		Worker w1 = company.createWorker("w1", qualifications, 10);
		company.assign(w1, p1);
		//company tests
		assertTrue(company.getEmployedWorkers().contains(w1));
		assertTrue(company.getAvailableWorkers().contains(w1));
		assertTrue(company.getAssignedWorkers().contains(w1));
		
		//project tests
		assertTrue(p1.getWorkers().contains(w1));

		//worker tests
		assertTrue(w1.getProjects().contains(p1));
	}

	@Test
	public void testAssignWithActiveProjectStatus(){
		for(Qualification q: qualifications){
			company.createQualification(q.toString());
		}
		Project p1 = company.createProject("p1", qualifications, ProjectSize.BIG);
		p1.setStatus(ProjectStatus.ACTIVE);
		company.createQualification(equalCompQual);
		Worker w1 = company.createWorker("w1", qualifications, 10);
		company.assign(w1, p1);
	}

	@Test
	public void testAssignWithFinishedProjectStatus(){
		for(Qualification q: qualifications){
			company.createQualification(q.toString());
		}
		Project p1 = company.createProject("p1", qualifications, ProjectSize.BIG);
		p1.setStatus(ProjectStatus.FINISHED);
		company.createQualification(equalCompQual);
		Worker w1 = company.createWorker("w1", qualifications, 10);
		company.assign(w1, p1);
	}

	@Test
	public void testAssignWithNullProject(){
		thrown.expect(IllegalArgumentException.class);
		company.createQualification(equalCompQual);
		Worker w1 = company.createWorker("w1", qualifications, 10);
		company.assign(w1, null);
	}

	@Test
	public void testAssignWithUnhelpfulWorker(){
		company.createQualification(equalCompQual);
		Qualification q2 = company.createQualification("q2");
		Set<Qualification> wQuals = new HashSet<>();
		wQuals.add(q2);
		Worker w1 = company.createWorker("w1", wQuals, 10);
		Project p1 = company.createProject("p1", qualifications, ProjectSize.BIG);
		company.assign(w1, p1);

	}

	@Test
	public void testAssignWhenWorkerWillBeOverloaded(){
		company.createQualification(equalCompQual);
		Worker w1 = company.createWorker("w1", qualifications, 10);
		Project p1 = company.createProject("p1", qualifications, ProjectSize.BIG);
		Project p2 = company.createProject("p2", qualifications, ProjectSize.BIG);
		Project p3 = company.createProject("p3", qualifications, ProjectSize.BIG);
		Project p4 = company.createProject("p4", qualifications, ProjectSize.SMALL);
		Project p5 = company.createProject("p5", qualifications, ProjectSize.BIG);
		company.assign(w1, p1);
		company.assign(w1, p2);
		company.assign(w1, p3);
		company.assign(w1, p4);
		company.assign(w1, p5);
	}

	@Test
	public void testAssignWithNullWorker(){
		thrown.expect(IllegalArgumentException.class);
		Project p1 = company.createProject("p1", qualifications, ProjectSize.BIG);
		company.assign(null, p1);
	}

	@Test
	public void testAssignWithProjectInWorkersProjects(){
		thrown.expect(IllegalArgumentException.class);
		company.createQualification(equalCompQual);
		Worker w1 = company.createWorker("w1", qualifications, 10);
		Project p1 = company.createProject("p1", qualifications, ProjectSize.BIG);
		w1.addProject(p1);
		company.assign(w1, p1);
	}

	@Test
	public void testAssignWithWorkerInProjectsSetOfWorkers(){
		thrown.expect(IllegalArgumentException.class);
		company.createQualification(equalCompQual);
		Worker w1 = company.createWorker("w1", qualifications, 10);
		Project p1 = company.createProject("p1", qualifications, ProjectSize.BIG);
		p1.addWorker(w1);
		company.assign(w1, p1);
	}
}