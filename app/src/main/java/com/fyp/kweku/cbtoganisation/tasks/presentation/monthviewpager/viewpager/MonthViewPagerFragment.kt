package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.viewpager


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.common.CBTOrganisationApplication
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarViewClassForViewPagerInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog.TasksBySpecificDayDialogFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate
import javax.inject.Inject


class MonthViewPagerFragment : Fragment(),MonthCalendarFragment.ParentListener , MonthCalendarViewClassForViewPagerInterface.MonthCalendarViewClassFragmentListener{


    override fun getViewPool(): RecyclerView.RecycledViewPool {
        return  this.viewPool
    }

    private lateinit var taskActivity: TaskActivity
    private lateinit var tasks: List<TaskPresentationModel>
    @Inject
    lateinit var taskOutput: TaskOutput
    private val viewPool = RecyclerView.RecycledViewPool()
    private lateinit var monthViewPagerViewClassInterface: MonthViewPagerViewClassInterface
    @Inject
    lateinit var monthViewPagerController: MonthViewPagerController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CBTOrganisationApplication.getComponent().inject(this)
        taskActivity = context as TaskActivity


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         monthViewPagerViewClassInterface =
             MonthViewPagerViewClass(
                 inflater,
                 container,
                 this
             )
        getAllTasksLiveData().observe(viewLifecycleOwner,allTasksObserver())
        with(monthViewPagerViewClassInterface){
            setToolbar(taskActivity)
            setListener(monthViewPagerController)
        }
        with(monthViewPagerController){
            bindView(monthViewPagerViewClassInterface)
            initViewPager()
        }

        return  monthViewPagerViewClassInterface.getRoot()
    }

    override fun onStart() {
        super.onStart()
        getAllTasksLiveData().observe(viewLifecycleOwner, allTasksObserver())
    }

    private fun getAllTasksLiveData(): LiveData<List<TaskPresentationModel>>{

        return taskOutput.getAllTasks()
    }

    private fun allTasksObserver(): Observer<List<TaskPresentationModel>> {
        return Observer { tasks -> monthViewPagerViewClassInterface.setTasks(tasks) }
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



}



