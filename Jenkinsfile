pipeline{
	    agent any
	     tools { 
      		  maven 'maven-3-8-5' 
       		  jdk 'jdk-8' 
       }
	    stages {
	 //   stage ('Initialize') {
          //  steps {
        //        sh '''
          //          echo "PATH = ${PATH}"
            //        echo "M2_HOME = ${M2_HOME}"
              //  '''
     //       }
   //     }
	 stage ('Build') {
	  steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
         }
	  }
	}
