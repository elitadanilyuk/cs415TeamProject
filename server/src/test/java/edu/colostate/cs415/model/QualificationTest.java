package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class QualificationTest {
	Qualification qualificationWithNullDesc;
	Qualification qualificationWithEmptyDesc;
	Qualification qualificationWithValidDesc;
	Set<Qualification> qualifications;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
    public void setUp() throws Exception {
		qualifications = new HashSet<Qualification>();
		qualificationWithValidDesc = new Qualification("test-description");
    }
	/***** Qualification ******/
	@Test
    public void testQualificationConstructorNullDesc(){
		thrown.expect(IllegalArgumentException.class);
		qualificationWithNullDesc = new Qualification(null);
    }

	@Test
	public void testQualificationConstructorWhiteSpace(){

		thrown.expect(IllegalArgumentException.class);
		qualificationWithEmptyDesc = new Qualification("    ");
	}

	@Test
    public void testQualificationConstructorEmptyDesc(){
		thrown.expect(IllegalArgumentException.class);
		qualificationWithEmptyDesc = new Qualification("");
    }

	/***** equals *****/

    @Test
    public void testEqualsReturnsFalseWithNullObject(){
        assertFalse("equals returns false with null object", qualificationWithValidDesc.equals(null));
    }

    @Test
    public void testEqualsReturnsFalseWithNonWorkerObject(){
        String nonQualification = "test";
        assertFalse("equals returns false with non qualification object", qualificationWithValidDesc.equals(nonQualification));
    }

	@Test
    public void testEqualsSelfValidOtherValid(){
        assertTrue("equals returns false with null name", qualificationWithValidDesc.equals(qualificationWithValidDesc));
    }

	@Test
    public void testEqualsOtherNull(){
        assertFalse("equals returns false with null name", qualificationWithValidDesc.equals(qualificationWithNullDesc));
    }

	@Test
    public void testEqualsOtherEmpty(){
        assertFalse("equals returns false with null name", qualificationWithValidDesc.equals(qualificationWithEmptyDesc));
    }

	@Test
	public void testEqualsWithTwoObjectsReturnsTrue(){
		Qualification q1 = new Qualification("test");
		assertFalse(qualificationWithValidDesc.equals(q1));
	}

	
	/***** hashCode *****/
	@Test
	public void testHashCodeWithValidString() {
		assertThat("Qualification.hashCode returns a non 0 code with a valid name", qualificationWithValidDesc.hashCode(), is(not(0)));
	}

	/***** toString *****/
	@Test
	public void testToStringWithValidString() {
		assertEquals("Qualification.toString returns the description \"test-description\"", qualificationWithValidDesc.toString(), "test-description");
	}

	 /*** addWorkers */
	 @Test
	 public void testAddWorkerNullWorker() {
		thrown.expect(IllegalArgumentException.class);
		Qualification qualWithWorkers = new Qualification("test");
		qualifications.add(qualWithWorkers);
		qualWithWorkers.addWorker(null);
	 }
	 @Test
	 public void testAddWorkerReturnsCorrectNumberOfWorkers() {
		Qualification qualWithWorkers = new Qualification("test");
		qualifications.add(qualWithWorkers);
		Worker w1 = new Worker("W1", qualifications, 1.0);
		Worker w2 = new Worker("W2", qualifications, 1.0);
		qualWithWorkers.addWorker(w1);
		qualWithWorkers.addWorker(w2);
		assertEquals(qualWithWorkers.getWorkers().size(), 2);
	 }
 
	 @Test
	 public void testAddWorkerWithDuplicateWorkersReturnsCorrectNumberOfWorkers() {
		Qualification qualWithWorkers = new Qualification("test");
		qualifications.add(qualWithWorkers);
		Worker w1 = new Worker("W1", qualifications, 1.0);
		qualWithWorkers.addWorker(w1);
		qualWithWorkers.addWorker(w1);
		assertEquals(qualWithWorkers.getWorkers().size(), 1);
	 }
 
	 @Test
	 public void testAddWorkerssWithTwoDuplicateWorkersReturnsCorrectNumberOfWorkers() {
		Qualification qualWithWorkers = new Qualification("test");
		qualifications.add(qualWithWorkers);
		Worker w1 = new Worker("W1", qualifications, 1.0);
		Worker w2 = new Worker("W2", qualifications, 1.0);
		qualWithWorkers.addWorker(w1);
		qualWithWorkers.addWorker(w1);
		qualWithWorkers.addWorker(w2);
		qualWithWorkers.addWorker(w2);
		assertEquals(qualWithWorkers.getWorkers().size(), 2);
	 }

	/*** removeWorkers */
	@Test
	public void testremoveWorkerWithValidWorkerEmptySet() {
		Qualification qualWithWorkers = new Qualification("test");
		qualifications.add(qualWithWorkers);
		Worker w1 = new Worker("W1", qualifications, 1.0);
		Qualification qualWithNoWorkers = new Qualification("test");
		qualWithNoWorkers.removeWorker((w1));
		assertEquals(qualWithNoWorkers.getWorkers().size(), 0);
	}

	@Test
	public void testremoveWorkerWithNullWorker() {
		thrown.expect(IllegalArgumentException.class);
		Qualification qualWithWorkers = new Qualification("test");
		qualifications.add(qualWithWorkers);
		Worker w1 = new Worker("W1", qualifications, 1.0);
		qualWithWorkers.addWorker(w1);
		qualWithWorkers.removeWorker(null);
	}

	@Test
	public void testRemoveWorkerWithValidWorkerLastInSet() {
		Qualification qualWithWorkers = new Qualification("test");
		qualifications.add(qualWithWorkers);
		Worker w1 = new Worker("W1", qualifications, 1.0);
		Worker w2 = new Worker("W2", qualifications, 1.0);
		Worker w3 = new Worker("W3", qualifications, 1.0);
		qualWithWorkers.addWorker(w1);
		qualWithWorkers.addWorker(w2);
		qualWithWorkers.addWorker(w3);
		qualWithWorkers.removeWorker((w3));
		assertEquals(qualWithWorkers.getWorkers().size(), 2);
	}

	@Test
	public void testRemoveWorkerWithValidWorkerMiddleOfSet() {
		Qualification qualWithWorkers = new Qualification("test");
		qualifications.add(qualWithWorkers);
		Worker w1 = new Worker("W1", qualifications, 1.0);
		Worker w2 = new Worker("W2", qualifications, 1.0);
		Worker w3 = new Worker("W3", qualifications, 1.0);
		qualWithWorkers.addWorker(w1);
		qualWithWorkers.addWorker(w2);
		qualWithWorkers.addWorker(w3);
		qualWithWorkers.removeWorker((w2));
		assertEquals(qualWithWorkers.getWorkers().size(), 2);
	}

	@Test
	public void testRemoveWorkerWithValidWorkerFirstInSet() {
		Qualification qualWithWorkers = new Qualification("test");
		qualifications.add(qualWithWorkers);
		Worker w1 = new Worker("W1", qualifications, 1.0);
		Worker w2 = new Worker("W2", qualifications, 1.0);
		Worker w3 = new Worker("W3", qualifications, 1.0);
		qualWithWorkers.addWorker(w1);
		qualWithWorkers.addWorker(w2);
		qualWithWorkers.addWorker(w3);
		qualWithWorkers.removeWorker((w1));
		assertEquals(qualWithWorkers.getWorkers().size(), 2);
	}

	@Test
	public void testRemoveWorkerWithInvalidWorker() {
		Qualification qualWithWorkers = new Qualification("test");
		qualifications.add(qualWithWorkers);
		Worker w1 = new Worker("W1", qualifications, 1.0);
		Worker w2 = new Worker("W2", qualifications, 1.0);
		Worker w3 = new Worker("W3", qualifications, 1.0);
		qualWithWorkers.addWorker(w1);
		qualWithWorkers.addWorker(w2);
		qualWithWorkers.removeWorker((w3));
		assertEquals(qualWithWorkers.getWorkers().size(), 2);
	}

	 /*** getWorkers *****/
	 @Test
	 public void testGetWorkersEmptySet() {
		Qualification q1 = new Qualification("test");
		assertEquals(q1.getWorkers().size(), 0);
	 }
 
	 @Test
	 public void testGetWorkersReturnsCorrectNumberOfWorkers() {
		Qualification qualWithWorkers = new Qualification("test");
		qualifications.add(qualWithWorkers);
		Worker w1 = new Worker("W1", qualifications, 1.0);
		Worker w2 = new Worker("W2", qualifications, 1.0);
		qualWithWorkers.addWorker(w1);
		qualWithWorkers.addWorker(w2);
		assertEquals(qualWithWorkers.getWorkers().size(), 2);
	 }

	 @Test
	 public void testGetWorkersDoesNotChangeThroughReference() {
		Qualification qualWithWorkers = new Qualification("test");
		qualifications.add(qualWithWorkers);
		Worker worker = new Worker("W1", qualifications, 1.0);
		Qualification qual = new Qualification("test");
		Set<Worker> workersClone = qual.getWorkers();
		workersClone.add(worker);
		assertFalse(qual.getWorkers().contains(worker));
	 }

	 /**** toDTO *****/

	@Test
	public void testtoDTOValidDescriptionFullSet() {
		Qualification qualWithWorkers = new Qualification("test");
		qualifications.add(qualWithWorkers);
		Worker w1 = new Worker("W1", qualifications, 1.0);
		Worker w2 = new Worker("W2", qualifications, 1.0);
		qualWithWorkers.addWorker(w1);
		qualWithWorkers.addWorker(w2);
		assertEquals(qualWithWorkers.toDTO().getDescription(), "test");
	}

	public void testtoDTOValidDescriptionEmptySet() {
		assertEquals(qualificationWithValidDesc.toDTO().getDescription(), "test-description");
	}

	public void testtoDTOEmptyDescriptionEmptySet() {
		assertEquals(qualificationWithEmptyDesc.toDTO().getDescription(), "");
	}

	public void testtoDTONullDescriptionEmptySet() {
		assertEquals(qualificationWithNullDesc.toDTO().getDescription(), null);
	}


}
