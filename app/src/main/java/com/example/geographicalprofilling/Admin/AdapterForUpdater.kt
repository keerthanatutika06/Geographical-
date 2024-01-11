package com.example.geographicalprofilling.Admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.geographicalprofilling.Model.Location
import com.example.geographicalprofilling.R
import com.example.geographicalprofilling.Responses.CommonResponse
import com.example.geographicalprofilling.Retrofit.RetrofitIn
import com.example.geographicalprofilling.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterForUpdater (val context: Context,val list:ArrayList<Location>):RecyclerView.Adapter<AdapterForUpdater.viewed>(){
    class viewed(item: View):RecyclerView.ViewHolder(item){
        val btn=item.findViewById<Button>(R.id.btnd)
        val check=item.findViewById<CheckBox>(R.id.carsh)
        val namess=item.findViewById<TextView>(R.id.namess)
        val spin=item.findViewById<Spinner>(R.id.spin)
        val textss=item.findViewById<EditText>(R.id.textss)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewed {
        val view=LayoutInflater.from(context).inflate(R.layout.card,parent,false)
        return viewed(view)
    }

    override fun onBindViewHolder(holder: viewed, position: Int) {
        holder.btn.isVisible=true
        holder.check.isVisible=false
        holder.namess.text=list[position].location
        holder.textss.setText(list[position].record)

        val arr= arrayListOf("High","Medium","Low")
        val array=ArrayAdapter(context,android.R.layout.simple_dropdown_item_1line,arr)
        holder.spin.adapter=array
var num=0
for (item in arr){
    if(item==list[position].zone){
        holder.spin.setSelection(num)
    }
    num++
}
        holder.btn.setOnClickListener{
        val selected=holder.spin.selectedItem.toString()
            val edit=holder.textss.text.toString()
            if(edit.trim().isEmpty()){
                it.toast("Write Somthing About the Area")
            }else {
                updatecard(selected,edit,list[position].id)
            }}
    }

    private fun updatecard(selected: String, edit: String, id: Int) {
CoroutineScope(IO).launch {
    RetrofitIn.instance.updatingPlaces(id,edit,selected).enqueue(object :Callback<CommonResponse>{
        override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
            Toast.makeText(context, response.body()!!.message, Toast.LENGTH_SHORT).show()
        }
        override fun onFailure(call: Call<CommonResponse>, t: Throwable) {

            Toast.makeText(context,"${t.message}", Toast.LENGTH_SHORT).show()
        }
    })
}
    }

    override fun getItemCount(): Int {
    return list.size
    }
}