FROM amazoncorretto:17
ADD target/*.jar phishing-validator.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "phishing-validator.jar"]
#Example run
#docker run --name phishing-validator-app -p 9090:8080 -d phishing-validator-app:latest