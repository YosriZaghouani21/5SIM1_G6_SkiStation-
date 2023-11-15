FROM openjdk:11
EXPOSE 8089

ARG APP_VERSION=1.0.0

# Download the JAR file from the specified URL and rename it to gestion-station-ski-${APP_VERSION}.jar
RUN set -eux; \
    curl -o gestion-station-ski-${APP_VERSION}.jar -L "http://192.168.33.113:8081/repository/maven-releases/tn/esprit/spring/gestion-station-ski/${APP_VERSION}/gestion-station-ski-${APP_VERSION}.jar" || \
    { echo "Error downloading JAR file"; exit 1; }

ENTRYPOINT ["java", "-jar", "gestion-station-ski-${APP_VERSION}.jar"]

# Clean up unnecessary dependencies
RUN apt-get purge -y --auto-remove curl \
    && rm -rf /var/lib/apt/lists/*
