pipeline{
    agent any
    stages {
        stage('Build Image') {
            steps {
	            script {
	                dockerapp = docker.build("eaoliveira5/estoque-api", '-f ./Dockerfile ./')
	                
	            }
            }
        }
    }
}
