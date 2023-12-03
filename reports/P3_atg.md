# P3 Automatic Test Generation Report

## Randoop

### Screenshots / Logs Showing Generated Tests
![Screenshot 2023-03-27 at 5 12 54 PM](https://user-images.githubusercontent.com/97856149/228089130-2986cebc-7bb5-4803-8c69-4e0e1ded5433.png)

### Screenshots / Logs Showing Ability to Run Generated Tests
![image](https://user-images.githubusercontent.com/97856149/228088944-b45a6c5b-adbc-41a8-acbe-e58306eadbbd.png)
## Evosuite

### Screenshots / Logs Showing Generated Tests
![Screenshot 2023-03-27 at 5 59 42 PM](https://user-images.githubusercontent.com/71053850/228092919-e8cfdf59-18d8-4b73-9476-f8ef02dc0eb8.png)

> * EvoSuite 1.0.6
> * Analyzing classpath (generating inheritance tree)
>   - target/classes
> * Found 6 matching classes for prefix edu.colostate.cs415.model
> * Current class: edu.colostate.cs415.model.Worker
> * Going to generate test cases for class: edu.colostate.cs415.model.Worker
> * Starting client
> * Connecting to master process on port 18462
> * Analyzing classpath: 
> * Inheritance tree loaded from /tmp/ES_inheritancetree8612704914095903918.xml.gz
> * Finished analyzing classpath
> * Generating tests for class edu.colostate.cs415.model.Worker
> * Test criteria:
>   - Line Coverage
>   - Branch Coverage
>   - Exception
>   - Mutation testing (weak)
>   - Method-Output Coverage
>   - Top-Level Method Coverage
>   - No-Exception Top-Level Method Coverage
>   - Context Branch Coverage
> * Setting up search algorithm for whole suite generation
> * Total number of test goals: 
>   - Line 55
>   - Branch 46
>   - Exception 0
>   - MutationFactory 76
>   - Output 37
>   - Method 16
>   - MethodNoException 16
>   - CBranchFitnessFactory 46
> * Using seed 1679961181460
> * Starting evolution
> [Progress:>                             1%] [Cov:===================>               55%][MASTER] 17:53:03.062 [logback-2] ERROR TestCluster - Failed to check cache for java.util.function.Predicate<T> : Type points to itself
> [Progress:==============================100%] [Cov:==========================>        76%]
> * Search finished after 61s and 1134 generations, 268529 statements, best individual has fitness: 113.02625152625153
> * Minimizing test suite
> * Going to analyze the coverage criteria
> * Coverage analysis for criterion LINE
> * Coverage of criterion LINE: 85%
> * Total number of goals: 55
> * Number of covered goals: 47
> * Coverage analysis for criterion BRANCH
> * Coverage of criterion BRANCH: 70%
> * Total number of goals: 46
> * Number of covered goals: 32
> * Coverage analysis for criterion EXCEPTION
> * Coverage of criterion EXCEPTION: 100%
> * Total number of goals: 6
> * Number of covered goals: 6
> * Coverage analysis for criterion WEAKMUTATION
> * Coverage of criterion WEAKMUTATION: 57%
> * Total number of goals: 76
> * Number of covered goals: 43
> * Coverage analysis for criterion OUTPUT
> * Coverage of criterion OUTPUT: 43%
> * Total number of goals: 37
> * Number of covered goals: 16
> * Coverage analysis for criterion METHOD
> * Coverage of criterion METHOD: 100%
> * Total number of goals: 16
> * Number of covered goals: 16
> * Coverage analysis for criterion METHODNOEXCEPTION
> * Coverage of criterion METHODNOEXCEPTION: 88%
> * Total number of goals: 16
> * Number of covered goals: 14
> * Coverage analysis for criterion CBRANCH
> * Coverage of criterion CBRANCH: 70%
> * Total number of goals: 46
> * Number of covered goals: 32
> * Generated 28 tests with total length 124
> * Resulting test suite's coverage: 76% (average coverage for all fitness functions)
> * Generating assertions
> * Resulting test suite's mutation score: 38%
> * Compiling and checking tests
> * Writing JUnit test case 'Worker_ESTest' to evosuite-tests
> * Done!

> * Computation finished
> * Current class: edu.colostate.cs415.model.Project
> * Going to generate test cases for class: edu.colostate.cs415.model.Project
> * Starting client
> * Connecting to master process on port 12264
> * Analyzing classpath: 
> * Inheritance tree loaded from /tmp/ES_inheritancetree8612704914095903918.xml.gz
> * Finished analyzing classpath
> * Generating tests for class edu.colostate.cs415.model.Project
> * Test criteria:
>   - Line Coverage
>   - Branch Coverage
>   - Exception
>   - Mutation testing (weak)
>   - Method-Output Coverage
>   - Top-Level Method Coverage
>   - No-Exception Top-Level Method Coverage
>   - Context Branch Coverage
> * Setting up search algorithm for whole suite generation
> * Total number of test goals: 
>   - Line 68
>   - Branch 54
>   - Exception 0
>   - MutationFactory 47
>   - Output 36
>   - Method 17
>   - MethodNoException 17
>   - CBranchFitnessFactory 54
> * Using seed 1679961246001
> * Starting evolution
> [Progress:==>                           8%] [Cov:======>                            19%][MASTER] 17:54:12.055 [logback-2] ERROR TestCluster - Failed to check cache for java.util.function.Predicate<T> : Type points to itself
> [Progress:==============================100%] [Cov:======>                            19%]
> * Search finished after 61s and 2055 generations, 250974 statements, best individual has fitness: 339.3181818181818
> * Minimizing test suite
> * Going to analyze the coverage criteria
> * Coverage analysis for criterion LINE
> * Coverage of criterion LINE: 4%
> * Total number of goals: 68
> * Number of covered goals: 3
> * Coverage analysis for criterion BRANCH
> * Coverage of criterion BRANCH: 22%
> * Total number of goals: 54
> * Number of covered goals: 12
> * Coverage analysis for criterion EXCEPTION
> * Coverage of criterion EXCEPTION: 100%
> * Total number of goals: 2
> * Number of covered goals: 2
> * Coverage analysis for criterion WEAKMUTATION
> * Coverage of criterion WEAKMUTATION: 0%
> * Total number of goals: 47
> * Number of covered goals: 0
> * Coverage analysis for criterion OUTPUT
> * Coverage of criterion OUTPUT: 0%
> * Total number of goals: 36
> * Number of covered goals: 0
> * Coverage analysis for criterion METHOD
> * Coverage of criterion METHOD: 6%
> * Total number of goals: 17
> * Number of covered goals: 1
> * Coverage analysis for criterion METHODNOEXCEPTION
> * Coverage of criterion METHODNOEXCEPTION: 0%
> * Total number of goals: 17
> * Number of covered goals: 0
> * Coverage analysis for criterion CBRANCH
> * Coverage of criterion CBRANCH: 22%
> * Total number of goals: 54
> * Number of covered goals: 12
> * Generated 6 tests with total length 17
> * Resulting test suite's coverage: 19% (average coverage for all fitness functions)
> * Generating assertions
> * Resulting test suite's mutation score: 0%
> * Compiling and checking tests
> * Writing JUnit test case 'Project_ESTest' to evosuite-tests
> * Done!

> * Computation finished
> * Current class: edu.colostate.cs415.model.ProjectStatus
> * Going to generate test cases for class: edu.colostate.cs415.model.ProjectStatus
> * Starting client
> * Connecting to master process on port 12540
> * Analyzing classpath: 
> * Inheritance tree loaded from /tmp/ES_inheritancetree8612704914095903918.xml.gz
> * Finished analyzing classpath
> * Generating tests for class edu.colostate.cs415.model.ProjectStatus
> * Test criteria:
>   - Line Coverage
>   - Branch Coverage
>   - Exception
>   - Mutation testing (weak)
>   - Method-Output Coverage
>   - Top-Level Method Coverage
>   - No-Exception Top-Level Method Coverage
>   - Context Branch Coverage
> * Setting up search algorithm for whole suite generation
> * Total number of test goals: 
>   - Line 0
>   - Branch 0
>   - Exception 0
>   - MutationFactory 0
>   - Output 0
>   - Method 0
>   - MethodNoException 0
>   - CBranchFitnessFactory 0
> * Using seed 1679961308850
> * Starting evolution
> [Progress:>                             0%] [Cov:===================================100%]
> * Search finished after 0s and 0 generations, 617 statements, best individual has fitness: 1.0
> * Minimizing test suite
> * Going to analyze the coverage criteria
> * Coverage analysis for criterion LINE
> * Coverage of criterion LINE: 100% (no goals)
> * Coverage analysis for criterion BRANCH
> * Coverage of criterion BRANCH: 100% (no goals)
> * Coverage analysis for criterion EXCEPTION
> * Coverage of criterion EXCEPTION: 100% (no goals)
> * Coverage analysis for criterion WEAKMUTATION
> * Coverage of criterion WEAKMUTATION: 100% (no goals)
> * Coverage analysis for criterion OUTPUT
> * Coverage of criterion OUTPUT: 100% (no goals)
> * Coverage analysis for criterion METHOD
> * Coverage of criterion METHOD: 100% (no goals)
> * Coverage analysis for criterion METHODNOEXCEPTION
> * Coverage of criterion METHODNOEXCEPTION: 100% (no goals)
> * Coverage analysis for criterion CBRANCH
> * Coverage of criterion CBRANCH: 100% (no goals)
> * Generated 0 tests with total length 0
> * Resulting test suite's coverage: 100% (average coverage for all fitness functions)
> * Generating assertions
> * Resulting test suite's mutation score: 100%
> * Compiling and checking tests
> * Writing JUnit test case 'ProjectStatus_ESTest' to evosuite-tests
> * Done!

> * Computation finished
> * Current class: edu.colostate.cs415.model.Qualification
> * Going to generate test cases for class: edu.colostate.cs415.model.Qualification
> * Starting client
> * Connecting to master process on port 14502
> * Analyzing classpath: 
> * Inheritance tree loaded from /tmp/ES_inheritancetree8612704914095903918.xml.gz
> * Finished analyzing classpath
> * Generating tests for class edu.colostate.cs415.model.Qualification
> * Test criteria:
>   - Line Coverage
>   - Branch Coverage
>   - Exception
>   - Mutation testing (weak)
>   - Method-Output Coverage
>   - Top-Level Method Coverage
>   - No-Exception Top-Level Method Coverage
>   - Context Branch Coverage
> * Setting up search algorithm for whole suite generation
> * Total number of test goals: 
>   - Line 27
>   - Branch 17
>   - Exception 0
>   - MutationFactory 7
>   - Output 13
>   - Method 8
>   - MethodNoException 8
>   - CBranchFitnessFactory 17
> [Progress:>                             0%] [Cov:>                                  0%]* Using seed 1679961309901
> * Starting evolution
> [Progress:===>                          11%] [Cov:=================================> 95%][MASTER] 17:55:18.451 [logback-2] ERROR TestCluster - Failed to check cache for java.util.function.Predicate<T> : Type points to itself
> [Progress:==============================100%] [Cov:=================================> 95%]
> * Search finished after 61s and 4484 generations, 663691 statements, best individual has fitness: 5.25
> * Minimizing test suite
> * Going to analyze the coverage criteria
> * Coverage analysis for criterion LINE
> * Coverage of criterion LINE: 100%
> * Total number of goals: 27
> * Number of covered goals: 27
> * Coverage analysis for criterion BRANCH
> * Coverage of criterion BRANCH: 100%
> * Total number of goals: 17
> * Number of covered goals: 17
> * Coverage analysis for criterion EXCEPTION
> * Coverage of criterion EXCEPTION: 100%
> * Total number of goals: 3
> * Number of covered goals: 3
> * Coverage analysis for criterion WEAKMUTATION
> * Coverage of criterion WEAKMUTATION: 100%
> * Total number of goals: 7
> * Number of covered goals: 7
> * Coverage analysis for criterion OUTPUT
> * Coverage of criterion OUTPUT: 62%
> * Total number of goals: 13
> * Number of covered goals: 8
> * Coverage analysis for criterion METHOD
> * Coverage of criterion METHOD: 100%
> * Total number of goals: 8
> * Number of covered goals: 8
> * Coverage analysis for criterion METHODNOEXCEPTION
> * Coverage of criterion METHODNOEXCEPTION: 100%
> * Total number of goals: 8
> * Number of covered goals: 8
> * Coverage analysis for criterion CBRANCH
> * Coverage of criterion CBRANCH: 100%
> * Total number of goals: 17
> * Number of covered goals: 17
> * Generated 13 tests with total length 38
> * Resulting test suite's coverage: 95% (average coverage for all fitness functions)
> * Generating assertions
> * Resulting test suite's mutation score: 64%
> * Compiling and checking tests
> * Writing JUnit test case 'Qualification_ESTest' to evosuite-tests
> * Done!

> * Computation finished
> * Current class: edu.colostate.cs415.model.Company
> * Going to generate test cases for class: edu.colostate.cs415.model.Company
> * Starting client
> * Connecting to master process on port 14374
> * Analyzing classpath: 
> * Inheritance tree loaded from /tmp/ES_inheritancetree8612704914095903918.xml.gz
> [MASTER] 17:56:13.743 [logback-1] WARN  CheapPurityAnalyzer - spark.utils.StringUtils was not found in the inheritance tree. Using DEFAULT value for cheap-purity analysis
> [MASTER] 17:56:13.743 [logback-1] WARN  InheritanceTree - Class not in inheritance graph: spark.utils.StringUtils
> * Finished analyzing classpath
> * Generating tests for class edu.colostate.cs415.model.Company
> * Test criteria:
>   - Line Coverage
>   - Branch Coverage
>   - Exception
>   - Mutation testing (weak)
>   - Method-Output Coverage
>   - Top-Level Method Coverage
>   - No-Exception Top-Level Method Coverage
>   - Context Branch Coverage
> * Setting up search algorithm for whole suite generation
> * Total number of test goals: 
>   - Line 113
>   - Branch 161
>   - Exception 0
>   - MutationFactory 182
>   - Output 58
>   - Method 20
>   - MethodNoException 20
>   - CBranchFitnessFactory 161
> * Using seed 1679961373178
> * Starting evolution
> [Progress:==============================100%] [Cov:======================>            65%]
> * Search finished after 61s and 435 generations, 131277 statements, best individual has fitness: 549.9370567375886
> * Minimizing test suite
> * Going to analyze the coverage criteria
> * Coverage analysis for criterion LINE
> * Coverage of criterion LINE: 59%
> * Total number of goals: 113
> * Number of covered goals: 67
> * Coverage analysis for criterion BRANCH
> * Coverage of criterion BRANCH: 45%
> * Total number of goals: 161
> * Number of covered goals: 73
> * Coverage analysis for criterion EXCEPTION
> * Coverage of criterion EXCEPTION: 100%
> * Total number of goals: 7
> * Number of covered goals: 7
> * Coverage analysis for criterion WEAKMUTATION
> * Coverage of criterion WEAKMUTATION: 36%
> * Total number of goals: 182
> * Number of covered goals: 66
> * Coverage analysis for criterion OUTPUT
> * Coverage of criterion OUTPUT: 55%
> * Total number of goals: 58
> * Number of covered goals: 32
> * Coverage analysis for criterion METHOD
> * Coverage of criterion METHOD: 100%
> * Total number of goals: 20
> * Number of covered goals: 20
> * Coverage analysis for criterion METHODNOEXCEPTION
> * Coverage of criterion METHODNOEXCEPTION: 80%
> * Total number of goals: 20
> * Number of covered goals: 16
> * Coverage analysis for criterion CBRANCH
> * Coverage of criterion CBRANCH: 45%
> * Total number of goals: 161
> * Number of covered goals: 73
> * Generated 41 tests with total length 119
> * Resulting test suite's coverage: 65% (average coverage for all fitness functions)
> * Generating assertions
> * Resulting test suite's mutation score: 25%
> * Compiling and checking tests
> * Writing JUnit test case 'Company_ESTest' to evosuite-tests
> * Done!

> * Computation finished
> * Current class: edu.colostate.cs415.model.ProjectSize
> * Going to generate test cases for class: edu.colostate.cs415.model.ProjectSize
> * Starting client
> * Connecting to master process on port 7422
> * Analyzing classpath: 
> * Inheritance tree loaded from /tmp/ES_inheritancetree8612704914095903918.xml.gz
> * Finished analyzing classpath
> * Generating tests for class edu.colostate.cs415.model.ProjectSize
> * Test criteria:
>   - Line Coverage
>   - Branch Coverage
>   - Exception
>   - Mutation testing (weak)
>   - Method-Output Coverage
>   - Top-Level Method Coverage
>   - No-Exception Top-Level Method Coverage
>   - Context Branch Coverage
> * Setting up search algorithm for whole suite generation
> * Total number of test goals: 
>   - Line 4
>   - Branch 1
>   - Exception 0
>   - MutationFactory 3
>   - Output 10
>   - Method 1
>   - MethodNoException 1
>   - CBranchFitnessFactory 1
> * Using seed 1679961438846
> * Starting evolution
> [Progress:==============================100%] [Cov:=============================>     83%]
> * Search finished after 61s and 11313 generations, 751905 statements, best individual has fitness: 8.75
> * Minimizing test suite
> * Going to analyze the coverage criteria
> * Coverage analysis for criterion LINE
> * Coverage of criterion LINE: 25%
> * Total number of goals: 4
> * Number of covered goals: 1
> * Coverage analysis for criterion BRANCH
> * Coverage of criterion BRANCH: 100%
> * Total number of goals: 1
> * Number of covered goals: 1
> * Coverage analysis for criterion EXCEPTION
> * Coverage of criterion EXCEPTION: 100% (no goals)
> * Coverage analysis for criterion WEAKMUTATION
> * Coverage of criterion WEAKMUTATION: 100%
> * Total number of goals: 3
> * Number of covered goals: 3
> * Coverage analysis for criterion OUTPUT
> * Coverage of criterion OUTPUT: 30%
> * Total number of goals: 10
> * Number of covered goals: 3
> * Coverage analysis for criterion METHOD
> * Coverage of criterion METHOD: 100%
> * Total number of goals: 1
> * Number of covered goals: 1
> * Coverage analysis for criterion METHODNOEXCEPTION
> * Coverage of criterion METHODNOEXCEPTION: 100%
> * Total number of goals: 1
> * Number of covered goals: 1
> * Coverage analysis for criterion CBRANCH
> * Coverage of criterion CBRANCH: 100%
> * Total number of goals: 1
> * Number of covered goals: 1
> * Generated 3 tests with total length 4
> * Resulting test suite's coverage: 82% (average coverage for all fitness functions)
> * Generating assertions
> * Resulting test suite's mutation score: 50%
> * Compiling and checking tests
> * Writing JUnit test case 'ProjectSize_ESTest' to evosuite-tests
> * Done!

> * Computation finished

### Screenshots / Logs Showing Ability to Run Generated Tests

![Screenshot 2023-03-27 at 6 10 24 PM](https://user-images.githubusercontent.com/71053850/228094113-704f7257-fa87-4036-ae68-c4222e6a1a5a.png)

![Screenshot 2023-03-27 at 6 11 11 PM](https://user-images.githubusercontent.com/71053850/228094188-0481003a-fb9a-4b30-b1c7-5aba0b207f50.png)

![Screenshot 2023-03-27 at 6 11 46 PM](https://user-images.githubusercontent.com/71053850/228094256-3a68d307-7844-4d9e-8ccf-510bdcb86c37.png)

![Screenshot 2023-03-27 at 6 12 41 PM](https://user-images.githubusercontent.com/71053850/228094356-c9a8e663-589c-428d-8317-3d1030ba7370.png)

![Screenshot 2023-03-27 at 6 13 10 PM](https://user-images.githubusercontent.com/71053850/228094399-e2043743-9e10-4c7f-9ef9-8faae5cf467c.png)
