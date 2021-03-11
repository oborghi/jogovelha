FROM openjdk:11.0.9-jre

COPY target/tic-tac-toe.jar .

EXPOSE 8080

ENTRYPOINT java \
    -XX:InitialRAMPercentage=25 \
    -XX:MaxRAMPercentage=50 \
    -Djava.net.preferIPv4Stack=true \
    -jar tic-tac-toe.jar

HEALTHCHECK CMD curl --fail http://localhost:8080/actuator/health || exit 1