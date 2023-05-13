FROM cimg/android:2021.08.1

COPY . /app
WORKDIR /app

EXPOSE 8080
#CMD ["gradlew", "backend:run -Ptesting=true"]
