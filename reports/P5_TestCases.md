# Tests 

## 1. View company qualifications. 

1.1 View qualifications from clicking on Qualifications Tab 
- Click on the Qualifications tab.  
- Verify that the list of qualifications matches what's in the database (DB).  

1.2 View qualifications from clicking on a qualification link from Project tab 
- Click on the Project tab.  
- Click on a project. 
- See that a list of qualifications and missing qualifications are displayed 
- Click on a qualification 
- Verify that you're redirected to the Qualifications tab and the list of qualifications matches what's in the DB. 

## 2. View company employed worker. 

2.1 View workers from clicking on Workers Tab 
- Click on the Workers tab. 
- Verify that the list of workers matches what's in the DB. 

2.2 View workers from clicking on a worker link from Qualification tab 
- Click on the qualifications tab.  
- Click on a qualification. 
- See that a worker or list of workers is displayed` 
- Click on a worker's name 
- Verify that you're redirected to the Workers tab and the list of workers matches what's in the DB. 

## 3.  View company projects.
3.1 View Projects from clicking on Projects tab
- Click on the Projects tab.
- Verify that the list of projects matches what's in the DB.

3.2 View Projects from clicking on a project link from the Workers tab
- Click on the Workers tab
- Click on a worker
- See that a worker or list of workers is displayed
- Click on a worker's name
- Click on a a listed project under the worker
- Verify that you are redirected to the Projects tab and that the list of projects matches what's in the DB

## 4.  View qualification details.

4.1 View Qualifications from clicking on Qualification tab
- Click on Qualifications tab.
- Click on individual Qualification
- Verify the table expands to show description and list of workers
- Verify the description and list of workers matches what's in the DB

4.2 View Workers from clicking on the worker link from Qualifications tab
- Click on the Qualifications tab
- Click on a specific Qualification
- Click on worker name from list of workers
- Verify that you're redirected to the matching worker in the Worker's tab  

## 5.  View worker details.  

5.1 View worker details from clicking on a Worker in the table
- Click on the Workers tab.
- Click on an individual worker in the table
- Verify that the table expands to show the worker's details
- Verify that the worker's salary, workload, qualifications, and projects all match what's in the DB.

5.2 View Qualifications from clicking on a qualification link in a Worker's details
- Click on the Workers tab.
- Click on an individual worker in the table
- Click on a qualification in the worker's list of qualifications
- Verify that you're redirected to the matching qualification in the Qualifications tab.

5.3 View projects from clicking on a project link in a worker's details
- Click on the Workers tab.
- Click on an individual worker in the table
- Click on a project in the worker's list of projects
- Verify that you're redirected to the matching project in the Projects tab.

## 6.  View project details.
6.1 View project details from clicking on a project in the projects list
- Click on the Projects tab
- Click on an individual project in the list
- Verify that the table expands to show the project's details
- Verify that the following match what's in the DB.
   - Name
   - Size
   - Status
   - Assigned employees
   - Qualifications
      - Required qualifications (both the red and green highlighted qualifications)
      - Missing qualifications (the red highlighted qualifications)

6.2 View Workers from clicking on the Assigned Employees in the Project's details
- Click on the Projects tab
- Click on an individual project in the list
- Click on a worker in the project's list of assigned employees
- Verify that you're redirected to the matching worker in the Workers tab

6.3 View Qualifications from clicking on the Qualifications in the Project's details
- Click on the Projects tab
- Click on an individual project in the list
- Click on a qualification in the project's list of qualifications
- Verify that you're redirected to the matching qualification in the Qualifications tab

## 7.  Create new qualification. 

7.1 A qualification can be created 
- Click on the qualifications tab. 
- Verify that the list of qualifications matches what's in the DB. 
- Enter a description for a new qualification.  
- Click "Create Qualification" 
- Verify that the new qualifaction is now in the list. 
 
7.2 A qualifaction is not created when the description matches an existing qualification 
- Click on the qualifications tab. 
- Verify that the list of qualifications matches what's in the DB. 
- Enter a description that matches an existing qualification.  
- Click "Create a Qualification" 
- Verify that there are no duplicate qualifications. 

## 8.  Create new worker.

8.1 A worker can be created
- Click on worker tab
- Verify that the list of workers matches what's in the DB
- Enter the following information:
   - A valid worker name
   - A valid worker salary
   - A valid qualification
- Click "Create Worker"
- Verify that the new worker is in the workers list

8.2 A worker is not created when the name is empty or blank
- Click on the worker tab
- Verify that the list of workers matches what's in the DB
- Enter the following information:
   - An empty or blank worker name
   - A valid worker salary
   - A valid qualification
- Click "Create Worker"
- Verify that an error message displays that there is missing required information

8.3 A worker is not created when the salary is blank or empty
- Click on the worker tab
- Verify that the list of workers matches what's in the DB
- Enter the following information:
   - A valid worker name (any string)
   - An empty or blank worker salary
   - A valid qualification
- Click "Create Worker"
- Verify that a error message displays that there is missing required information

8.4 A worker is not created when the qualifications are blank or not in the list of qualifications
- Click on the worker tab
- Verify that the list of workers matches what's in the DB
- Enter the following information:
   - A valid worker name (any string)
   - A valid worker salary
   - A blank qualification or a qualificatoins not in the company set of qualifications
- Click "Create Worker"
- Verify that a error message displays that there is missing required information

## 9.  Create new project.
9.1 A project can be created
- Click on the projects tab
- Verify that the list of projects matches what's in the DB
- Enter the following information:
   - A valid project name (any string)
   - Valid qualifications within the company set of qualifications
   - A selected project size
- Click "Create Project"
- Verify that the new project is in the projects list

9.2 A project is not created when the name is empty or blank
- Click on the projects tab
- Verify that the list of projects matches what's in the DB
- Enter the following information:
   - An empty or blank project name
   - Valid qualifications within the company set of qualifications
   - A selected project size
- Click "Create Project"
- Verify that a error message displays that there is missing required information

9.3 A project is not created when the qualifications are blank or not in the list of qualifications
- Click on the projects tab
- Verify that the list of projects matches what's in the DB
- Enter the following information:
   - A valid project name (any string)
   - A blank qualification or a qualificatoins not in the company set of qualifications
   - A selected project size
- Click "Create Project"
- Verify that a error message displays that there is missing required information

9.4 A project is not created with the project size is not selected
- Click on the projects tab
- Verify that the list of projects matches what's in the DB
- Enter the following information:
   - A valid project name (any string)
   - Valid qualifications within the company set of qualifications
   - A non-selected project status
- Click "Create Project"
- Verify that a error message displays that there is missing required information

## 10. Assign worker.  

10.1 A valid worker can be assigned to a project that is planned or suspended
- Click on the project tab
- Click on a project that is planned or suspended
- Find the workers in the DB that satisfy some/all of the project's missing requirements and will not be overloaded
- Verify that all of those workers appear in the "Asssign Worker" dropdown
- Click one of the workers in the dropdown, and verify that the project details are updated to reflect their assignment

10.2 A worker cannot be assigned to a project if they would be overloaded
- Click on a project that is planned or suspended
- Find a worker in the DB that satisfy some/all of the project's missing requirements, but will be overloaded by the assignment
- Verify that the worker does not appear in the "Asssign Worker" dropdown

10.3 A worker cannot be assigned to a project they are already working on
- Click on a project that is planned or suspended
- Verify that none of the workers already assigned to the project appear in the "Asssign Worker" dropdown

10.4 An active/finished project cannot have workers added to it
- Click on a project that is active or finished
- Verify that the "Assign Worker" dropdown is not present in the project's details

## 11. Unassign worker.  

11.1 A worker can be unassigned from a project
- Click on the project tab
- Click on a project
- Click on the unassign worker dropdown
- See that only the workers currently assigned to the project are listed
- Click on a worker
- Verify that the worker was removed from the projects details
- Verify that the projects qualifications were updated

## 12. Start project.
12.1 A planned or suspended project with no missing qualifications can be started
- Click the project tab
- Click on a project
- Verify that the project status is either  planned or suspended
- Verify that the project has no missing qualifications
- Verify that the Start Project button is visible
- Click the Start Project button
- Verify that the project status is set to Active
- Verify that the Start Project button is no longer visible

12.2 A planned or suspended project with missing qualifications cannot be started
- Click the project tab
- Click on a project
- Verify that the project status is either  planned or suspended
- Verify that the project has missing qualifications
- Verify that the Start Project button is not visible

12.3 An active/finished project cannot be started
- Click the project tab
- Click on a project 
- Verify that the project status is either active or finished
- Verify that the start button is not visible

## 13. Finish project.  
13.1 An active project can be finished
- Click the project tab
- Click on a project
- Verify that the project status is active
- Verify that the Finish Project button is visible
- Click the Finish Project button
- Verify that the project status is set to Finished
- Verify that the Finish Project button is no longer visible

13.2 A planned or suspended project cannot be finished
- Click the project tab
- Click on a project
- Verify that the project status is either  planned or suspended
- Verify that the Start Finish button is not visible

12.3 A finished project cannot be finished
- Click the project tab
- Click on a project 
- Verify that the project status is finished
- Verify that the finish button is not visible
 
 

 
