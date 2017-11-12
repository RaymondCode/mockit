FROM java:8

ENV APP_NAME @project.build.finalName@.@project.packaging@

VOLUME /tmp
ADD $APP_NAME app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]