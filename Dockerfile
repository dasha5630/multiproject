FROM openjdk:11
COPY . /src
WORKDIR /src
RUN ./mvnw package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/multiproject-0.0.1-SNAPSHOT.jar"]