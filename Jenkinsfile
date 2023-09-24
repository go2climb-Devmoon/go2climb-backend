pipeline {
    agent any
    stages {
        stage('Compile Stage') {
            steps {
                script {
                    def mavenTool = tool name: 'MAVEN_3_8_6', type: 'maven'
                    withMaven(maven: mavenTool) {
                        sh 'mvn clean compile'
                    }
                }
            }
        }
        stage('Testing Stage') {
            steps {
                script {
                    def mavenTool = tool name: 'MAVEN_3_8_6', type: 'maven'
                    withMaven(maven: mavenTool) {
                        sh 'mvn test'
                    }
                }
            }
        }
    }
}