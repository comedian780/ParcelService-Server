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
          returnStatus : true)
          //Remove the previous build image if it was build before
          if(IMAGE_EXISTS!="")
          {
              sh "docker rmi -f parcelservice-server"
          }
          //Build new container with image parcelservice-server
          sh "docker build -t parcelservice-server:${currentBuild.number} ."
      }
      else
      {
          //Remove the previous build image
          bat "docker rmi parcelservice-server"
          bat "docker build -t parcelservice-server ."
      }

   }
   stage('Deploy Image to Asset-Server')
   {
      sh "sshpass -p 'vagrant' scp -o StrictHostKeyChecking=no ./web/js/*.js vagrant@192.168.56.100:/home/vagrant/js"
      sh "docker save parcelservice-server:${currentBuild.number} > server.${currentBuild.number}.tar"
      sh "sshpass -p 'vagrant' scp -o StrictHostKeyChecking=no server.${currentBuild.number}.tar vagrant@192.168.56.100:/home/vagrant/images"
   }
   stage('Build and Start Testserver')
   {
      sh "vagrant up"
   }
   stage('Integration-Test')
   {
    if(isUnix())
    {
      sh "vagrant up"
      sh "python integration.py"
    }
  }
  stage('User Acceptance Test')
  {
     if(isUnix())
     {
         sh "vagrant up"
         sh "python uat.py"
     }
  }
  stage('Performance-Test')
  {
    sh "gradle gatlingRun"
    gatlingArchive()
  }
  stage('Manual User Test')
  {
    input 'Build Working ?'
  }
}
