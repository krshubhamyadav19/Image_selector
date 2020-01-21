package com.app.image_selector.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Kumar Shubham
 * 20/1/20
 */
internal class LoadMoreListener(layoutManager: GridLayoutManager) : RecyclerView.OnScrollListener() {

    private var visibleThreshold = 5
    private lateinit var mOnLoadMoreListener: OnLoadMoreListener
    private var isLoading: Boolean = false
    private var isFinish: Boolean = false
    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0
    private var mLayoutManager: GridLayoutManager = layoutManager

    fun setLoaded() {
        isLoading = false
    }

    internal fun setFinished(finished:Boolean= true) {
        isFinish = finished
    }


    internal fun setOnLoadMoreListener(mOnLoadMoreListener: OnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener
    }

    init {
        visibleThreshold *= layoutManager.spanCount
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy <= 0) return

        totalItemCount = mLayoutManager.itemCount
        lastVisibleItem = mLayoutManager.findLastVisibleItemPosition()

        if (!isFinish && !isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
            mOnLoadMoreListener.onLoadMore()
            isLoading = true
        }

    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}