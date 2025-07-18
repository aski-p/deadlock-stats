FROM tomcat:9.0-jdk11-openjdk

# Remove default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy webapp to tomcat webapps directory
COPY webapp/ /usr/local/tomcat/webapps/ROOT/

# Set environment variables
ENV CATALINA_OPTS="-Dfile.encoding=UTF-8 -Xms512m -Xmx1024m"

# Expose port 8080
EXPOSE 8080

# Start tomcat
CMD ["catalina.sh", "run"]