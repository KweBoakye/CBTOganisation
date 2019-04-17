package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.get


class MonthCalendarFragment : Fragment() {

    private lateinit var monthCalendarController: MonthCalendarController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        monthCalendarController = get()
        val monthCalendarViewClassInterface: MonthCalendarViewClassInterface = MonthCalendarViewClass(inflater,container)

        monthCalendarController.bindView(monthCalendarViewClassInterface)
        monthCalendarController.let {monthCalendarController ->
            monthCalendarController.bindView(monthCalendarViewClassInterface)
            monthCalendarController.setControllerAsHorizontalCalendarViewClassListener()
            monthCalendarController.initHorizontalCalendar()
        }

        return monthCalendarViewClassInterface.getRootView()
    }


}
