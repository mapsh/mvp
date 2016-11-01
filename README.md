##使用说明
# 1 如出现如下build错误
![](doc/build_error.png)
```Java
android {

    configurations.all {
        resolutionStrategy.force 'com.google.code.findbugs:jsr305:3.0.1'
    }
}
```