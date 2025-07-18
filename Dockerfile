FROM tomcat:9.0-jdk8-openjdk

# Remove default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy webapp
COPY webapp/ /usr/local/tomcat/webapps/ROOT/

# JDK 8 사용으로 CgroupV2 문제 우회
ENV CATALINA_OPTS="-Djava.awt.headless=true -Dfile.encoding=UTF-8 -server -Xms256m -Xmx512m"
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -Dcom.sun.management.jmxremote=false"

# Expose port
EXPOSE 8080

# Start tomcat
CMD ["catalina.sh", "run"]