FROM maven:3.9.12-eclipse-temurin-21
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY src/main/resources/app.settings.docker.properties src/main/resources/app.settings.properties
RUN sed -i 's/>26</>21</g' pom.xml
RUN mvn clean package -Dcheckstyle.skip=true -Dmaven.test.skip=true
RUN cp target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]