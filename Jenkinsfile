pipeline{
	    agent any
	    stages {
			  stage ('Build') {
	    git url: 'https://github.com/EmersonOliver/estoque-rest'
	    withMaven {
	      sh "mvn clean verify"
	    } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports
	  }
	}
}
