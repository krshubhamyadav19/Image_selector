package com.app.image_selector.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author Kumar Shubham
 * 20/1/20
 */
@Parcelize
internal data class ImageItem(var imagePath: String, var source: ImageSource, var selected: Int) :
    Parcelable
