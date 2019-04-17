package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.round

class CustomGridLayoutManager:GridLayoutManager {

    constructor(context: Context, spanCount: Int):super(context,spanCount)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int):super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context, spanCount: Int, orientation:Int, reverseLayout: Boolean): super(context,spanCount,orientation, reverseLayout)



    override fun canScrollVertically(): Boolean {
        return false
    }
   override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
       super.onLayoutChildren(recycler, state)
       scrollVerticallyBy(0, recycler, state)
   }

    private fun getHorizontalSpace():Int{
        return width -paddingRight - paddingLeft
    }


    private fun getVerticalSpace():Int{
        return height - paddingBottom - paddingTop   }

    private fun spanLayoutSize(layoutParams: RecyclerView.LayoutParams):RecyclerView.LayoutParams{
        if (orientation == HORIZONTAL){
            layoutParams.width = (round(getHorizontalSpace().toDouble())/ spanCount).toInt()

        }

        else if(orientation == VERTICAL){
            layoutParams.height =  (round(getVerticalSpace().toDouble())/ spanCount).toInt()
        }

        return layoutParams
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return spanLayoutSize(super.generateDefaultLayoutParams())
    }

    override fun generateLayoutParams(c: Context?, attrs: AttributeSet?): RecyclerView.LayoutParams {
        return spanLayoutSize(super.generateLayoutParams(c, attrs))
    }

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams?): RecyclerView.LayoutParams {
        return spanLayoutSize(super.generateLayoutParams(lp))
    }

    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
        return super.checkLayoutParams(lp)
    }




}