FROM openjdk:8
MAINTAINER Adarsh <adarshchandran65@gmail.com>
VOLUME  /tmp
Expose 9096
ARG JAR_FILE=/target/micro-inventory-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} inventory.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/inventory.jar" ]
