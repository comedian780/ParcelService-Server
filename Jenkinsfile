node {
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/sabertoothx6/ParcelService-Server.git'
   }
   stage('Build') {
      // Run the gradle build
      if (isUnix())
      { //Build through shell command
         sh "gradle clean build"
      } else
      {
         bat 'gradlew.bat clean build'
      }
   }
   stage('Build Docker Image')
   {
      if(isUnix())
      {
          //Create Variable that holds the info if docker image exists
          IMAGE_EXISTS = sh(
          script: "docker images -q parcelservice-server",
          returnStatus : true) !=""
          //Remove the previous build image if it was build before
          if(IMAGE_EXISTS)
          {
              sh "docker rm -f parcelservice-server"
              sh "docker rmi -f parcelservice-server"
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
   /*stage('Run ParcelService-Server')
   {
      sh "docker run -d -p 8443:8443 --name=test_rest parcelservice-server java -jar ParSer-Server-1.0.jar"
   }*/
}
