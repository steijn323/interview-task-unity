FROM eclipse-temurin:17-jdk-alpine
COPY build/libs/repository-task-0.0.1-SNAPSHOT.jar repository-task-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/repository-task-0.0.1-SNAPSHOT.jar"]