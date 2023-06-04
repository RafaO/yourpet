FROM adoptopenjdk:11-jdk-hotspot

USER root
RUN adduser --system --uid 10000 backend-user

EXPOSE 8080

COPY --chown=backend-user backend/build/libs/backend-jvm.jar .
USER backend-user
CMD ["java", "-jar", "backend-jvm.jar"]
