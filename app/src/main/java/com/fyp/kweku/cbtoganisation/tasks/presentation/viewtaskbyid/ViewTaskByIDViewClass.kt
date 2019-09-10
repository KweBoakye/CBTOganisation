package com.fyp.kweku.cbtoganisation.tasks.presentation.viewtaskbyid

import android.view.LayoutInflater
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.databinding.ViewTaskByIdFragmentBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.utils.CircularRevealAnimationUtilClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber

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
    private  lateinit var editButton:View
    private var pressX: Int =0
    private var pressY:Int = 0
    private lateinit var revealSettings: CircularRevealAnimationUtilClass.RevealAnimationSetting
    val scope: CoroutineScope = CoroutineScope(Job() + Dispatchers.Main )


    private val editButtonTouchHandler: View.OnTouchListener = View.OnTouchListener { v, event ->
        if (event != null) {


            when(event.action){

                ACTION_DOWN->{
                    val x = event.rawX.toInt()
                    val y = event.rawY.toInt()
                    createRevealSettings(x,y)
                }
                ACTION_UP -> {
                    v.performClick()
                    fragmentListener.launchEditTaskFragment(revealSettings)
                    }
            }
        }
        true
    }


   override fun setupToolbar(){
       with(viewTasksByIDToolbar){
           val editMenuItem =  R.id.edit_task
           editButton = findViewById(editMenuItem)
           editButton.setOnTouchListener(editButtonTouchHandler)
           setNavigationIcon(R.drawable.ic_close_white_24dp)
           title = ""
           setNavigationOnClickListener { fragmentListener.dismissDialogFragment() }
           setOnMenuItemClickListener {

               when(it.itemId){
                   editMenuItem -> {
                       true
                   }
                   else ->{false}
               }
           }
       }
   }

    private fun createRevealSettings(x:Int, y: Int){
        Timber.i("$x,$y")
        revealSettings = CircularRevealAnimationUtilClass.RevealAnimationSetting(
           x,
            y,
            root.width,
            root.height
        )
    }

    override fun getRevealSettings():CircularRevealAnimationUtilClass.RevealAnimationSetting{
        return revealSettings
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
