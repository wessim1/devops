FROM openjdk:8-jre-alpine
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \JAVA_OPTS=""
WORKDIR /app
EXPOSE 8083
ADD target/*.war timesheet-1.0-SNAPSHOT.war
ENTRYPOINT ["java","-war","/timesheet-1.0-SNAPSHOT.war"]