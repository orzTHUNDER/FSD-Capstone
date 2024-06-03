FROM openjdk
WORKDIR /opt
ENV PORT 8081
EXPOSE 8081
COPY target/*.jar /opt/app.jar

ENTRYPOINT exec java -jar app.jar