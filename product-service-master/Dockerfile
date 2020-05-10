FROM openjdk:8
MAINTAINER Adarsh <adarshchandran65@gmail.com>
VOLUME  /tmp
Expose 8808
ARG JAR_FILE=/target/product-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} product-service.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/product-service.jar" ]
