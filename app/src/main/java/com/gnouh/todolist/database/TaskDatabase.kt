package com.gnouh.todolist.database

import android.app.Application
import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gnouh.todolist.models.Task

@Database(entities = [Task::class], version = 2, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDAO

    companion object {

        val migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE TASK_TABLE ADD COLUMN COMPLETE BOOLEAN")
                database.execSQL("ALTER TABLE TASK_TABLE ADD COLUMN DEL BOOLEAN")
            }
        }

        private var INSTANCE: TaskDatabase? = null
        fun getInstance(context: Application): TaskDatabase {
            if (INSTANCE != null) {
                return INSTANCE!!
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "TASK_DATABASE"
                )
                    .addMigrations(migration)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}