package com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel

data class TaskPresentationModel(
    val taskID: String,
    val taskName: String,       /*Denotes task Name*/
    val taskLocation: String,   /*Denotes Location Task will take place */
    val taskDescription: String,/*Description of the task*/
    val taskDate: String, /* Date task will take place*/
    val taskStartTime: String, /* Time task will start*/
    val taskEndTime: String /* Length/duration of task*/
)
