# news_one

INTRODUCTION

	"news_one" is a simple application which converts news input from public news api to another format and serves them as REST service on the local machine. It also provides simple GUI to search and display the news. 
	Application is written with Spring Boot framework and plain JavaScript. The project is managed by maven.

USAGE

	There are two options to start the app. In both cases, the first thing you need to do is to make sure that the current pofile is set to 'prod' (spring.profiles.active=prod in application.properties file)

	Then:

	1. With IDE
		Simply import the project as maven project in favourite IDE (e.g. IntelliJ) and then run it.

	2. With command line
		a. call 'mvnw clean install' from projects main directory
		b. call 'mvnw package && java -jar target/news_one-0.1.0.jar', also from projects main directory.
		
	After 'deployment':
		
	Application's GUI will be accessible at the default adress: 'http://localhost:8080/index.html'

TESTS

	1. There are three kinds of tests in the application: JUnit, Integration tests and LiveTest(s). The latter one requires the application to be deployed with the 'dev' profile (spring.profiles.active=dev in application.properties file).

	2. Tests can be run either from the IDE or from the command line with the following command: 'mvnw clean install -DskipTests=false'.


Thats all! 
Thank you for your attention and please feel free to ask questions or give comments.
