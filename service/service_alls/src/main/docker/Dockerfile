FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD service_alls-1.0-SNAPSHOT.jar service-alls.jar
RUN sh -c 'touch /service-alls.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/service-alls.jar"]