FROM openjdk
WORKDIR /opt
ENV PORT 8765
EXPOSE 8765
COPY target/*.jar /opt/app.jar

ENTRYPOINT exec java -jar app.jar