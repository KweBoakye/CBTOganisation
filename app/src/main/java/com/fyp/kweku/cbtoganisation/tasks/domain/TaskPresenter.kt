package com.fyp.kweku.cbtoganisation.tasks.domain

import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate


class TaskPresenter {

     fun taskNamesListsChecker(taskNamesLists: MutableList<MutableList<String>>): MutableList<MutableList<String>>{
         var checkedTasksNamesList: MutableList<MutableList<String>> = mutableListOf()
         if (taskNamesLists.isEmpty()) {
            checkedTasksNamesList = MutableList(42){(MutableList(5){"" })}
        } else taskNamesLists.mapTo(checkedTasksNamesList){taskNames ->taskNamesChecker(taskNames)}
          return checkedTasksNamesList
    }


     fun taskNamesChecker(taskNames: MutableList<String>):MutableList<String>{
         var checkedTaskNames: MutableList<String> = mutableListOf()
         if (taskNames.isEmpty()){checkedTaskNames = MutableList(5){""}}
        else taskNames.mapTo(checkedTaskNames){taskName -> taskNameChecker(taskName)}
         return  checkedTaskNames
    }

     fun taskNameChecker(taskName: String?): String {
        return if (taskName.isNullOrBlank()) {""}
        else taskName
    }

    fun filterTasksByDate(taskList: MutableList<TaskPresentationModel>, date: LocalDate):MutableList<TaskPresentationModel>{

        return taskList.filter { taskPresentationModel ->
            ProjectDateTimeUtils.checkIfDateIsInRange(date, taskPresentationModel.taskStartDate, taskPresentationModel.taskEndDate)
        }.toMutableList()}

}