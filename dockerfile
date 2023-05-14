FROM cimg/android:2023.05.1

COPY . /app
WORKDIR /app

EXPOSE 8080
#CMD ["gradlew", "backend:run -Ptesting=true"]
CMD ["./gradlew", "backend:run", "-Ptesting=true"]
