# e`지 플래너

강남IT학원 자바 풀스택 과정 박진우 개인 프로젝트 - 일정 관리 서비스


**서비스 호스팅 페이지 : https://parkjinwoo.com/easy_planner**

![Preview](https://github.com/pjw1112/easyplanner/tree/main/src/images/mobile6)

## 특징

- [x] 웹 - 모바일 반응형 디자인
- [x] 캘린더 구현 - 자바스크립트 Date() 함수
- [x] 일정 관리 view - 이벤트 버블링 / 이벤트 캡쳐링 / event.stopPropagation 사용
- [x] MVC2 디자인
- [x] Custom date view/composable - Make your day cells look however you want, with any
  functionality you want.
- [x] Custom calendar view/composable - Make your calendar look however you want, with whatever
  functionality you want.
- [x] Custom first day of the week - Use any day as the first day of the week.
- [x] Horizontal or vertical scrolling calendar.
- [x] HeatMap calendar - Suitable for showing how data changes over time, like GitHub's contribution
  chart.
- [x] Month/Week headers and footers - Add headers/footers of any kind on each month/week.
- [x] Easily scroll to any date/week/month on the calendar via swipe actions or programmatically.
- [x] Use all RecyclerView/LazyRow/LazyColumn customizations since the calendar extends from
  RecyclerView for the view system and uses LazyRow/LazyColumn for compose.
- [x] Design your calendar [however you want.](https://github.com/kizitonwose/Calendar/issues/1) The
  library provides the logic, you provide the views/composables.

## Sample project

It's important to check out the sample app. There are lots of examples provided for both view and compose implementations. 
Most techniques that you would want to implement are already done in the examples.

Download the sample app [here](https://github.com/kizitonwose/Calendar/releases/download/2.0.0/sample.apk)

View the sample app's source code [here](https://github.com/kizitonwose/Calendar/tree/main/sample)

## Setup

The library uses `java.time` classes via [Java 8+ API desugaring](https://developer.android.com/studio/write/java8-support#library-desugaring) for backward compatibility since these classes were added in Java 8.

#### Step 1

This step is required ONLY if your app's `minSdkVersion` is below 26. Jump to [step 2](#step-2) if this does not apply to you.

To set up your project for desugaring, you need to first ensure that you are using [Android Gradle plugin](https://developer.android.com/studio/releases/gradle-plugin#updating-plugin) 4.0.0 or higher.

Then include the following in your app's build.gradle file:

```groovy
android {
  defaultConfig {
    // Required ONLY if your minSdkVersion is below 21
    multiDexEnabled true
  }

  compileOptions {
    // Enable support for the new language APIs
    coreLibraryDesugaringEnabled true
    // Set Java compatibility (version can be higher if desired)
    sourceCompatibility JavaVersion.VERSION_1_8
    targetCompatibility JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    // Also add this for Kotlin projects (version can be higher if desired)
    jvmTarget = "1.8"
  }
}

dependencies {
  coreLibraryDesugaring 'com.android.tools:desugar_jdk_libs:<latest-version>'
}
```

You can find the latest version of `desugar_jdk_libs` [here](https://mvnrepository.com/artifact/com.android.tools/desugar_jdk_libs).

#### Step 2

Add the desired calendar library (view or compose) to your app `build.gradle`:

```groovy
dependencies {
  // The view calendar library
  implementation 'com.kizitonwose.calendar:view:<latest-version>'

  // The compose calendar library
  implementation 'com.kizitonwose.calendar:compose:<latest-version>'
}
```

You can find the latest version of the library on the maven central badge above.

Snapshots of the development version are available in [Sonatype’s snapshots repository](https://s01.oss.sonatype.org/content/repositories/snapshots/com/kizitonwose/calendar/).

If you're upgrading from version 1.x.x to 2.x.x, see the [migration guide](https://github.com/kizitonwose/calendar/blob/main/docs/MigrationGuide.md).

For the compose calendar library, ensure that you are using the library version that matches the Compose UI version in your project. If you use a version of the library that has a higher version of Compose UI than the one in your project, gradle will upgrade the Compose UI version in your project via transitive dependency.

| Compose UI | Calendar Library |
|:----------:|:----------------:|
|   1.2.x    |      2.0.x       |
|   1.3.x    |  2.1.x - 2.2.x   |
|   1.4.x    |      2.3.x       |
|   1.5.x    |      2.4.x       |
|   1.6.x    |      2.5.x       |

## Usage

You can find the relevant documentation for the library in the links below.

|[View-based documentation](https://github.com/kizitonwose/Calendar/blob/main/docs/View.md)|[Compose documentation](https://github.com/kizitonwose/Calendar/blob/main/docs/Compose.md)|
|:-:|:-:|

## Share your creations

Made a cool calendar with this library? Share an image [here](https://github.com/kizitonwose/Calendar/issues/1).

## Contributing

Found a bug? feel free to fix it and send a pull request or [open an issue](https://github.com/kizitonwose/Calendar/issues).

## License

Calendar library is distributed under the MIT license.
See [LICENSE](https://github.com/kizitonwose/Calendar/blob/main/LICENSE.md) for details.