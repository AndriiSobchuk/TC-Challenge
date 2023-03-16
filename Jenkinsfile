pipeline{

	agent any

	environment {
		DOCKERHUB_CREDENTIALS=credentials('dockerhub')
                KUBECONFIG = credentials('kubeconfig')
	}

	stages {

		stage('Build') {

			steps {
				sh 'docker build -t andriisobchuk/march-test:latest .'
			}
		}

		stage('Login') {

			steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
			}
		}

		stage('Push') {

			steps {
				sh 'docker push andriisobchuk/march-test:latest'
			}
		}
                stage('Deploy to Kubernetes') {
                        steps {
                                withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                                sh 'kubectl --kubeconfig=$KUBECONFIG apply -f myrepo/deployment.yaml'
                        }
                }
	}

	post {
		always {
			sh 'docker logout'
		}
	}

}
