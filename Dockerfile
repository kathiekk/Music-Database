FROM maven:3.8.4-openjdk-17

WORKDIR /app

COPY pom.xml ./

RUN mvn dependency:go-offline -B

COPY src /app/src

RUN mvn package -DskipTests

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=docker

CMD ["mvn", "spring-boot:run", "-e"]