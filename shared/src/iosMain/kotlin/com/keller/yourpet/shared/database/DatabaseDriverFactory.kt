package com.keller.yourpet.shared.database

import com.keller.yourpet.shared.data.MyDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver = NativeSqliteDriver(MyDatabase.Schema, "test.db")
}
