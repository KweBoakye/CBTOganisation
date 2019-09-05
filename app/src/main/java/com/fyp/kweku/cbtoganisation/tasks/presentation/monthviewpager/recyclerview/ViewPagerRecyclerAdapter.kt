package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.common.CBTOrganisationApplication

import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarControllerForViewPager
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarViewClassForViewPager
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarViewClassForViewPagerInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import org.koin.core.KoinComponent
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth


class ViewPagerRecyclerAdapter(private val fragmentListener: MonthCalendarViewClassForViewPagerInterface.MonthCalendarViewClassFragmentListener) : RecyclerView.Adapter<ViewPagerRecyclerAdapter.CalendarViewHolder>(), KoinComponent{

    private var tasks: List<TaskPresentationModel> = listOf()
    private var months:List<YearMonth> = List(2400){  (YearMonth.of(1900,1).plusMonths(it.toLong()))}

    fun setTasks(tasks: List<TaskPresentationModel>){
        this.tasks = tasks
    }

    fun getMonths(): List<YearMonth> = this.months
    fun getMonth(position:Int):YearMonth{
        return months[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
       val monthCalendarViewClassForViewPagerInterface:MonthCalendarViewClassForViewPagerInterface = MonthCalendarViewClassForViewPager(LayoutInflater.from(parent.context), parent.context, parent,
          fragmentListener)
        monthCalendarViewClassForViewPagerInterface.setViewPool(fragmentListener.getViewPool())
        return CalendarViewHolder(
            monthCalendarViewClassForViewPagerInterface,
            tasks
        )
    }

    override fun getItemCount(): Int {
        return months.size
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(months[position])
    }


    class CalendarViewHolder( val monthCalendarViewClassForViewPagerInterface:MonthCalendarViewClassForViewPagerInterface, val tasks: List<TaskPresentationModel>):
        RecyclerView.ViewHolder(monthCalendarViewClassForViewPagerInterface.getRoot()){

        private var calendarData: Deferred<List<Triple<LocalDate, Boolean, MutableList<String>>>> =
            CompletableDeferred()

        var monthCalendarControllerForViewPager: MonthCalendarControllerForViewPager =
            CBTOrganisationApplication.getComponent().monthCalendarControllerForViewPager

        /*@Inject
        lateinit var monthCalendarControllerForViewPager: MonthCalendarControllerForViewPager*/

        fun bind(month: YearMonth){
           with(monthCalendarControllerForViewPager){
               setMonth(month)
               bindView(monthCalendarViewClassForViewPagerInterface)
         setAdapterData(generateCalendarAsync())


           }

            monthCalendarViewClassForViewPagerInterface.initRecyclerview()
            //monthCalendarControllerForViewPager.setAdapterData()




        }
    }
}