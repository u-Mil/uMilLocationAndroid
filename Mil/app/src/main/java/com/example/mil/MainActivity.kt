package com.example.mil

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.model.LatLng
import java.security.Permissions
import java.util.jar.Manifest

class   MainActivity : AppCompatActivity() {

    private lateinit var mainbtn:Button
    private lateinit var mainListView:ListView
    private lateinit var itnt:Intent
    //sqlite
    private lateinit var dbHelper:DBHelper
    lateinit var database:SQLiteDatabase
    val REQUEST_PERMISSION_CODE = 1
    //permission
    val PERMISSONS = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //actionbar
        setSupportActionBar(findViewById(R.id.toolbar))
        //permission
        if(!hasPermissions()) ActivityCompat.requestPermissions(this, PERMISSONS, REQUEST_PERMISSION_CODE)

        mainbtn = findViewById(R.id.main_button_1)
        mainbtn.setOnClickListener(btnListener)
        mainListView = findViewById(R.id.main_list_view)

        initDBHelper()
        sqliteTest()
        listItemInput()
        setListViewListener(list_listener)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }
    //permission
    private fun hasPermissions():Boolean{
        for(permission in PERMISSONS){
            if(ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                return false
            }
        }
        return true
    }
    //button listener object
    private val btnListener = View.OnClickListener { v ->
        if(v?.id == R.id.main_button_1){
            itnt = Intent(this, MainActivity2::class.java)
            var loc = MyLocation(35.837608, 128.557634)
            itnt.putExtra("Location", loc)
            startActivity(itnt)
        }
//        itnt = Intent(this, MainActivity3::class.java)
//        startActivity(itnt)
    }
    //listview listener object
    //AdapterView<?> parent, View v, Int position, Long id
    private val list_listener = AdapterView.OnItemClickListener{ parent, view, position, id ->
        var mainListViewItem:ListViewItem = parent.getAdapter().getItem(position) as ListViewItem
        Toast.makeText(this, mainListViewItem.address, Toast.LENGTH_SHORT).show()
    }

    //sqlite
    private fun initDBHelper(){
        dbHelper = DBHelper(this, "mildb.db", null, 2)
    }

    private fun sqliteTest() {
        database = dbHelper.writableDatabase
        database.execSQL("INSERT INTO `a` values ('hello sqlite')")
        database = dbHelper.readableDatabase
        var cursor = database.rawQuery("SELECT * FROM 'a'", null)
        cursor.moveToFirst()
        println("cursor count: " +cursor.count)
        /*
        for(i in 0 .. cursor.count-1) {
            val dbstr = cursor.getString(0)
            cursor.moveToNext()
        }
        */
        cursor.close()
        database.close()
    }

    //list view data input
    private fun listItemInput(){
        val itemList = mutableListOf<ListViewItem>()
        var item = ListViewItem(getDrawable(R.drawable.ch), "title", "address")
        itemList.add(item)
        item = ListViewItem(getDrawable(R.drawable.ch), "asdfadfasfasdfasdfasd", "asdfasdasfasdfasdfasdfasdfsda")
        for(i in 0..2) {
            itemList.add(item)
        }
        mainListView.adapter = MyAdapter(itemList)
    }
    private fun setListViewListener(listener:AdapterView.OnItemClickListener){
        mainListView.onItemClickListener = listener
    }
}