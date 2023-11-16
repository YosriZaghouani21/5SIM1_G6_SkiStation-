FROM openjdk:11
EXPOSE 8089

#Download the JAR file from the specified URL and rename it to gestion-station-ski-1.0.jar
RUN curl -o SkiStationProject-1.0.0.jar -L "http://192.168.33.10:8081/repository/maven-releases/tn/esprit/ds/SkiStationProject/1.0.0/SkiStation-1.0.0.jar"

ENTRYPOINT ["java", "-jar", "SkiStationProject-1.0.0.jar"]