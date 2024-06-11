FROM openjdk:11-jre-slim
COPY target/examen-cyn-vocal-0.0.1.jar java-app.jar
ENTRYPOINT ["java","-jar","/java-app.jar"]
EXPOSE 8081


