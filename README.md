# Android_image_selector
##### Image selector is an Easy, lightweight and high performance image picker library for Android!
##### Image selector load images using Android content resolver with the help of coroutines to lazy load the images and improve the performance!
##### Image selector handle permission requests, support camera capture, and limit for the max number of images to pick. 

#### Simpler than ever!

To open the Picker: 

### Kotlin
```kotlin
ImagePicker().requestCode(PICKER_REQUEST_CODE).withActivity(this).show()
```

To get the result override onActivityResult method: 

### Kotlin
```kotlin
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }

        when (requestCode) {
            PICKER_REQUEST_CODE -> {
                val imagesList = data?.extras?.getStringArray(ImagePicker.IMAGES_RESULT)// return list of selected images paths.
                if (!imagesList.isNullOrEmpty()) {
                    imagesCount.text = "Number of selected Images: ${imagesList.size}"
                }
            }
        }
    }
```



| Method | usage |
| ------ | ------ |
| ``` withActivity(activity: Activity) ``` | used to set the activity will recive the result  |
| ``` withFragment(fragment: Fragment) ``` | used to set the fragment will recive the result  |
| ``` requestCode(requestCode: Int) ``` | used to set the request code for the result  |
| ``` limit(limit: Int) ``` | used to set the max number of images to select  |
| ``` disableCamera(disableCamera: Boolean) ``` | by default the value of disableCamera is false, it determine to allow camera captures or not  |
| ``` cameraDirect(cameraDirect: Boolean) ``` | by default the value of cameraDirect is false, it determine to open the camera before showing the picker or not |


## UI customization

### Customizable Colors
override any color to change inside your project colors.xml

|		Property		|		Description		|
|:----------------------|:---------------------:|
|		`counter_bg`			|	selection counter background color			|
|		`counter_color`		|	selection counter text color	|
|		`selector_color`		|	selection tint color	|
|		`place_holder_color`		|	empty image holder color	|
|		`ripple_color`		|	selection ripple color	|
|		`toolbar_bg`		|	toolbar color	|
|		`done`		|	Done button color	|
|		`change_album_color`		|	change album text color	|
|		`camera_icon_color`		|	camera icon color	|
|		`camera_text_color`		|	camera text color	|
|		`permission_alert_bg`		|	permission alert background color	|
|		`permission_alert_color`		|	permission alert text color	|



### Customizable Texts
override any string to change inside your project strings.xml

|		Property		|		Description		|
|:----------------------|:---------------------:|
|		`over_limit_msg`			|	selection limit reached message			|
|		`capture_new_image`		|	capture image text	|
|		`change_album_text`		|	change album text	|
|		`permission_storage_never_ask`		|	permission denied message 	|
|		`all_images`		|	all images album name	|


