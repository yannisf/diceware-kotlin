FROM eclipse-temurin:17
COPY ./web/build/libs/app.jar app.jar
EXPOSE 8080 9090
ENTRYPOINT ["java", "-jar", "app.jar"]