package com.fyp.kweku.cbtoganisation.tasks.presentation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewtasks.ViewTasksContract
import org.koin.android.ext.android.inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ViewTasksFragment : Fragment(), ViewTasksContract.View {

    override val presenter by inject<ViewTasksContract.Presenter>()


    override fun showTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_tasks, container, false)
    }


}
