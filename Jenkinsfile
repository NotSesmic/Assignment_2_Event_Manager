pipeline {
    agent any

    environment {
        IMAGE = "vertex-symposium:${env.BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${IMAGE} ."
            }
        }

        // Add a Docker Push stage here once you have a registry (Docker Hub, ECR, etc.)

        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f k8s/deployment.yaml -f k8s/service.yaml'
            }
        }
    }
}
