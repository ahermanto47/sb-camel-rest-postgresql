FROM openjdk:11.0.10-slim as builder
COPY target/*.jar /app.jar
RUN java -Djarmode=layertools -jar /app.jar extract

FROM openjdk:11.0.4-jre-slim
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
ENTRYPOINT exec java $JAVA_OPTS org.springframework.boot.loader.JarLauncher
