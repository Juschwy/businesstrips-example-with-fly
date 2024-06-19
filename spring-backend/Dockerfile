FROM maven:3.8.5-openjdk-17-slim as backend-build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN ls -laR
RUN mvn install -DskipTests

FROM openjdk:17-slim
COPY --from=backend-build /app/target/*.jar app.jar
ARG ALLOW_ORIGIN="http://localhost:3000"
ENTRYPOINT ["sh", "-c", "APP_ALLOWEDORIGIN=$ALLOW_ORIGIN", "java", "-jar", "app.jar"]