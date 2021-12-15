package com.example.mil

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) :SQLiteOpenHelper(context, name, factory, version){
    override fun onCreate(db: SQLiteDatabase?) {

        var sql:String
        /*
        sql = "CREATE TABLE IF NOT EXISTS `list` ("+
            "`_image`	TEXT,"+
            "`_title`	TEXT,"+
            "`_address`	TEXT,"+
            "`_range`	INTEGER,"+
            "`_loc_lat`	TEXT,"+
            "`_loc_lon`	TEXT )"

         */
        sql = "CREATE TABLE IF NOT EXISTS `a` (`name` TEXT )"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //sql

    }
}