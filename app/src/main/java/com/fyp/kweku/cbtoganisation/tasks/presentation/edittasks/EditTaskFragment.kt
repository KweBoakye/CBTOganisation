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
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.utils.CircularRevealAnimationUtilClass
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import org.koin.android.ext.android.get
import timber.log.Timber


class EditTaskFragment :DialogFragment(), DatePickerDialog.OnDateSetListener {

    companion object {
        val TAG: String = "EditTaskFragment"
        val ARG_REVEAL_SETTINGS = "ARG_REVEAL_SETTINGS"
    }

   private var taskID: String? = null
    private lateinit var editTaskController: EditTaskController
    private lateinit var revealSettings: CircularRevealAnimationUtilClass.RevealAnimationSetting
    private lateinit var taskOutput: TaskOutput


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskOutput = get()
        val taskIDBundle: Bundle? = arguments
        taskID = taskIDBundle?.getString("taskID")
        revealSettings = taskIDBundle!!.getParcelable(ARG_REVEAL_SETTINGS)!!
        Timber.i("${revealSettings.centerX}")
        Timber.i(taskID)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
        editTaskController = get()
        taskID?.let { editTaskController.getTask(it) }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object :Dialog(requireContext(), theme){
            override fun onBackPressed() {
                fun backpress() = super.onBackPressed()
                CircularRevealAnimationUtilClass.startCircularRevealExitAnimation(context, view!!, revealSettings, R.color.background, R.color.primaryDarkColor, object :CircularRevealAnimationUtilClass.AnimationFinishedListener{
                    override fun onAnimationFinished() {
                        backpress()
                    }
                })

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val editTaskViewClassInterface : EditTaskViewClassInterface = EditTaskViewClass(inflater, container)
        val root = editTaskViewClassInterface.getRoot()
        val v: View = root.rootView
        CircularRevealAnimationUtilClass.registerCircularRevealAnimation(context!!, v, revealSettings, R.color.background, R.color.primaryDarkColor, object :CircularRevealAnimationUtilClass.AnimationFinishedListener{
            override fun onAnimationFinished() {

            }
        })
        editTaskController.bindView(editTaskViewClassInterface)
       // editTaskController.setTaskData()

        val taskObserver = Observer<TaskPresentationModel>{task ->
            Timber.i(task.taskID)
            editTaskViewClassInterface.setInitialData(task)}

        getTaskAsLiveData().observe(viewLifecycleOwner, taskObserver)

        return root
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
        return taskOutput.getSingleTaskLiveData()
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

    fun onBackPressed(){}
}
