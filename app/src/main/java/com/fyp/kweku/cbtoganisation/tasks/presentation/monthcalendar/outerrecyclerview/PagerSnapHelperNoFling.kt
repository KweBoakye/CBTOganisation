package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.outerrecyclerview

import androidx.recyclerview.widget.PagerSnapHelper

class PagerSnapHelperNoFling:PagerSnapHelper() {

    override fun onFling(velocityX: Int, velocityY: Int): Boolean {
        return false
    }
}