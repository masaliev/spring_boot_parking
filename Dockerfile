FROM gradle AS builder
RUN mkdir -p /usr/src/app
ADD . /usr/src/app/
WORKDIR /usr/src/app
RUN ./gradlew build -x test

FROM openjdk:8-jdk
RUN wget -qO- https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/6.4.3/flyway-commandline-6.4.3-linux-x64.tar.gz | tar xvz && ln -s `pwd`/flyway-6.4.3/flyway /usr/local/bin && \
    mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY --from=builder /usr/src/app/build/libs/parking-0.0.1-SNAPSHOT.jar parking.jar
ADD entrypoint.sh .
ADD migrations/flyway.conf .
ADD migrations/sql ./sql/
ENTRYPOINT ["/bin/bash", "entrypoint.sh"]