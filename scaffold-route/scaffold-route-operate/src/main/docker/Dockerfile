FROM frolvlad/alpine-oraclejdk8
#指定维护者信息
MAINTAINER zzf
VOLUME /tmp
ADD target/scaffold-route-operate-1.0-SNAPSHOT.jar bootDemo.jar
RUN sh -c 'touch /bootDemo.jar'
ENV JAVA_OPTS=""
EXPOSE 9191
#指定执行启动spring boot小项目     ENTRYPOINT 为容器启动后执行的命令
ENTRYPOINT ["java","-jar","/bootDemo.jar"]
