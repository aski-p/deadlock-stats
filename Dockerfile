FROM tomcat:9.0-jdk11-openjdk

# Remove default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy webapp to tomcat webapps directory
COPY webapp/ /usr/local/tomcat/webapps/ROOT/

# Expose port
EXPOSE 8080

# Start tomcat
CMD ["catalina.sh", "run"]