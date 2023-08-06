FROM eclipse-temurin:17
COPY ./web/build/libs/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]