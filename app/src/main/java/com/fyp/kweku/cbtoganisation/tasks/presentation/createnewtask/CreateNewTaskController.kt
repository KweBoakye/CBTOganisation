package com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask


import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.CreateNewTaskInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class CreateNewTaskController(val createNewTaskInteractorInterface: CreateNewTaskInteractorInterface): CreateNewTaskViewClassInterface.CreateNewTaskListener
    {

        private lateinit var createNewTaskViewClassInterface: CreateNewTaskViewClassInterface
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

        override fun onSaveNewTaskButtonClick(input: Array<String>) {
            persistTask(input)
        }


        fun bindView(createNewTaskViewClassInterface: CreateNewTaskViewClassInterface) {
        this.createNewTaskViewClassInterface = createNewTaskViewClassInterface
    }

    fun persistTask(input: Array<String>) = scope.launch(Dispatchers.IO){
        if (input.isEmpty()){}
        else{
        val taskID = createNewTaskInteractorInterface.generateTaskID()
        val task = Task(taskID,input[0],input[1],input[2],
            LocalDate.parse(input[3],ProjectDateTimeUtils.getCustomDateFormatter()),
            LocalDate.parse(input[4],ProjectDateTimeUtils.getCustomDateFormatter()),
            LocalTime.parse(input[5]),
            LocalTime.parse(input[6]))
        createNewTaskInteractorInterface.SendTaskToDataLayer(task)
        Timber.i("persisttask called")}
    }



    fun onStart(){createNewTaskViewClassInterface.setListener(this) }



    fun onStop(){createNewTaskViewClassInterface.setListener(null)}


}
