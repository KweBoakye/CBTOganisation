package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskViewModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog.TasksBySpecificDayDialogFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import timber.log.Timber

private const val MONTH = "com.fyp.kweku.cbtoganisation.MONTH"
class MonthCalendarFragment : Fragment(), MonthCalendarViewClassForViewPagerInterface.MonthCalendarViewClassFragmentListener {



    lateinit var currentMonth: YearMonth
    lateinit var monthCalendarControllerForViewPager: MonthCalendarControllerForViewPager
    lateinit var taskActivity: TaskActivity
    val taskViewModel by sharedViewModel<TaskViewModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        monthCalendarControllerForViewPager = get()
        //monthCalendarControllerForViewPager.loadAllTasksForRecycler()
        val monthString = arguments?.getString(MONTH) ?: throw IllegalStateException()//monthBundle!!["month"] as YearMonth
        currentMonth = YearMonth.parse(monthString)
        monthCalendarControllerForViewPager.filterTasksByMonth(currentMonth)
        taskActivity = context as TaskActivity
        Timber.i(monthString)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Timber.i("${container ==null}")
        val monthCalendarViewClassForViewPagerInterface: MonthCalendarViewClassForViewPagerInterface = MonthCalendarViewClassForViewPager(inflater, context)
        monthCalendarViewClassForViewPagerInterface.setFragmentListener(this)
        val datesAndTasksByMonthLiveDataObserver = Observer<List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>>{datesAndTasks -> monthCalendarViewClassForViewPagerInterface.initRecyclerview(datesAndTasks)}
        getDatesAndTasksByMonthAsLiveData().observe(viewLifecycleOwner, datesAndTasksByMonthLiveDataObserver)


        return monthCalendarViewClassForViewPagerInterface.getRoot()
    }

    private fun getDatesAndTasksByMonthAsLiveData(): LiveData<List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>>{
        @Suppress("UNCHECKED_CAST")
        return monthCalendarControllerForViewPager.getDatesAndTasksByMonthAsAny() as LiveData<List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>>
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

    fun launchDialogFragmentWithArguments(day: LocalDate){
        val dayString = day.format(ProjectDateTimeUtils.getCustomDateFormatter())
        val dateBundle: Bundle = Bundle()
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

    companion object {
        fun create(month: String) =
            MonthCalendarFragment().apply {
                arguments = Bundle(1).apply {
                    putString(MONTH, month)
                }
            }
    }
}
