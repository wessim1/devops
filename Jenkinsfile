pipeline{

	environment{
		registry = 'daghbari1/devopsimage'
		registryCredential= 'dockerHub'
		dockerImage = ''
	}


		agent any 
	stages{
		stage ('Checkout GIT'){
			steps{
				echo 'Pulling...';
					git branch: 'nabil',
					url : 'https://github.com/wessim1/devops.git';
			}
		}

		stage ("Verification du  version Maven"){
			steps{
				bat """mvn -version"""
			}
		}


		 stage ("clean"){
		 	steps{
		 		bat """mvn clean """
		 	}
		 }


 		 stage ("creation de livrable"){
		 	steps{
		 		bat """mvn package -Dmaven.test.skip=true"""
		 	}
		 }

		 stage ("Lancement des Tests Unitaires"){
		 	steps{
		 		bat """mvn test"""
		 	}
		 }


		 stage ("Analyse avec Sonar"){
		 	steps{
		 		bat """mvn sonar:sonar"""
		 	}
		 }

		 stage ("Deploiement "){
		 	steps{
		 		bat """mvn clean package -Dmaven.test.skip=true deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=timesheet -Dversion=1.0 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-releases/ -Dfile=target/timesheet-1.0-SNAPSHOT.war"""
		 	}
		 }
		 stage('Building our image'){
			steps{ 
				script{ 
					dockerImage= docker.build registry + ":$BUILD_NUMBER" 
				}
			}
		}

		stage('Deploy our image'){
			steps{ 
				script{
					docker.withRegistry( '', registryCredential){
						dockerImage.push()
					} 
				} 
			}
		}

		stage('Cleaning up'){
			steps{
				bat "docker rmi $registry:$BUILD_NUMBER" 
			}
		}
		stage('email'){
			steps{
			mail bcc: '', body: 'build_success', cc: '', from: '', replyTo: '', subject: 'jenkins', to: 'nabil.daghbari39@gmail.com'
			}
		}
	}


	
}
