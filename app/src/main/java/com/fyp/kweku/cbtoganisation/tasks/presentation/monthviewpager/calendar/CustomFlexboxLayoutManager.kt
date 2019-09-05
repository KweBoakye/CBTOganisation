package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager

class CustomFlexboxLayoutManager:FlexboxLayoutManager {

    constructor(context: Context): super(context)

    constructor(context: Context,@FlexDirection  flexDirection:Int ): super(context, flexDirection)

    constructor(context: Context, @FlexDirection flexDirection:Int, @FlexWrap flexWrap: Int): super(context, flexDirection, flexWrap)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes)

    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun canScrollHorizontally(): Boolean {
        return false
    }


    override fun collectAdjacentPrefetchPositions(
        dx: Int,
        dy: Int,
        state: RecyclerView.State?,
        layoutPrefetchRegistry: LayoutPrefetchRegistry?
    ) {
        super.collectAdjacentPrefetchPositions(dx, dy, state, layoutPrefetchRegistry)
    }

    override fun collectInitialPrefetchPositions(
        adapterItemCount: Int,
        layoutPrefetchRegistry: LayoutPrefetchRegistry?
    ) {
        super.collectInitialPrefetchPositions(adapterItemCount, layoutPrefetchRegistry)
    }




}