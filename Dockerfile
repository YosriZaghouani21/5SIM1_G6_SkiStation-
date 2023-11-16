FROM openjdk:11
EXPOSE 8089

# Set the working directory
WORKDIR /app

# Download the JAR file and rename it
RUN curl -o SkiStationProject-1.0.0.jar -L "http://192.168.33.113:8081/repository/maven-releases/tn/esprit/ds/SkiStationProject/1.0.0/SkiStationProject-1.0.0.jar"

ENTRYPOINT ["java", "-jar", "SkiStationProject-1.0.0.jar"]
