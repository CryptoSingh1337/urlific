FROM maven:3.9.5-eclipse-temurin-17-alpine as builder

ARG port=8080
ARG mongo-url
ARG sever-base-address
ARG secret-key
ARG google-client-id
ARG google-client-secret
ARG google-redirect-uri
ARG google-authorization-uri
ARG google-token-uri
ARG google-user-info-uri
ARG google-jwk-set-uri

ENV PORT=$port
ENV MONGO_URL=$mongo-url
ENV SERVER_BASE_ADDRESS=$sever-base-address
ENV SECRET_KEY=$secret-key
ENV GOOGLE_CLIENT_ID=$google-client-id
ENV GOOGLE_CLIENT_SECRET=$google-client-secret
ENV GOOGLE_REDIRECT_URI=$google-redirect-uri
ENV GOOGLE_AUTHORIZATION_URI=$google-authorization-uri
ENV GOOGLE_TOKEN_URI=$google-token-uri
ENV GOOGLE_USER_INFO_URI=$google-user-info-uri
ENV GOOGLE_JWK_SET_URI=$google-jwk-set-uri

COPY ./src src/
COPY ./pom.xml pom.xml

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
COPY --from=builder target/*.jar app.jar
EXPOSE $PORT
CMD ["java", "-jar", "app.jar"]
