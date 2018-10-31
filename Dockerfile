#Get OpenJDK Image
FROM openjdk:alpine


#Copy Server.jar to target location
ADD ./build/libs/. /Server

#Set working dir to new folder
WORKDIR Server

#Show me what you got
EXPOSE 8443

CMD java -jar ParSer-Server-1.0.jar
