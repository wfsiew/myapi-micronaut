FROM openjdk:14-alpine
COPY build/libs/myapi-*-all.jar myapi.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "myapi.jar"]