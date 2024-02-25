## e`지 플래너
<br/>
강남IT학원 자바 풀스택 과정 박진우 개인 프로젝트 - 일정 관리 서비스

<br/><br/><br/>
**서비스 호스팅 페이지 : https://parkjinwoo.com/easy_planner**
<br/>
![Group 106](https://github.com/pjw1112/easyplanner/assets/18456659/4aa4612c-f626-4b33-9d63-45101b4163ab)

<br/><br/><br/>
## 특징

- [x] 웹 - 모바일 반응형 디자인
- [x] 캘린더 구현 - 자바스크립트 Date() 함수
- [x] 일정 관리 view - 이벤트 버블링 / 이벤트 캡쳐링 / event.stopPropagation 사용
- [x] 하단 프롬프트에서 대화형으로 일정 조작 가능 - Google Gemini AI API
- [x] MVC2 디자인


<br/><br/><br/>
## 페이지 구조 ( Information Architecture )

<img src="https://github.com/pjw1112/easyplanner/assets/18456659/184537eb-e4ca-4185-a729-3a48aa17b6de.png" width="400" />
<br/><br/><br/>

## CURD

<img src="https://github.com/pjw1112/easyplanner/assets/18456659/b461baec-c65d-48e0-8fbc-89f089db9e9e.png" width="600" />
<br/><br/><br/>

## ERD

<img src="https://github.com/pjw1112/easyplanner/assets/18456659/5116e4b2-36b7-4092-9729-74b5b8ab0225.png" width="600" />
<br/><br/><br/>

## 대화형 입력으로 일정 추가/ 수정/ 삭제

<img src="https://github.com/pjw1112/easyplanner/assets/18456659/517101b0-09be-441a-a5dd-73fd40110242" width="250" />
<img src="https://github.com/pjw1112/easyplanner/assets/18456659/d88f60f4-c615-4331-aa5d-e50168515fad" width="250" />
<img src="https://github.com/pjw1112/easyplanner/assets/18456659/a3d46a8d-fae8-4958-b1be-ef6ddd482eec" width="250" />
<br/><br/><br/>


![GOMCAM 20240223_0019040403 (3)](https://github.com/pjw1112/easyplanner/assets/18456659/d2b18b8e-f591-4e45-9aee-2a83028b945a)
![GOMCAM 20240223_0019040403 (2)](https://github.com/pjw1112/easyplanner/assets/18456659/9cbb7b4e-3935-4fc3-beeb-a1a184e24769)
![GOMCAM 20240223_0019040403 (1)](https://github.com/pjw1112/easyplanner/assets/18456659/70ea1484-8c4b-49f2-b7a1-99e58814a6c4)
![GOMCAM 20240223_0019040403](https://github.com/pjw1112/easyplanner/assets/18456659/afa793db-101e-4168-b5dd-9ae028b08e26)
![GOMCAM 20240223_2335210295 (5)](https://github.com/pjw1112/easyplanner/assets/18456659/1f971088-a37f-480f-84f8-9372298d161d)
![GOMCAM 20240223_2335210295 (4)](https://github.com/pjw1112/easyplanner/assets/18456659/8a676fff-9bdf-4952-8acb-e835d640425a)
![GOMCAM 20240223_2335210295 (3)](https://github.com/pjw1112/easyplanner/assets/18456659/9c7a870e-d27f-42f0-b7fb-e27e4e43b074)
![GOMCAM 20240223_2335210295 (2)](https://github.com/pjw1112/easyplanner/assets/18456659/21b9f6bb-7e48-4aa0-a08a-c35f8f6cacc7)
![GOMCAM 20240223_2335210295 (1)](https://github.com/pjw1112/easyplanner/assets/18456659/d7b54df3-1134-41e0-a719-987ee0d9fc8c)
![GOMCAM 20240223_2335210295](https://github.com/pjw1112/easyplanner/assets/18456659/6fac7c9b-df02-4fcf-84ac-fcbe93f68668)

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
