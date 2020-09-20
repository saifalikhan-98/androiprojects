package com.example.appyhightask.db

import androidx.room.TypeConverter
import com.example.appyhightask.models.Source

class typeConverters {


    @TypeConverter
    fun fromSource(source: Source):String{
        return source.name
    }
    @TypeConverter
    fun toSource(name:String): Source {
        return Source(name,name)
    }
}