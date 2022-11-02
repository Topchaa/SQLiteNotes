package com.example.myapplication

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
@RequiresApi(Build.VERSION_CODES.O)


data class NotesModel (
    var id : Int ,
    var note:String = "",
    var date:String = getDate()



){
    companion object{


fun getDate():String{

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val current = LocalDateTime.now().format(formatter).toString()
    return current
}
}
}