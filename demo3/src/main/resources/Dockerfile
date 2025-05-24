FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/hotel.jar hotel.jar
ENTRYPOINT ["java", "-jar", "/hotel.jar"]