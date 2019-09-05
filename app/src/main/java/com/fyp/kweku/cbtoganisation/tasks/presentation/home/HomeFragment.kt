package com.fyp.kweku.cbtoganisation.tasks.presentation.home


import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.common.CBTOrganisationApplication
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskDeletionOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import com.fyp.kweku.cbtoganisation.tasks.presentation.deletetask.DeleteTasksController
import com.fyp.kweku.cbtoganisation.tasks.presentation.deletetask.DeleteTasksViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.deletetask.DeleteTasksViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc.HorizontalCalendarController
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc.HorizontalCalendarViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc.HorizontalCalendarViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayController
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayRecyclerViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayRecyclerViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewtaskbyid.ViewTaskByIDFragment
import javax.inject.Inject


class HomeFragment : Fragment(),HomeViewClassInterface.HomeFragmentListener,
    TasksByDayRecyclerViewClassInterface.TasksByDayRecyclerViewClassFragmentListener{

    lateinit var taskActivity: TaskActivity
    @Inject
     lateinit var homeController: HomeController
    @Inject
     lateinit var horizontalCalendarController: HorizontalCalendarController
    @Inject
    lateinit var tasksByDayController: TasksByDayController
    @Inject
    lateinit var deleteTasksController: DeleteTasksController
    @Inject
    lateinit var taskDeletionOutput: TaskDeletionOutput
    @Inject
    lateinit var taskOutput: TaskOutput
    var dayPosition: Parcelable? = null
    private var dayPositionState: String = "Day Position"

    override fun onAttach(context: Context) {
        super.onAttach(context)

        taskActivity = context as TaskActivity

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CBTOrganisationApplication.getComponent().inject(this)
        /*tasksByDayController = get()
        taskDeletionOutput = get()
        taskOutput = get()*/

        tasksByDayController.loadAllTasksForRecycler()



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeViewClassInterface: HomeViewClassInterface = HomeViewClass(layoutInflater, container, this)
        homeViewClassInterface.setTaskActivity(taskActivity)
        //homeController = get()
        homeController.bindView(homeViewClassInterface)
        val deleteTasksViewClassInterface: DeleteTasksViewClassInterface =  DeleteTasksViewClass(homeViewClassInterface.gethomeCoordinatorLayout())
        //deleteTasksController = get()
        deleteTasksController.setDeleteTasksViewClassInterface(deleteTasksViewClassInterface)
        homeViewClassInterface.setToolbar()
        val horizontalCalendarViewClassInterface: HorizontalCalendarViewClassInterface =
            HorizontalCalendarViewClass(this.context!!, container,homeViewClassInterface.getRootView())
        val tasksByDayRecyclerViewClassInterface: TasksByDayRecyclerViewClassInterface = TasksByDayRecyclerViewClass(this.context!!, homeViewClassInterface.getRootView(),this)
        tasksByDayController.run {
            setTasksByDayRecyclerViewClassInterface(tasksByDayRecyclerViewClassInterface)
            setDeleteTasksController(deleteTasksController)
        }

        //horizontalCalendarController = get()
        horizontalCalendarController.let {horizontalCalendarController ->
            horizontalCalendarController.bindView(horizontalCalendarViewClassInterface)
            horizontalCalendarController.setControllerAsHorizontalCalendarViewClassListener()
            horizontalCalendarController.initHorizontalCalendar()



            //getSelectedDateForHomeTitleAsLiveData().observe(this, selectedDateObserver)
        }

        val tasksByDayObserver = Observer<MutableList<TaskPresentationModel>> {tasks -> tasksByDayRecyclerViewClassInterface.setTasks(tasks)}

       taskOutput.getMediatorTasksByDay().observe(
            this,
            tasksByDayObserver
        )



        return homeViewClassInterface.getRootView()
    }



   /* private fun getSelectedDateForHomeTitleAsLiveData():LiveData<LocalDate>{
        @Suppress("UNCHECKED_CAST")
        return homeController.getSelectedDate() as LiveData<LocalDate>
    }*/


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*binding.goToCreateNewTaskFragmentButton.setOnClickListener {
             //Navigate to details fragment with the given
             taskActivity.navController.navigate(R.id.action_homeFragment_to_createNewTaskFragment)
         }*/
        homeController.onviewCreated()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(dayPositionState, dayPosition)
    }

    override fun onStart() {
        super.onStart()
        taskDeletionOutput.getShouldSnackBarBeLaunched().observe(viewLifecycleOwner, showSnackbarObserver())

    }

    override fun onStop() {
        super.onStop()
        taskDeletionOutput.getShouldSnackBarBeLaunched().removeObservers(viewLifecycleOwner)
    }

    override fun onGoToCreateNewTaskFragmentButtonClicked(taskActivity: TaskActivity) {
        taskActivity.navController.navigate(R.id.action_homeFragment_to_createNewTaskFragment)
    }

    override fun launchDialog(taskID: String){
        launchDialogFragmentWithArguments(taskID)
    }

    private fun launchDialogFragmentWithArguments(taskID: String){

        val taskIDBundle = Bundle()
        taskIDBundle.putString("taskID", taskID)
        launchDialog(taskIDBundle)
    }

    fun launchDialog(taskIDBundle: Bundle){
        val dialog = ViewTaskByIDFragment()
        dialog.arguments = taskIDBundle
        val fragmentTransaction: FragmentTransaction = fragmentManager!!
            .beginTransaction()
        dialog.show(fragmentTransaction, ViewTaskByIDFragment.TAG)
    }

    private fun showSnackbarObserver():Observer<Boolean>{
        return Observer{ showSnackbar -> deleteTasksController.askToRestoreTask(showSnackbar) }
    }






}