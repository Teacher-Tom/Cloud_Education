FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD service_exp-1.0-SNAPSHOT.jar service_exp.jar
RUN sh -c 'touch /service_exp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/service_exp.jar"]