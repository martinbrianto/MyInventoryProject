package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Model.Item;
import com.example.myapplication.SQL.DatabaseHelper;



public class AddItemMenu extends AppCompatActivity {

    EditText ETname, ETdesc, ETquantity;
    String name, desc;
    String quantity, ID;
    Button btnSubmit;
    int jumlahData;
    DatabaseHelper myDB;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_add_item_menu);
        ETname = findViewById(R.id.et_name);
        ETdesc = findViewById(R.id.et_desc);
        ETquantity = findViewById(R.id.et_quantity);
        btnSubmit = findViewById(R.id.btn_submit);
        myDB = new DatabaseHelper(this);
        context = AddItemMenu.this;

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                name = ETname.getText().toString();
                desc = ETdesc.getText().toString();
                quantity = ETquantity.getText().toString();

                if (name.trim().equals("")) {
                    Toast.makeText(AddItemMenu.this, "Item's name cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (desc.trim().equals("")) {
                    Toast.makeText(AddItemMenu.this, "Please input item description", Toast.LENGTH_SHORT).show();
                } else if (quantity.trim().equals("")) {
                    Toast.makeText(AddItemMenu.this, "Please input item quantity", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddItemMenu.this, "Item added successfully", Toast.LENGTH_LONG).show();
//                    jumlahData = items.size() + 1;
//                    if (jumlahData < 10) {
//                        ID = "ID00" + jumlahData;
//                    } else if (jumlahData < 100) {
//                        ID = "ID0" + jumlahData;
//                    } else if (jumlahData >= 100) {
//                        ID = "ID" + jumlahData;
//                    } else if (jumlahData == 1000) {
//                        Toast.makeText(AddItemMenu.this, "Can't add more item", Toast.LENGTH_LONG).show();
//                    }

                    AddData(name, desc, Integer.parseInt(quantity));
                    //items.add(new Item(ID, name, Integer.parseInt(quantity), desc));
                    Intent i = new Intent(context, MainActivity.class);
                    MainActivity.items.clear();
                    startActivity(i);
                    finish();
                    }
            }
        });

    }

    public void AddData(String newName, String newDesc, int newQty){
        boolean insertData = myDB.addData(newName, newQty, newDesc);

        if(insertData == true){
            Toast.makeText(AddItemMenu.this, "Item added successfully", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(AddItemMenu.this, "Item add failed", Toast.LENGTH_LONG).show();
        }

    }

}







