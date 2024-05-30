FROM openjdk
WORKDIR /opt
ENV PORT 8761
EXPOSE 8761
COPY target/*.jar /opt/app.jar

ENTRYPOINT exec java -jar app.jar