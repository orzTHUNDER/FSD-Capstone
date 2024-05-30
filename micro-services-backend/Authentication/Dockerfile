FROM openjdk
WORKDIR /opt
ENV PORT 8082
EXPOSE 8082
COPY target/*.jar /opt/app.jar

ENTRYPOINT exec java -jar app.jar