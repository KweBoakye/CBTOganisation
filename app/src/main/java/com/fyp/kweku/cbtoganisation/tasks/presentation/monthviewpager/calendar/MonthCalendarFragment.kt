package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView

import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog.TasksBySpecificDayDialogFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.koin.android.ext.android.get
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth


private const val MONTH = "com.fyp.kweku.cbtoganisation.MONTH"
class MonthCalendarFragment : Fragment(), MonthCalendarViewClassForViewPagerInterface.MonthCalendarViewClassFragmentListener {

    override fun getViewPool(): RecyclerView.RecycledViewPool {
        return  parentListener!!.getViewPool()
    }

    private lateinit var currentMonth: YearMonth
    private lateinit var monthCalendarControllerForViewPager: MonthCalendarControllerForViewPager
    private lateinit var monthCalendarViewClassForViewPagerInterface: MonthCalendarViewClassForViewPagerInterface
     var parentListener: ParentListener? = null
    lateinit var taskActivity: TaskActivity


    /*override fun onAttach(context: Context) {
        super.onAttach(context)

        val pContext =   as ParentListener
            parentListener = pContext

    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        monthCalendarControllerForViewPager = get()
        //monthCalendarControllerForViewPager.loadAllTasksForRecycler()
        val monthString = arguments?.getString(MONTH) ?: throw IllegalStateException()//monthBundle!!["month"] as YearMonth
        currentMonth = YearMonth.parse(monthString)
        monthCalendarControllerForViewPager.setMonth(currentMonth)
        //monthCalendarControllerForViewPager.setDates()
       // monthCalendarControllerForViewPager.filterTasks(parentListener.getAllTasks())
        monthCalendarControllerForViewPager.generateCalendar()

        taskActivity = context as TaskActivity


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //monthCalendarControllerForViewPager.filterTasksByMonth(currentMonth)
         monthCalendarViewClassForViewPagerInterface = MonthCalendarViewClassForViewPager(inflater, context,container,this)
        monthCalendarControllerForViewPager.bindView(monthCalendarViewClassForViewPagerInterface)
        //monthCalendarViewClassForViewPagerInterface.setFragmentListener(this)
        //getAllTasksLiveData().observe(this, allTasksObserver())
        monthCalendarViewClassForViewPagerInterface.initRecyclerview()
        monthCalendarControllerForViewPager.setAdapterData()

        /*val datesAndTasksByMonthLiveDataObserver = Observer<List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>>{datesAndTasks -> monthCalendarViewClassForViewPagerInterface.setAdapterData(datesAndTasks)}
        getDatesAndTasksByMonthAsLiveData().observe(viewLifecycleOwner, datesAndTasksByMonthLiveDataObserver)*/


        return monthCalendarViewClassForViewPagerInterface.getRoot()
    }

   /* fun observe():Observer<List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>>{
        return Observer<List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>>{datesAndTasks -> monthCalendarControllerForViewPager.initRecyclerview(datesAndTasks) }
    }*/




    override fun onDetach() {
        super.onDetach()
        parentListener = null
    }





    /*override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(MONTH, currentMonth.toString())
    }*/

    fun convertDates(){}

    //region used to launch a new dialogFragment to display a specific day
    override fun launchDialog(date: LocalDate) {
        launchDialogFragmentWithArguments(date)
    }

    /*fun allTasksObserver(): Observer<List<TaskPresentationModel>>{
        return Observer { tasks -> monthCalendarControllerForViewPager.filterTasks(tasks) }
    }*/

    private fun launchDialogFragmentWithArguments(day: LocalDate){
        val dayString = day.format(ProjectDateTimeUtils.getCustomDateFormatter())
        val dateBundle = Bundle()
        dateBundle.putString("day", dayString)
        launchDialog(dateBundle)
    }

     fun launchDialog(dateBundle: Bundle){
        val dialog = TasksBySpecificDayDialogFragment()
        dialog.arguments = dateBundle
        val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        dialog.show(fragmentTransaction, TasksBySpecificDayDialogFragment.TAG)
    }
    //endregion


    interface ParentListener{
        fun getAllTasks(): List<TaskPresentationModel>
        fun getViewPool(): RecyclerView.RecycledViewPool
    }

    companion object {
        fun create(month: String, parentListener: ParentListener) =
            MonthCalendarFragment().apply {
                arguments = Bundle(1).apply {
                    putString(MONTH, month)
                }
                this.parentListener = parentListener
            }
    }
}
