package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(){
private var ntsList:ArrayList<NotesModel> = ArrayList()
private var onCLickDeleteItem:((NotesModel)-> Unit)? = null
private var onClickItem:((NotesModel)-> Unit) ?= null

    fun additems(items : ArrayList<NotesModel>){

this.ntsList = items
notifyDataSetChanged()

    }
fun setOnClickItem(callback: (NotesModel) -> Unit){

    this.onClickItem = callback

}
fun setOnClickDeleteItem(callback:(NotesModel)->Unit){
    this.onCLickDeleteItem = callback
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

     var view =  LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent,false)
        return NotesViewHolder(view)

    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        val nts  = ntsList[position]
        holder.bindView(nts)
        holder.delete.setOnClickListener{onCLickDeleteItem?.invoke(nts)}
         holder.itemView.setOnClickListener{onClickItem?.invoke(nts)}
    }

    override fun getItemCount(): Int {
       return ntsList.size
    }


    class NotesViewHolder( view:View) : RecyclerView.ViewHolder(view){

private var id = view.findViewById<TextView>(R.id.ids)
        private var note = view.findViewById<TextView>(R.id.notetext)
        private var date = view.findViewById<TextView>(R.id.date)
      var delete = view.findViewById<TextView>(R.id.deletebtn)
      fun bindView(nts: NotesModel){
           note.text = nts.note
           date.text = nts.date
           id.text = nts.id.toString()
      }

    }


}