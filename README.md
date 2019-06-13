#Palindrome web rest service.
Identified palindromes given a range of values.

# Technology stack 
- Java 1.8
- Gradle build system
- Spring-boot, a Micro-services framework.


## How to import project in your favorite IDE 
 - Most moderns Java IDEs (Eclipse, Netbeans, IDEA)  comes with build in support for gradle if not please review plugins/add-ons for your IDE.
 - To import project use standard import gradle project functionality.
 
 
## Run unit and integration test
    ./gradlew test (Linux)
    gradle.bat test (Windows


## Run standalone version
Project technology (Spring boot) enable to run project without an application server. The standalone mode can be used for testing or debugging an specific scenario.

    ./gradlew bootRun (Linux)
    gradle.bat bootRun (Windows
    
##  Rest service Api
After run the project palindromes operations is available at

    http://localhost:8080/palindromes/7/1000000
    
 ## Jenkinsfile 
  To Check out the Code from and build the Code with gradle in Jenkins
  ## Dockerfile
  Docker file Contains to run the java and gradle
 
