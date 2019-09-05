package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.fyp.kweku.cbtoganisation.databinding.FragmentMonthViewPagerBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.recyclerview.ViewPagerRecyclerAdapter
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.coroutines.*
import org.threeten.bp.YearMonth

class MonthViewPagerViewClass(val inflater: LayoutInflater, val parent: ViewGroup?, monthViewPagerFragment: MonthViewPagerFragment):
    MonthViewPagerViewClassInterface, ViewPager2.OnPageChangeCallback() {

    private val monthViewPagerBinding: FragmentMonthViewPagerBinding = FragmentMonthViewPagerBinding.inflate(inflater, parent, false)
    private val root = monthViewPagerBinding.root
    private val monthViewPager: ViewPager2 = monthViewPagerBinding.monthViewpager
    private val viewPagerAdapter =
        MonthViewPagerAdapter(
            monthViewPagerFragment
        )
    private val viewPagerRecyclerAdapter: ViewPagerRecyclerAdapter =
        ViewPagerRecyclerAdapter(
            monthViewPagerFragment
        )
    private lateinit var monthViewPagerViewClassListener: MonthViewPagerViewClassInterface.MonthViewPagerViewClassListener
    private lateinit var taskActivity: TaskActivity
    private lateinit var toolbar: Toolbar
    private var currentMonthIndex: Int = 0
    //private val coroutineContext: CoroutineContext
    //get() = Job() + Dispatchers.Main
    private val scope = CoroutineScope(Job() + Dispatchers.Main)
    private var firstLoad : Boolean = true


    override fun setToolbar(taskActivity: TaskActivity){
        setTaskActivity(taskActivity)
        this.toolbar = taskActivity.toolbar
    }

    override fun setTasks(tasks: List<TaskPresentationModel>) {
        viewPagerRecyclerAdapter.setTasks(tasks)
    }


    private fun setTaskActivity(taskActivity: TaskActivity){
        this.taskActivity = taskActivity
    }



   override fun initViewPager(months: List<YearMonth>,monthIndex: Int){
       /* with(viewPagerAdapter){
            setMonths(months)


        }*/

        with(monthViewPager){
            registerOnPageChangeCallback(this@MonthViewPagerViewClass)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = viewPagerRecyclerAdapter
            setCurrentItem(monthIndex, false)
            offscreenPageLimit = 1
            checkIfTitleChangeNeeded()


        }
    }

    private fun checkIfTitleChangeNeeded()= scope.launch(Dispatchers.IO) {
        val position = monthViewPager.currentItem
        val positionChanged: Boolean = currentMonthIndex != position

        if (positionChanged or firstLoad) {
            //monthViewPagerViewClassListener.setCalendar(viewPagerAdapter.getMonth(position))
            //setTitle(position)
            createAndSetTitleString(position)
            currentMonthIndex = position
        }
    }

    private suspend fun createAndSetTitleString(position: Int){
        val currentMonth: YearMonth = viewPagerRecyclerAdapter.getMonth(position)
        val titleString = "${currentMonth.month} ${currentMonth.year}"
        setTitle(titleString)
    }

   private suspend fun setTitle( titleString:String)= withContext(Dispatchers.Main){
        /*val currentMonth: YearMonth = viewPagerAdapter.getMonth(position)
        val titleString: String = "${currentMonth.month} ${currentMonth.year}"*/
        toolbar.title = titleString
    }

   override fun getRoot(): View = this.root



    override fun onPageScrollStateChanged(state: Int) {
        super.onPageScrollStateChanged(state)
        if (state == ViewPager2.SCROLL_STATE_IDLE)
            checkIfTitleChangeNeeded()
    }


    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
       /* if (firstLoad){
            // monthViewPagerViewClassListener.setCalendar(viewPagerAdapter.getMonth(position))
        firstLoad = false}
*/
    }


    override fun setListener(monthViewPagerViewClassListener: MonthViewPagerViewClassInterface.MonthViewPagerViewClassListener) {
        this.monthViewPagerViewClassListener = monthViewPagerViewClassListener
    }
}
