# Ekstazi

hartford:~/414/t05/server$ mvn ekstazi:ekstazi

[INFO] Scanning for projects...  
[INFO]   
[INFO] ---------------< edu.colostate.cs415:company_management >---------------  
[INFO] Building company_management 1.0-SNAPSHOT. 
[INFO] --------------------------------[ jar ]---------------------------------  
[INFO] 
[INFO] >>> ekstazi-maven-plugin:5.3.0:ekstazi (default-cli) > [ekstazi]test @ company_management >>>. 
[INFO]   
[INFO] --- jacoco-maven-plugin:0.8.8:prepare-agent (default) @ company_management ---  
[INFO] argLine set to -javaagent:/s/chopin/g/under/bjurenka/.m2/repository/org/jacoco/org.jacoco.agent/0.8.8/org.jacoco.agent-0.8.8- runtime.jar=destfile=/s/chopin/g/under/bjurenka/414/t05/server/target/jacoco.exec. 
[INFO]   
[INFO] --- maven-resources-plugin:3.0.2:resources (default-resources) @ company_management ---  
[INFO] Using 'UTF-8' encoding to copy filtered resources.  
[INFO] skip non existing resourceDirectory /s/chopin/g/under/bjurenka/414/t05/server/src/main/resources. 
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ company_management ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 13 source files to /s/chopin/g/under/bjurenka/414/t05/server/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:3.0.2:testResources (default-testResources) @ company_management ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /s/chopin/g/under/bjurenka/414/t05/server/src/test/resources
[INFO]   
[INFO] --- maven-compiler-plugin:3.8.0:testCompile (default-testCompile) @ company_management ---  
[INFO] Changes detected - recompiling the module!  
[INFO] Compiling 5 source files to /s/chopin/g/under/bjurenka/414/t05/server/target/test-classes. 
[INFO]   
[INFO] --- ekstazi-maven-plugin:5.3.0:select (default-cli) @ company_management ---  
[INFO]   
[INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @ company_management ---  
[INFO]   
[INFO] -------------------------------------------------------  
[INFO]  T E S T S  
[INFO] -------------------------------------------------------  
[INFO] Running edu.colostate.cs415.model.QualificationTest. 
[INFO] Tests run: 25, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.063 s - in edu.colostate.cs415.model.QualificationTest. 
[INFO] Running edu.colostate.cs415.model.CompanyTest. 
[INFO] Tests run: 115, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.022 s - in edu.colostate.cs415.model.CompanyTest. 
[INFO] Running edu.colostate.cs415.model.WorkerTest. 
[INFO] Tests run: 50, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.004 s - in edu.colostate.cs415.model.WorkerTest. 
[INFO] Running edu.colostate.cs415.model.ProjectTest. 
[INFO] Tests run: 66, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.009 s - in edu.colostate.cs415.model.ProjectTest. 
[INFO]   
[INFO] Results:  
[INFO]   
[INFO] Tests run: 256, Failures: 0, Errors: 0, Skipped: 0. 
[INFO]   
[INFO]   
[INFO] --- jacoco-maven-plugin:0.8.8:report (report) @ company_management ---  
[INFO] Loading execution data file /s/chopin/g/under/bjurenka/414/t05/server/target/jacoco.exec. 
[INFO] Analyzed bundle 'company_management' with 13 classes. 
[INFO]   
[INFO] <<< ekstazi-maven-plugin:5.3.0:ekstazi (default-cli) < [ekstazi]test @ company_management <<<. 
[INFO]   
[INFO]   
[INFO] --- ekstazi-maven-plugin:5.3.0:ekstazi (default-cli) @ company_management ---  
[INFO] ------------------------------------------------------------------------  
[INFO] BUILD SUCCESS. 
[INFO] ------------------------------------------------------------------------  
[INFO] Total time:  3.634 s. 
[INFO] Finished at: 2023-03-09T07:10:13-07:00. 
[INFO] ------------------------------------------------------------------------  
hartford:~/414/t05/server$ mvn ekstazi:ekstazi. 
[INFO] Scanning for projects...  
[INFO]   
[INFO] ---------------< edu.colostate.cs415:company_management >---------------  
[INFO] Building company_management 1.0-SNAPSHOT. 
[INFO] --------------------------------[ jar ]---------------------------------  
[INFO]   
[INFO] >>> ekstazi-maven-plugin:5.3.0:ekstazi (default-cli) > [ekstazi]test @ company_management >>>. 
[INFO]   
[INFO] --- jacoco-maven-plugin:0.8.8:prepare-agent (default) @ company_management ---  
[INFO] argLine set to -javaagent:/s/chopin/g/under/bjurenka/.m2/repository/org/jacoco/org.jacoco.agent/0.8.8/org.jacoco.agent-0.8.8-   runtime.jar=destfile=/s/chopin/g/under/bjurenka/414/t05/server/target/jacoco.exec. 
[INFO]   
[INFO] --- maven-resources-plugin:3.0.2:resources (default-resources) @ company_management ---  
[INFO] Using 'UTF-8' encoding to copy filtered resources.  
[INFO] skip non existing resourceDirectory /s/chopin/g/under/bjurenka/414/t05/server/src/main/resources. 
[INFO]   
[INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ company_management ---  
[INFO] Nothing to compile - all classes are up to date. 
[INFO]   
[INFO] --- maven-resources-plugin:3.0.2:testResources (default-testResources) @ company_management ---  
[INFO] Using 'UTF-8' encoding to copy filtered resources.  
[INFO] skip non existing resourceDirectory /s/chopin/g/under/bjurenka/414/t05/server/src/test/resources. 
[INFO]   
[INFO] --- maven-compiler-plugin:3.8.0:testCompile (default-testCompile) @ company_management ---  
[INFO] Nothing to compile - all classes are up to date. 
[INFO]   
[INFO] --- ekstazi-maven-plugin:5.3.0:select (default-cli) @ company_management ---  
[INFO]   
[INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @ company_management ---  
[INFO]   
[INFO] --- jacoco-maven-plugin:0.8.8:report (report) @ company_management ---  
[INFO] Loading execution data file /s/chopin/g/under/bjurenka/414/t05/server/target/jacoco.exec. 
[INFO] Analyzed bundle 'company_management' with 13 classes. 
[INFO]   
[INFO] <<< ekstazi-maven-plugin:5.3.0:ekstazi (default-cli) < [ekstazi]test @ company_management <<<. 
[INFO]   
[INFO]   
[INFO] --- ekstazi-maven-plugin:5.3.0:ekstazi (default-cli) @ company_management ---  
[INFO] ------------------------------------------------------------------------  
[INFO] BUILD SUCCESS. 
[INFO] ------------------------------------------------------------------------  
[INFO] Total time:  2.353 s. 
[INFO] Finished at: 2023-03-09T07:11:40-07:00. 
[INFO] ------------------------------------------------------------------------  
''' 

## After refactors/fixes:
![Screen Shot 2023-03-27 at 12 35 08 PM](https://user-images.githubusercontent.com/58609154/228035634-b33b9896-61b0-472a-b2a5-eb066ba468e3.png)
![Screen Shot 2023-03-27 at 12 36 28 PM](https://user-images.githubusercontent.com/58609154/228035682-31247190-2868-45b7-a810-82bb428ef9fe.png)

# Clover

![Screenshot 2023-03-23 150730](https://user-images.githubusercontent.com/98073413/227362578-4824507c-85c4-4cf7-923f-1689ba445bd1.png)

## After refactoring code:

![cloverAfter](https://user-images.githubusercontent.com/98073413/228048038-ed350b92-d343-4a37-aba1-95f370595081.png)