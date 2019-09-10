package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.common.CBTOrganisationApplication
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog.TasksBySpecificDayDialogFragment
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
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
    private var calendarData: Deferred<List<Triple<LocalDate, Boolean, MutableList<String>>>> =
        CompletableDeferred()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CBTOrganisationApplication.getComponent().inject(this)

        val monthString = arguments?.getString(MONTH) ?: throw IllegalStateException()//monthBundle!!["month"] as YearMonth
        currentMonth = YearMonth.parse(monthString)
        monthCalendarControllerForViewPager.setMonth(currentMonth)

        calendarData = monthCalendarControllerForViewPager.generateCalendarAsync()

        taskActivity = context as TaskActivity


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


         monthCalendarViewClassForViewPagerInterface = MonthCalendarViewClassForViewPager(inflater, context,container,this)
        monthCalendarControllerForViewPager.bindView(monthCalendarViewClassForViewPagerInterface)

        monthCalendarControllerForViewPager.setAdapterData(calendarData)


        return monthCalendarViewClassForViewPagerInterface.getRoot()
    }






    override fun onDetach() {
        super.onDetach()
        parentListener = null
    }






    override fun launchDialog(date: LocalDate) {
        launchDialogFragmentWithArguments(date)
    }



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



    interface ParentListener{
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
