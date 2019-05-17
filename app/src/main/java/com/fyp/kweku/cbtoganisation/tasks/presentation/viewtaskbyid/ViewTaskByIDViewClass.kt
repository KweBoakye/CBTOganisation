package com.fyp.kweku.cbtoganisation.tasks.presentation.viewtaskbyid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.databinding.ViewTaskByIdFragmentBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.threeten.bp.format.DateTimeFormatter

class ViewTaskByIDViewClass(val  inflater: LayoutInflater, val parent: ViewGroup?): ViewTaskByIDViewClassInterface {

    private val viewTaskByIDBinding: ViewTaskByIdFragmentBinding = ViewTaskByIdFragmentBinding.inflate(inflater, parent, false)
    private val root: View = viewTaskByIDBinding.root
    private lateinit var listener: ViewTaskByIDViewClassInterface.ViewTaskByIDViewClassListener
    private lateinit var fragmentListener: ViewTaskByIDViewClassInterface.ViewTaskByIDViewClassFragmentListener
    private val viewTasksByIDToolbar: Toolbar = viewTaskByIDBinding.viewTasksByIDToolbar
    private val titleTextView: TextView = viewTaskByIDBinding.TitleTextView
    private val location: TextView = viewTaskByIDBinding.locationTextView
    private val startDateTextView: TextView = viewTaskByIDBinding.startDateTextView
    private val startTimeTextView: TextView = viewTaskByIDBinding.startTimeTextView
    private val endDateTextView: TextView = viewTaskByIDBinding.endDateTextView
    private val endTimeTextView: TextView = viewTaskByIDBinding.endTimeTextView
    private val descriptionTextView: TextView = viewTaskByIDBinding.descriptionTextView
    private lateinit var taskID: String
    private val formatter: DateTimeFormatter = ProjectDateTimeUtils.getCustomDateFormatter()

   override fun setupToolbar(){
       with(viewTasksByIDToolbar){
           setNavigationIcon(R.drawable.ic_close_white_24dp)
           title = ""
           setNavigationOnClickListener { fragmentListener.dismissDialogFragment() }
           setOnMenuItemClickListener {
               when(it.itemId){
                   R.id.edit_task -> {
                       fragmentListener.launchEditTaskFragment()
                       true
                   }
                   else ->{false}
               }
           }
       }
   }

    override fun populateTextViews(task: TaskPresentationModel){
        with(task){
            this@ViewTaskByIDViewClass.taskID = taskID
            titleTextView.text = taskName
            location.text = taskLocation
            startDateTextView.text = taskStartDate.format(formatter)
            startTimeTextView.text = taskStartTime.toString()
            endDateTextView.text = taskEndDate.format(formatter)
            endTimeTextView.text = taskEndTime.toString()
            descriptionTextView.text = taskDescription
        }
    }

    override fun getRoot():View = this.root

    override  fun setFragmentListener(fragmentListener: ViewTaskByIDViewClassInterface.ViewTaskByIDViewClassFragmentListener){
        this.fragmentListener = fragmentListener
       }

}
