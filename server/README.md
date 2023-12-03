# server
This repository contains the server for the CS-415 project. The server by default runs on port 4567.  You can check if the server is running correctly by accessing http://localhost:4567/helloworld.

`cd server/` before running any of the following commands.

## Start server

You can either run `mvn exec:exec` in the command line,

or through your IDE run the class Main directly.

## Package server into standalone JAR

Run `mvn package`.

## Run tests with JaCoCo's coverage

Run `mvn test`.

The coverage report will be inside `server/target/site/jacoco`.