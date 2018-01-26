FROM java:latest
VOLUME /tmp
ADD ./spring-rest-micro-service*.jar service.jar
RUN sh -c 'touch /service.jar'
ENTRYPOINT ["java", "-Dlog4j.configurationFile=/config/log4j2.xml", "-jar","/service.jar"]