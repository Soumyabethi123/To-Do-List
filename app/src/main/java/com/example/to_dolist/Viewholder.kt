package com.example.to_dolist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView){

    var listPositionTextView = itemView.findViewById<TextView>(R.id.textView)

    var listTitleTextView = itemView.findViewById<TextView>(R.id.textView3)


}