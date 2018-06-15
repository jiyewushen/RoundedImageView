# RoundedImageView
With rounded corners ImageView，带圆角的ImageView

### User Gudie
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
| --- | --- |
|     |   |
* use code to config.
