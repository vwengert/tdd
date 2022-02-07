FROM adoptopenjdk/openjdk11:alpine-jre

ADD /build/libs/*.war app.war

CMD java -jar -Dspring.profiles.active=docker /app.war
