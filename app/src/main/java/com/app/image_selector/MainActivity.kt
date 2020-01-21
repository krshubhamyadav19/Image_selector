package com.app.image_selector

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_picker.*

class MainActivity : AppCompatActivity() {

    val PICKER_REQUEST_CODE = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        menu_chat.setOnClickListener {
            openDialog()
        }
    }

    fun openDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_picker)
        dialog.show()

        dialog.iv_camera_dialogpicker.setOnClickListener {

            /**
             *
             *
             * the given below method ImagePicker() will redirect to image picker class and limit() set
             * the limitation of image slection
             *  disableCamera
             *  if disableCamera is true then in list of image picker their will be no camera option
             *  and if disableCamera is false the you will get an option capture new image
             *  cameraDirect
             *  if cameraDirect is true then its will open camera direct to capture photo
             *  if cameraDirect is false then you will get list of image picker
             *
             *
             *
             */

            ImagePicker().limit(5).disableCamera(true).cameraDirect(true)
                .requestCode(PICKER_REQUEST_CODE)
                .withActivity(this).show()
        }
        dialog.iv_gallery_dialogpicker.setOnClickListener {
            ImagePicker().limit(5).disableCamera(true).cameraDirect(false)
                .requestCode(PICKER_REQUEST_CODE)
                .withActivity(this).show()
            dialog.dismiss()

        }
        dialog.show()
    }


    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }

        when (requestCode) {
            PICKER_REQUEST_CODE -> {
                val imagesList = data?.extras?.getStringArray(ImagePicker.IMAGES_RESULT)
                if (!imagesList.isNullOrEmpty()) {
                    imagesCount.text = "Number of selected Images: ${imagesList.size}"
                }
            }
        }
    }

}