FROM cimg/android:2023.05.1

WORKDIR /app

USER root
RUN adduser --system --uid 10000 backend-user
RUN export ANDROID_HOME
RUN sudo chmod -R 777 $ANDROID_HOME
RUN sudo chmod 777 /home/circleci

EXPOSE 8080

COPY --chown=backend-user . /app
RUN chmod 777 /app
USER backend-user
CMD ["./gradlew", "backend:run", "-Ptesting=true"]
