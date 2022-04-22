FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD dr-my-blog-1.0.0.RELEASE.jar dr-my-blog.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/dr-my-blog.jar"]