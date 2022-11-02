package com.example.myapplication

import android.content.ContentValues
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    private lateinit var button : Button
    private lateinit var text : EditText
    private lateinit var button2 : Button
private lateinit var helper: DBhelper
private lateinit var recyclerView: RecyclerView
private var adapter:NotesAdapter ?= null
private var nts:NotesModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.btn)
        button2 = findViewById(R.id.btn2)


        text = findViewById(R.id.notetext)
        recyclerView = findViewById(R.id.listid)
        helper  =  DBhelper(this)
        var db  =  helper.readableDatabase
        initRecyclerView()

        button.setOnClickListener{

            addNote()
            getNotes()
        }
        button2.setOnClickListener{

            updateStudents()


        }


        getNotes()

        adapter?.setOnClickDeleteItem { deleteStudent(it.id) }
        adapter?.setOnClickItem {


text.setText(it.note)
  nts = it

        }
    }

fun initRecyclerView(){
recyclerView.layoutManager = LinearLayoutManager(this)
    adapter = NotesAdapter()
    recyclerView.adapter = adapter


}
    private fun addNote(){
       val txt = text.text.toString()
if (txt.isEmpty()){

    Toast.makeText(this,"write something!" , Toast.LENGTH_SHORT).show()

}else{
val status = helper.insert(txt)
if (status > -1){

Toast.makeText(this,"Note Added",Toast.LENGTH_SHORT).show()
    text.setText("")

}else{
    Toast.makeText(this,"Unable to add Note",Toast.LENGTH_SHORT).show()
}

}


    }

    private fun getNotes(){

val ntlist = helper.getAllNotes()
Log.e("pppp" , "${ntlist}")
adapter?.additems(ntlist)

    }
private fun deleteStudent(id:Int){

helper.deleteNoteById(id)
    getNotes()

}
private fun updateStudents(){
val note  = text.text.toString()
val date  = NotesModel.getDate()

    if (note == nts?.note){


    }else{
        if (nts == null)return
      val nts = NotesModel(id = nts!!.id, note = note , date = date)
        helper.updateNote(nts)
        text.setText("")
        getNotes()

    }

}




}