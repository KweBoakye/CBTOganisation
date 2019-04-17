package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.FragmentMonthCalendarBinding
import timber.log.Timber

class MonthCalendarViewClass(val inflater: LayoutInflater, val parent: ViewGroup?): MonthCalendarViewClassInterface, MonthCalendarRecyclerAdapter.OnDaySelectedListener {



    val monthCalendarViewClassBinding: FragmentMonthCalendarBinding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_month_calendar, parent, false)
   private val rootView: View = monthCalendarViewClassBinding.root
    private val monthCalendarRecyclerView: RecyclerView = monthCalendarViewClassBinding.MonthCalendarRecyclerView
    private val monthCalendarRecyclerAdapter: MonthCalendarRecyclerAdapter = MonthCalendarRecyclerAdapter(this)
    private val layoutManager = CustomGridLayoutManager(parent!!.context, 7, GridLayoutManager.VERTICAL, false )
    private lateinit var monthCalendarViewClassListener: MonthCalendarViewClassInterface.MonthCalendarViewClassListener

    init {

        monthCalendarRecyclerView.layoutManager = layoutManager
        monthCalendarRecyclerView.hasFixedSize()
        monthCalendarRecyclerView.adapter = monthCalendarRecyclerAdapter

    }



    override fun getRootView(): View {
        return rootView
    }




    override fun initMonthCalender() {
        monthCalendarRecyclerView.smoothScrollToPosition(monthCalendarViewClassListener.SmoothScrollToPosionParameters())
        monthCalendarRecyclerAdapter.setDays(monthCalendarViewClassListener.generateDates())


    }

    override fun setListener(monthCalendarViewClassListener: MonthCalendarViewClassInterface.MonthCalendarViewClassListener) {
        this.monthCalendarViewClassListener = monthCalendarViewClassListener
    }




    //Methods From Implemented Listener Interfaces
    override fun onDaySelected(view: View, date: String, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}