package com.example.zhaozhen_countbook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddNewActivity extends MainActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        final EditText nameTxt = (EditText) findViewById(R.id.body1);

        final EditText InitialValue = (EditText) findViewById(R.id.body2);

        final EditText commentTxt = (EditText) findViewById(R.id.body3);

        final Button saveBtn = (Button) findViewById(R.id.saveBtn);


        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                int init=0;

                if(!"".equals(InitialValue.getText().toString().trim())) {
                    init = Integer.parseInt(InitialValue.getText().toString());


                    if (!"".equals(nameTxt.getText().toString().trim()) ) {
                        counter.add(new Counter(nameTxt.getText().toString(), commentTxt.getText().toString(), init));

                        saveInFile();

                        finish();
                    }
                    else Toast.makeText(getApplicationContext(), "InputError", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(), "InputError", Toast.LENGTH_SHORT).show();

            }
        });


    }



}




