# 如何使用
Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```java
    allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
Step 2. Add the dependency
```java
	dependencies {
	        compile 'com.github.mapsh:mvp:1.0.0'
	}
```
# 说明
##  如出现如下build错误
![](doc/build_error.png)
```java
android {

    configurations.all {
        resolutionStrategy.force 'com.google.code.findbugs:jsr305:3.0.1'
    }
}
```