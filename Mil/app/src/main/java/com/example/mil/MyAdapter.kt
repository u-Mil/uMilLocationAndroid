package com.example.mil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(private val items: MutableList<ListViewItem>):BaseAdapter(){
    override fun getCount(): Int = items.size

    override fun getItem(position: Int): ListViewItem = items[position]

    override fun getItemId(position: Int): Long = position.toLong()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View? = convertView
        if(convertView == null){
            //?
            view = LayoutInflater.from(parent?.context).inflate(R.layout.main_lv_item, parent, false)
        }
        val image = view?.findViewById<ImageView>(R.id.main_list_image)
        val title = view?.findViewById<TextView>(R.id.main_list_title)
        val address = view?.findViewById<TextView>(R.id.main_list_address)

        val data = items[position]
        image?.setImageDrawable(data.icon)
        title?.text = data.title
        address?.text = data.address

        return view!!
    }
}