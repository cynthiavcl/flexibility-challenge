FROM openjdk:11-jre-slim
COPY target/examenCynVocal-0.0.1.jar java-app.jar
ENTRYPOINT ["java","-jar","/java-app.jar"]
EXPOSE 8080


