FROM amazoncorretto:11
VOLUME /tmp
ADD build/libs/servicio-documentacion-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]