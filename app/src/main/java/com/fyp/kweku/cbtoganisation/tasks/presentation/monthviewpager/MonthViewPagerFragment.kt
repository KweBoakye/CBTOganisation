package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.koin.android.ext.android.get


class MonthViewPagerFragment : Fragment() {
    private lateinit var taskActivity: TaskActivity

    private lateinit var monthViewPagerController:MonthViewPagerController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskActivity = context as TaskActivity
        monthViewPagerController = get()
        monthViewPagerController.loadAllTasksForRecycler()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val monthViewPagerViewClassInterface: MonthViewPagerViewClassInterface = MonthViewPagerViewClass(inflater, container,this)
        with(monthViewPagerViewClassInterface){
            setToolbar(taskActivity)
            setListener(monthViewPagerController)
        }
        with(monthViewPagerController){
            bindView(monthViewPagerViewClassInterface)
            initViewPager()
        }

        return  monthViewPagerViewClassInterface.getRoot()
    }

    private fun reConvertedMonthCalendarTasksByDayLiveData(): LiveData<MutableList<MutableList<TaskPresentationModel>>> {
        @Suppress("UNCHECKED_CAST")
        return   (  monthViewPagerController.getTasksInteractorInterface.getMonthCalendarTasksByDayAsAny() as LiveData<MutableList<MutableList<TaskPresentationModel>>>)
    }


    /*fun createViewPagerAdapter(): RecyclerView.Adapter<*> {
        val dates = monthViewPagerController.setCalendarRange()
        return object : FragmentStatePagerAdapter(this) {

            override fun getItem(position: Int): MonthCalendarFragment{

                val month = dates[position]
                return MonthCalendarFragment.create(month.toString())
            }
            override fun getItemCount(): Int = dates.size

           // override fun getItemId(position: Int): Long = dates.itemId(position)

        }
    }*/
}



