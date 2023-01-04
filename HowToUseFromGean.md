# Create sub-project "gean-appodeal"

npm install /Users/geansantos/git/00_Angular/gean-appodeal

File Created by Gean. Read carefully

```
in android/capacitor.settings.gradle
include ':gean-appodeal'
project(':gean-appodeal').projectDir = new File('../node_modules/gean-appodeal/android')
```

# Add sub-project gean-appodeal as an app project dependency

```
android/app/build.gradle
dependencies{
...
implementation project(':gean-appodeal')
...
}
```

ln -s ../../../gean-appodeal node_modules/gean-appodeal

# Update gradle version

```
android/gradle/wrapper/gradle-wrapper.properties
distributionUrl=https\://services.gradle.org/distributions/gradle-7.0-all.zip
```

# Update @capacitor/android at least to 3.3.2 (only tested version)

npm uninstall @capacitor/android
npm install @capacitor/android@3.3.2

# Use correct version of Capacitor

```
"dependencies": {
    "@capacitor/android": "^3.3.2",
    "@capacitor/app": "1.0.6",
    "@capacitor/core": "3.3.2",
```
