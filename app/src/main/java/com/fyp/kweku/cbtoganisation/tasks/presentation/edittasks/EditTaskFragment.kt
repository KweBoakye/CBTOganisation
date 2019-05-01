package com.fyp.kweku.cbtoganisation.tasks.presentation.edittasks


import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.fyp.kweku.cbtoganisation.R



class EditTaskFragment :DialogFragment() {

    companion object {
        val TAG: String = "EditTaskFragment"
    }

    var taskID: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskIDBundle: Bundle? = arguments
        taskID = taskIDBundle?.getString("taskID")
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val editTaskViewClassInterface : EditTaskViewClassInterface = EditTaskViewClass(inflater, container)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.save_task->{
            true
        }
        else ->{
        super.onOptionsItemSelected(item)}
    }
}
