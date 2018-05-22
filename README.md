# ParcelService-server

## build command
```sh
docker build -t parcelservice-server .
```

## create docker container
```sh
docker run -d -p 8443:8443 --network=ParcelService --name=rest parcelservice-server java -jar ParSer-Server-1.0.jar
```
