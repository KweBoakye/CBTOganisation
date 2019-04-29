package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarControllerForViewPager
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarViewClassForViewPager
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarViewClassForViewPagerInterface
import org.koin.core.KoinComponent
import org.koin.core.get
import org.threeten.bp.YearMonth


class MonthViewPagerAdapter(monthViewPagerFragment: MonthViewPagerFragment): FragmentStateAdapter(monthViewPagerFragment) {

    override fun getItem(position: Int): MonthCalendarFragment {
        val monthString = months[position].toString()
        return MonthCalendarFragment.create(monthString)
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