package com.fyp.kweku.cbtoganisation.tasks.presentation.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R

class Divider(val context: Context): RecyclerView.ItemDecoration(){

    private val divider: Drawable = ContextCompat.getDrawable(context, R.drawable.divider)!!

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left: Int = parent.paddingLeft
        val right: Int = parent.paddingRight

        val childCount = parent.childCount

        var i = 0
        while ( i < childCount){
        val child: View = parent.getChildAt(i)

            val recyclerViewParams: RecyclerView.LayoutParams = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + recyclerViewParams.bottomMargin
            val bottom = top + (divider.intrinsicHeight )

            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
            i++
        }
    }

}