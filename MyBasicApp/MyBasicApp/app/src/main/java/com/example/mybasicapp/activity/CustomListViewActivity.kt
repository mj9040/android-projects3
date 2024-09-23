package com.example.mybasicapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ListView
import com.example.mybasicapp.adapter.CustomListAdapter
import com.example.mybasicapp.R

class CustomListViewActivity : AppCompatActivity() {


    private lateinit var arrayList:ArrayList<Model>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_list_view)

        supportActionBar!!.hide()

       val customList = findViewById<ListView>(R.id.customList)

        addItem()



       val myAdapter= CustomListAdapter(this,arrayList)

        customList.adapter=myAdapter

    }

    private fun addItem() {


       arrayList= ArrayList<Model>()

        val model = Model(R.drawable.rc,"Ktm Rc-200","Ktm")
        val model1 = Model(R.drawable.r15,"Yamaha R15","Yamaha")
        val model2 = Model(R.drawable.ns,"Pulsar Ns-200","Bajaj")
        val model3 = Model(R.drawable.ktm,"Duke-390","Ktm")
        val model4 = Model(R.drawable.bmw,"Bmw J10r","Bmw")
        val model5 = Model(R.drawable.rc,"Ktm Rc-200","Ktm")
        val model6 = Model(R.drawable.jawa,"Java Bobber","Jawa")
        val model7 = Model(R.drawable.ather,"Ather 450x","Ather")
        val model8 = Model(R.drawable.ninjah2,"Ninja H2r","Kawasaki")
        val model9 = Model(R.drawable.bullet,"Bullet 350","Royal Enfield")
        val model10 = Model(R.drawable.hayabusa,"Hayabusa","Hayabusa")
        val model11 = Model(R.drawable.rc,"Ktm Rc-200","Ktm")
        val model12 = Model(R.drawable.shine,"Shine","Honda")
        val model13 = Model(R.drawable.ktm,"Duke-390","Ktm")
        val model14 = Model(R.drawable.bmw,"Bmw J10r","Bmw")
        val model15 = Model(R.drawable.rc,"Ktm Rc-200","Ktm")

        arrayList.add(model)
        arrayList.add(model1)
        arrayList.add(model2)
        arrayList.add(model3)
        arrayList.add(model4)
        arrayList.add(model5)
        arrayList.add(model6)
        arrayList.add(model7)
        arrayList.add(model8)
        arrayList.add(model9)
        arrayList.add(model10)
        arrayList.add(model11)
        arrayList.add(model12)
        arrayList.add(model13)
        arrayList.add(model14)
        arrayList.add(model15)



    }
}




data class Model(var img:Int,var bikeName:String,var brand:String)
{
    override fun toString(): String {

        return "Model{" +
                "img=" + img +
                ", bikeName='" + bikeName + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}

//data class Bean(val name:String)
//{
//    override fun toString(): String {
//        return name
//    }
//}