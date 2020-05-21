package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.SQL.DatabaseHelper;


public class EditItemMenu extends AppCompatActivity {

    EditText Ename, Edesc, Equantity;
    String name, desc, quantity;
    String Oname, Odesc, Oquantity;
    int position;
    Button save;
    DatabaseHelper myDB;

    int ID;
    String Sname, Sdesc;
    int Sqty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        ID = i.getIntExtra("ID",-1);
        Sname = i.getStringExtra("SNAME");
        Sdesc = i.getStringExtra("SDESC");
        Sqty = i.getIntExtra("SQTY", -1);

//        Oname=items.get(position).getName();
//        Odesc=items.get(position).getDescription();
//        Oquantity=items.get(position).getQuantity()+"";


        Ename = findViewById(R.id.edit_name);
        Edesc = findViewById(R.id.edit_desc);
        Equantity = findViewById(R.id.edit_quantity);
        save = findViewById(R.id.edit_submit);
        myDB = new DatabaseHelper(this);

        Ename.setText(Sname);
        Edesc.setText(Sdesc);
        Equantity.setText(String.valueOf(Sqty));

        save.setOnClickListener(new View.OnClickListener(){



            @Override
            public void onClick(View view) {
                name = Ename.getText().toString();
                desc = Edesc.getText().toString();
                quantity = Equantity.getText().toString();


                if (name.equals("")) {
                    Toast.makeText(EditItemMenu.this, "Item's name cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (desc.equals("")) {
                    Toast.makeText(EditItemMenu.this, "Please input item description", Toast.LENGTH_SHORT).show();
                } else if (quantity.equals("")) {
                    Toast.makeText(EditItemMenu.this, "Please input item quantity", Toast.LENGTH_SHORT).show();
                }else{

                    myDB.updateItem(ID, name, desc, Integer.parseInt(quantity));


//                    items.get(position).setName(name);
//                    items.get(position).setDescription(desc);
//                    items.get(position).setQuantity(Integer.parseInt(quantity));
                    Toast.makeText(EditItemMenu.this, "Item editted successfully", Toast.LENGTH_LONG).show();
                   Intent i = new Intent(EditItemMenu.this, MainActivity.class);
                   MainActivity.items.clear();
                   startActivity(i);
                    finish();

                }
            }
        });
    }
}
