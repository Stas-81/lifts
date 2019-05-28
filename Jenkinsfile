pipeline {
<<<<<<< HEAD
    agent any
	environment {
        	DISABLE_AUTH = 'true'
        	DB_ENGINE    = 'sqlite'
    	}
    stages {
        stage('build') {
            steps {
		timeout(time: 3, unit: 'MINUTES') {
                    retry(5) {
                        bat 'echo "hello"'
			bat 'set'
			// esh 'ls -la'
                    }
            	}
	    }
	stage('Test') {
            steps {
                bat 'gradle clean build'
            }
        }
        }
    }
    post {
        always {
            echo 'This will always run'
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
=======
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Jenkins minute pipeline'
      }
    }
  }
>>>>>>> ae82fca7e4d984f735413f6ebe4fecc23d3c55ad
}