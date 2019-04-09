package com.fyp.kweku.cbtoganisation.tasks.presentation

import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.PresentationModelMapper
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.*
import kotlin.coroutines.*
import org.threeten.bp.LocalDate

class TaskPresenter:TaskOutput {

    var allTasksList: MutableList<TaskPresentationModel> = mutableListOf()
    val modelMapper = PresentationModelMapper()
    var tasksByDay: MutableList<TaskPresentationModel> = mutableListOf()

    override suspend fun showAllTasks(tasks: List<Task>) {
        allTasksList = tasks.map { modelMapper.toEntity(it) }.toMutableList()
    }

    override fun showTask(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun postTasksByDay(date: LocalDate) {

        tasksByDay = allTasksList.filter { taskPresentationModel ->
            ProjectDateTimeUtils.checkIfDateIsInRange(
                date,
                taskPresentationModel.taskStartDate,
                taskPresentationModel.taskEndDate
            )
        }.toMutableList()
    }
     var reciever= main()

    fun main() = runBlocking<Unit> {
        // create a channel that produces numbers from 1 to 3 with 200ms delays between them
        val source = produce<Int> {
            println("Begin") // mark the beginning of this coroutine in output
            for (x in 1..3) {
                delay(200) // wait for 200ms
                send(x) // send number x to the channel
            }
        }
        // print elements from the source
        println("Elements:")

        //
    }

    fun aa() = runBlocking { source.consumeEach {
        // consume elements from it
        println(it)
    }
        // print elements from the source AGAIN
        println("Again:")
        source.consumeEach {
            // consume elements from it
            println(it)
        } }
}