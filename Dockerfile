FROM openjdk:11
EXPOSE 8089

ARG APP_VERSION=1.0

# Download the JAR file from the specified URL and rename it to gestion-station-ski-${APP_VERSION}.jar
ADD http://192.168.33.113:8081/repository/maven-releases/tn/esprit/spring/gestion-station-ski/${APP_VERSION}/gestion-station-ski-${APP_VERSION}.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
