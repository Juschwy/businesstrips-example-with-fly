FROM maven:3.8.5-openjdk-17-slim as backend-build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN ls -laR
RUN mvn install -DskipTests

FROM openjdk:18-slim
COPY --from=backend-build /app/target/*.jar app.jar
ARG ALLOWED_ORIGIN="http://localhost:3000"
ENV ALLOWED_ORIGIN_ENV=$ALLOWED_ORIGIN
ENTRYPOINT java -jar ./app.jar --allowedOrigin=$ALLOWED_ORIGIN_ENV