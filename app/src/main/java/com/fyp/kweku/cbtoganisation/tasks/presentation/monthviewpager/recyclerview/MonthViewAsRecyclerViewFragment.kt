package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.recyclerview


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

import com.fyp.kweku.cbtoganisation.common.CBTOrganisationApplication
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarViewClassForViewPagerInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog.TasksBySpecificDayDialogFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate
import javax.inject.Inject


class MonthViewAsRecyclerViewFragment : Fragment() , MonthCalendarViewClassForViewPagerInterface.MonthCalendarViewClassFragmentListener{


    private val viewPool= RecyclerView.RecycledViewPool()

    override fun getViewPool(): RecyclerView.RecycledViewPool = this.viewPool

    private lateinit var tasks: List<TaskPresentationModel>
    private lateinit var monthViewAsRecyclerViewClassInterface: MonthViewAsRecyclerViewClassInterface
    @Inject
   lateinit var taskOutput: TaskOutput

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CBTOrganisationApplication.getComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        monthViewAsRecyclerViewClassInterface =
            MonthViewAsRecyclerViewClass(
                inflater,
                container
            )
        getAllTasksLiveData().observe(viewLifecycleOwner, allTasksObserver())
        monthViewAsRecyclerViewClassInterface.initAdapter(this)
        monthViewAsRecyclerViewClassInterface.initRecyclerview()
        return monthViewAsRecyclerViewClassInterface.getRoot()
    }

    private fun getAllTasksLiveData(): LiveData<List<TaskPresentationModel>> {
        // @Suppress("UNCHECKED_CAST")
        // return monthViewPagerController.getAllTasks() as LiveData<List<TaskPresentationModel>>
        return taskOutput.getAllTasks()
    }

    private fun allTasksObserver(): Observer<List<TaskPresentationModel>> {
        return Observer { tasks -> setTasks(tasks) }
    }

    private fun setTasks(tasks: List<TaskPresentationModel>){
        monthViewAsRecyclerViewClassInterface.setTasks(tasks)
    }

    override fun launchDialog(date: LocalDate) {
        launchDialogFragmentWithArguments(date)
    }

    /*fun allTasksObserver(): Observer<List<TaskPresentationModel>>{
        return Observer { tasks -> monthCalendarControllerForViewPager.filterTasks(tasks) }
    }*/

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


}
