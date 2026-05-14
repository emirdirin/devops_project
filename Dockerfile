
FROM amazoncorretto:25

COPY build/libs/devops_project2-0.0.1-SNAPSHOT.jar ./project2.jar

#LABEL authors="EMİR"

ENTRYPOINT ["java", "-jar", "project2.jar"]

EXPOSE 8080
