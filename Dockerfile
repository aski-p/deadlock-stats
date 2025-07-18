FROM tomcat:9.0-jdk11-openjdk

# Remove default ROOT webapp
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy our webapp as ROOT
COPY webapp/ /usr/local/tomcat/webapps/ROOT/

# Make sure Tomcat starts properly
ENV CATALINA_OPTS="-Djava.awt.headless=true -server -Xms512m -Xmx1024m"

# Expose port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]