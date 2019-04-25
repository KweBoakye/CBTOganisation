package com.fyp.kweku.cbtoganisation.tasks.presentation

interface TaskPresenterInterface {

    fun taskNamesListsChecker(taskNamesLists: MutableList<MutableList<String>>): MutableList<MutableList<String>>
    fun taskNamesChecker(taskNames: MutableList<String>):MutableList<String>
    fun taskNameChecker(taskName: String?): String


}