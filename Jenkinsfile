pipeline {
    agent any
	environment {
        	DISABLE_AUTH = 'true'
        	DB_ENGINE    = 'sqlite'
    	}
    tools {
        gradle "gradle-5.4"
    }
    stages {
        stage('build') {
            steps {
		timeout(time: 3, unit: 'MINUTES') {
                    retry(5) {
                        sh 'echo "Build step"'
			//bat 'set'
			// esh 'ls -la'
                    }
            	}
	    }
        }
	stage('Test') {
            steps {
                sh 'echo "Test step"'
		sh 'gradle clean build'
            }
        }
    }
    post {
        always {
            junit '**/testng-results.xml'

        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }
}