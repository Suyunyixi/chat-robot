FROM openjdk:17-jdk
LABEL maintainer=suyunyixi


#docker run -e PARAMS="--server.port 9090"
ENV PARAMS="--server.port=8080"
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

COPY target/*.jar /app.jar
EXPOSE 8080

# java -Dmirai.slider.captcha.supported -Dfile.encoding=utf8 -Djava.security.egd=file:/dev/./urandom -jar app.jar --server.port=8080
# docker run --name chat -p 10220:8080 -it chat:latest bash
#ENTRYPOINT ["/bin/sh","-c","java -Dmirai.slider.captcha.supported -Dfile.encoding=utf8 -Djava.security.egd=file:/dev/./urandom -jar app.jar ${PARAMS}"]