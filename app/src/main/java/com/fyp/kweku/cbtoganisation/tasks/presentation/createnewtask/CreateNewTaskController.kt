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
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CreateNewTaskController @Inject constructor(val createNewTaskInteractorInterface: CreateNewTaskInteractorInterface): CreateNewTaskViewClassInterface.CreateNewTaskListener
    {


        override fun stringValid(string: String): Boolean{
            return string.isNotEmpty()
        }

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
        if (!input.isEmpty()) {
            val taskID = createNewTaskInteractorInterface.generateTaskID()
            val task = Task(taskID,input[0],input[1],input[2],
                LocalDate.parse(input[3],ProjectDateTimeUtils.getCustomDateFormatter()),
                LocalDate.parse(input[4],ProjectDateTimeUtils.getCustomDateFormatter()),
                LocalTime.parse(input[5]),
                LocalTime.parse(input[6]))
            createNewTaskInteractorInterface.SendTaskToDataLayer(task)
            Timber.i("persisttask called")}
    }


        fun updateStartDate(date: LocalDate) = createNewTaskViewClassInterface.updateStartDate(date)
        fun updateEndDate(date: LocalDate) = createNewTaskViewClassInterface.updateEndDate(date)
        fun updateStartTime(time:LocalTime) = createNewTaskViewClassInterface.updateStartTime(time)
        fun updateEndTime(time:LocalTime) = createNewTaskViewClassInterface.updateEndTime(time)



    fun onStart(){createNewTaskViewClassInterface.setListener(this) }



    fun onStop(){createNewTaskViewClassInterface.setListener(null)}


}
