package com.fyp.kweku.cbtoganisation.tasks.presentation.home


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskViewModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc.HorizontalCalendarController
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc.HorizontalCalendarViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc.HorizontalCalendarViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayController
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayRecyclerViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayRecyclerViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment : Fragment() {

    private lateinit var taskActivity: TaskActivity
    private lateinit var homeController: HomeController
    private lateinit var horizontalCalendarController: HorizontalCalendarController
    private lateinit var tasksByDayController: TasksByDayController
    val taskViewModel by sharedViewModel<TaskViewModel>()


    override fun onAttach(context: Context) {
        super.onAttach(context)

        taskActivity = context as TaskActivity

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
tasksByDayController = get()
        tasksByDayController.loadAllTasksForRecycler()



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {





        val homeViewClassInterface: HomeViewClassInterface = HomeViewClass(layoutInflater, container)
        homeViewClassInterface.bindTaskViewModel(taskViewModel)
        homeController = get()
        homeController.bindView(homeViewClassInterface)
        homeController.onCreateView(taskActivity)
        val horizontalCalendarViewClassInterface: HorizontalCalendarViewClassInterface =
            HorizontalCalendarViewClass(this.context!!, container,homeViewClassInterface.getRootView())
        val tasksByDayRecyclerViewClassInterface: TasksByDayRecyclerViewClassInterface = TasksByDayRecyclerViewClass(this.context!!, homeViewClassInterface.getRootView(),this)
        horizontalCalendarController = get()
        horizontalCalendarController.let {horizontalCalendarController ->
            horizontalCalendarController.bindView(horizontalCalendarViewClassInterface)
            horizontalCalendarController.setControllerAsHorizontalCalendarViewClassListener()
            horizontalCalendarController.initHorizontalCalendar()
        }




        val tasksByDayObserver = Observer<MutableList<TaskPresentationModel>> {tasks -> tasksByDayRecyclerViewClassInterface.setTasks(tasks)}

        reConvertedTasksByDayLiveData().observe(
            this,
            tasksByDayObserver
        )

        return homeViewClassInterface.getRootView()
    }

    private fun reConvertedTasksByDayLiveData(): LiveData<MutableList<TaskPresentationModel>> {
        @Suppress("UNCHECKED_CAST")
        return   ( tasksByDayController.getTasksInteractorInterface.getTasksByLiveDataAsAny() as LiveData<MutableList<TaskPresentationModel>>)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*binding.goToCreateNewTaskFragmentButton.setOnClickListener {
             //Navigate to details fragment with the given
             taskActivity.navController.navigate(R.id.action_homeFragment_to_createNewTaskFragment)
         }*/
        homeController.onviewCreated()
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDetach() {
        super.onDetach()
    }




}