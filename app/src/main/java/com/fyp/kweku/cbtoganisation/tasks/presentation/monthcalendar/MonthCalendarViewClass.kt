package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.FragmentMonthCalendarBinding

class MonthCalendarViewClass(val inflater: LayoutInflater, val parent: ViewGroup?):MonthCalendarViewClassInterface{

    private val monthCalendarViewClassBinding: FragmentMonthCalendarBinding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_month_calendar, parent, false)
    private val rootView: View = monthCalendarViewClassBinding.root


    override fun getRootView(): View {
        return rootView
    }

}