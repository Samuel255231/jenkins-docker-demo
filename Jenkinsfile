pipeline {
    agent any

    environment {
        IMAGE_NAME = "samuel643/jenkins-docker-demo"
        IMAGE_TAG = "latest"
        MAVEN_OPTS = "-Xms128m -Xmx512m -XX:+UseSerialGC"
    }

    tools {
        maven 'Maven-3.9.16'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvnw.cmd clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvnw.cmd test'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t %IMAGE_NAME%:%IMAGE_TAG% .'
            }
        }

        stage('Push Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    bat '''
                    docker login -u %DOCKER_USER% -p %DOCKER_PASS%
                    docker push %IMAGE_NAME%:%IMAGE_TAG%
                    '''
                }
            }
        }
    }

    post {

    success {
        echo 'Pipeline exécutée avec succès.'

        mail(
            to: 'rakotonandrasanasamuel5@gmail.com',
            subject: "SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
            body: """
        Bonjour,

        La pipeline Jenkins a réussi.

        Projet : ${env.JOB_NAME}
        Build : ${env.BUILD_NUMBER}
        Statut : SUCCESS

        Voir les détails :
        ${env.BUILD_URL}

        Cordialement,
        Jenkins
        """
                )
            }


        failure {
            echo 'La pipeline a échoué.'

            mail(
                to: 'rakotonandrasanasamuel5@gmail.com',
                subject: "FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
        Bonjour,

        La pipeline Jenkins a échoué.

        Projet : ${env.JOB_NAME}
        Build : ${env.BUILD_NUMBER}
        Statut : FAILED

        Voir les logs :
        ${env.BUILD_URL}

        Cordialement,
        Jenkins
        """
                )
            }


            always {
                cleanWs()
            }
        }
}