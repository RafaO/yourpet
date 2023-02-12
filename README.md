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

- Make sure you are running mongodb in localhost.
- To run the backend, execute `./gradlew backend:runServer` in the main directory.
- Then visit `http://localhost:8080/graphql` to reach the playground and documentation.

In order to get the schema file, once the backend is up and running. Execute the following command:
```./gradlew downloadApolloSchema --endpoint="http://localhost:8080/graphql" --schema="shared/src/commonMain/graphql/schema.json"```
