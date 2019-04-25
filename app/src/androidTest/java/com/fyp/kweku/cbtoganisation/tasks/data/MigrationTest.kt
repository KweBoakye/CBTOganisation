package com.fyp.kweku.cbtoganisation.tasks.data


import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fyp.kweku.cbtoganisation.tasks.data.migrations.MIGRATION_1_2
import org.junit.Test
import com.fyp.kweku.cbtoganisation.tasks.data.AppRoomDatabase
import com.fyp.kweku.cbtoganisation.tasks.data.migrations.MIGRATION_2_3
import com.fyp.kweku.cbtoganisation.tasks.data.migrations.MIGRATION_3_4
import com.fyp.kweku.cbtoganisation.tasks.data.migrations.MIGRATION_4_5
import org.junit.Before


import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MigrationTest {
    companion object {
        private const val TEST_DB = "App_database"
        private const val TAG = "test_db"
    }

    lateinit var db: SupportSQLiteDatabase


    @get:Rule
   private lateinit var  helper: MigrationTestHelper

            @Before
            fun init() {
                helper = MigrationTestHelper(
                    InstrumentationRegistry.getInstrumentation(),
                    "com.fyp.kweku.cbtoganisation.tasks.data.appRoomDatabase",
                    FrameworkSQLiteOpenHelperFactory()
                )
            }

    @Test
    @Throws(IOException::class)
    fun migrate1To2() {
        db = helper.createDatabase(TEST_DB, 1).apply {
            // db has schema version 1. insert some data using SQL queries.
            // You cannot use DAO classes because they expect the latest schema.
            execSQL(
                "INSERT INTO tasks (taskID,taskName, taskLocation,taskDescription, taskStartDate,TaskEndDate,taskStartTime,taskEndTime) " +
                        "VALUES (\"testID\", \"TestName\", \"Test Location\", \"Test Description\", \"12/03/2002\", \"16/07/2002\", \"10:00\", \"12:00\")"
            )

            // Prepare for the next version.
            close()
        }

        // Re-open the database with version 2 and provide
        // MIGRATION_1_2 as the migration process.
        db = helper.runMigrationsAndValidate(
            TEST_DB, 2, true,
            MIGRATION_1_2
        )
    }

        @Test
        @Throws(IOException::class)
        fun migrate2To3() {
            db = helper.createDatabase(TEST_DB, 2).apply {
                // db has schema version 1. insert some data using SQL queries.
                // You cannot use DAO classes because they expect the latest schema.
                execSQL(
                    "INSERT INTO tasks (taskID,taskName, taskLocation,taskDescription, taskStartDate,TaskEndDate,taskStartTime,taskEndTime, taskCompleted) " +
                            "VALUES (\"testID\", \"TestName\", \"Test Location\", \"Test Description\", \"12/03/2002\", \"16/07/2002\", \"10:00\", \"12:00\",\"0\")"
                )

                // Prepare for the next version.
                close()

                db = helper.runMigrationsAndValidate(
                    TEST_DB, 3, true,
                    MIGRATION_2_3
                )

            }
        }

        @Test
        @Throws(IOException::class)
                fun migrate3To4(){
            db = helper.createDatabase(TEST_DB, 3).apply {
                // db has schema version 1. insert some data using SQL queries.
                // You cannot use DAO classes because they expect the latest schema.
                execSQL("INSERT INTO tasks (taskID,taskName, taskLocation,taskDescription, taskStartDate,TaskEndDate,taskStartTime,taskEndTime, taskCompleted) " +
                        "VALUES (\"testID\", \"TestName\", \"Test Location\", \"Test Description\", \"12/03/2002\", \"16/07/2002\", \"10:00\", \"12:00\",\"0\")")

                // Prepare for the next version.
                close()
            }

            db = helper.runMigrationsAndValidate(TEST_DB, 4, true,
                MIGRATION_3_4
            )
    }

    @Test
    @Throws(IOException::class)
    fun migrate4To5() {
        db = helper.createDatabase(TEST_DB, 4).apply {
            // db has schema version 1. insert some data using SQL queries.
            // You cannot use DAO classes because they expect the latest schema.
            execSQL(
                "INSERT INTO tasks (taskID,taskName, taskLocation,taskDescription, taskStartDate,TaskEndDate,taskStartTime,taskEndTime) " +
                        "VALUES (\"testID\", \"TestName\", \"Test Location\", \"Test Description\", \"12/03/2002\", \"16/07/2002\", \"10:00\", \"12:00\")"
            )

            // Prepare for the next version.
            close()
        }
        db = helper.runMigrationsAndValidate(
            TEST_DB, 5, true,
            MIGRATION_4_5
        )
    }
}
