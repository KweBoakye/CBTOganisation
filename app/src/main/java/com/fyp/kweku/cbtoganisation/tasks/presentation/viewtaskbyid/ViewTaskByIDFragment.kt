package com.fyp.kweku.cbtoganisation.tasks.presentation.viewtaskbyid

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.common.CBTOrganisationApplication
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.presentation.edittasks.EditTaskFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.utils.CircularRevealAnimationUtilClass
import javax.inject.Inject


class ViewTaskByIDFragment : DialogFragment(), ViewTaskByIDViewClassInterface.ViewTaskByIDViewClassFragmentListener {


   @Inject
   lateinit var viewTaskByIDController: ViewTaskByIDController


    companion object {
        val TAG: String = "ViewTaskByIDFragment"

    }

    var taskID: String? = null
    @Inject
    lateinit var taskOutput: TaskOutput

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CBTOrganisationApplication.getComponent().inject(this)
        //viewTaskByIDController = get()
        //taskOutput = get()
        val taskIDBundle: Bundle? = arguments
        taskID = taskIDBundle?.getString("taskID")
        viewTaskByIDController.loadTask(taskID!!)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val viewTaskByIDViewClassInterface: ViewTaskByIDViewClassInterface = ViewTaskByIDViewClass(inflater, container)
        viewTaskByIDController.setViewTaskByIDViewClassInterface(viewTaskByIDViewClassInterface)
       val root = viewTaskByIDViewClassInterface.getRoot()

       val taskObserver = Observer<TaskPresentationModel>{ task -> viewTaskByIDViewClassInterface.populateTextViews(task)}
        getSingleTaskLiveDataAsLiveData().observe(viewLifecycleOwner, taskObserver)
        viewTaskByIDViewClassInterface.setFragmentListener(this)
        viewTaskByIDViewClassInterface.setupToolbar()

        return root
    }


    private fun getSingleTaskLiveDataAsLiveData(): LiveData<TaskPresentationModel>{
       return taskOutput.getSingleTaskLiveData()

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

    override fun dismissDialogFragment(){
        this.dismiss()
    }

    override fun launchEditTaskFragment(revealSettings: CircularRevealAnimationUtilClass.RevealAnimationSetting){
        taskID?.let {
            launchDialogFragmentWithArguments(it, revealSettings) }
    }



    private fun launchDialogFragmentWithArguments(taskID: String,revealSettings: CircularRevealAnimationUtilClass.RevealAnimationSetting){

        val taskIDBundle = Bundle()
        taskIDBundle.putString("taskID", taskID)
        taskIDBundle.putParcelable("ARG_REVEAL_SETTINGS", revealSettings)
        //Timber.i("${taskIDBundle.getParcelable<CircularRevealAnimationUtilClass.RevealAnimationSetting?>("ARG_REVEAL_SETTINGS").centerX}")
        launchDialog(taskIDBundle)
    }


    private fun launchDialog(taskIDBundle: Bundle){
        val dialog = EditTaskFragment()
        dialog.arguments = taskIDBundle
        val fragmentTransaction = parentFragment!!.fragmentManager!!.beginTransaction()
        dialog.show(fragmentTransaction, "EditTaskFragment")
    }

}
