FROM tomcat:9.0-jdk11-openjdk

# Remove default apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy our app
COPY webapp/ /usr/local/tomcat/webapps/ROOT/

# Expose port
EXPOSE 8080

# Start tomcat in foreground
CMD ["catalina.sh", "run"]