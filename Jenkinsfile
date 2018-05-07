node {
   def mvnHome
   stage('Get ParcelService-Server') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/sabertoothx6/ParcelService-Server.git'
   }
   stage('Build ParcelService-Server') {
      // Run the gradle build
      if (isUnix())
      { //Build through shell command
         sh "gradle clean build"
      } else
      {
         bat 'gradlew.bat clean build'
      }
   }
   stage('Put in Docker')
   {
      if(isUnix())
      {
          //Remove the previous build image
          sh "docker rmi ParcelService-Server"
          //Build new container with image ParcelService-Server
          sh "docker build -t ParcelService-Server ."
      }
      else
      {
          //Remove the previous build image
          bat "docker rmi ParcelService-Server"
          bat "docker build -t ParcelService-Server ."
      }

   }
}
