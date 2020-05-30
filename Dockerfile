FROM gradle AS builder
RUN mkdir -p /usr/src/app
ADD . /usr/src/app/
WORKDIR /usr/src/app
RUN ./gradlew build -x test

FROM openjdk:8-jdk-alpine
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY --from=builder /usr/src/app/build/libs/parking-0.0.1-SNAPSHOT.jar parking.jar
ENTRYPOINT ["java","-jar","parking.jar"]