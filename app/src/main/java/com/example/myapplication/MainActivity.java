package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.myapplication.Adapter.ItemListAdapter;
import com.example.myapplication.Model.Item;
import com.example.myapplication.SQL.DatabaseHelper;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ItemListAdapter.OnItemListener {

    private RecyclerView recyclerView;
    private ItemListAdapter itemListAdapter;

    String name, desc, id;
    int quantity;
    DatabaseHelper myDB;
    public static ArrayList<Item> items = new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;

        recyclerView = findViewById(R.id.recyclerView);


        showItemList();
    }


    private void showItemList() {
        myDB = new DatabaseHelper(this);
        Cursor data = myDB.getListContents();

        if(data.getCount() == 0){
            Toast.makeText(MainActivity.this, "There's no data", Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                items.add(new Item(data.getString(0),data.getString(1),data.getInt(2),data.getString(3)));
            }
        }

        itemListAdapter = new ItemListAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            //Toast.makeText(this, "add selected", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, AddItemMenu.class);
            //startActivityForResult(i, 1);
            items.clear();
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position) {
        //insert new activity

        int index = position;
        //Bundle packet=getIntent().getExtras();

        //Toast.makeText(this, "add selected: "+position, Toast.LENGTH_SHORT).show();

        String nameE = items.get(position).getName();
        String descE = items.get(position).getDescription();
        int qtyE = items.get(position).getQuantity();
        Cursor data = myDB.getItemID(nameE);
        int itemID = -1;
        while(data.moveToNext()){
            itemID = data.getInt(0);
        }

        if(itemID > -1){
            Log.d("111", "onItemClick: "+itemID);
            Intent i = new Intent(this, EditItemMenu.class);
             i.putExtra("ID", itemID);
            i.putExtra("SNAME", nameE);
            i.putExtra("SDESC", descE);
            i.putExtra("SQTY", qtyE);
            startActivity(i);


        }else{
            Log.d("111", "not working: "+itemID);
        }


//        Intent i = new Intent(this, EditItemMenu.class);
//        i.putExtra("Index", index);
//        startActivity(i);
//        itemListAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 1){
            itemListAdapter.onActivityResult(requestCode,1);
        }
    }
}
