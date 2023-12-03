# Qualification

### 1. `Qualification( description )`
<table>
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
    <td> Test Method>
  </tr>
  <tr>
    <td rowspan="3">Description</td>
    <td rowSpan="3">Content</td>
    <td> Null </td>
    <td> null </td>
    <td>QualificationTest.testToStringWithNullString()</td>
  <tr>
    <td> Not Null</td>
    <td> "Test" </td>
    <td>QualificationTest.testToStringWithValidString()</td>
  </tr>
  <tr>
    <td> Empty </td>
    <td> "" </td>
    <td>QualificationTest.testToStringWithEmptyString()</td>
  </tr>
</table>


### 2. `equals( o )`
<table>
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
    <td>Test Methods</td>
  </tr>
  <tr>
    <td rowspan="3">Self</td>
    <td rowSpan="3">Description</td>
    <td> Null </td>
    <td> null </td>
    <td> QualificationTest.testEqualsSelfNull()<td>
  </tr>
  <tr>
    <td> Not Null</td>
    <td> "test-description"</td>
    <td> QualificationTest.testSelfValidOtherValid()</td>
  </tr>
  <tr>
    <td> Empty </td>
    <td> "" </td>
    <td>QualificationTest.testEqualsSelfEmpty()</td>
  </tr>
  <tr>
    <td rowspan="5">Other</td>
    <td rowSpan="2">Object Type</td>
    <td> Qualification </td>
    <td> Qualification.class </td>
    <td> QualificationTest.testEqualsSelfValidOtherValid()</td>
  </tr>
  <tr>
    <td> Not Qualification </td>
    <td> String.class </td>
    <td> QualificationTest.testEqualsReturnsFalseWithNonWorkerObject()</td>
  </tr>
  <tr>
    <td rowSpan="3">Description</td>
    <td> Null </td>
    <td> null </td>
    <td>QualificationTest.testEqualsOtherNull()</td>
  </tr>
  <tr>
    <td> Not Null</td>
    <td> "Test" </td>
    <td>QualificationTest.testEqualsSelfValidOtherValid()</td>
  </tr>
  <tr>
    <td> Empty </td>
    <td> "" </td>
    <td>QualificationTest.testEqualsOtherEmpty()</td>
  </tr>

</table>

### 3. `hashCode( )`
<table>
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
    <td>Test Methods</td>
  </tr>
  <tr>
    <td rowspan="3">Description</td>
    <td rowSpan="3">Content</td>
    <td> Null </td>
    <td> null </td>
    <td>QualificationTest.testHashCodeWithNullString()</td>
  <tr>
    <td> Not Null</td>
    <td> "Test" </td>
    <td>QualificaitonTest.testHashCodeWithValidString()</td>
  </tr>
  <tr>
    <td> Empty </td>
    <td> "" </td>
    <td>QualificationTest.testHashCodeWithEmptyString()</td>
  </tr>
</table>

### 4. `toString( )`
<table>
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
    <td>Test Methods</td>
  </tr>
  <tr>
    <td rowspan="3">Description</td>
    <td rowSpan="3">Content</td>
    <td> Null </td>
    <td> null </td>
    <td>QualificationTest.testToStringWithNullString()</td>
  <tr>
    <td> Not Null</td>
    <td> "Test" </td>
    <td>QualificationTest.testToStringWithValidString()</td>
  </tr>
  <tr>
    <td> Empty </td>
    <td> "" </td>
    <td>QualificationTest.testToStringWithEmptyString()</td>
  </tr>
</table>

### 5. `getWorkers( )`
<table>
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
    <td>Test Method</td>
  </tr>
  <tr>
    <td rowspan="2">Workers<></td>
    <td rowspan="2">Relationship to Capacity</td>
    <td> Empty </td>
    <td> [] </td>
    <td>QualificationsTest.testGetWorkersEmptySet()</td>
  </tr>
  <tr>
    <td> Not Empty </td>
    <td> [w1, w2] </td>
    <td> QualificationsTest.testGetWorkersReturnsCorrectNumberOfWorkers()</td>
  </tr>
</table>

### 6. `addWorker( w )` 7. `removeWorker( w )`
<table>
  <!--Titles-->
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
    <td> Test Method </td>
  </tr>
