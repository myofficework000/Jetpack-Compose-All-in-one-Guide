package com.example.jetpack_compose_all_in_one.features.profile_update

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val migration1To2 = object: Migration(1, 2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE profile ADD COLUMN age INTEGER NOT NULL DEFAULT 0")
    }
}