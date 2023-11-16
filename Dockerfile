FROM openjdk:11
EXPOSE 8089

# Replace with the correct JAR file name
COPY ./SkiStationProject-1.0.0.jar /app/SkiStationProject-1.0.0.jar

WORKDIR /app
ENTRYPOINT ["java", "-jar", "SkiStationProject-1.0.0.jar"]