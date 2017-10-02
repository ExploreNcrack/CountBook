package com.example.zhaozhen_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends MainActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);




        final Button finish = (Button) findViewById(R.id.Finish_View);


        finish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

    }



    protected void onStart(){
        super.onStart();
        Intent intent = getIntent();
        int pos = intent.getIntExtra("MyClass",0);


        final TextView nameTxt = (TextView) findViewById(R.id.body1);

        final TextView InitialValue = (TextView) findViewById(R.id.body2);

        final TextView CurrentValue = (TextView) findViewById(R.id.body4);

        final TextView commentTxt = (TextView) findViewById(R.id.body3);

        //final Button saveBtn = (Button) findViewById(R.id.saveBtn);

        nameTxt.setText(counter.get(pos).getName());
        InitialValue.setText(String.valueOf(counter.get(pos).getInitialValue()));
        CurrentValue.setText(String.valueOf(counter.get(pos).getCurrentValue()));
        commentTxt.setText(counter.get(pos).getComment());

        //ArrayAdapter<TheSize> adapter= new TheSizeListAdapter();
        //sizeListView.setAdapter(adapter);
    }




}
