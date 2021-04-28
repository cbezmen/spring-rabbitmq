FROM adoptopenjdk/openjdk11:alpine-jre

MAINTAINER Can Bezmen <can.bezmen@mavi.com>

ADD ./target/*.jar app.jar

CMD ["java", "-jar", "-Xmx128m", "-Dspring.profiles.active=docker", "-Duser.timezone=UTC", "app.jar"]
