FROM openjdk:8u212-jre
MAINTAINER zlczhou 646908036@qq.com

COPY ./target/febs-server-member-2.0-RELEASE.jar /febs/febs-server-member-2.0-RELEASE.jar
ADD agent/ /agent

ENTRYPOINT ["java", "-javaagent:/agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=febs-server-member", "-Dskywalking.collector.backend_service=${skywalking.url}:11800", "-jar", "/febs/febs-server-member-1.4-RELEASE.jar"]