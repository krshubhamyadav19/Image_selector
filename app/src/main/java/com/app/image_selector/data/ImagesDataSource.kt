package com.app.image_selector.data

import android.content.ContentResolver
import android.database.Cursor
import android.provider.MediaStore
import com.app.image_selector.data.model.AlbumItem
import com.app.image_selector.data.model.ImageItem
import com.app.image_selector.data.model.ImageSource
import com.app.image_selector.utills.*
import com.app.image_selector.utills.DISPLAY_NAME_COLUMN
import com.app.image_selector.utills.ORDER_BY
import com.app.image_selector.utills.cursorUri
import com.app.image_selector.utills.doWhile

/**
 * @author Kumar Shubham
 * 20/1/20
 */
internal class ImagesDataSource(private val contentResolver: ContentResolver){

    fun loadAlbums(): ArrayList<AlbumItem> {
        val albumCursor = contentResolver.query(
            cursorUri,
            arrayOf(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME),
            null,
            null,
            ORDER_BY
        )
        val list = arrayListOf<AlbumItem>()
        try {
            list.add(AlbumItem("All", true))
            if (albumCursor == null) {
                return list
            }
            albumCursor.doWhile {
                val albumItem = AlbumItem(
                    albumCursor.getString(albumCursor.getColumnIndex(DISPLAY_NAME_COLUMN)),
                    false
                )
                if (!list.contains(albumItem)) {
                    list.add(albumItem)
                }
            }
        } finally {
            if (albumCursor != null && !albumCursor.isClosed) {
                albumCursor.close()
            }
        }
        return list
    }

    fun loadAlbumImages(
        albumItem: AlbumItem?,
        page: Int
    ): ArrayList<ImageItem> {
        val offset = page * PAGE_SIZE
        val list: ArrayList<ImageItem> = arrayListOf()
        var photoCursor: Cursor? = null
        try {
            if (albumItem == null || albumItem.isAll) {
                photoCursor = contentResolver.query(
                    cursorUri,
                    arrayOf(
                        ID_COLUMN,
                        PATH_COLUMN
                    ),
                    null,
                    null,
                    "$ORDER_BY LIMIT $PAGE_SIZE OFFSET $offset"
                )
            } else {
                photoCursor = contentResolver.query(
                    cursorUri,
                    arrayOf(
                        ID_COLUMN,
                        PATH_COLUMN
                    ),
                    "${MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME} =?",
                    arrayOf(albumItem.name),
                    "$ORDER_BY LIMIT $PAGE_SIZE OFFSET $offset"
                )
            }
            photoCursor?.isAfterLast ?: return list
            photoCursor.doWhile {
                val image = photoCursor.getString((photoCursor.getColumnIndex(PATH_COLUMN)))
                list.add(ImageItem(image, ImageSource.GALLERY, 0))
            }
        } finally {
            if (photoCursor != null && !photoCursor.isClosed()) {
                photoCursor.close()
            }
        }
        return list
    }
}