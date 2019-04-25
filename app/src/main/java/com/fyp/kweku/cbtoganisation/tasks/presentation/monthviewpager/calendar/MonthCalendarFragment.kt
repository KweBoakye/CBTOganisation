package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.koin.android.ext.android.get
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import timber.log.Timber

private const val MONTH = "com.fyp.kweku.cbtoganisation.MONTH"
class MonthCalendarFragment : Fragment() {

    lateinit var currentMonth: YearMonth
    lateinit var monthCalendarControllerForViewPager: MonthCalendarControllerForViewPager
    lateinit var taskActivity: TaskActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        monthCalendarControllerForViewPager = get()
        monthCalendarControllerForViewPager.loadAllTasksForRecycler()
        val monthString = arguments?.getString(MONTH) ?: throw IllegalStateException()//monthBundle!!["month"] as YearMonth
        currentMonth = YearMonth.parse(monthString)
        monthCalendarControllerForViewPager.filterTasksByMonth(currentMonth)
        taskActivity = context as TaskActivity

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Timber.i("${container ==null}")
        val monthCalendarViewClassForViewPagerInterface: MonthCalendarViewClassForViewPagerInterface = MonthCalendarViewClassForViewPager(inflater, context)

        val datesAndTasksByMonthLiveDataObserver = Observer<List<Pair<LocalDate, MutableList<TaskPresentationModel>>>>{datesAndTasks -> monthCalendarViewClassForViewPagerInterface.setAdapterDatesAndTasks(datesAndTasks)}
        getDatesAndTasksByMonthAsLiveData().observe(this, datesAndTasksByMonthLiveDataObserver)
        monthCalendarViewClassForViewPagerInterface.initRecyclerview()

        return monthCalendarViewClassForViewPagerInterface.getRoot()
    }

    fun getDatesAndTasksByMonthAsLiveData(): LiveData<List<Pair<LocalDate, MutableList<TaskPresentationModel>>>>{
        @Suppress("UNCHECKED_CAST")
        return monthCalendarControllerForViewPager.getTasksInteractorInterface.getMonthCalendarTaskNamesByDayAsAny() as LiveData<List<Pair<LocalDate, MutableList<TaskPresentationModel>>>>
    }

    /*override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(MONTH, currentMonth.toString())
    }*/

    fun convertDates(){}

    companion object {
        fun create(month: String) =
            MonthCalendarFragment().apply {
                arguments = Bundle(1).apply {
                    putString(MONTH, month)
                }
            }
    }
}
