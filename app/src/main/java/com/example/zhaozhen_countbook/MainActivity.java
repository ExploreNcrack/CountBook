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

public class MainActivity extends AppCompatActivity {

    public static final String FILENAME = "file.sav";

    int LongClickedItemIndex;

    public ListView view_list;
    public ArrayList<Counter> counter = new ArrayList<Counter>();
    public ArrayAdapter<Counter>adapter;
    //private ArrayList<tweet> tweets = new ArrayList<tweet>();
    //private ArrayAdapter<tweet> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.add);
        view_list = (ListView) findViewById(R.id.listView_content);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewActivity.class);
                startActivity(intent);
            }
        });


        registerForContextMenu(view_list);


        view_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                LongClickedItemIndex=position;
                return false;
            }
        });


    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Menu");
        menu.add(0, v.getId(), 0, "Edit info");
        menu.add(0, v.getId(), 0, "View info");
        menu.add(0, v.getId(), 0, "Delete");
    }



    @Override
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
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();

        adapter = new ArrayAdapter<Counter>(this,android.R.layout.simple_list_item_1, counter);
        view_list.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

    }





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
