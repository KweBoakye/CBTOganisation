package com.fyp.kweku.cbtoganisation.tasks.data.model
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "tasks")
data class TaskDataModel (@PrimaryKey
    val taskID: String,
    val taskName: String,       /*Denotes task Name*/
    val taskLocation: String,   /*Denotes Location Task will take place */
    val taskDescription: String,/*Description of the task*/
    val taskDate: String, /* Date task will take place*/
    val taskStartTime: String, /* Time task will start*/
    val taskLength: Int /* Length/duration of task*/
)