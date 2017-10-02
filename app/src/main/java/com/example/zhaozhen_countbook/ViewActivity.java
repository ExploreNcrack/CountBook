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


//sub Activity for just viewing all the existed content for a counter
public class ViewActivity extends MainActivity {



    @Override
    //initialization
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);




        final Button finish = (Button) findViewById(R.id.Finish_View); //getting referece for the finish button

        //check click and handle
        finish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

    }


    //initialization
    protected void onStart(){
        super.onStart();
        Intent intent = getIntent();
        int pos = intent.getIntExtra("position",0);


        //overwrite the edit text from datastructure and then display it out

        final TextView nameTxt = (TextView) findViewById(R.id.body1);

        final TextView InitialValue = (TextView) findViewById(R.id.body2);

        final TextView CurrentValue = (TextView) findViewById(R.id.body4);

        final TextView commentTxt = (TextView) findViewById(R.id.body3);



        nameTxt.setText(counter.get(pos).getName());
        InitialValue.setText(String.valueOf(counter.get(pos).getInitialValue()));
        CurrentValue.setText(String.valueOf(counter.get(pos).getCurrentValue()));
        commentTxt.setText(counter.get(pos).getComment());


    }




}
