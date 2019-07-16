FROM openjdk:12-jdk as build-env
WORKDIR /building/
COPY ./ ./
RUN ./gradlew clean check build

FROM adoptopenjdk/openjdk12:jre-12.33

WORKDIR /running/
COPY --from=build-env /building/build/libs/filtering-matches.jar filtering-matches.jar
COPY --from=build-env /building/src/main/resources/database_setup.json database_setup.json
EXPOSE 8080
ENTRYPOINT java -jar filtering-matches.jar database_setup.json
