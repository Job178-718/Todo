# 记事本
> MVVM架构的记事本项目11111
## 技术 
+ LiveData 
+ ViewModel 
+ Kotlin 
+ Room 

## 知识点
+ 1.gradle插件
> 方法一 新引入插件的方式
```gradle
    plugins {
      id("org.jetbrains.kotlin.kapt") version "1.8.20-Beta"
    }
```
> 方法二 旧引入插件的方式
```gradle
buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
        dependencies {
            <!--第一步引入依赖：-->
            classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20-Beta")
        }
    }
     <!--第二步：使用apply plugin 导入-->
    apply(plugin = "org.jetbrains.kotlin.kapt")
```
+ 2.JetPack的Navigation
+ 引用Navigation
```xml
    <fragment
        android:id="@+id/fragmentContainerView"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:defaultNavHost="true"
        app:navGraph="@navigation/my_nav" />
```
> app:navGraph="@navigation/my_nav"指向NavigationBar对应的xml文件


