pipeline {
    agent { label 'jenkins-slave' }
    
    environment {
        NOTES_MONGO_URL_SECRET = credentials('NOTES_MONGO_URL');
        NOTES_MONGO_URL = "${env.NOTES_MONGO_URL_SECRET}"
    }
    
    stages {
 
        stage("Environment configuration") {
            steps {
                sh 'git --version'
                echo "Branch: ${env.BRANCH_NAME}"
                sh 'docker -v'
                sh 'printenv'
            }
        }

        stage("Build") {
            steps {
                echo 'Cloning git ...'
                
                echo '$NOTES_MONGO_URL'
                echo  'NOTES_MONGO_URL'
                echo 'NOTES_MONGO_URL_SECRET'
                echo '$NOTES_MONGO_URL_SECRET'
                git([url: 'https://github.com/jovanibrasil/notes-api.git', branch: 'master', credentialsId: '18a17f19-9870-4bcc-8c7b-75eec38a059a'])
                echo 'Installing dependencies ...'
                sh 'mvn package'
                echo 'Building ...'
                sh 'docker build -t notes-api ~/workspace/notes-api'
            }
        }

        stage("Test"){
            steps {
                echo 'Todo'
            }
        }

        stage("Registry image"){
            steps {
                echo 'TODO'
            }
        }

        stage("Deploy"){
            steps {
                // sh 'docker stop notes-api'
                // sh 'docker rm notes-api'                
                sh 'docker run -p 8082:8080 --name=notes-api -d notes-api'
            }
        }

        stage("Remove temporary files"){
            steps {
                echo 'cleaning ...'
                echo 'rm ~/workspace/notes-app ~/workspace/notes-app@tmp -rf'
            }
        }

    }
    
}
