package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.lang.Integer;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.colostate.cs415.dto.WorkerDTO;

import org.junit.Before;
import org.junit.Rule;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class WorkerTest {
    private Worker worker;
    private Set<Qualification> qualifications;
    Qualification q;

    @Before
    public void setUp() throws Exception {
		qualifications = new HashSet<Qualification>();
        q = new Qualification("Test Qualification");
        qualifications.add(q);
        worker = new Worker("Test", qualifications, 1.0);
    }

    @Rule public ExpectedException thrown = ExpectedException.none();

    /*** Constructor */
    @Test
    public void testConstructorThrowsExceptionWithNullName() {
        thrown.expect( IllegalArgumentException.class );
        worker = new Worker(null, qualifications, 1);
    }

    @Test
    public void testConstructorThrowsExceptionWithEmptyName() {
        thrown.expect( IllegalArgumentException.class );
        worker = new Worker("", qualifications, 1);
    }

    @Test
    public void testConstructorThrowsExceptionWithNullQuals() {
        thrown.expect( IllegalArgumentException.class );
        worker = new Worker("Test", null, 1);
    }

    @Test
    public void testConstructorThrowsExceptionWithEmptyQuals() {
        thrown.expect( IllegalArgumentException.class );
        HashSet<Qualification> emptyQuals = new HashSet<Qualification>();
        worker = new Worker("Test", emptyQuals, 1);
    }

    @Test
    public void testConstructorThrowsExceptionWithNegativeSalary() {
        thrown.expect( IllegalArgumentException.class );
        worker = new Worker("Test", qualifications, -1);
    }

    @Test
    public void testConstructorThrowsExceptionWithSpacesName() {
        thrown.expect( IllegalArgumentException.class );
        worker = new Worker("   ", qualifications, 1);
    }

    /**** EQUALS *****/
    @Test
    public void testEqualsReturnsTrue() {
        assertTrue("worker should equal worker", worker.equals(worker));
    }

    @Test
    public void testEqualsReturnsFalse(){
        Worker testWorker2 = new Worker("test", qualifications, 1.0);
        assertFalse("worker should not equal test worker", worker.equals(testWorker2));
    }

    @Test
    public void testEqualsReturnsFalseWithNullObject(){
        assertFalse("equals returns false with null object", worker.equals(null));
    }

    @Test
    public void testEqualsReturnsFalseWithNonWorkerObject(){
        String nonWorker = "test";
        assertFalse("equals returns false with non worker object", worker.equals(nonWorker));
    }

    /***** TOSTRING *****/
    @Test
    public void testToString() {
       assertEquals("Worker.toString returns Test:0:1:1", worker.toString(), "Test:0:1:1");
    }

    @Test
    public void testToStringWithQuals() {
       Qualification q2 = new Qualification("test2");
       Qualification q3 = new Qualification("test3");
       qualifications.add(q2);
       qualifications.add(q3);
       Worker workerWithName = new Worker("test2", qualifications, 1.0);
       assertEquals("test2:0:3:1", workerWithName.toString());
    }

    @Test
    public void testToStringWithQualsAndNonZeroSalary() {
       Set<Qualification> qualifications = new HashSet<Qualification>();
       Qualification q = new Qualification("test");
       qualifications.add(q);
       Worker workerWithName = new Worker("test3", qualifications, 5122.1232);
       assertEquals(workerWithName.toString(), "test3:0:1:5122");
    }

    @Test
    public void testToStringWithIntSalary() {
       Worker workerWithName = new Worker("name", qualifications, 4);
       assertEquals(workerWithName.toString(), "name:0:1:4");
    }

    // Test for displaying number of projects when addProjects is done.

    /***** HASHCODE *****/
	@Test
    public void testHashCodeWithValidString() {
		Worker workerWithValidName = new Worker(("test"), qualifications,1.0);
        assertThat("Worker.hashCode returns a non 0 code with a valid name", workerWithValidName.hashCode(), is(not(0)));
    }

    /**** getName ****/

    @Test
    public void testGetNameWithNonEmptyName() {
        Worker worker = new Worker("test", qualifications, 1.0);
        assertEquals(worker.getName(), "test");
    }

    /*** getSalary - setSalary */
    @Test
    public void testGetSalary() {
        assertEquals(worker.getSalary(), 1.0, 0.001);
    }

    @Test
    public void testSetSalary() {
        double expectedSalary = 1000.99;
        worker.setSalary(expectedSalary);
        assertEquals(worker.getSalary(), expectedSalary, 0.001);
    }

    @Test
    public void testSetSalaryToZero() {
        worker.setSalary(0);
        assertEquals(0.0, worker.getSalary(), 0.0001);
    }

    @Test
    public void testSetSalaryToNegativeThrowsException() {
        thrown.expect( IllegalArgumentException.class );
        worker.setSalary(-1234.12);
    }

    /*** getQualificiations */
    @Test
    public void testGetQualificationsReturnsCorrectNumber() {
        assertEquals(worker.getQualifications().size(), 1);
    }

    @Test
    public void testGetQualificationsReturnsCorrectNumberOfQuals() {
        Qualification q1 = new Qualification("q1");
        Qualification q2 = new Qualification("q2");
        qualifications.add(q1);
        qualifications.add(q2);
        Worker workerWithQuals = new Worker("test", qualifications, 1.0);
        assertEquals(workerWithQuals.getQualifications().size(), 3);
    }

    @Test
	public void testRemoveQualifications() {
		Set<Qualification> attemptDamage = worker.getQualifications();
		int qualSize = attemptDamage.size();

		attemptDamage.remove(q);
		assertEquals(0, attemptDamage.size());

		Set<Qualification> checkUnchanged = worker.getQualifications();
		assertEquals(qualSize, checkUnchanged.size());
	}

    /*** addQualifications */
    @Test
    public void testAddQualificationsReturnsCorrectNumberOfQuals() {
        Qualification q1 = new Qualification("q1");
        Qualification q2 = new Qualification("q2");
        worker.addQualification(q1);
        worker.addQualification(q2);
        assertEquals(worker.getQualifications().size(), 3);
    }

    @Test
    public void testAddQualificationsWithDuplicateQualsReturnsCorrectNumberOfQuals() {
        Qualification q1 = new Qualification("q1");
        worker = new Worker("test", qualifications, 1.0);
        worker.addQualification(q1);
        worker.addQualification(q1);
        assertEquals(worker.getQualifications().size(), 2);
    }

    @Test
    public void testAddQualificationsWithTwoDuplicateQualsReturnsCorrectNumberOfQuals() {
        Qualification q1 = new Qualification("q1");
        Qualification q2 = new Qualification("q2");
        worker = new Worker("test", qualifications, 1.0);
        worker.addQualification(q1);
        worker.addQualification(q1);
        worker.addQualification(q2);
        worker.addQualification(q2);
        assertEquals(worker.getQualifications().size(), 3);
    }

    @Test
    public void testAddNullQualificationThrowsException(){
        thrown.expect( IllegalArgumentException.class );
        worker.addQualification(null);
    }

    /*** getProjects - addProjects - removeProjects */
    @Test
    public void testGetProjectsReturnsEmptySet(){
        assertEquals(worker.getProjects().size(), 0);
    }

    @Test
    public void testGetProjectsReturnsCorrectNumberOfProjects(){
        Project proj = new Project("p1", qualifications, ProjectSize.BIG);
        worker.addProject(proj);
        assertEquals(worker.getProjects().size(), 1);
    }

    @Test
    public void testAddingDuplicateProjects(){
        Project proj = new Project("p1", qualifications, ProjectSize.BIG);
        worker.addProject(proj);
        worker.addProject(proj);
        assertEquals(worker.getProjects().size(), 1);
    }

    @Test
    public void testWorkerDoesntLimitAddingProjects(){
        int numberSmallProjects = 13;
        for(Integer i = 0; i < numberSmallProjects; i++){
            String projectName = "p" + i.toString();
            Project p = new Project(projectName, qualifications, ProjectSize.BIG);
            worker.addProject(p);
        }
        assertEquals(worker.getProjects().size(), numberSmallProjects);
    }

    @Test 
    public void testAddNullProjectThrowsException(){
        thrown.expect( IllegalArgumentException.class );
        worker.addProject(null);
    }

    @Test
    public void testRemoveProject(){
        Project project = new Project("test", qualifications, ProjectSize.BIG);
        worker.addProject(project);
        assertTrue(worker.getProjects().size() == 1);
        worker.removeProject(project);
        assertTrue(worker.getProjects().size() == 0);
    }

    @Test
    public void testRemoveProjectNotInWorkersProjects(){
        Project project = new Project("test", qualifications, ProjectSize.BIG);
        Project notWorkersProject = new Project("bad proj", qualifications, ProjectSize.SMALL);
        worker.addProject(project);
        assertTrue(worker.getProjects().size() == 1);
        worker.removeProject(notWorkersProject);
        assertTrue(worker.getProjects().size() == 1);
    }

    @Test
    public void testRemoveProjectWhenMultipleProjects(){
        Project project1 = new Project("test1", qualifications, ProjectSize.SMALL);
        Project project2 = new Project("test2", qualifications, ProjectSize.SMALL);
        Project project3 = new Project("test3", qualifications, ProjectSize.SMALL);
        worker.addProject(project1);
        worker.addProject(project2);
        worker.addProject(project3);
        assertTrue(worker.getProjects().size() == 3);
        worker.removeProject(project2);
        LinkedList<String> names = new LinkedList<String>();
        for(Project proj: worker.getProjects()){
            names.add(proj.getName());
        }
        assertTrue(names.contains("test1"));
        assertTrue(names.contains("test3"));
        assertTrue(worker.getProjects().size() == 2);
    }

    @Test
    public void testRemoveNullProjectThrowsException(){
        thrown.expect( IllegalArgumentException.class );
        worker.removeProject(null);
    }

    /**** WORKLOAD ****/

    @Test
    public void testGetWorkloadWithNoProjects(){
        assertTrue(worker.getWorkload() == 0);
    }

    @Test
    public void testGetWorkloadWithProject(){
        worker.addProject(new Project("p1", qualifications, ProjectSize.BIG));
        worker.addProject(new Project("p2", qualifications, ProjectSize.MEDIUM));
        worker.addProject(new Project("p3", qualifications, ProjectSize.SMALL));
        assertTrue(worker.getWorkload() == 6);
    }

    @Test
    public void testGetWorkloadWithFinishedProject(){
        Project p1 = new Project("p1", qualifications, ProjectSize.BIG);
        p1.setStatus(ProjectStatus.FINISHED);
        worker.addProject(p1);
        worker.addProject(new Project("p2", qualifications, ProjectSize.MEDIUM));
        worker.addProject(new Project("p3", qualifications, ProjectSize.SMALL));
        assertTrue(worker.getWorkload() == 3);
    }

    /**** WILLOVERLOAD ****/

    @Test
    public void testWillOverloadWithNullProject(){
        worker.willOverload(null);
        assertFalse(worker.willOverload(null));
    }

    @Test
    public void testWillOverload(){
        Project p1 = new Project("p1", qualifications, ProjectSize.BIG);
        Project p2 = new Project("p2", qualifications, ProjectSize.BIG);
        Project p3 = new Project("p3", qualifications, ProjectSize.BIG);
        Project p4 = new Project("p4", qualifications, ProjectSize.BIG);
        Project p5 = new Project("p5", qualifications, ProjectSize.SMALL);
        worker.addProject(p1);
        worker.addProject(p2);
        worker.addProject(p3);
        worker.addProject(p4);
        assertTrue("adding project overloads worker", worker.willOverload(p5));
    }

    @Test
    public void testWillNotOverload(){
        Project p1 = new Project("p1", qualifications, ProjectSize.SMALL);
        Project p2 = new Project("p2", qualifications, ProjectSize.SMALL);
        Project p3 = new Project("p3", qualifications, ProjectSize.SMALL);
        Project p4 = new Project("p4", qualifications, ProjectSize.SMALL);
        Project p5 = new Project("p5", qualifications, ProjectSize.SMALL);
        worker.addProject(p1);
        worker.addProject(p2);
        worker.addProject(p3);
        worker.addProject(p4);
        assertFalse("adding project will not overload worker", worker.willOverload(p5));
    }

    @Test
    public void testWillOverloadWithFinishedProjects(){
        Project p1 = new Project("p1", qualifications, ProjectSize.BIG);
        Project p2 = new Project("p2", qualifications, ProjectSize.BIG);
        Project p3 = new Project("p3", qualifications, ProjectSize.BIG);
        Project p4 = new Project("p4", qualifications, ProjectSize.BIG);
        Project p5 = new Project("p5", qualifications, ProjectSize.SMALL);
        worker.addProject(p1);
        worker.addProject(p2);
        worker.addProject(p3);
        worker.addProject(p4);
        assertTrue("adding project will overload before one of the 4 current projects is finished", worker.willOverload(p5));
        p4.setStatus(ProjectStatus.FINISHED);
        assertFalse("adding project overloads worker", worker.willOverload(p5));
    }

    @Test
    public void testWillReturnFalseWithSameProject(){
        Project p1 = new Project("p1", qualifications, ProjectSize.BIG);
        Project p2 = new Project("p2", qualifications, ProjectSize.BIG);
        Project p3 = new Project("p3", qualifications, ProjectSize.BIG);
        Project p4 = new Project("p4", qualifications, ProjectSize.BIG);
        worker.addProject(p1);
        worker.addProject(p2);
        worker.addProject(p3);
        worker.addProject(p4);
        assertFalse("adding duplicate project throws exception", worker.willOverload(p4));
    }

    /**** ISAVAILABLE ****/
    
    @Test
    public void testIsAvaialbleWithAvailibility(){
        Project p1 = new Project("p1", qualifications, ProjectSize.BIG);
        Project p2 = new Project("p2", qualifications, ProjectSize.BIG);
        worker.addProject(p1);
        worker.addProject(p2);
        assertTrue("Current workload = 6; Worker is available", worker.isAvailable());
    }

    @Test
    public void testIsAvailableWithNoAvailability(){
        Project p1 = new Project("p1", qualifications, ProjectSize.BIG);
        Project p2 = new Project("p2", qualifications, ProjectSize.BIG);
        Project p3 = new Project("p3", qualifications, ProjectSize.BIG);
        Project p4 = new Project("p4", qualifications, ProjectSize.BIG);
        worker.addProject(p1);
        worker.addProject(p2);
        worker.addProject(p3);
        worker.addProject(p4);
        assertFalse("Current workload = 12; worker is not available", worker.isAvailable());
    }

    /**** TODTO ****/
    @Test
    public void testToDTOAllValid(){
        Set<Qualification> quals = new HashSet<Qualification>();
		Qualification qual1 = new Qualification("qualification test-1");
		Qualification qual2 = new Qualification("qualification test-2");
        quals.add(qual1);
        quals.add(qual2);

        Worker validWorker = new Worker("Valid Worker", quals, 100.0);

        Project p1 = new Project("p1", quals, ProjectSize.MEDIUM);
        Project p2 = new Project("p2", quals, ProjectSize.MEDIUM);
        validWorker.addProject(p1);
        validWorker.addProject(p2);

        WorkerDTO workerDTO = validWorker.toDTO();

        assertEquals(workerDTO.getName(), validWorker.getName());
        assertEquals(((int)workerDTO.getSalary()), ((int)validWorker.getSalary()));
        assertEquals(workerDTO.getProjects().length, validWorker.getProjects().size());
        assertEquals(workerDTO.getQualifications().length, validWorker.getQualifications().size());
    }

    @Test
    public void testToDTOGetsCorrectName(){
        Worker w1 = new Worker("w1", qualifications, 10.0);
        assertEquals(w1.toDTO().getName(), "w1");

    }

    @Test
    public void testToDTOGetsCorrectSalary(){
        Worker w1 = new Worker("w1", qualifications, 10.0);
        assertEquals(w1.toDTO().getSalary(), 10.0, 0.001);

    }

    @Test
    public void testToDTOGetsCorrectWorkload(){
        Worker w1 = new Worker("w1", qualifications, 10.0);
        Project p1 = new Project("p1", qualifications, ProjectSize.BIG);
        w1.addProject(p1);
        assertEquals(w1.toDTO().getWorkload(), 3);

    }

    /****Leaving this test in for if/when the toString method in the DTO classes is fixed.****
    
    @Test
    public void testToDTOGetsCorrectProjects(){
        Project p1 = new Project("p1", qualifications, ProjectSize.BIG);
        Project p2 = new Project("p2", qualifications, ProjectSize.BIG);
        Worker w1 = new Worker("w1", qualifications, 10.0);
        Worker w2 = new Worker("w2", qualifications, 10.0);
        w1.addProject(p1);
        w1.addProject(p2);
        p1.addWorker(w1);
        p1.addWorker(w2);
        String test1 = "p1:0:PLANNED";
        //assertTrue(w1.toDTO().toString().contains("test1"));
        
        System.out.println(w1.toDTO().getQualifications());
        System.out.println(w1.toDTO().toString());
    }
     */
}
