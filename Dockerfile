FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/shopping-cart-service-0.0.1.jar
COPY ${JAR_FILE} app_cart.jar
ENTRYPOINT ["java", "jar", "app_cart.jar"]