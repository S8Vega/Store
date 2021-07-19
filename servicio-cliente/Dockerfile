FROM amazoncorretto:11
VOLUME /tmp
ADD build/libs/servicio-cliente-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]