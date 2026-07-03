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
        sh "docker build -t ${IMAGE} -t vertex-symposium:latest ."
    }
}

stage('Load Image into kind') {
    steps {
        sh 'kind load docker-image vertex-symposium:latest --name event-manager'
    }
}

        // Add a Docker Push stage here once you have a registry (Docker Hub, ECR, etc.)

        stage('Deploy to Kubernetes') {
    steps {
        sh 'kubectl apply -f k8s/deployment.yaml -f k8s/service.yaml'
        sh 'kubectl rollout restart deployment vertex-symposium'
    }
}
    }
}
