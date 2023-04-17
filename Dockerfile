FROM openjdk:17-alpine AS builder
COPY . /source
WORKDIR /source
RUN ./gradlew bootJar

FROM openjdk:17-alpine
RUN apt-get update && apt-get install curl -y
ARG app_version=DEV
ENV APP_VERSION=$app_version
COPY --from=builder /source/build/libs/scaffold-1.0.0.jar /app/
COPY --from=builder /source/config/newrelic.yml /app/config/
RUN curl --show-error --fail -sL -o /usr/local/bin/shush https://github.com/realestate-com-au/shush/releases/download/v1.3.4/shush_linux_amd64 && chmod +x /usr/local/bin/shush
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["/usr/local/bin/shush", "exec", "--"]
CMD ["java", "-jar", "scaffold-1.0.0.jar"]
