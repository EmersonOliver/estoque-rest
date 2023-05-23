pipeline{
	    agent any
	    stages {
			  stage ('Build') {
	   steps { 
               echo 'This is a minimal pipeline.' 
               withMaven {
	     		 sh "mvn clean install -DskipTests"
	  	  } 
         }
	  }
	}
}
