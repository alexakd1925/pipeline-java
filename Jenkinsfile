pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "alexacademy33/pipeline-java"
    }

    stages {
        stage('1. Checkout del codigo') {
            steps {
                checkout scm
            }
        }

        stage('2. Contruccuib (Build)') {
            steps {
                script {
                    echo "Compilando codigo Java y construyendo imagen Docker ...."
                    sh "docker build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} ."
                    sh "docker tag ${DOCKER_IMAGE}:${BUILD_NUMBER} ${DOCKER_IMAGE}:latest"
                }
            }
        }
        stage('3. Subir a Docker HUB (Push)') {
            steps {
                echo "Autenticando en Docker Hub....."
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'HUB_PSW', usernameVariable: 'HUB_USR')]) {
                    sh 'echo $HUB_PSW | docker login -u $HUB_USR --password-stdin'
                }

                script {
                    echo "Subiendo imagen....."
                    sh "docker push ${DOCKER_IMAGE}:${BUILD_NUMBER}"
                    sh "docker push ${DOCKER_IMAGE}:latest"
                }
            }
        }
        stage('4. Despliegue (Deploy)') {
            steps {
                script {
                    echo "Desplegando contenedor Java...."
                    sh "docker pull ${DOCKER_IMAGE}:latest"
                    sh "docker stop app-java-produccion || true"
                    sh "docker rm app-java-produccion || true"

                    sh "docker run -d --name app-java-produccion -p 80:8080 --restart always ${DOCKER_IMAGE}:latest"

                    sh "docker system prune -af"
                }
            }
        }
    }
    post {
        always {
            sh "docker logout || true"
        }
    }
}