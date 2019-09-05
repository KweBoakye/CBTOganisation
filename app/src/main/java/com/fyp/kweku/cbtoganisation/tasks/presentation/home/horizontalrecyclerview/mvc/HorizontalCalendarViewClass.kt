package com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.*
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.HorizontalCalendarAdapter
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.HorizontalCalendarLayoutManager
import timber.log.Timber


import com.fyp.kweku.cbtoganisation.tasks.presentation.utils.snaponscrolllistener.SnapOnScrollListener
import com.fyp.kweku.cbtoganisation.tasks.presentation.utils.snaponscrolllistener.attachSnapHelperWithListener
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext


class HorizontalCalendarViewClass(context: Context, val parent: ViewGroup?,view: View):HorizontalCalendarViewClassInterface,
    HorizontalCalendarAdapter.OnEndReachedListener, HorizontalCalendarAdapter.OnDaySelectedListener , SnapOnScrollListener.OnSnapPositionChangeListener {




    override fun onSnapPositionChange(position: Int) {
        if (calendarRecycler.scrollState == RecyclerView.SCROLL_STATE_IDLE ) {
            calendarAdapter.onScrollStopped(position)


        }
    }



    //private var binding: CalendarRecyclerBinding = CalendarRecyclerBinding.inflate(LayoutInflater.from(context), parent,false)
    private var calendarRecycler: RecyclerView = view.calender_recycler
    private var layoutManager : HorizontalCalendarLayoutManager =
        HorizontalCalendarLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    private lateinit var horizontalCalendarViewClassListener: HorizontalCalendarViewClassInterface.HorizontalCalendarViewClassListener


    private var calendarAdapter: HorizontalCalendarAdapter = HorizontalCalendarAdapter(context, this, this)
    private var snapHelper: SnapHelper = LinearSnapHelper()



    override fun setListener(horizontalCalendarViewClassListener: HorizontalCalendarViewClassInterface.HorizontalCalendarViewClassListener) {
        this.horizontalCalendarViewClassListener = horizontalCalendarViewClassListener
        Timber.i("horizontalCalendarViewClassListener set")
    }


     private fun scrollToCenter(view: View, layoutManager: LinearLayoutManager) {
        val itemToScroll: Int = calendarRecycler.getChildLayoutPosition(view)
        val centerOfScreen: Int = (calendarRecycler.width / 2) - (view.width / 2)
        layoutManager.scrollToPositionWithOffset(itemToScroll, centerOfScreen)

    }

    override fun getCalendarRecycler():RecyclerView{
        return calendarRecycler
    }






    override fun initHorizontalCalendar(){

        // calendarRecycler.layoutManager = layoutManager

      //  snapHelper.attachToRecyclerView(calendarRecycler)
        calendarRecycler.layoutManager = layoutManager
        calendarAdapter.setData(horizontalCalendarViewClassListener.CalenderAdapterSetDataParameters())
        calendarRecycler.adapter = calendarAdapter

        calendarRecycler.attachSnapHelperWithListener(snapHelper,SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE, this)
        //scrollToStart()








        // calendarRecycler.setAdapter(calendarAdapter)




    }

    //Adapter Listener Methods


    private fun sendDateToListener(date: String){
        horizontalCalendarViewClassListener.onDaySelected(date)
    }

    override fun onDaySelected(view: View, date: String, position: Int) {
        //scrollToCenter(view, layoutManager)
        calendarRecycler.smoothScrollToPosition(position)
        sendDateToListener(date)


    }

    override fun onDayScrolled(date: String) {
        sendDateToListener(date)
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