package com.fyp.kweku.cbtoganisation.tasks.data.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/* These Nigrations are used to Allow Room to Migrate to a new Database Version.
*
* They Describe how  the Databse has changed and the version change*/

object MIGRATION_1_2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE tasks ADD COLUMN taskCompleted INTEGER NOT NULL  DEFAULT 0 "
        )
    }
}

object MIGRATION_2_3 : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE VIEW `MonthCalendarDatabaseView` AS SELECT taskID FROM tasks")
        }
    }

object MIGRATION_3_4 : Migration(3,4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("BEGIN TRANSACTION")
        database.execSQL(
            "CREATE TEMPORARY TABLE IF NOT EXISTS tasks_backup(taskID, taskName ,taskLocation,taskDescription,taskStartDate,taskEndDate,taskStartTime,taskEndTime)"
        )
        database.execSQL("INSERT INTO tasks_backup SELECT taskID, taskName ,taskLocation,taskDescription,taskStartDate,taskEndDate,taskStartTime,taskEndTime FROM tasks")
        database.execSQL("Drop TABLE tasks")
        database.execSQL("CREATE TABLE IF NOT EXISTS `tasks` (`taskID` TEXT NOT NULL, `taskName` TEXT NOT NULL, `taskLocation` TEXT NOT NULL, `taskDescription` TEXT NOT NULL, `taskStartDate` TEXT NOT NULL, `taskEndDate` TEXT NOT NULL, `taskStartTime` TEXT NOT NULL, `taskEndTime` TEXT NOT NULL, PRIMARY KEY(`taskID`))")
        database.execSQL("INSERT INTO tasks SELECT taskID, taskName ,taskLocation,taskDescription,taskStartDate,taskEndDate,taskStartTime,taskEndTime FROM tasks_backup")
        database.execSQL("DROP TABLE tasks_backup")
        database.execSQL("COMMIT")
    }
}

    object MIGRATION_4_5 : Migration(4,5){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("DROP VIEW  MonthCalendarDatabaseView")
        }
    }






