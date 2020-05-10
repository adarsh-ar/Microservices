FROM openjdk:8
MAINTAINER Adarsh <adarshchandran65@gmail.com>
VOLUME  /tmp
Expose 8089
ARG JAR_FILE=/target/mircro-prices-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} micro-price.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/micro-price.jar" ]
