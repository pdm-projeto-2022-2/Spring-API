FROM maven:latest as build
ADD 'pom.xml' '/app/'
WORKDIR '/app'
RUN ["mvn","dependency:go-offline"]
ADD 'src/' '/app/src'
RUN ["touch", "/app/src/main/resources/upload-config.yml"]
RUN ["mvn", "install", "-Dmaven.test.skip"]

FROM openjdk:latest
COPY --from=build '/app/target/api-0.0.1-SNAPSHOT.jar' '/app/api.jar'
RUN ["mkdir", "/app/uploads"]
ENTRYPOINT [ "java", "-jar", "/app/api.jar" ]