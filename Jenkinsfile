pipeline {

    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM',
                          branches: [[name: '*/main']],
                          doGenerateSubmoduleConfigurations: false,
                          extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'myrepo']],
                          submoduleCfg: [],
                          userRemoteConfigs: [[url: 'https://github.com/AndriiSobchuk/TC-Challenge.git']]])
            }
        }
        stage('Build and Push') {
            steps {
                sh 'docker build -t andriisobchuk/march-test:latest myrepo'
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'dockerhub', usernameVariable: 'DOCKERHUB_CREDENTIALS_USR', passwordVariable: 'DOCKERHUB_CREDENTIALS_PSW']]) {
                    sh 'docker login -u $DOCKERHUB_CREDENTIALS_USR -p $DOCKERHUB_CREDENTIALS_PSW'
                }
                sh 'docker push andriisobchuk/march-test:latest'
            }
        }
    }

    post {
        always {
            sh 'docker logout'
        }
    }
}

