arquillian_eap5_resteasy_quickstart
===================================

Very simple quickstart that shows how to setup Arquillian with EAP 5 and RestEasy 1.2.1# Arquillian - EAP 5 - RestEasy Quickstart

This is a very simple quickstart to show how to deploy and test an Arquillian test case on JBoss EAP 5. In particular the our tests are going to call some REST methods exposed using JBoss RestEasy, bundled with JBoss EAP 5 distribution.

## Prerequisites:

- JBoss EAP 5.1.2 installed and running on default port 8080 ( download from http://access.redhat.com)


## Configuration instructions: 

- In *EAP_EXTRACT_PATH/jboss-eap-5.1/jboss-as/* create a copy of *server/default* folder to *server/arq_resteasy*
- Edit *EAP_EXTRACT_PATH/jboss-eap-5.1/jboss-as/server/arq_resteasy/conf/props/jmx-console-users.properties* and enable **admin** user (remove the leading hash)
- Copy RestEasy libraries from *EAP_EXTRACT_PATH/jboss-eap-5.1/resteasy/lib/* to *EAP_EXTRACT_PATH/jboss-eap-5.1/jboss-as/server/arq_resteasy/lib*
- Start EAP 5 with the just created profile:
- 
> sh EAP_EXTRACT_PATH/jboss-eap-5.1/jboss-as/bin/run.sh -c arq_resteasy

## MVN  
With JBoss already running: from command line you can execute normal Maven steps to exectue the tests:
> mvn test

## Run in Eclipse  
With JBoss already running:  
If you want to execute the Arquillian/JUnit tests directly in eclipse a jvm command line variable has to be passed to the test launch configuration:  
> -Djava.security.auth.login.config=src/test/resources/auth.conf    

I have added an Eclipse Launch profile with the source code:  
> Arq_EAP5.launch    

That should apply the configuration automatically for you and offer a launch profile called Arq_EAP5

## References  
http://howtojboss.com/2012/03/14/integration-testing-w-maven-arquillian-jboss-eap-5/  
https://docs.jboss.org/author/display/ARQ/JBoss+EAP+5.1+-+Remote  


paolo  
paolo.antinori@gmail.com