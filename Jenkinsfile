pipeline {
    agent any
    tools {
        maven 'MAVEN_3_8_6'
    }
    stages {
        stage('Compile Stage') {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('Testing Stage') {
            steps {
                bat 'mvn test'
            }
        }
    }
}