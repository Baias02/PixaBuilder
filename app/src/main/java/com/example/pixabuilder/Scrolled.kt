package com.example.pixabuilder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class Scrolled(private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    private var loading = true
    private var previousTotalItemCount = 0
    private val visibleThreshold = 5

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val totalItemCount = layoutManager.itemCount
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (!loading && totalItemCount - lastVisibleItemPosition <= visibleThreshold) {
            onLoadMore()
            loading = true
        }
    }

    fun resetState() {
        previousTotalItemCount = 0
        loading = true
    }

    abstract fun onLoadMore()
}
