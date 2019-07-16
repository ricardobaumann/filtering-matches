FROM openjdk:12-jdk as build-env
WORKDIR /building/
COPY ./ ./
RUN ./gradlew clean check build

FROM adoptopenjdk/openjdk12:jre-12.33

WORKDIR /running/
COPY --from=build-env /building/build/libs/filtering-matches-frontend.jar filtering-matches-frontend.jar
EXPOSE 8080
ENTRYPOINT java -Dspring.profiles.active=docker -jar filtering-matches-frontend.jar
