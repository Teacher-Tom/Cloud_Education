FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD service_manager-1.0-SNAPSHOT.jar service_manager.jar
RUN sh -c 'touch /service_manager.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/service_manager.jar"]