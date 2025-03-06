pipeline {
    agent any
    tools {
        maven 'Tu Maven'
    }
    environment {
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
        DOCKERHUB_REPO = 'mitudinh/week7_inclass'
        DOCKER_IMAGE_TAG = 'latest_v1'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/tuMetropolia/InclassW7.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Code Coverage') {
            steps {
                sh 'mvn jacoco:report'
            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }
        stage('Verify Docker Installation') {
            steps {
                sh 'docker --version'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                }
            }
        }
        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    // Use Docker registry credentials to push images
                    def commitHash = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
                    def imageTag = "${DOCKERHUB_USER}/${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}"
                    def commitTag = "${DOCKERHUB_USER}/${DOCKERHUB_REPO}:${commitHash}"

                    withCredentials([usernamePassword(credentialsId: DOCKERHUB_CREDENTIALS_ID, usernameVariable: 'DOCKERHUB_USER', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                        // Log in to Docker Hub
                        sh "/usr/local/bin/docker login -u ${DOCKERHUB_USER} -p ${DOCKERHUB_PASSWORD}"

                        // Push Docker images to Docker Hub
                        sh "/usr/local/bin/docker push ${imageTag}"
                        sh "/usr/local/bin/docker push ${commitTag}"
                    }
                }
            }
        }
    }
}