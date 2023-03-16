pipeline{

	agent any

	environment {
		DOCKERHUB_CREDENTIALS=credentials('dockerhub')
                KUBECONFIG = credentials('Jenkins_ServiceAccount')
	}

	stages {

		stage('Build') {

			steps {
				sh 'docker build -t andriisobchuk/tc-assessment:latest .'
			}
		}

		stage('Login') {

			steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
			}
		}

		stage('Push') {

			steps {
				sh 'docker push andriisobchuk/tc-assessment:latest'
			}
		}
                stage('Deploy to Kubernetes') {
                        steps {
                                withCredentials([file(credentialsId: 'Jenkins_ServiceAccount', variable: 'KUBECONFIG')]) {
                                sh 'kubectl --kubeconfig=$KUBECONFIG apply -f k8s-deployment/deployment.yaml'
                              }
                        }
                }
	}

	post {
		always {
			sh 'docker logout'
		}
	}

}
