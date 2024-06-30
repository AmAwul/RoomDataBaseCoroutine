package com.awul.roomdatabasecoroutine

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.awul.roomdatabasecoroutine.databinding.ActivityMainBinding
import com.awul.roomdatabasecoroutine.room.App
import com.awul.roomdatabasecoroutine.room.PersonModel
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var bainding : ActivityMainBinding
    private var pList: ArrayList<PersonModel> = arrayListOf()
    private var adapter = PersonAdapter(pList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bainding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bainding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initAdapter()



        bainding.fabHome.setOnClickListener{
            FabDialog(this)
        }

        loadData()






    }//onCreate

    fun initAdapter(){
        bainding.recyclerHome.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        bainding.recyclerHome.adapter = adapter

        adapter.setOnEditClick {

        }
        adapter.setonDeleteClick {

        }
        adapter.setonMoreClick {

        }


    }//initAdapter

    fun loadData(){
        var  allData = App.db.personDao().getAllPerson()
        pList.clear()
        pList.addAll(allData)
        adapter.notifyDataSetChanged()

        Log.d("TAG22", "loadData: $allData")
    }//loadData


    fun FabDialog(context: Context?) {
        val dialog = Dialog(context!!)
        dialog.setContentView(R.layout.item_add_dialog)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val cancel_btn = dialog.findViewById<Button>(R.id.cancel_btn)
        val add_btn = dialog.findViewById<Button>(R.id.add_btn)

        val edt_name = dialog.findViewById<TextInputEditText>(R.id.edt_name)
        val edt_phone = dialog.findViewById<TextInputEditText>(R.id.edt_phone)

        cancel_btn.setOnClickListener { v1: View? -> dialog.dismiss() }

        add_btn.setOnClickListener {
            val p_name = edt_name.text.toString()
            val phone = edt_phone.text.toString()
            if (p_name.isEmpty()) {
                edt_name.error = "Products Name is Required"
                edt_name.requestFocus()
            } else if (phone.isEmpty()) {
                edt_phone.error = "Generic Name is Required"
                edt_phone.requestFocus()
            } else {
                val personModel = PersonModel(0,p_name,phone)
                App.db.personDao().addPerson(personModel)

                loadData()
                dialog.dismiss()
                Toast.makeText(context, "Successfully Added New Person", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }




}