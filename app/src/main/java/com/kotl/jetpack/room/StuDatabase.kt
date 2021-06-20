/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kotl.jetpack.room

import android.content.Context
import android.util.Log
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StuDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var instance: StuDatabase? = null

        fun getInstance(context: Context): StuDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): StuDatabase {
            return Room.databaseBuilder(context, StuDatabase::class.java, "students_db")
                    .addCallback(
                            object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    Log.d("studatabase", "onCreate()")
                                }

                                override fun onOpen(db: SupportSQLiteDatabase) {
                                    super.onOpen(db)
                                    Log.d("studatabase", "onOpen()")
                                }
                            }
                    )
                    .build()
        }
    }
}
