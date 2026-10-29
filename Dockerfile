FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /workspace

COPY gradlew build.gradle settings.gradle ./
COPY gradle ./gradle
RUN chmod +x gradlew

COPY src ./src
RUN ./gradlew --no-daemon --max-workers=2 bootJar --console=plain \
    && test -s /workspace/build/libs/app.jar

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

LABEL org.opencontainers.image.authors="Mailebai Beksultan"
LABEL org.opencontainers.image.version="1.0.1-37691"

RUN groupadd --system app \
    && useradd --system --gid app app

COPY --from=build --chown=app:app /workspace/build/libs/app.jar /app/app.jar

USER app
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
