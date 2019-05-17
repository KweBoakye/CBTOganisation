package com.fyp.kweku.cbtoganisation.tasks.presentation.edittasks


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import org.koin.android.ext.android.get
import timber.log.Timber


class EditTaskFragment :DialogFragment(), DatePickerDialog.OnDateSetListener {

    companion object {
        val TAG: String = "EditTaskFragment"
    }

   private var taskID: String? = null
    private lateinit var editTaskController: EditTaskController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskIDBundle: Bundle? = arguments
        taskID = taskIDBundle?.getString("taskID")
        Timber.i(taskID)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
        editTaskController = get()
        taskID?.let { editTaskController.getTask(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val editTaskViewClassInterface : EditTaskViewClassInterface = EditTaskViewClass(inflater, container)

        editTaskController.bindView(editTaskViewClassInterface)
       // editTaskController.setTaskData()

        val taskObserver = Observer<TaskPresentationModel>{task ->
            Timber.i(task.taskID)
            editTaskViewClassInterface.setInitialData(task)}

        getTaskAsLiveData().observe(viewLifecycleOwner, taskObserver)

        return editTaskViewClassInterface.getRoot()
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width: Int = ViewGroup.LayoutParams.MATCH_PARENT
            val height: Int = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

   private fun getTaskAsLiveData(): LiveData<TaskPresentationModel>{
        @Suppress("UNCHECKED_CAST")
        return editTaskController.getTaskAsAny() as LiveData<TaskPresentationModel>
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.save_task->{
            editTaskController.updateTask()
            true
        }
        else ->{
        super.onOptionsItemSelected(item)}
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
