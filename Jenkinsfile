pipeline {
    agent any

    environment {
        DOCKER_HUB_USER = 'emirdirin'

        // Jenkins will create the image with this name
        IMAGE_NAME      = 'devops_project4_image'

        // The Docker Hub ID that we will add to Jenkins Credentials.
        DOCKER_CRED_ID  = 'docker-hub-credentials'
    }

    stages {
        stage('1. Clean the code and compile (Gradle Build)') {
            steps {
                echo 'Project is being compiled and .jar file is being created'
                sh 'chmod +x gradlew'
                //sh './gradlew clean build -x test' //
                sh "./gradlew clean bootJar"
            }
        }

        stage('2. Create Docker Image (Docker Build)') {
            steps {
                echo 'Jenkins is creating Docker Image in the background'
                script {
                    sh "docker build -t ${DOCKER_HUB_USER}/${IMAGE_NAME}:latest ."
                }
            }
        }

        stage('3. Send to Docker Hub (Docker Push)') {
            steps {
                echo 'İmage is being uploaded to Docker Hub'
                script {
                    withCredentials([usernamePassword(credentialsId: "${DOCKER_CRED_ID}", usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                        sh "echo \$PASS | docker login -u \$USER --password-stdin"
                        sh "docker push ${DOCKER_HUB_USER}/${IMAGE_NAME}:latest"
                    }
                }
            }
        }


        stage('4. Deploy to Kubernetes (K8s Deploy)') {
                    steps {
                        echo 'Our application is being launched on Minikube...'
                        // KUBECONFIG yolunu senin WSL kullanıcının gizli config dosyasına eşitliyoruz
                        withEnv(["KUBECONFIG=/var/lib/jenkins/.kube/config"]) {
                            sh 'kubectl apply -f k8s/mysql-deployment.yaml'
                            sh 'sleep 10'
                            sh 'kubectl apply -f k8s/deployment.yaml --validate=false'
                            sh 'kubectl apply -f k8s/service.yaml --validate=false'
                        }
                    }
        }
    }

    post {
        always{
            sh 'docker logout'
        }
        success {
            echo '🚀 Congratulations! The automated process has been successfully completed and the application is now live on K8s!'
        }
        failure {
            echo '❌ Something went wrong. Please check the logs from the Jenkins interface.'
        }
    }
}