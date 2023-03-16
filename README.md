# 记事本

> MVVM架构的记事本项目

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
> 适用于单个Activity对应多个fragment的app开发
```xml
    <!-- res/layout/activity_main.xml -->
    <fragment
        android:id="@+id/fragmentContainerView"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:defaultNavHost="true"
        app:navGraph="@navigation/my_nav" />
```
> app:navGraph="@navigation/my_nav"指向Navigation对应的xml文件

```xml
<!-- res/navigation/my_nav.xml-->
<fragment
    android:id="@+id/listFragment"
    android:name="com.sun.todo.view.list.ListFragment"
    android:label="记事本"
    tools:layout="@layout/fragment_list" >
    <action
        android:id="@+id/action_listFragment_to_addFragment"
        app:destination="@id/addFragment"
        app:enterAnim="@anim/from_right"
        app:exitAnim="@anim/to_left"
        app:popEnterAnim="@anim/from_left"
        app:popExitAnim="@anim/to_right" />
    <action
        android:id="@+id/action_listFragment_to_updateFragment"
        app:destination="@id/updateFragment"
        app:enterAnim="@anim/from_right"
        app:exitAnim="@anim/to_left"
        app:popEnterAnim="@anim/from_left"
        app:popExitAnim="@anim/to_right" />
</fragment>
```
> **页面跳转**:通过action标签中的id,findNavController().navigate(R.id.action_listFragment_to_updateFragment)

> enterAnim、exitAnim、popEnterAnim、popExitAnim页面移动动画
+ 3.Binding
> app目录下的build.gradle中配置
```gradle
android {
         ...
        buildFeatures {
            viewBinding true
            dataBinding true
        }
    }
```
> 简化findViewById的操作,模板写法
```kotlin
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindind = FragmentAddBinding.inflate(layoutInflater,container,false)
        return bindind?.root
    }
```
> FragmentAddBinding为自动生成的代码，在../build/generated/data_binding_base_class_source_out目录下可以找到