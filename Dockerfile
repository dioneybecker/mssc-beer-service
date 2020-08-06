FROM openjdk:8-jre-alpine
COPY ./target/mssc-beer-service-0.0.1-SNAPSHOT.war /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=test", "mssc-beer-service-0.0.1-SNAPSHOT.war"]