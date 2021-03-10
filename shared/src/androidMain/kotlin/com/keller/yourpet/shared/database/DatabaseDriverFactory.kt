package com.keller.yourpet.shared.database

import android.content.Context
import com.keller.yourpet.shared.data.MyDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(MyDatabase.Schema, context, "test.db")
}
