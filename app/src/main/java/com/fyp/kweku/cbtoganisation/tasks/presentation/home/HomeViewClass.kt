package com.fyp.kweku.cbtoganisation.tasks.presentation.home

import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.FragmentHomeBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeViewClass(val inflater: LayoutInflater, val parent: ViewGroup?,val homeFragmentListener: HomeViewClassInterface.HomeFragmentListener):HomeViewClassInterface {



    private var binding: FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, parent, false)
    private var rootView = binding.root
    private var listener: HomeViewClassInterface.HomeListener? = null
    private lateinit var taskActivity: TaskActivity
    private lateinit var toolbar: Toolbar
    private val homeCoordinatorLayout: CoordinatorLayout = binding.homeCoordinatorLayout
    private val goToCreateNewTaskFragmentButton: FloatingActionButton = binding.goToCreateNewTaskFragmentButton
    private val motionLayout = binding.motionLayout
    private var calendarRecyclerVisible: Boolean = true
    private val openClose = binding.openclose
    val closeAnimator: ObjectAnimator = ObjectAnimator.ofFloat(openClose, "rotation", 0f, 180f).setDuration(500)
    val openAnimator: ObjectAnimator = ObjectAnimator.ofFloat(openClose, "rotation", 180f, 360f).setDuration(500)

    init{
        setMotionLayoutTranstitioListener()

    }

    private fun setMotionLayoutTranstitioListener(){
        motionLayout.setTransitionListener(
            object : MotionLayout.TransitionListener {
                override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                }
                override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                }
                override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                    calendarRecyclerVisible = !calendarRecyclerVisible
                }
                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                    if (calendarRecyclerVisible) closeAnimator.start()
                    else openAnimator.start()
                }
            }
        )
    }

    override fun getRootView(): View {
        return this.rootView
    }

    override fun gethomeCoordinatorLayout():CoordinatorLayout = this.homeCoordinatorLayout

   override fun setToolbar(){
        this.toolbar = taskActivity.toolbar
    }

   override fun setGoToCreateNewTaskFragmentButtonOnClickListener(){
        goToCreateNewTaskFragmentButton.setOnClickListener {homeFragmentListener.onGoToCreateNewTaskFragmentButtonClicked(getTaskActivity()) }
    }

    override fun setListener(listener: HomeViewClassInterface.HomeListener) {
        this.listener = listener
    }

   override fun setTaskActivity(taskActivity: TaskActivity){
        this.taskActivity = taskActivity
   }

    override fun getTaskActivity():TaskActivity{
        return this.taskActivity
    }
}