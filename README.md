# RoundedImageView[ ![Download](https://api.bintray.com/packages/jiyewushen/maven/roundedimageview-release/images/download.svg) ](https://bintray.com/jiyewushen/maven/roundedimageview-release/_latestVersion)
With rounded corners ImageView，带圆角的ImageView

### User Gudie
* gradle
  ```java
  compile 'com.cx:roundedimageview-release:1.0.0'
  ```
* use XML to config. like background,you can use color or image.

```java
//方式一：
<com.cx.uilib.RoundedImageView
       android:id="@+id/image"
       android:layout_width="200dp"
       android:layout_height="200dp"
       android:background="#ff3478"
       app:background_radius="20dp"
       app:src_radius="30dp"
       android:src="@drawable/nature"
       />

//方式二：
<com.cx.uilib.RoundedImageView
        android:id="@+id/image"
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:background="@drawable/nature"
        app:background_radius="20dp"
        app:src_radius="30dp"
        android:src="@drawable/city"
        />
```
| 方式一 |方式二|
| :---: | :---: |
| ![](https://github.com/jiyewushen/RoundedImageView/blob/master/test1.png)|![](https://github.com/jiyewushen/RoundedImageView/blob/master/test2.png)|
* use code to config.
