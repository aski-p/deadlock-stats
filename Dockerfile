FROM tomcat:9.0-jdk11-openjdk

# Remove default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy webapp
COPY webapp/ /usr/local/tomcat/webapps/ROOT/

# Management Bean 관련 문제 해결을 위한 설정
ENV CATALINA_OPTS="-Djava.awt.headless=true -Dfile.encoding=UTF-8 -server -Xms256m -Xmx512m"
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -Dcom.sun.management.jmxremote=false"

# JMX 및 Management 기능 비활성화
RUN sed -i '/GlobalResourcesLifecycleListener/d' /usr/local/tomcat/conf/server.xml

# Expose port
EXPOSE 8080

# Start tomcat
CMD ["catalina.sh", "run"]