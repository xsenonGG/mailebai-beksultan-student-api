pipeline {
    agent any

    options {
        skipDefaultCheckout(true)
        disableConcurrentBuilds()
        timeout(time: 20, unit: 'MINUTES')
    }

    triggers {
        pollSCM('* * * * *')
    }

    environment {
        IMAGE_NAME = 'mailebai-beksultan-student-api:v1.0.1-mailebai-beksultan-37691'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                echo 'Student: Mailebai Beksultan | IT2-2313 | ID 37691'
                sh 'java -version && javac -version'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew --no-daemon --max-workers=2 --console=plain classes bootJar'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew --no-daemon --max-workers=2 --console=plain test'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t "$IMAGE_NAME" .'
            }
        }

        stage('Verify') {
            steps {
                sh 'docker image inspect "$IMAGE_NAME" --format "ID={{.Id}} Size={{.Size}}"'
                echo 'PASS: Docker image exists — Mailebai Beksultan'
            }
        }
    }

    post {
        always {
            junit testResults: 'build/test-results/test/*.xml',
                  allowEmptyResults: true
        }
        success {
            echo 'SUCCESS: Mailebai Beksultan Student API'
        }
        failure {
            echo 'FAILED: inspect the failed stage and console output'
        }
    }
}
