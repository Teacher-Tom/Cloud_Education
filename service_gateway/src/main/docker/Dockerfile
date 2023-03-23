FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD service_gateway-1.0-SNAPSHOT.jar service_gateway.jar
RUN sh -c 'touch /service_gateway.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/service_gateway.jar"]