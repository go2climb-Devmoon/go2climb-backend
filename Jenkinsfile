pipeline {
    agent any
    tools {
        maven 'MAVEN_3_8_6'
    }
    stages {
        stage('Compile Stage') {
            steps {
                withMaven(maven : 'MAVEN_3_8_6') {
                    bat 'mvn clean compile'
                }
            }
        }
        
        stage('Testing Stage') {
            steps {
                withMaven(maven : 'MAVEN_3_8_6') {
                    bat 'mvn test'
                }
            }
        }
        
        stage('Build application') {
            steps {
                echo "mvn clean install -Dmaven.test.skip-true" 
                
            }
        }

         stage('Create docker image') {
            steps {
                echo "creando docker" 
                
            }
        }
    }

    post {
            always {
                mail to: 'leoac_2002@hotmail.com',
                     subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
                     body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER} More info at: ${env.BUILD_URL}"
            }
        }
}
