pipeline{
    agent any
    stages {
        stage ('Compile Stage'){
            step{
                withMaven(maven : 'MAVEN_3_8_6') {
                    bat 'mvn clean compile'
                }
            }
        }
        stage ('Testing Stage'){
            step{
                withMaven(maven : 'MAVEN_3_8_6') {
                    bat 'mvn test'
                }
            }
        }
    }
}