<!--Workers-Capacity-->
  <tr>
    <td rowspan="2">Workers<></td>
    <td rowspan="2">Relationship to Capacity</td>
    <td> Empty </td>
    <td> [] </td>
    <td>QualificationTest.testRemoveWorkerWithValidWorkerEmptySet()<br> QualificationTest.testAddWorkerReturnsCorrectNumberOfWorkers()</td>
    <td>
  </tr>
  <tr>
    <td> Not Empty </td>
    <td> [w1, w2] </td>
  <td>QualificationTest.testAddWorkerWithDuplicateWorkersReturnsCorrectNumberOfWorkers() <br> QualificationTest.testRemoveWorkerWithValidWorkerMiddleOfSet()</td>
  </tr>
<!--Worker-Name-->
  <tr>
    <td rowspan="9">Worker</td>
    <td rowspan="3">relationship to workers<> set</td>
    <td> In the set</td>
    <td> w1 </td>
    <td>QualificationTest.testAddWorkerWithDuplicateWorkersReturnsCorrectNumberOfWorkers() <br> QualificationTest.testRemoveWorkerWithValidWorkerFirstInSet()</td>
  </tr>
  <tr>
    <td>Not in the set</td>
    <td>w5</td>
    <td> QualificationTest.	public void testremoveWorkerWithValidWorkerEmptySet() {
() <br> QualificationTest.testAddWorkerReturnsCorrectNumberOfWorkers()</td>
  </tr>
  
</table>

### 8. `toDTO( )`
<table>
<!--Titles-->
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
  </tr>
  <tr>
<!--Description-Content-->
    <td rowspan="3">Description</td>
    <td rowSpan="3">Content</td>
    <td> Null </td>
    <td> null </td>
    <td> QualificationTest.testtoDTONullDescriptionEmptySet()</td>
  <tr>
    <td> Not Null</td>
    <td> "Test" </td>
    <td>QualificationTest.testtoDTOValidDescriptionEmptySet()</td>
  </tr>
  <tr>
    <td> Empty </td>
    <td> "" </td>
    <td> QualificationTest.testtoDTOEmptyDescriptionEmptySet()</td>
  </tr>
<!--Workers-->
  <tr>
    <td rowspan="4">Workers<></td>
    <td rowspan="2">Relationship to Capacity</td>
    <td> Empty </td>
    <td> [] </td>
    <td> QualificationTest.testtoDTOValidDescriptionEmptySet()</td>
  </tr>
  <tr>
    <td> Not Empty </td>
    <td> [w1, w2] </td>
    <td> QualificationTest.testtoDTOValidDescriptionFullSet()</td>
  </tr>
</table>


# Worker

### 1. `Worker( name, qs, salary )`
<table>
<thead>
  <tr>
    <th>Variable</th>
    <th>Characteristic</th>
    <th>Partition</th>
    <th>Value</th>
     <th>Tests</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>name</td>
    <td>name</td>
    <td>null <br> empty <br> not empty </td>
    <td>null <br> "" <br> "name"</td>
<td> testConstructorThrowsExceptionWithNullName <br> testConstructorThrowsExceptionWithEmptyName
</td>

  </tr>
  <tr>
    <td>qs</td>
    <td>nullness <br> emptiness </td>
    <td>null <br> empty <br> not empty</td>
    <td>null <br> [] <br> [q1,q2] </td>
<td> testConstructorThrowsExceptionWithNullQuals</td>

  </tr>
  <tr>
    <td>salary</td>
    <td>value</td>
    <td>value&lt; 0, value == 0,&nbsp;&nbsp;value &gt; 0</td>
    <td>-1, 0,&nbsp;&nbsp;1</td>
<td> testConstructorThrowsExceptionWithZeroSalary</td>

  </tr>

<td>( Other tests cover the valid cases )</td>


</tbody>
</table>

### 2. `equals( o )`
<table>
<thead>
  <tr>
    <th>Variable</th>
    <th>Characteristic</th>
    <th>Partition</th>
    <th>Value</th>
     <th>Tests</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>self</td>
    <td>name <br> nullness <br> objectType</td>
    <td>not empty <br> not null <br>Worker</td>
    <td>"name"<br> not null <br> Worker.class </td>
<td> testEqualsReturnsFalseWithNonWorkerObject <br> testEqualsReturnsFalse </td>
  </tr>
  <tr>
    <td>other</td>
    <td>name <br><br> nullness <br> objectType</td>
    <td>null <br> empty <br> not empty <br> Worker <br> Not Worker </td>
    <td>null <br> "" <br> "name" <br> Worker.class <br> String.class </td>
<td> testEqualsReturnsFalseWithNullObject <br> testEqualsReturnsTrue 
 </td>
  </tr>
</tbody>
</table>

### 3. `hashCode( )`

<table>
<thead>
  <tr>
    <th>Variable</th>
    <th>Characteristic</th>
    <th>Partition</th>
    <th>Value</th>
     <th>Tests</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Worker (self)</td>
    <td>name</td>
    <td>(Can only be a valid string due to constructor)</td>
    <td>"name"<br></td>
<td> testHashCodeWithValidString </td>
  </tr>
</tbody>
</table>

### 4. `toString( )`

<table>
<thead>
  <tr>
    <th>Variable</th>
    <th>Characteristic</th>
    <th>Partition</th>
    <th>Value</th>
     <th>Tests</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Worker (self)</td>
    <td>name <br> salary <br> projects <br> qualifications </td>
    <td>name: can only be a valid string<br>salary: can only be &gt; 0<br>projects: empty or non empty<br>qualifications: empty or non empty <br><br></td>
    <td>"name"<br>12.2<br>List of projects or Empty List of projects<br>List of Quals or Empty List of Quals</td>
<td> testToString <br> testToStringWithQuals <br> testToStringWithQualsAndNonZeroSalary <br> testToStringWithIntSalary </td>
  </tr>
</tbody>
</table>

### 5. `getName( )`
<table>
<thead>
  <tr>
    <th>Variable</th>
    <th>Characteristic</th>
    <th>Partition</th>
    <th>Value</th>
     <th>Tests</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Worker (self)</td>
    <td>name</td>
    <td>name: can only be a valid string</td>
    <td>"name"</td>
<td>  testGetNameWithNonEmptyName </td>
  </tr>
</tbody>
</table>

### 6. `getSalary( )`
<table>
<thead>
  <tr>
    <th>Variable</th>
    <th>Characteristic</th>
    <th>Partition</th>
    <th>Value</th>
    <th>Tests</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Worker (self)</td>
    <td>salary</td>
    <td>name: can only be > than 0</td>
    <td>12.2</td>
    <td>testGetSalary</td>
  </tr>
</tbody>
</table>


### 7. `setSalary( salary )`
<table>
<thead>
  <tr>
    <th>Variable</th>
    <th>Characteristic</th>
    <th>Partition</th>
    <th>Value</th>
     <th>Tests</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>salary</td>
    <td>value</td>
    <td>value &gt; 0 <br> value = 0 <br> value &lt; 0</td>
    <td>12.2 <br> 0 <br> -2.3</td>
<td> testSetSalary<br>testSetSalaryToZeroThrowsException<br> testSetSalaryToNegativeThrowsException</td>
  </tr>
</tbody>
</table>


### 8. `getQualifications( )`
<table>
<thead>
  <tr>
    <th>Variable</th>
    <th>Characteristic</th>
    <th>Partition</th>
    <th>Value</th>
     <th>Tests</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>qualifications</td>
    <td>emptiness</td>
    <td>empty <br> not empty</td>
    <td>[] <br> [q1,q2]</td>
<td>testGetQualificationsReturnsEmptySet <br> testGetQualificationsReturnsCorrectNumberOfQuals </td>
  </tr>
</tbody>
</table>


### 9. `addQualifications( q )`
<table>
<thead>
  <tr>
    <th>Variable</th>
    <th>Characteristic</th>
    <th>Partition</th>
    <th>Value</th>
     <th>Tests</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Qualifications</td>
    <td>Empty or not empty </br> nullness </td>
    <td>List with no Qualifications <br> List with Qualification </br> null </td>
    <td>[] <br> [q1,q2] <br> null</td>
<td> testAddQualificationsReturnsCorrectNumberOfQuals<br> testAddQualificationsWithDuplicateQualsReturnsCorrectNumberOfQuals<br>testAddQualificationsWithTwoDuplicateQualsReturnsCorrectNumberOfQuals <br> testAddNullQualificationThrowsException<td>
  </tr>
</tbody>
</table>

### 10. `getProjects( )`
<table>
<thead>
  <tr>
    <th>Variable</th>
    <th>Characteristic</th>
    <th>Partition</th>
    <th>Value</th>
     <th>Tests</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>projects</td>
    <td>emptiness</td>
    <td>empty <br> not empty</td>
    <td>[] <br> [p1,p2]</td>
<td> testGetProjectsReturnsEmptySet <br> testGetProjectsReturnsCorrectNumberOfProjects </td>
  </tr>
</tbody>
</table>


### 11. `addProject( p )` 12. `removeProject( p )`

<table>
<thead>
  <tr>
    <th>Variable</th>
    <th>Characteristic</th>
    <th>Partition</th>
    <th>Value</th>
     <th>Tests</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Project</td>
    <td> nullness </td>
    <td>null <br> not-null </td>
    <td>null <br> a project</td>
<td>testAddingDuplicateProjects <br> testWorkerDoesntLimitAddingProjects<br> testAddNullProjectThrowsException<br>testRemoveProject <br>testRemoveProjectNotInWorkersProjects <br>testRemoveProjectWhenMultipleProjects <br> testRemoveNullProjectThrowsException </td>
  </tr>
</tbody>
</table>


### 13. `getWorkload( )`
<table>
<thead>
  <tr>
    <th>Variable</th>
    <th>Characteristic</th>
    <th>Partition</th>
    <th>Value</th>
     <th>Tests</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>workload</td>
    <td>value</td>
    <td>value &gt; 0 <br> value == 0</td>
    <td>12  <br> 0 </td>
<td>testGetWorkloadWithNoProjects <br>testGetWorkloadWithProject <br>testGetWorkloadWithFinishedProject </td>
  </tr>
</tbody>
</table>


### 14. `willOverload( p )`
<table>
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
     <th>Tests</th>
  </tr>
  <tr>
    <td rowspan="2">Worker</td>
    <td rowSpan="2">Workload</td>
    <td> value >= 12 </td>
    <td> 13 </td>
<td> testWillOverload </td>
  </tr>
  <tr>
    <td> value < 12 </td>
    <td> 9 </td>
<td> WorkerTest.testWillNotOverloadWithSameProject() <br> WorkerTest.testWillNotOverloadWithFinishedProjects()</td>
  </tr>
  <tr>
    <td rowspan="2">Project</td>
    <td rowSpan="2">Nullness</td>
    <td> null Project </td>
    <td> null </td>
<td> WorkerTest.testWillOverloadWithNullProject() </td>
  </tr>
  <tr>
    <td> valid Project </td>
    <td> Project </td>
     <td> WorkerTest.testWillOverload() </td>
  </tr>
</table>


### 15. `isAvailable( )`
<table>
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
    <td>Tests</td>
  </tr>
  <tr>
    <td rowspan="2">Worker</td>
    <td rowSpan="2">Workload</td>
    <td> value >= 12 </td>
    <td> 12 </td>
    <td>WorkerTest.testIsAvailableWithNoAvailability() </td>
  </tr>
  <tr>
    <td> value < 12 </td>
    <td> 6 </td>
    <td> WorkerTest.testIsAvaialbleWithAvailibility() <td>
  </tr>
</table>


### 16. `toDTO( )`
<table>
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
    <td>Tests</td>
  </tr>
  <tr>
    <td rowspan ="3"> Worker DTO </td>
    <td> Name </td>
    <td> Valid Name</td>
    <td> "w1" </td>
    <td> WorkerTest.testToDTOGetsCorrectName() </td>
  </tr>
  <tr>
    <td> Salary </td>
    <td> Valid Salary </td>
    <td> 10.0 </td>
    <td> WorkerTest.testToDTOGetsCorrectSalary() </td>
  </tr>
  <tr>
    <td> Workload </td>
    <td> Valid Workload </td>
    <td> 3 </td>
    <td> WorkerTest.testToDTOGetsCorrectWorkload() <br> WorkerTest.testToDTOAllValid() </td>
  </tr>
</table>


# Project

### 1. `Project( name, qs, size )`
<img width="599" alt="Screenshot 2023-02-13 at 2 02 43 PM" src="https://user-images.githubusercontent.com/71053850/218573841-daecbebc-e919-4fb2-93ea-99576a8a4462.png">

### 2. `equals( o )`
<img width="716" alt="Screenshot 2023-02-13 at 2 07 44 PM" src="https://user-images.githubusercontent.com/71053850/218574733-7f47841d-816d-4781-b64e-5898c4152496.png">


### 3. `hashCode( )`
<img width="614" alt="Screenshot 2023-02-13 at 2 04 14 PM" src="https://user-images.githubusercontent.com/71053850/218574094-7e48a7c8-4ede-4eff-b440-097c89b7abce.png">


### 4. `toString( )`
<img width="650" alt="Screenshot 2023-02-13 at 2 14 04 PM" src="https://user-images.githubusercontent.com/71053850/218575905-33d7365c-6de4-47a2-93bb-d13d40d5f87b.png">


### 5. `getName( )`
<img width="631" alt="Screenshot 2023-02-13 at 2 09 49 PM" src="https://user-images.githubusercontent.com/71053850/218575115-0038ad9e-922a-4cf9-a256-7be318cb85eb.png">


### 6. `getSize( )`
<img width="553" alt="Screenshot 2023-02-13 at 2 11 21 PM" src="https://user-images.githubusercontent.com/71053850/218575353-c5237ed0-be9a-4534-a719-2f8e6ce479a9.png">


### 7. ~~`setSize( ps )`~~

### 8. `getStatus( )`
<table>
<thead>
  <tr>
    <th>Variable</th>
    <th>Characteristic</th>
    <th>Partition</th>
    <th>Value</th>
    <th>Tests</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Project (self)</td>
    <td>status</td>
    <td>status: can only be set to a ProjectStatus</td>
    <td>PLANNED</td>
<td>ProjectTest.testGetStatus()</td>
  </tr>
</tbody>
</table>


### 9. `setStatus( s )`
<table>
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
    <td>Tests</td>
  </tr>
  <tr>
    <td rowspan="2"> Project Status </td>
    <td rowspan="2"> Status </td>
    <td> not-null </td>
    <td> ACTIVE </td>
    <td> ProjectTest.testSetStatus() </td>
  </tr>
  <tr>
    <td> null </td>
    <td> null </td>
    <td> ProjectTest.testSetStatusNull() </td>
</table>


### 10. `addWorker( w )` 11. `removeWorker( w )`
<table>
  <!--Titles-->
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
    <td> Test Method </td>
  </tr>
<!--Workers-Capacity-->
  <tr>
    <td rowspan="2">Workers<></td>
    <td rowspan="2">Relationship to Capacity</td>
    <td> Empty </td>
    <td> [] </td>
    <td>ProjectTest.testRemovingNonExistentWorker()<br> ProjectTest.testAddWorkerWithOneWorker()</td>
    <td>
  </tr>
  <tr>
    <td> Not Empty </td>
    <td> [w1, w2] </td>
  <td>ProjectTest.testAddWorkerWithTwoIdenticalyWorkers() <br> ProjectTest.testRemovingValidWorker()</td>
  </tr>
<!--Worker-Name-->
  <tr>
    <td rowspan="9">Worker</td>
    <td rowspan="3">relationship to workers<> set</td>
    <td> In the set</td>
    <td> w1 </td>
    <td>ProjectTest.testAddWorkerWithTwoIdenticalyWorkers() <br> ProjectTest.testRemovingNonExistentWorker()</td>
  </tr>
  <tr>
    <td>Not in the set</td>
    <td>w2</td>
    <td> ProjectTest.testRemovingNonExistentWorker() <br> ProjectTest.testAddWorkersWithMultipleWorkers()</td>
  </tr>
  
</table>

### 12. `getWorkers( )`
<table>
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
    <td>Tests</td>
  </tr>
  <tr>
    <td rowspan="2"> Worker<> </td>
    <td rowspan="2"> Relationship to Capacity </td>
    <td> Empty </td>
    <td> [] </td>
    <td> ProjectTest.testGetWorkers() </td>
  </tr>
  <tr>
    <td> Not Empty </td>
    <td> [w1] </td>
    <td> ProjectTest.testGetWorkersWithMultipleWorkers() </td>
  </tr>
</table>


### 13. `removeAllWorkers( )`
<table>
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
    <td>Tests</td>
  </tr>
  <tr>
    <td rowspan="2"> Workers<> </td>
    <td rowspan="2"> Relationship to Capacity </td>
    <td> Empty </td>
    <td> [] </td>
    <td> ProjectTest.testRemoveAllWorkersWithNoWorkers() </td>
  </tr>
  <tr>
    <td> Not Empty </td>
    <td> [w1, w2, w3] </td>
    <td> ProjectTest.testRemoveAllWorkersWithMultipleWorkers() </td>
  </tr>
</table>


### 14. `getRequiredQualifications( )`
<table>
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
  </tr>
<!--Qualifications-Nullness-->
  <tr>
    <td rowspan="4">Qualifications<></td>
    <td rowspan="2">Relationship to Capacity</td>
    <td> Empty </td>
    <td> [] </td>
    <td> ProjectTest.testgetRequiredQualificationsEmptyset()<td>
  </tr>
  <tr>
    <td> Not Empty </td>
    <td> [w1, w2] </td>
    <td> ProjectTest.testgetRequiredQualificationsReturnsCorrectNumberOfQuals()<td>
  </tr>
</table>


### 15. `addQualification( q )`
<table>
  <!--Titles-->
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
    <td>Test Method<td>
  </tr>
<!--Qualifications-Nullness-->
  <tr>
    <td rowspan="5">Qualifications<></td>
    <td rowspan="2">Relationship to Capacity</td>
    <td> Empty </td>
    <td> [] </td>
    <td>ProjectTest.testAddQualificationsReturnsCorrectNumberOfQuals()</td>
  </tr>
  <tr>
    <td> Not Empty </td>
    <td> [w1, w2] </td>
    <td>ProjectTest.testAddQualificationsWithDuplicateQualsReturnsCorrectNumberOfQuals()</td>
  </tr>
<!--QualificationsName-->
  <tr>
    <td rowspan="3">Qualification.Description</td>
    <td> Null </td>
    <td> null </td>
    <td>ProjectTest.testAddQualificationsNullQual()</td>
  </tr>
  <tr>
    <td>Empty</td>
    <td>""</td>
    <td>ProjectTest.testAddQualificationsEmptyQual()</td>
  </tr>
  <tr>
    <td>Not Empty</td>
    <td>"q1"</td>
    <td>ProjectTest.testAddQualificationsReturnsCorrectNumberOfQuals()</td>
  </tr>
</table>


### 16. `getMissingQualifications( )`
<table>
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
    <td>Tests</td>
  </tr>
  <tr>
    <td rowspan="3"> Qualifications<> </td>
    <td rowspan="3"> Relationship to capacity </td>
    <td> None Missing </td>
    <td> [] </td>
    <td> ProjectTest.testMissingQualificationsWithZeroMissing() </td>
  </tr>
  <tr>
    <td> Missing </td>
    <td> [w1,w2] </td>
    <td> ProjectTest.testMissingQualificationsWithOneMissing()<br> ProjectTest.testMissingQualificationsWithMultipleMissingWithSpecifications()<br> ProjectTest.testMissingQualificationsWithMultipleQualsPerWorker()</td>
  </tr>
</table>


### 17. `isHelpful( w )`
<table>
  <tr>
    <td>Variable</td>
    <td>Characteristic</td>
    <td>Partition</td>
    <td>Value</td>
    <td>Tests</td>
  </tr>
  <tr>
    <td rowspan="2"> Boolean </td>
    <td rowspan="2"> Is Helpful </td>
    <td> False </td>
    <td> [w1, w2] </td>
    <td> ProjectTest.testIsHelpfulWhenNotHelpful()<br> ProjectTest.testIsHelpfulWithMissingProjectQualificationsButNotHelpfulWorker() </td>
  </tr>
  <tr>
    <td> True </td>
    <td> [w1, w2] </td>
    <td> ProjectTest.testIsHelpfulWhenHelpful() </td>
  </tr>
</table>


### 18. `toDTO( )`
<img width="719" alt="Screenshot 2023-02-13 at 2 17 37 PM" src="https://user-images.githubusercontent.com/71053850/218576541-29038469-55f6-4fb7-a339-f2b19611eea8.png">
