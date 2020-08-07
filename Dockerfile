FROM openjdk:11-jdk AS builder
COPY . /source
WORKDIR /source
RUN ./gradlew bootJar

FROM openjdk:11-jre-slim
RUN apt-get update && apt-get install curl -y
ARG app_version=DEV
ENV APP_VERSION=$app_version
COPY --from=builder /source/build/libs/scaffold-1.0.0.jar /app/
COPY --from=builder /source/build/libs/newrelic.jar /app/
COPY --from=builder /source/config/newrelic.yml /app/config/
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["/usr/local/bin/shush", "exec", "--"]
CMD ["java", "-javaagent:/app/newrelic.jar", "-jar", "scaffold-1.0.0.jar"]
