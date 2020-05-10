FROM openjdk:8
MAINTAINER Adarsh <adarshchandran65@gmail.com>
VOLUME  /tmp
Expose 9091
ARG JAR_FILE=/target/Promotion-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} Promotion.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/Promotion.jar" ]
