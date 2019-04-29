package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.gridlayout.widget.GridLayout.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.MonthCalendarViewpagerviewBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.innerercyclerview.CustomGridLayoutManager
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate

class MonthCalendarViewClassForViewPager(val inflater: LayoutInflater, val context: Context?):  MonthCalendarViewClassForViewPagerInterface, MonthCalendarRecyclerAdapterForViewPager.DayListener {
    override fun daySelected(date: LocalDate) {
        fragmentListener.launchDialog(date)
    }

    val binding: MonthCalendarViewpagerviewBinding = MonthCalendarViewpagerviewBinding.inflate(inflater, null, false)
    private val root = binding.root
    private val monthCalendarRecyclerViewForViewPager: RecyclerView = binding.MonCalendarRecyclerViewForViewPager
   private lateinit var fragmentListener: MonthCalendarViewClassForViewPagerInterface.MonthCalendarViewClassFragmentListener
    private val customGridLayoutManager = CustomGridLayoutManager(context!!,7, VERTICAL, false)
    private val dayTextboxBackgroundColor: Int = ContextCompat.getColor(context!!, R.color.secondaryColor)

    override fun initRecyclerview(datesAndTasks: List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>){
        val monthCalendarRecyclerAdapterForViewPager = MonthCalendarRecyclerAdapterForViewPager(datesAndTasks,this, dayTextboxBackgroundColor)
        with(monthCalendarRecyclerViewForViewPager){
            layoutManager = customGridLayoutManager
            adapter = monthCalendarRecyclerAdapterForViewPager
            setHasFixedSize(true)



        }

    }

  override  fun setFragmentListener(monthCalendarViewClassFragmentListener: MonthCalendarViewClassForViewPagerInterface.MonthCalendarViewClassFragmentListener){
      this.fragmentListener = monthCalendarViewClassFragmentListener
  }



    override fun getRoot(): View=  this.root




}