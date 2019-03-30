package com.fyp.kweku.cbtoganisation.tasks.presentation.home


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview.HorizontalCalendarAdapter
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview.HorizontalCalendarItem
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview.HorizontalCalendarProperties
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview.HorizontalCalendarUtils
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.android.ext.android.get
import java.util.Calendar
import java.util.Date

class HomeFragment : Fragment() {

    private lateinit var taskActivity: TaskActivity
    private lateinit var homeController: HomeController
    private lateinit var calendarAdapter: HorizontalCalendarAdapter
    private lateinit var calendarProperties: HorizontalCalendarProperties
    private lateinit var calendarRecycler: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)

        taskActivity = context as TaskActivity

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        /*binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root*/
        val homeViewClassInterface: HomeViewClassInterface = HomeViewClass(layoutInflater, container)
        homeController = get()
        homeController.bindView(homeViewClassInterface)
        homeController.onCreateView(taskActivity)
        val root = homeViewClassInterface.getRootView()
        calendarRecycler = root.calender_recycler as RecyclerView
        initHorizontalCalendar()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*binding.goToCreateNewTaskFragmentButton.setOnClickListener {
             //Navigate to details fragment with the given
             taskActivity.navController.navigate(R.id.action_homeFragment_to_createNewTaskFragment)
         }*/
        homeController.onviewCreated()
        super.onViewCreated(view, savedInstanceState)
    }

    fun initHorizontalCalendar() {
        val calendar: Calendar = Calendar.getInstance();
        calendar.time = Date()
        setCalenderProperties(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR))

        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        calendarRecycler.layoutManager = layoutManager


        val onDaySelectedListener: HorizontalCalendarAdapter.OnDaySelectedListener =
            object : HorizontalCalendarAdapter.OnDaySelectedListener {
                override fun onDaySelected(view: View, date: String, position: Int) {}
            }

        val onEndReachedListener: HorizontalCalendarAdapter.OnEndReachedListener =
            object : HorizontalCalendarAdapter.OnEndReachedListener {
                override fun onEndReached() {}

                override fun onStartReached() {}
            }


        calendarAdapter = HorizontalCalendarAdapter(this.context!!, onDaySelectedListener, onEndReachedListener)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(calendarRecycler)
        calendarRecycler.adapter = calendarAdapter
        calendarAdapter.setData(getCalendarItems(calendarProperties.currentMonth, calendarProperties.currentYear))

    }

    fun getCalendarItems(month: Int, year: Int): List<HorizontalCalendarItem> {
        val items = ArrayList<HorizontalCalendarItem>()
        for (day in 0 until HorizontalCalendarUtils.calculateMonthLength(month)) {
            items.add(HorizontalCalendarItem(day + 1, month, R.color.colorPrimary, year))
        }
        return items
    }

    fun setCalenderProperties(currentMonth: Int, currentYear: Int) {
        calendarProperties = HorizontalCalendarProperties(currentMonth, currentYear)
    }

    override fun onDetach() {
        super.onDetach()
    }


}