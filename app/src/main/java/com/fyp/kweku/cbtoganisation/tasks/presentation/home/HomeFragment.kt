package com.fyp.kweku.cbtoganisation.tasks.presentation.home


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview.mvc.HorizontalCalendarController
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview.mvc.HorizontalCalendarViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview.mvc.HorizontalCalendarViewClassInterface
import org.koin.android.ext.android.get


class HomeFragment : Fragment() {

    private lateinit var taskActivity: TaskActivity
    private lateinit var homeController: HomeController
    private lateinit var horizontalCalendarController: HorizontalCalendarController

    override fun onAttach(context: Context) {
        super.onAttach(context)

        taskActivity = context as TaskActivity

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        /*binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root*/
        val homeViewClassInterface: HomeViewClassInterface = HomeViewClass(layoutInflater, container)
        val horizontalCalendarViewClassInterface: HorizontalCalendarViewClassInterface = HorizontalCalendarViewClass(this.context!!, homeViewClassInterface.getRootView())
        homeController = get()
        homeController.bindView(homeViewClassInterface)
        homeController.onCreateView(taskActivity)
        horizontalCalendarController = get()
        horizontalCalendarController.bindView(horizontalCalendarViewClassInterface)
        horizontalCalendarController.setControllerAsHorizontalCalendarViewClassListener()
        horizontalCalendarController.initHorizontalCalendar()

        return homeViewClassInterface.getRootView()
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