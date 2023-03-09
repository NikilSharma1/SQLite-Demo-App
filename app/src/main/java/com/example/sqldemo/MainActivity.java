package com.example.sqldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sqldemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private ArrayList<ListDataModel> dataModelArrayList;
    private CustomAdapter madapter;
    private ActivityMainBinding binding;
    private LinearLayoutManager layoutManager;
    private String Column_name="Name";
    private String Column_name2="Age";
    private String users="users";
    private boolean firstTime=false; // it means firstTime viewAll button is pressed or not

    public void add(View view){
        //try{
            String name=String.valueOf(binding.editTextName.getText());
            int age=Integer.parseInt(String.valueOf(binding.editTextAge.getText()));
            boolean status=binding.activePersonal.isChecked();
            //Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, String.valueOf(status), Toast.LENGTH_SHORT).show();
            if(status){

                dataModelArrayList.add(new ListDataModel(name,age,binding.activePersonal.isChecked()));
                ContentValues values = new ContentValues();
                values.put(Column_name, name);
                values.put(Column_name2, age);

// Insert the new row, returning the primary key value of the new row
                database.insert(users, null, values);
                Log.i(" TRY CATCH ACTIVITY -> ",String.valueOf(dataModelArrayList.size()));
                //Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Person added", Toast.LENGTH_SHORT).show();
                if(firstTime){
                    madapter.notifyDataSetChanged();
                }
                binding.activePersonal.setChecked(false);
            }else{
                Toast.makeText(MainActivity.this, "Person is Not active", Toast.LENGTH_SHORT).show();
            }
//        }catch (Exception e){
//            Toast.makeText(MainActivity.this, "Wrong Details Entered", Toast.LENGTH_SHORT).show();
//            Log.i("info ", String.valueOf(e));
//        }
    }
    public void viewAll(View view){
        if(!firstTime) {
            firstTime=true;
            madapter=new CustomAdapter(getApplicationContext(),dataModelArrayList);
            binding.recyclerView.setAdapter(madapter);
        }
        else{
            Toast.makeText(getApplicationContext(),"Already visible",Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataModelArrayList=new ArrayList<>();

        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        //DataBase with Name 'Details' is Created
        database=this.openOrCreateDatabase("Details",MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS users(Name Varchar, Age INT(3))");
        Cursor c=database.rawQuery("SELECT * FROM USERS ",null);
        int namecolumnindex=c.getColumnIndex("Name");
        int agecolumnindex=c.getColumnIndex("Age");
        c.moveToFirst();
        while(!c.isAfterLast()){
            String name=c.getString(namecolumnindex);
            int age=c.getInt(agecolumnindex);
            dataModelArrayList.add(new ListDataModel(name,age,true));
            c.moveToNext();
        }
        c.close();
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add(view);
            }
        });

        binding.viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAll(view);
            }
        });


    }
}