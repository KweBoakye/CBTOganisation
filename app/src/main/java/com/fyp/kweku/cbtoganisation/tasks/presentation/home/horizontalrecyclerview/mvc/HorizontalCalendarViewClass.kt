package com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.HorizontalCalendarAdapter
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.HorizontalCalendarLayoutManager
import timber.log.Timber
import com.fyp.kweku.cbtoganisation.databinding.CalendarRecyclerBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.utils.snaponscrolllistener.SnapOnScrollListener
import com.fyp.kweku.cbtoganisation.tasks.presentation.utils.snaponscrolllistener.attachSnapHelperWithListener
import kotlinx.android.synthetic.main.fragment_home.view.*


class HorizontalCalendarViewClass(context: Context, val parent: ViewGroup?,view: View):HorizontalCalendarViewClassInterface,
    HorizontalCalendarAdapter.OnEndReachedListener, HorizontalCalendarAdapter.OnDaySelectedListener , SnapOnScrollListener.OnSnapPositionChangeListener {


    override fun onSnapPositionChange(position: Int) {
        if (calendarRecycler.scrollState == RecyclerView.SCROLL_STATE_IDLE ) {
            calendarAdapter.onScrollStopped(position)


        }
    }


    private var binding: CalendarRecyclerBinding = CalendarRecyclerBinding.inflate(LayoutInflater.from(context), parent,false)
    private var calendarRecycler: RecyclerView
        private var layoutManager : HorizontalCalendarLayoutManager
    private lateinit var horizontalCalendarViewClassListener: HorizontalCalendarViewClassInterface.HorizontalCalendarViewClassListener


    private var calendarAdapter: HorizontalCalendarAdapter
    private var snapHelper: SnapHelper = LinearSnapHelper()


    init {
        calendarRecycler = view.calender_recycler
        layoutManager = HorizontalCalendarLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        calendarAdapter= HorizontalCalendarAdapter(context, this, this)
        calendarRecycler.layoutManager = layoutManager   /*.also { it.onItemSelectedListener = object: HorizontalCalendarLayoutManager.OnItemSelectedListener {
            override fun onItemSelected(layoutPosition: Int) {
                calendarAdapter.onScrollStopped(layoutPosition)
            }
        } }*/



        calendarRecycler.adapter = calendarAdapter



    }




    override fun setListener(horizontalCalendarViewClassListener: HorizontalCalendarViewClassInterface.HorizontalCalendarViewClassListener) {
        this.horizontalCalendarViewClassListener = horizontalCalendarViewClassListener
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

      //  snapHelper.attachToRecyclerView(calendarRecycler)
calendarRecycler.attachSnapHelperWithListener(snapHelper,SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE, this)

        calendarRecycler.smoothScrollToPosition(horizontalCalendarViewClassListener.SmoothScrollToPositionParameters())

        // calendarRecycler.setAdapter(calendarAdapter)

        calendarAdapter.setData(horizontalCalendarViewClassListener.CalenderAdapterSetDataParameters())


    }

    //Adapter Listener Methods


    fun sendDateToListener(date: String){
        horizontalCalendarViewClassListener.onDaySelected(date)
    }

    override fun onDaySelected(view: View, date: String, position: Int) {
        scrollToCenter(view, layoutManager)
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