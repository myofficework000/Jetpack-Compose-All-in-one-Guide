# Jetpack Compose All-in-One Guide

[![Kotlin](https://img.shields.io/badge/Kotlin-2.4.20-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Android Gradle Plugin](https://img.shields.io/badge/AGP-9.4.0-3DDC84?logo=android&logoColor=white)](https://developer.android.com/build)
[![Compose](https://img.shields.io/badge/Compose-BOM%202026.09.00-4285F4)](https://developer.android.com/develop/ui/compose/bom)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

![App preview](https://github.com/user-attachments/assets/71e3cf09-0247-4d12-b648-15f266f751b9)

An open-source collection of small, focused Android examples for learning Jetpack Compose—from core layouts and state to architecture, maps, persistence, networking, and complete UI demos. Browse the lessons independently or use the project as a reference while building your own Compose apps.

## What you can explore

| Area | Topics included |
| --- | --- |
| Compose fundamentals | `Column`, `Row`, `Box`, modifiers, state, visibility, themes, tabs, dialogs, lists, flow layouts, animations, and ConstraintLayout |
| Android platform | Activities, services, broadcast receivers, content providers, permissions, CameraX, ML Kit barcode scanning, biometrics, and media playback |
| Architecture | MVP, MVVM, MVI, Clean Architecture, Hilt, ViewModel, LiveData, Room, WorkManager, Paging, DataStore, and testing |
| Integrations | Retrofit, Moshi, Gson, RxJava, Firebase, Google Maps and Places, Stripe, Apollo GraphQL, Coil, Glide, Lottie, and WebView |
| UI demos | Instagram, TikTok, WhatsApp, quotes, weather, currency conversion, news, polls, and more |

## Requirements

- Android Studio with Android SDK Platform 37 installed
- JDK 17
- Gradle 9.6 (provided by the wrapper)
- Android Gradle Plugin 9.4.0

The project uses AGP's built-in Kotlin support, Kotlin 2.4.20 for the Compose compiler plugin, and the Compose BOM `2026.09.00`. The BOM keeps all Compose UI dependencies aligned; individual Compose dependencies intentionally have no version numbers.

## Getting started

1. Clone the repository:

   ```bash
   git clone https://github.com/myofficework000/Jetpack-Compose-All-in-one-Guide.git
   cd Jetpack-Compose-All-in-one-Guide
   ```

2. Open the project in Android Studio and allow the Gradle sync to complete.

3. Optional: create a `local.properties` file for features that need your own credentials. The project still syncs without this file; those features receive empty placeholder values.

   ```properties
   GOOGLE_MAPS_API_KEY=your_google_maps_api_key
   STRIPE_BACKEND_BASEURL=https://your-secure-backend.example
   ```

   Never commit credentials. Restrict any Google Maps key to this app's package name and signing certificate, and keep Stripe secret keys on your backend—not in the Android app.

4. Run the `app` configuration on an emulator or physical device running Android 10 (API 29) or newer.

### Command line

```bash
./gradlew :app:assembleDebug
./gradlew :app:testDebugUnitTest
```

On Windows, use `gradlew.bat` instead. The first build downloads the Gradle distribution and dependencies.

## Project layout

```text
app/src/main/java/com/example/jetpack_compose_all_in_one/
├── lessons/                 # Incremental Compose lessons
├── android_architectures/   # MVP, MVVM, MVI, and Clean Architecture examples
├── jetpack_components/      # Room, WorkManager, Paging, Navigation, and more
├── third_party_lib/         # API, Maps, Stripe, GraphQL, Firebase integrations
├── demos/                   # End-to-end UI and feature demos
└── ui/                      # App entry points, shared views, and theme
```

## Lessons and demos

The in-app menu is the source of truth for runnable examples. Highlights include:

- **Lessons 1–8:** layouts, Material components, lists, dialogs, Maps, theme switching, ConstraintLayout, and animation.
- **Lessons 9–23:** testing, theming, XML interoperability, WebView, localization, menus, state, flow layouts, caching, biometrics, DataStore, Media3, and tabs. Some lessons are works in progress.
- **Architecture and components:** MVP, MVVM, MVI, Clean Architecture, Navigation, Room, WorkManager, Paging 3, and Compose testing.
- **Demos:** Instagram, TikTok, WhatsApp, weather, quotes, currency conversion, news, camera, barcode scanning, and other experiments.

## Contributing

Contributions are welcome. Please open an issue to discuss a substantial change, then submit a focused pull request with a clear description and appropriate tests where practical.

When adding a Compose dependency, prefer the existing BOM unless the library is intentionally versioned independently. Keep credentials and generated files out of commits.

## License

This project is licensed under the [MIT License](LICENSE).

## Author and apps

- [LinkedIn](https://www.linkedin.com/in/myofficework/)
- [Medium](https://medium.com/@myofficework000)
- [Instagram](https://www.instagram.com/kumarpathakabhishek/)
- [Email](mailto:myofficework000@gmail.com)

Apps built with Jetpack Compose:

- [Tetris](https://play.google.com/store/apps/details?id=com.abhishekpathak.tetris)
- [Business](https://play.google.com/store/apps/details?id=abhishek.pathak.business)
- [Money Minder](https://play.google.com/store/apps/details?id=com.business.money_minder)
- [QuickTask](https://play.google.com/store/apps/details?id=com.codegalaxy.quicktask)
