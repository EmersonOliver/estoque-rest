pipeline{
    agent any
    stages {
        stage('Build Image') {
            steps {
	            script {
	                dockerapp = docker.build("eaoliveira5/estoque-rest:${env.BUILD_ID}", '-f ./Dockerfile ./')
	            }
            }
        }
        stage('Push image') {
                        steps{
                            script {
                                docker.withRegistry('https://registry.hub.docker.com', 'dockerhub'){
                                    dockerapp.push('latest')
                                    dockerapp.push("${env.BUILD_ID}")
                                }

                            }

                        }

                    }

    }
}
