#!/bin/sh
#
# Shell script that automatically rebuilding a service's Docker image from source and then launch a container.
# Note this script is intended only for debugging purposes and should not be used as part of an automated build pipeline.
#
# Author: James Cruddas

apt install -y dos2unix
dos2unix *
apt install -y openjdk-8-jdk
apt install -y maven
mvn package -f ../../pom.xml
cp ../../target/spring-rest-micro-service-0.0.1-SNAPSHOT.jar . #Copy output jar to local Docker build path
rm -f -R /opt/docker/spring-rest-micro-service
mkdir -p /opt/docker/spring-rest-micro-service/config
mkdir -p /opt/docker/spring-rest-micro-service/logs
cp ../../src/main/resources/* /opt/docker/spring-rest-micro-service/config
docker stop spring-rest-micro-service || true && docker rm spring-rest-micro-service  || true && docker rmi spring-rest-micro-service  || true
docker build -t spring-rest-micro-service .
docker run -d -v /opt/docker/spring-rest-micro-service/logs:/logs \ 
	-v /opt/docker/spring-rest-micro-service/config:/config \
	--restart=always \ 
	--name spring-rest-micro-service \ 
	-p 8100:8080 \
	spring-rest-micro-service