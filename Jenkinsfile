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


    stage('Deploy to Kubernetes') {
        steps {
            sh 'kubectl apply -f k8s/monitoring/ -f k8s/deployment.yaml -f k8s/service.yaml'
            sh 'kubectl rollout restart deployment vertex-symposium'
        
            sh 'pkill -f "kubectl port-forward" || true'
        
            sh 'nohup kubectl port-forward svc/grafana 3000:3000 --address 0.0.0.0 > /dev/null 2>&1 &'
            sh 'nohup kubectl port-forward svc/graphite 8080:80 --address 0.0.0.0 > /dev/null 2>&1 &'
            sh 'nohup kubectl port-forward svc/nagios 8084:80 --address 0.0.0.0 > /dev/null 2>&1 &'
        }
    }   
    }
}
