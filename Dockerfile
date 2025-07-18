FROM tomcat:9.0-jdk11-openjdk

# Remove default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy webapp
COPY webapp/ /usr/local/tomcat/webapps/ROOT/

# Create startup script that handles dynamic port
RUN echo '#!/bin/bash\n\
if [ -n "$PORT" ]; then\n\
  sed -i "s/8080/$PORT/g" /usr/local/tomcat/conf/server.xml\n\
fi\n\
catalina.sh run' > /start.sh && chmod +x /start.sh

# Expose dynamic port
EXPOSE $PORT

# Use startup script
CMD ["/start.sh"]