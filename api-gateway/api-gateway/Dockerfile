FROM maven:3.8.5-eclipse-temurin-17-alpine@sha256:38e7fe344f85a25e88fb15c5588360e439dab00cd4abdb72c11670543075965b AS builder
WORKDIR build
COPY . .
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jre-alpine@sha256:d69f8cf3526fd75992366d2e96348682dfbc04c5d321c08d084e1fc26980d81d AS layers
WORKDIR layer
COPY --from=builder /build/target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar
RUN java -Djarmode=layertools -jar api-gateway.jar extract

FROM eclipse-temurin:17-jre-alpine@sha256:d69f8cf3526fd75992366d2e96348682dfbc04c5d321c08d084e1fc26980d81d
WORKDIR /opt/app
RUN addgroup --system appuser && adduser -S -s /usr/sbin/nologin -G appuser appuser
COPY --from=layers /layer/dependencies/ ./
COPY --from=layers /layer/spring-boot-loader/ ./
COPY --from=layers /layer/snapshot-dependencies/ ./
COPY --from=layers /layer/application/ ./
RUN chown -R appuser:appuser /opt/app
USER appuser
HEALTHCHECK --interval=30s --timeout=3s --retries=1 CMD wget -qO- http://localhost:9191/actuator/health/ | grep UP || exit 1
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]