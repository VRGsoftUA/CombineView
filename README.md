#### [HIRE US](http://vrgsoft.net/)

# CombineView
<img src="https://github.com/VRGsoftUA/CombineView/blob/master/Sample.jpg" width="270" height="480" />

# Usage

*For a working implementation, Have a look at the Sample Project - sample*

1. Include the library as local library project.
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
   implementation 'com.github.VRGsoftUA:CombineView:0.1'
}
```
2. Add view to layout:
```
<com.vrg.combineView.CombineView
        android:id="@+id/image"
        app:cvType="two"
        app:cvCorners="16dp"/>
```
3. Load image to view
```
  //with Glide
        Glide.with(this)
                .load(R.drawable.elephant)
                .intoTop(image)

        Glide.with(this)
                .load(R.drawable.racoon)
                .intoBottom(image)
                
  //or without Glide
        image.setTopImage(ContextCompat.getDrawable(context, R.drawable.elephant))
	image.setBottomImage(ContextCompat.getDrawable(context, R.drawable.racoon))
```

# Customization
| Method  | Description |
| ------------- | ------------- |
| setCorners(float corners)  | Sets current view corners  |
| setType(Type type)   | Sets current view type(two or four sub view)  |
#### Contributing
* Contributions are always welcome
* If you want a feature and can code, feel free to fork and add the change yourself and make a pull request
