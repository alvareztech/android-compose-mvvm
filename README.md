# Employee Directory App

By Daniel Alvarez, [alvarez.tech](https://alvarez.tech).

## Build tools & versions used

Android SDK Build Tools version 33

Other dependencies:

- Jetpack Compose 1.2.1
- Google Accompanist 0.25.1
- Coil 2.2.2
- Retrofit 2.9.0
- moshi Kotlin 1.14.0
- OkHttp 4.10.0

## Steps to run the app

```
gradlew installDebug
```

Or just open and run with _Android Studio_.

## What areas of the app did you focus on?

- UI with Jetpack Compose
- UI Navigation
- MVVM, Networking

## What was the reason for your focus? What problems were you trying to solve?

It was a good opportunity to test the new versions of the Jetpack libraries, among other experimental and/or recent libraries.

## How long did you spend on this project?

Around 5 hours.

## Did you make any trade-offs for this project? What would you have done differently with more time?

Although it is a small application, it would have improved the dependency of the components, implementing some `DataSource`, `Repository` classes, it would have used [Hilt](https://dagger.dev/hilt/) for dependency injection to facilitate the testing part as well.

## What do you think is the weakest part of your project?

The testing code coverage is not good, with a little more time it would have handled dependencies better, mock data to test the complete flow and navigation.

## Did you copy any code or dependencies? Please make sure to attribute them here!

Inspired by some examples of [Google Accompanist](https://github.com/google/accompanist) (a collection of extension libraries for Jetpack Compose).

## Is there any other information you’d like us to know?

I tried to use new and experiment with new versions of the libraries. I really like this. The existence of Square's open source projects was essential to this project and to Android development in general. Without it Android development would have been even more tedious.
