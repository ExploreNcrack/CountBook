package com.example.zhaozhen_countbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 *Root Activity class for the main page display activity
 */

public class MainActivity extends AppCompatActivity {


    public static final String FILENAME = "file.sav";  //file destination to save arraylist data for the user

    private int LongClickedItemIndex;   //pos of click

    private ListView view_list;  // reference for the listview in MainActivity xml

    public ArrayList<Counter> counter = new ArrayList<Counter>(); //data structure for the user data info and the listview display content

    public ArrayAdapter<Counter>adapter;  //adapter in Counter type for transfering data structure info to the xml display



    @Override
    /**
     * first method to call for initialzing the main activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.add);   // getting reference to the add button in xml
        view_list = (ListView) findViewById(R.id.listView_content);  //getting reference to the listview in xml

        //check button click
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewActivity.class);
                startActivity(intent);
            }
        });


        //register for menu initialization
        registerForContextMenu(view_list);

        //check long click and find position
        view_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                LongClickedItemIndex=position;
                return false;
            }
        });


    }



    @Override
    //menu setting
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Menu");
        menu.add(0, v.getId(), 0, "Edit info");
        menu.add(0, v.getId(), 0, "View info");
        menu.add(0, v.getId(), 0, "Delete");
    }



    @Override
    //handle click menu activity
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        if (item.getTitle().equals("Edit info")) {
            Toast.makeText(this, "Editing", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, EditActivity.class);
            i.putExtra("position", position);
            startActivity(i);
        }
        else if (item.getTitle() == "Delete") {
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            counter.remove(LongClickedItemIndex);
            adapter= new ArrayAdapter<Counter>(this,android.R.layout.simple_list_item_1, counter);
            view_list.setAdapter(adapter);
            saveInFile();
        }

        else if (item.getTitle() == "View info") {
            Toast.makeText(this, "Viewing", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, ViewActivity.class);
            i.putExtra("position", position);
            startActivity(i);
        }

        else {
            return false;
        }
        return true;
    }






    @Override
    //initializing
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();

        adapter = new ArrayAdapter<Counter>(this,android.R.layout.simple_list_item_1, counter);
        view_list.setAdapter(adapter);


    }




    //load file method
    public void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Counter>>() {}.getType();
            counter = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            counter = new ArrayList<Counter>();
        }
    }

    //save file method
    public void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(counter, writer);
            writer.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }


}
