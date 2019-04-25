package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.gridlayout.widget.GridLayout.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.databinding.MonthCalendarViewpagerviewBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.innerercyclerview.CustomGridLayoutManager
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate

class MonthCalendarViewClassForViewPager(val inflater: LayoutInflater, val context: Context?):  MonthCalendarViewClassForViewPagerInterface {

    val binding: MonthCalendarViewpagerviewBinding = MonthCalendarViewpagerviewBinding.inflate(inflater, null, false)
    private val root = binding.root
    val monthCalendarRecyclerViewForViewPager: RecyclerView = binding.MonCalendarRecyclerViewForViewPager
    val monthCalendarRecyclerAdapterForViewPager: MonthCalendarRecyclerAdapterForViewPager = MonthCalendarRecyclerAdapterForViewPager()
    val customGridLayoutManager = CustomGridLayoutManager(context!!,7, HORIZONTAL, false)

    override fun initRecyclerview(){
        with(monthCalendarRecyclerViewForViewPager){
            layoutManager = customGridLayoutManager
            setHasFixedSize(true)
            adapter = monthCalendarRecyclerAdapterForViewPager
        }

    }

    override fun setAdapterDatesAndTasks(datesAndTasks: List<Pair<LocalDate, MutableList<TaskPresentationModel>>>){
        monthCalendarRecyclerAdapterForViewPager.setDatesAndTasks(datesAndTasks)
    }


    override fun getRoot(): View=  this.root




}