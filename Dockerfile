FROM eclipse-temurin:17-jre-alpine
COPY . /src
WORKDIR /src
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/multiproject-0.0.1-SNAPSHOT.jar"]
