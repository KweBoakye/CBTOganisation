package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarFragment
import kotlinx.coroutines.*
import org.threeten.bp.YearMonth
import kotlin.coroutines.CoroutineContext


class MonthViewPagerAdapter(private val monthViewPagerFragment: MonthViewPagerFragment): FragmentStateAdapter(monthViewPagerFragment) {
    override fun createFragment(position: Int): Fragment = runBlocking(Dispatchers.IO) {
        val monthString = months[position].toString()
        val newMonthCalendarFragment: Deferred<MonthCalendarFragment> = createMonthCalendarFragmentAsync(monthString)
        return@runBlocking newMonthCalendarFragment.await()
    }

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)




    private fun createMonthCalendarFragmentAsync(monthString: String): Deferred<MonthCalendarFragment> = scope.async(Dispatchers.IO) {
        return@async MonthCalendarFragment.create(monthString,monthViewPagerFragment)
    }





    private var months = listOf<YearMonth>()  //List(2400){  (YearMonth.of(1900,1).plusMonths(it.toLong()))}

    fun setMonths(months: List<YearMonth>){
        this.months = months
        notifyDataSetChanged()
    }

   fun getMonth(position: Int): YearMonth{
       return months[position]
   }

    override fun getItemCount(): Int {
       return months.size
    }



   /* override fun getItemId(position: Int): Long {
        return months[position].hashCode().toLong()
    }*/


   /* inner class MonthCalendarViewPagerViewHolder( parent: ViewGroup,monthCalendarViewClassForViewPagerInterface: MonthCalendarViewClassForViewPagerInterface):
        RecyclerView.ViewHolder(monthCalendarViewClassForViewPagerInterface.getRoot()),KoinComponent{
        lateinit var monthCalendarControllerForViewPager : MonthCalendarControllerForViewPager

        fun bind(position: Int){
            monthCalendarControllerForViewPager = MonthCalendarControllerForViewPager(months[position])
        }

    }*/



}