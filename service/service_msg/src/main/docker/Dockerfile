FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD service_msg-1.0-SNAPSHOT.jar service_msg.jar
RUN sh -c 'touch /service_msg.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/service_msg.jar"]