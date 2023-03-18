FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD service_ucenter-1.0-SNAPSHOT.jar service-ucenter.jar
RUN sh -c 'touch /service-ucenter.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/service-ucenter.jar"]