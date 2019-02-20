FROM openjdk:8
ADD target/delivery-fast-spring-boot.jar delivery-fast-spring-boot.jar
ADD data  /data
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "delivery-fast-spring-boot.jar"]