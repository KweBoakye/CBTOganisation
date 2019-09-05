package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.fyp.kweku.cbtoganisation.databinding.FragmentMonthViewAsRecyclerViewBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarViewClassForViewPagerInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.YearMonth

class MonthViewAsRecyclerViewClass(val inflater: LayoutInflater, val parent: ViewGroup?):
    MonthViewAsRecyclerViewClassInterface {


    private val fragmentMonthViewAsRecyclerViewBinding: FragmentMonthViewAsRecyclerViewBinding = FragmentMonthViewAsRecyclerViewBinding.inflate(inflater,parent, false)
    private val root: View = fragmentMonthViewAsRecyclerViewBinding.root
    private val monthFullPageRecyclerview: RecyclerView = fragmentMonthViewAsRecyclerViewBinding.monthFullPageRecyclerview
    private val pagerSnapHelper: PagerSnapHelper = PagerSnapHelper()
    private lateinit var viewPagerRecyclerAdapter: ViewPagerRecyclerAdapter
    private var currentMonth: YearMonth = YearMonth.now()
    private val linearLayoutManager: ExtraSpaceLineraLayoutManager =
        ExtraSpaceLineraLayoutManager(
            parent!!.context,
            HORIZONTAL,
            false
        )
    override fun getRoot(): View = this.root

    override fun setTasks(tasks: List<TaskPresentationModel>) {
       viewPagerRecyclerAdapter.setTasks(tasks)
    }

    fun getThisMonthsPosition(): Int{
       return viewPagerRecyclerAdapter.getMonths().indexOf(currentMonth)
    }

    override fun initAdapter(fragmentListener: MonthCalendarViewClassForViewPagerInterface.MonthCalendarViewClassFragmentListener){
        viewPagerRecyclerAdapter =
            ViewPagerRecyclerAdapter(
                fragmentListener
            )
    }


    override fun initRecyclerview(){
        with(monthFullPageRecyclerview){
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = viewPagerRecyclerAdapter
            pagerSnapHelper.attachToRecyclerView(this)
            setItemViewCacheSize(2)
            scrollToPosition(getThisMonthsPosition())


        }

    }
}