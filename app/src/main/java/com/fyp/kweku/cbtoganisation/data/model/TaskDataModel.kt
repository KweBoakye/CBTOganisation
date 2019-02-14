package com.fyp.kweku.cbtoganisation.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tasks")
data class TaskDataModel (@PrimaryKey
    val taskID: Int,
    val taskName: String,       /*Denotes task Name*/
    val taskLocation: String,   /*Denotes Location Task will take place */
    val taskDescription: String,/*Description of the task*/
    val taskDate: String, /* Date task will take place*/
    val taskStartTime: String, /* Time task will start*/
    val taskLength: Int,  /* Length/duration of task*/
    val taskCategoryDataModel: TaskCategoryDataModel
)
