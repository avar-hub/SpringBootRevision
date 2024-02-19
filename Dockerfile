#It is base image and it will fetch openjdk from Dockerhub
FROM openjdk:17
#Make this port availibe outside the container
EXPOSE 8080
#Here we have to add jar of springboot docker , 2nd time we have written for the root directory of the jar
ADD ./build/libs/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar
#Here we have to specify command to run the jar (last argument is the name of the jar)
ENTRYPOINT ["java", "-jar", "/demo-0.0.1-SNAPSHOT.jar"]

