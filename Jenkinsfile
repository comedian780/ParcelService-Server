node {
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
          if((sh "docker images -q parcelservice-server") != "")
          {
              sh "docker rmi parcelservice-server"
          }
          //Build new container with image parcelservice-server
          sh "docker build -t parcelservice-server ."
      }
      else
      {
          //Remove the previous build image
          bat "docker rmi parcelservice-server"
          bat "docker build -t parcelservice-server ."
      }

   }
}
