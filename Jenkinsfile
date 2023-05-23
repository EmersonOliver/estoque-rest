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
            bat  "mvn clean install -DskipTests"
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
