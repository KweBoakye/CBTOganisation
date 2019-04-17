package com.fyp.kweku.cbtoganisation.common.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object MIGRATION_1_2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE tasks ADD COLUMN taskCompleted INTEGER NOT NULL  DEFAULT 0 "
        )
    }
}
