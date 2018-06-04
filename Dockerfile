#Get OpenJDK Image
FROM openjdk:alpine

#Make dir for the server jar
RUN mkdir Server

#Set working dir to new folder
WORKDIR Server

#Copy Server.jar to target location
COPY ./build/libs/. /Server

#Show me what you got
EXPOSE 8443

CMD java -jar ParSer-Server-1.0.jar
