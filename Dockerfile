FROM openjdk:21
LABEL authors="yago"
ADD target/api-product-0.0.1-SNAPSHOT.jar api-product-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "api-product-0.0.1-SNAPSHOT.jar"]