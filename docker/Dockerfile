FROM openjdk:8-alpine
ADD sampleproject.three-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]