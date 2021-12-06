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