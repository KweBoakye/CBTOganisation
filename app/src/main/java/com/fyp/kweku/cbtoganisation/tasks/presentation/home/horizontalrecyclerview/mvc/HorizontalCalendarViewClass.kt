package com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.HorizontalCalendarAdapter
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.HorizontalCalendarLayoutManager
import timber.log.Timber
import com.fyp.kweku.cbtoganisation.databinding.CalendarRecyclerBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayRecyclerViewClassInterface
import kotlinx.android.synthetic.main.fragment_home.view.*


class HorizontalCalendarViewClass(context: Context, val parent: ViewGroup?,view: View):HorizontalCalendarViewClassInterface,
    HorizontalCalendarAdapter.OnEndReachedListener, HorizontalCalendarAdapter.OnDaySelectedListener  {



 private var binding: CalendarRecyclerBinding = CalendarRecyclerBinding.inflate(LayoutInflater.from(context), parent,false)
    private var calendarRecycler: RecyclerView
        private var layoutManager : HorizontalCalendarLayoutManager
    private lateinit var horizontalCalendarViewClassListener: HorizontalCalendarViewClassInterface.HorizontalCalendarViewClassListener

    private var calendarAdapter: HorizontalCalendarAdapter
    private var snapHelper: SnapHelper = LinearSnapHelper()

    init {
        calendarRecycler = view.calender_recycler
        layoutManager = HorizontalCalendarLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        calendarRecycler.layoutManager = layoutManager

        calendarAdapter= HorizontalCalendarAdapter(context, this, this)
        calendarRecycler.adapter = calendarAdapter


    }



    override fun setListener(HorizontalCalendarViewClassListener: HorizontalCalendarViewClassInterface.HorizontalCalendarViewClassListener) {
        this.horizontalCalendarViewClassListener = HorizontalCalendarViewClassListener
        Timber.i("horizontalCalendarViewClassListener set")
    }


     fun scrollToCenter(view: View, layoutManager: LinearLayoutManager) {
        val itemToScroll: Int = calendarRecycler.getChildLayoutPosition(view)
        val centerOfScreen: Int = (calendarRecycler.width / 2) - (view.width / 2)
        layoutManager.scrollToPositionWithOffset(itemToScroll, centerOfScreen)
    }

    override fun getCalendarRecycler():RecyclerView{
        return calendarRecycler
    }

    override fun initHorizontalCalendar(){

        // calendarRecycler.layoutManager = layoutManager
        snapHelper.attachToRecyclerView(calendarRecycler)

        calendarRecycler.smoothScrollToPosition(horizontalCalendarViewClassListener.SmoothScrollToPosionParameters())

        // calendarRecycler.setAdapter(calendarAdapter)

        calendarAdapter.setData(horizontalCalendarViewClassListener.CalenderAdapterSetDataParameters())


    }

    //Adapter Listener Methods

    override fun onDaySelected(view: View, date: String, position: Int) {
        scrollToCenter(view, layoutManager)
        horizontalCalendarViewClassListener.onDaySelected(date)

    }

    override fun onEndReached() {
        horizontalCalendarViewClassListener.onEndReached()

        calendarAdapter.addItemsAtTop(
            horizontalCalendarViewClassListener.fetchHorizontalCalenderItemMutableListForOnEndReached()
            )
    }

    override fun onStartReached() {
       horizontalCalendarViewClassListener.onStartReached()
        calendarAdapter.addItemsAtBottom(
            horizontalCalendarViewClassListener.horizontalCalenderItemMutableListForOnStartReached()
        )
    }







}