FROM maven:latest AS builder

# Copy the Emulator sources to the container
COPY . .
# Package it
RUN mvn package && mv /target/HabForgeServer-*.jar /target/HabForgeServer.jar

# Use Java 8 for running
FROM openjdk:8-jdk-alpine AS runner

# Copy the generated source
COPY --from=builder /target/HabForgeServer.jar /app/HabForgeServer.jar

# Save the script to wait for the database, among running the Arcturus Emulator
RUN echo "#!/bin/bash \n java -Dfile.encoding=UTF-8 -jar /app/HabForgeServer.jar" > /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh

# Run the Emulator with Java
ENTRYPOINT ["/app/entrypoint.sh"]
