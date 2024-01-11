package com.example.geographicalprofilling.Admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.geographicalprofilling.Model.ArraylistData
import com.example.geographicalprofilling.Model.CommonData
import com.example.geographicalprofilling.R
import com.example.geographicalprofilling.toast

class AdapterForLoad(
    val context: Context,
    val array: ArrayList<String>,
   val  adapterg: ArrayList<ArraylistData>,
    val array1:ArrayList<CommonData>):RecyclerView.Adapter<AdapterForLoad.view>(){

    class view(item: View):RecyclerView.ViewHolder(item){
val text=item.findViewById<TextView>(R.id.namess)
        val spin=item.findViewById<Spinner>(R.id.spin)
        val edit=item.findViewById<EditText>(R.id.textss)
        val btn=item.findViewById<CheckBox>(R.id.carsh)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
        val view=LayoutInflater.from(context).inflate(R.layout.card,parent,false)
        return view(view)
    }

    override fun onBindViewHolder(holder: view, position: Int) {
     holder.text.text=array[position]
  val list= arrayListOf("Zone Level?","High","Medium","Low")
        val adapter=ArrayAdapter(context,android.R.layout.simple_dropdown_item_1line,list)
holder.spin.adapter=adapter
holder.btn.setOnClickListener {
    val type=holder.spin.selectedItem.toString()
    val data=holder.edit.text.toString()

    if(type=="Zone Level?"){
        Toast.makeText(context, "Select a typo", Toast.LENGTH_SHORT).show()
    }else if(data.isEmpty()){
        it.toast("Please Enter a Crime Details of Zones")
    }else{

if (holder.btn.isChecked){
    array1.add(CommonData(type,data))
    adapterg.add(ArraylistData(array1))
}else{
    array1.remove(CommonData(type,data))
    adapterg.remove(ArraylistData(array1))
}
    }
}

    }

    override fun getItemCount(): Int {
return array.size
    }
}