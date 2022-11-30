# ------------------------------------------------------------------------
#
# Directorio de ubicacion de todos los microservicio /opt/<NOMBRE>
#
# docker build -f Dockerfile -t telco-micro-java-arch-restblank .
# 
# ------------------------------------------------------------------------

FROM openjdk:11-jre-slim-buster
RUN apt-get update && apt-get install curl -y
COPY ./src/web-call-back.jar /opt/microservice/web-call-back.jar
ENTRYPOINT ["java","-Duser.timezone=CO","-jar","/opt/microservice/web-call-back.jar"]