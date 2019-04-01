package com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview.mvc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.fyp.kweku.cbtoganisation.databinding.FragmentHomeBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.HomeViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview.HorizontalCalendarAdapter
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview.HorizontalCalendarLayoutManager
import kotlinx.android.synthetic.main.fragment_home.view.*
import timber.log.Timber

class HorizontalCalendarViewClass(val context: Context,val view: View):HorizontalCalendarViewClassInterface,
    HorizontalCalendarAdapter.OnEndReachedListener, HorizontalCalendarAdapter.OnDaySelectedListener  {




    private var calendarRecycler = view.calender_recycler
    private var layoutManager = HorizontalCalendarLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    private lateinit var horizontalCalendarViewClassListener: HorizontalCalendarViewClassInterface.HorizontalCalendarViewClassListener
    private var calendarAdapter: HorizontalCalendarAdapter = HorizontalCalendarAdapter(context, this, this)
    private var snapHelper: SnapHelper = LinearSnapHelper()

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






    override fun initHorizontalCalendar(){

        calendarRecycler.layoutManager = layoutManager
        snapHelper.attachToRecyclerView(calendarRecycler)

      calendarRecycler.smoothScrollToPosition(horizontalCalendarViewClassListener.SmoothScrollToPosionParameters())

        calendarRecycler.setAdapter(calendarAdapter)

        calendarAdapter.setData(horizontalCalendarViewClassListener.CalenderAdapterSetDataParameters())


    }
}