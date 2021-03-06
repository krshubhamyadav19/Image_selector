package com.app.image_selector.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.app.image_selector.R
import com.app.image_selector.data.model.AlbumItem

/**
 * @author Kumar Shubham
 * 20/1/20
 */
internal class AlbumsAdapter(var albumItems: List<AlbumItem>, context: Context) : ArrayAdapter<AlbumItem>(context, android.R.layout.simple_list_item_1, albumItems) {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getDropDownView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        val viewHolder: ViewHolderDrop
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_spinner, parent, false)
            viewHolder = ViewHolderDrop(convertView)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolderDrop
        }
        val item = getItem(position)
        if (item != null) {
            viewHolder.name?.text = item.name
        }
        return convertView!!
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        val viewHolder: ViewHolderView
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_album_spinner, parent, false)
            viewHolder = ViewHolderView(convertView)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolderView
        }
        val item = getItem(position)
        if (item != null) {
            viewHolder.tvLabel?.text = item.name
        }
        return convertView!!
    }


    internal class ViewHolderView(view: View) {
        var tvLabel: TextView? = null

        init {
            tvLabel = view.findViewById(R.id.tvLabel)
        }
    }

    internal class ViewHolderDrop(view: View) {
        var name: TextView? = null

        init {
            name = view.findViewById(R.id.label)
        }
    }
}