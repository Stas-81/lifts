// Powered by Infostretch 

timestamps {

node () {

	stage ('lift2 - Checkout') {
 	 checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'git_login', url: 'https://github.com/Stas-81/lifts']]]) 
	}
	stage ('lift2 - Build') {
 	
// Unable to convert a build step referring to "hudson.plugins.gradle.Gradle". Please verify and convert manually if required.
		// TestNG Results
		step([$class: 'Publisher', escapeExceptionMsg: true, escapeTestDescp: true, failureOnFailedTestConfig: false, reportFilenamePattern: '**/testng-results.xml', showFailedBuilds: false, thresholdMode: 2, unstableSkips: 100, failedSkips: 100, unstableFails: 0, failedFails: 100]) 
	}
}
}