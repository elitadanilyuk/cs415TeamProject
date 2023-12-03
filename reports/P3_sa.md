## PMD and CPD before refactoring

![PMD Results1024_1](https://user-images.githubusercontent.com/98073413/227048819-39bd2072-2719-4daa-bcaa-5e90fb366b35.jpg)
![PMD Results1024_2](https://user-images.githubusercontent.com/98073413/227048824-afe8846d-f884-4ea0-874b-d7bcb1b599e6.jpg)

 We decided to fix the CollapsibleIfStatements and UselessParentheses issues in Company.java as well as the UnnecessaryModifier issue in ProjectSize.java

## PMD and CPD after refactoring
![image](https://user-images.githubusercontent.com/97856149/228036600-09577833-0423-4173-b0cd-7cb163504b97.png)

## Spotbugs berfore refactoring

![SpotBugs1024_1](https://user-images.githubusercontent.com/98073413/227689429-6b9ce899-fdd7-4a70-9f51-406615af3799.jpg)
![SpotBugs1024_2](https://user-images.githubusercontent.com/98073413/227689430-27c6af76-9e08-4fe5-9538-c1fd797aea17.jpg)
![SpotBugs1024_3](https://user-images.githubusercontent.com/98073413/227689431-ec720f23-3dca-4c4f-9efc-3ce65f4afc45.jpg)
![SpotBugs1024_4](https://user-images.githubusercontent.com/98073413/227689428-8d6f4d93-fac0-43fe-ac56-1d36d4fbb968.jpg)

We decided to fix the following issues
* DMI removeAll used to clear a collection in edu.colotstate.cs415.model.Project.removeAllWorkers()
* Eq edu.colostate.cs415.model.Company.equals(Object) fails for subtypes
* Eq edu.colostate.cs415.model.Project.equals(Object) fails for subtypes
* Eq edu.colostate.cs415.model.Qualification.equals(Object) fails for subtypes
* Eq edu.colostate.cs415.model.Worker.equals(Object) fails for subtypes
* E12 new edu.colostate.cs415.model.Project(String, Set, ProjectSize) may expose internal representation by storing an externally mutable object into Worker.qualifications
* E12 new edu.colostate.cs415.model.Worker(String, Set, double) may expose internal representation by storing an externally mutable object into Worker.qualifications


## Spotbugs after refactoring
![image](https://user-images.githubusercontent.com/97856149/228042700-d315d1c2-56b7-4c24-9add-c6ef5877d571.png)
![image](https://user-images.githubusercontent.com/97856149/228042785-c4ec0fd5-c81b-41be-866e-da0e94949084.png)
![image](https://user-images.githubusercontent.com/97856149/228042854-be8fa47c-a845-4f9d-8585-eeb3298c129a.png)
![image](https://user-images.githubusercontent.com/97856149/228042906-7867339f-fb4f-461f-96dc-f1a960c03595.png)