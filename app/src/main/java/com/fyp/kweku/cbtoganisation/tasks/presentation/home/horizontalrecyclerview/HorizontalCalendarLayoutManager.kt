package com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider

class HorizontalCalendarLayoutManager: LinearLayoutManager, ScrollVectorProvider{

    constructor(context: Context?) : super(context)

    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout)

    private val mShrinkAmount = 0.7f
    private val mShrinkDistance = 1.25f
    //lateinit var onItemSelectedListener: OnItemSelectedListener
    //private lateinit var recyclerView: RecyclerView


    override fun onAttachedToWindow(view: RecyclerView?) {
        super.onAttachedToWindow(view)
      //  recyclerView = view!!

    }

    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State?, position: Int) {
        val centerSmoothScroller = CenterSmoothScroller(recyclerView.context)
        centerSmoothScroller.targetPosition = position
        startSmoothScroll(centerSmoothScroller)
    }

    class CenterSmoothScroller(context: Context) : LinearSmoothScroller(context) {

        override fun calculateDtToFit(
            viewStart: Int,
            viewEnd: Int,
            boxStart: Int,
            boxEnd: Int,
            snapPreference: Int
        ): Int {
            return (((boxEnd + boxStart) / 2) - ( (viewEnd + viewStart) / 2))
        }


    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val orientation = orientation
        if (orientation == VERTICAL) {
            val scrolled = super.scrollVerticallyBy(dy, recycler, state)
            val midpoint = height / 2f
            val d0 = 0f
            val d1 = mShrinkDistance * midpoint
            val s0 = 1f
            val s1 = 1f - mShrinkAmount
            for (i in 0 until childCount) {
                val child = getChildAt(i)
                val childMidpoint = (getDecoratedBottom(child!!) + getDecoratedTop(child)) / 2f
                val d = Math.min(d1, Math.abs(midpoint - childMidpoint))
                val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                child.scaleX = scale
                child.scaleY = scale
            }
            return scrolled
        } else {
            return 0
        }
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val orientation = orientation
        if (orientation == HORIZONTAL) {
            val scrolled = super.scrollHorizontallyBy(dx, recycler, state)

            val midpoint = width / 2f
            val d0 = 0f
            val d1 = mShrinkDistance * midpoint
            val s0 = 1f
            val s1 = 1f - mShrinkAmount
            for (i in 0 until childCount) {
                val child = getChildAt(i)
                val childMidpoint = (getDecoratedRight(child!!) + getDecoratedLeft(child)) / 2f
                val d = Math.min(d1, Math.abs(midpoint - childMidpoint))
                val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
             if (!scale.isNaN()) {
                 child.scaleX = scale
                child.scaleY = scale
             }
            }
            return scrolled
        } else {
            return 0
        }
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        super.onLayoutChildren(recycler, state)
        scrollHorizontallyBy(0, recycler, state)
    }

    /*override fun onScrollStateChanged(state: Int) {


        if (state == RecyclerView.SCROLL_STATE_IDLE) {
             findItemToSelect()

            // Find the closest child to the recyclerView center --> this is the selected item.
            val recyclerViewCenterX = getRecyclerViewCenterX()

            var minDistance = recyclerView.height
            var position = -1
            for (i in 0 until recyclerView.childCount) {
                val child = recyclerView.getChildAt(i)
                val centerOfScreen: Int = (recyclerView.width / 2) - (child.width / 2)
                val childCenterX = getDecoratedLeft(child) + (getDecoratedRight(child) - getDecoratedLeft(child)) / 2
                val childDistanceFromCenter = Math.abs(childCenterX - centerOfScreen)
                if (childDistanceFromCenter < minDistance) {
                    minDistance = childDistanceFromCenter
                    position = recyclerView.getChildLayoutPosition(child)
                    recyclerView.stopScroll()
                }

            }


            // Notify on the selected item
            onItemSelectedListener.onItemSelected(position)

        }
    }

    fun findItemToSelect() {
        val recyclerViewCenterX = getRecyclerViewCenterX()

        var minDistance = recyclerView.width
        var position = -1
        for (i in 0 until recyclerView.childCount) {
            val child = recyclerView.getChildAt(i)
            val centerOfScreen: Int = (recyclerView.width / 2) - (child.width / 2)
            val childCenterX = getDecoratedLeft(child) + (getDecoratedRight(child) - getDecoratedLeft(child)) / 2
            val childDistanceFromCenter = Math.abs(childCenterX - getRecyclerViewCenterX())
            if (childDistanceFromCenter < minDistance) {
                minDistance = childDistanceFromCenter

                position = recyclerView.getChildLayoutPosition(child)
                //recyclerView.stopScroll()
            }
        }
        onItemSelectedListener.onItemSelected(position)

    }*/

   /* private fun getRecyclerViewCenterX() : Int {

        return ((recyclerView.right/2) - (recyclerView.left)) + recyclerView.left
    }


    interface OnItemSelectedListener {
        fun onItemSelected(layoutPosition: Int)
    }*/


}