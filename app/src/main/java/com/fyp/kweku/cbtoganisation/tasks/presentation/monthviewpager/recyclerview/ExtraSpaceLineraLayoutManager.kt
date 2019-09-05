package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.recyclerview

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.Display
import android.view.WindowManager
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExtraSpaceLineraLayoutManager: LinearLayoutManager {



    constructor( context: Context): super(context)

    constructor(context: Context, @RecyclerView.Orientation  orientation:Int, reverseLayout: Boolean): super(context, orientation,reverseLayout)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int):super(context, attrs, defStyleAttr, defStyleRes)




    override fun calculateExtraLayoutSpace(state: RecyclerView.State, extraLayoutSpace: IntArray) {
        if(state.remainingScrollHorizontal == 0){
        extraLayoutSpace[0] = width
        extraLayoutSpace[1] = width}
    }
}