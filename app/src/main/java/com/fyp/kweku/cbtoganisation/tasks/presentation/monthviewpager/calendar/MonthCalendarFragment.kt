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
        Timber.i("onCreate")
        taskActivity = context as TaskActivity
        Timber.i(monthString)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Timber.i("${container ==null}")
        //monthCalendarControllerForViewPager.filterTasksByMonth(currentMonth)
        val monthCalendarViewClassForViewPagerInterface: MonthCalendarViewClassForViewPagerInterface = MonthCalendarViewClassForViewPager(inflater, context)
        monthCalendarControllerForViewPager.bindView(monthCalendarViewClassForViewPagerInterface)
        monthCalendarViewClassForViewPagerInterface.setFragmentListener(this)
        monthCalendarViewClassForViewPagerInterface.initRecyclerview()
        val datesAndTasksByMonthLiveDataObserver = Observer<List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>>{datesAndTasks -> monthCalendarViewClassForViewPagerInterface.setAdapterData(datesAndTasks)}
        getDatesAndTasksByMonthAsLiveData().observe(viewLifecycleOwner, datesAndTasksByMonthLiveDataObserver)
       Timber.i("onCreateView")

        return monthCalendarViewClassForViewPagerInterface.getRoot()
    }

   /* fun observe():Observer<List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>>{
        return Observer<List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>>{datesAndTasks -> monthCalendarControllerForViewPager.initRecyclerview(datesAndTasks) }
    }*/


    private fun getDatesAndTasksByMonthAsLiveData(): LiveData<List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>>{
        @Suppress("UNCHECKED_CAST")
        return monthCalendarControllerForViewPager.getDatesAndTasksByMonthAsAny() as LiveData<List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>>
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.i("onDetatch")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy")
       // getDatesAndTasksByMonthAsLiveData().removeObserver(observe())
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
