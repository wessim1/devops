FROM openjdk:8
EXPOSE 8084
ADD /target/timesheet-1.0-SNAPSHOT.war timesheet-1.0-SNAPSHOT.war
ENTRYPOINT ["java","-jar","timesheet-2.0-SNAPSHOT.war","--spring.config.name=prod"]
