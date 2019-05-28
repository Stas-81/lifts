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
			sh 'gradle clean'
			//bat 'set'
			// esh 'ls -la'
                    }
            	}
	    }
        }
	stage('Test') {
            steps {
                sh 'echo "Test step"'
		sh 'gradle build'
		// bat 'echo "error"'
            }
        }
    }
    post {
        always {
	    archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
            step([$class: 'Publisher', reportFilenamePattern: '**/testng-results.xml'])
	    deleteDir()
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            mail to: 'msy82@mail.ru',
             subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
             body: "Something is wrong with ${env.BUILD_URL}"
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