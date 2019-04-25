package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.outerrecyclerview.MonthCalendarOuterController
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.outerrecyclerview.MonthCalendarOuterViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.outerrecyclerview.MonthCalendarOuterViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.koin.android.ext.android.get


class MonthCalendarFragment : Fragment() {


    private lateinit var monthCalendarController: MonthCalendarOuterController
    private lateinit var root: View

    override fun onAttach(context: Context) {
        super.onAttach(context)
        monthCalendarController = get()
        monthCalendarController.loadAllTasksForRecycler()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val monthCalendarViewClassInterface: MonthCalendarViewClassInterface = MonthCalendarViewClass(inflater,container)
        root = monthCalendarViewClassInterface.getRootView()

        val monthCalendarOuterViewClassInterface: MonthCalendarOuterViewClassInterface =
            MonthCalendarOuterViewClass(container , root)

        monthCalendarController.bindView(monthCalendarOuterViewClassInterface)
        monthCalendarController.setControllerAsMonthCalendarViewClassListener()
        monthCalendarController.initMonthCalender()
        

        val monthCalendarTasksByDayLiveDataObserver = Observer<MutableList<MutableList<TaskPresentationModel>>> { taskNameLists -> monthCalendarOuterViewClassInterface.setChildTaskNamesLists(taskNameLists) }

reConvertedMonthCalendarTasksByDayLiveData().observe(
    this,
    monthCalendarTasksByDayLiveDataObserver
)
       // reConvertedMonthCalendarTasksByDayLiveData().observe(this, Observer { taskLists -> monthCalendarViewClassInterface.setTaskLists(taskLists) })

        return root
    }

    private fun reConvertedMonthCalendarTasksByDayLiveData(): LiveData<MutableList<MutableList<TaskPresentationModel>>> {
        @Suppress("UNCHECKED_CAST")
        return   ( monthCalendarController.getTasksInteractorInterface.getMonthCalendarTasksByDayAsAny() as LiveData<MutableList<MutableList<TaskPresentationModel>>>)
    }

    private fun reConvertedMonthCalendarTaskNamesByDayLiveData(): LiveData<MutableList<MutableList<String>>> {
        @Suppress("UNCHECKED_CAST")
        return   ( monthCalendarController.getTasksInteractorInterface.getMonthCalendarTaskNamesByDayAsAny() as LiveData<MutableList<MutableList<String>>>)
    }


}
