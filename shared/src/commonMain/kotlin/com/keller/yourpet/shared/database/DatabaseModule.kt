package com.keller.yourpet.shared.database

import com.keller.yourpet.shared.data.MyDatabase
import com.squareup.sqldelight.db.SqlDriver

class DatabaseModule {
    fun createDataBase(driver: SqlDriver) = MyDatabase(driver)
}
