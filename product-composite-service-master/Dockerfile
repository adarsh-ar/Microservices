FROM openjdk:8
MAINTAINER Adarsh <adarshchandran65@gmail.com>
VOLUME  /tmp
Expose 8360
ARG JAR_FILE=/target/product-composite-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} composite-service.jar
ENTRYPOINT ["java","-Xmx1024m","-Xms1024m", "-Djava.security.egd=file:/dev/./urandom","-jar", "/composite-service.jar" ]
