FROM openjdk:17-jdk-alpine

ARG DB_NAME
ARG DB_USERNAME
ARG DB_PASSWORD

ENV DB_NAME=${DB_NAME}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV DOCKERIZE_VERSION=v0.7.0

WORKDIR /app

COPY pom.xml .

COPY . .

RUN apk --no-cache add maven

RUN mvn package -DskipTests

EXPOSE 8080

RUN apk update --no-cache \
    && apk add --no-cache wget openssl \
    && wget -O - https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz | tar xzf - -C /usr/local/bin \
    && apk del wget
