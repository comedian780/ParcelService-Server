node {
   def mvnHome
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/sabertoothx6/ParcelService-Server.git'
   }
   stage('Build') {
      // Run the gradle build
      if (isUnix()) {
         sh "gradle clean build"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
   /*stage('Results') {
      junit '*target/surefire-reports/TEST-*.xml'
      archive 'target/*.jar'
   }*/
}
