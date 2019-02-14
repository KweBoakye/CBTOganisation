package com.fyp.kweku.cbtoganisation.domain.Model

data class Task(
    val taskID: Int,
    val taskName: String,       /*Denotes task Name*/
    val taskLocation: String,   /*Denotes Location Task will take place */
    val taskDescription: String,/*Description of the task*/
    val taskDate: String, /* Date task will take place*/
    val taskStartTime: String, /* Time task will start*/
    val taskLength: Int,  /* Length/duration of task*/
    val taskCategory: TaskCategory
)

