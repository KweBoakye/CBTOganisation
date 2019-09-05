package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.MonthCalendarViewpagerviewBinding
import com.google.android.flexbox.*
import com.google.android.flexbox.AlignItems.FLEX_START
import com.google.android.flexbox.FlexDirection.ROW
import com.google.android.flexbox.FlexWrap.WRAP
import org.threeten.bp.LocalDate

class MonthCalendarViewClassForViewPager(val inflater: LayoutInflater, val context: Context?,val parent: ViewGroup?, val fragmentListener: MonthCalendarViewClassForViewPagerInterface.MonthCalendarViewClassFragmentListener):  MonthCalendarViewClassForViewPagerInterface, MonthCalendarRecyclerAdapterForViewPager.DayListener {


    override fun daySelected(date: LocalDate) {
        fragmentListener.launchDialog(date)
    }

    val binding: MonthCalendarViewpagerviewBinding = MonthCalendarViewpagerviewBinding.inflate(inflater, parent, false)
    private val root = binding.root
    private val monthCalendarRecyclerViewForViewPager: RecyclerView = binding.MonCalendarRecyclerViewForViewPager
   //private lateinit var fragmentListener: MonthCalendarViewClassForViewPagerInterface.MonthCalendarViewClassFragmentListener

   // private val customGridLayoutManager = CustomGridLayoutManager(context!!,7, VERTICAL, false)
    private val dayTextboxBackgroundColor: Int = ContextCompat.getColor(context!!, R.color.background)
    private val monthCalendarRecyclerAdapterForViewPager = MonthCalendarRecyclerAdapterForViewPager(this, dayTextboxBackgroundColor)
    private val flexbox: FlexboxLayoutManager = CustomFlexboxLayoutManager(context!!, ROW, WRAP).also {

       // it.flexWrap = WRAP
       // it.flexDirection = ROW
        it.alignItems = FLEX_START
        it.justifyContent  = JustifyContent.FLEX_START
    }


    override fun setAdapterData(datesAndTasks: List<Triple<LocalDate, Boolean, MutableList<String>>>){
        monthCalendarRecyclerAdapterForViewPager.submitList(datesAndTasks)
    }

    private fun intitFlexbox() {
        with(flexbox) {
            flexWrap = WRAP
            flexDirection = ROW
            alignItems = FLEX_START
            justifyContent = JustifyContent.FLEX_START
        }
    }

    override fun setViewPool(viewPool: RecyclerView.RecycledViewPool){
        monthCalendarRecyclerViewForViewPager.setRecycledViewPool(viewPool)
    }

    override fun initRecyclerview(){

        //val a: ViewGroup.LayoutParams = monthCalendarRecyclerViewForViewPager.layoutParams
       /* with(flexbox){
            flexWrap = WRAP
            flexDirection = ROW
            alignItems = FLEX_START
            justifyContent  = FLEX_START



        }*/

        with(monthCalendarRecyclerViewForViewPager){
            layoutManager = flexbox//customGridLayoutManager
            adapter = monthCalendarRecyclerAdapterForViewPager
            //setRecycledViewPool(fragmentListener.getViewPool())

            //setHasFixedSize(true)
            //addItemDecoration(Divider(context))






        }

    }

  /*override  fun setFragmentListener(monthCalendarViewClassFragmentListener: MonthCalendarViewClassForViewPagerInterface.MonthCalendarViewClassFragmentListener){
      this.fragmentListener = monthCalendarViewClassFragmentListener
  }*/



    override fun getRoot(): View=  this.root




}