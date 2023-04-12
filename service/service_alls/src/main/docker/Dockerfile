FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD service_alls-1.0-SNAPSHOT.jar service-alls.jar
RUN apk add --update font-adobe-100dpi ttf-dejavu fontconfig
RUN sh -c 'touch /service-alls.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/service-alls.jar"]