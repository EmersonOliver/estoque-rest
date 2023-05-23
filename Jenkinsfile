pipeline{
	    agent any
	     tools { 
      		  maven 'maven-3-8-5' 
       		  jdk 'jdk-8' 
       }
	    stages {
	stage ('Clone code') {
	  steps {
             git credentialsId: 'github', url: 'https://github.com/EmersonOliver/estoque-rest.git'
            }
         }
	 stage ('Build code') {
	  steps {
	  	   script {
		        DATE_TAG = java.time.LocalDate.now()
		  		DATETIME_TAG = java.time.LocalDateTime.now()
    		}
    		bat "mvn versions:set -DnewVersion=${BUILD_ID}-${DATETIME_TAG}-SNAPSHOT -f enforcer/pom.xml"
            bat  "mvn clean install -Dbuild.number=${BUILD_ID} -DskipTests" 
            }
         }
	  }
	  post {
	      always{
	          echo 'Teste'
	      }
	      success {
	          echo 'sucess'
	      }
	      failure{
	          echo 'failure'
	      }
	      unstable{
	      	echo 'unstable'
	      }
	      changed{
	      echo 'Changed'
	      }
	  }
	}
