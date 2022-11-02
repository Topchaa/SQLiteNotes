package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
@RequiresApi(Build.VERSION_CODES.O)
class DBhelper(context: Context) : SQLiteOpenHelper(context , DATABASE_NAME , null , 1){
    companion object{

   private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME=  "NOTESDB"
        private const val TABLE_NAME = "NOTES"
        private const val ID = "id"
        private const val NOTE = "note"
        private const val DATE = "date"




    }


    override fun onCreate(db: SQLiteDatabase?) {

        val createquery = ( "CREATE TABLE " + TABLE_NAME + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + NOTE +" TEXT  ,"+ DATE+" TEXT)    " )

 db?.execSQL(createquery)


    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


    fun insert(txt: String): Long{
        val nt = NotesModel.getDate()
        val db  = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(NOTE , txt)
        contentValues.put(DATE , nt)
        val success = db.insert(TABLE_NAME , null , contentValues)
        db.close()

        return success
    }


  fun getAllNotes(): ArrayList<NotesModel>{
    val ntList: ArrayList<NotesModel> = ArrayList()
    val selectquery = "SELECT * FROM $TABLE_NAME"
val db  = this.readableDatabase
val cursor : Cursor
cursor = db.rawQuery(selectquery ,null)

var id: Int
var note: String
var date: String

if (cursor.moveToFirst()) {

do {
    var index1  =  cursor.getColumnIndex(ID)
    var index2  =  cursor.getColumnIndex(NOTE)
    var index3  =  cursor.getColumnIndex(DATE)
    id = cursor.getInt(index1)
    note = cursor.getString(index2)
    date  =  cursor.getString(index3)

    val nts = NotesModel(id = id , note = note , date = date)
    ntList.add(nts)

}while (cursor.moveToNext())


}

      return ntList


}

    fun deleteNoteById(id:Int):Int{

val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID,id)
val success = db.delete(TABLE_NAME , "id = $id" , null)
        db.close()
        return success
    }



    fun updateNote(nts:NotesModel): Int{


        val db  = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NOTE , nts.note)
        contentValues.put(DATE , nts.date)
val success = db.update(TABLE_NAME , contentValues , "id = "+nts.id , null)
db.close()
return success
    }


  }



