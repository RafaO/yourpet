# YourPet

Welcome to `YourPet`, a side project I started with three main goals:

- Don't get too rusty in my coding skills since my current role doesn't have much room to do coding.
- Play around with Kotlin Multiplatform Mobile so I get to explore its potential first hand.
- Play with any other technology I might come across and attracts me. Below you can find some that I
  have currently in mind (can't promise I will get to try them all, but PR are always welcomed)

## Tech stack

- [Jetpack compose](https://developer.android.com/jetpack/compose)
- [Swift UI](https://developer.apple.com/xcode/swiftui/)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Flow](https://developer.android.com/kotlin/flow)
- [SqlDelight](https://github.com/cashapp/sqldelight)
- [GraphQL](https://graphql.org/)
- [MongoDB](https://www.mongodb.com/)
- [Shot](https://github.com/pedrovgs/Shot) + [Showkase](https://github.com/airbnb/Showkase) for
  screenshot testing on Android
- [Maestro](https://maestro.mobile.dev/)
- [Github actions](https://github.com/features/actions)

## Instructions

- To run the backend, execute `./gradlew backend:run` in the main directory. There are two ways of
running the backend:
  - Using local database. Make sure you are running mongodb in localhost. You can use the provided 
  container application in the backend directory.
For that you just need to run `./docker-compose up` in the `backend` directory. Bear in mind that in
  order to create the database with mock data, you can use the flag `createContent` in `Server.kt` file.
  - Testing mode. If you want the backend to retrieve mocked in memory data you can run it with
  `./gradlew backend:run -Ptesting=true`
- Then visit `http://localhost:8080/graphql` to reach the playground and documentation.

In order to get the schema file, once the backend is up and running. Execute the following command:
```./gradlew downloadApolloSchema --endpoint="http://localhost:8080/graphql" --schema="shared/src/commonMain/graphql/schema.json"```
