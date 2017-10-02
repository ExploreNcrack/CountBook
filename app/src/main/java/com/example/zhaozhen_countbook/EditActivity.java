package com.example.zhaozhen_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


// sub activity for editing existed info for a counter
public class EditActivity extends MainActivity {



    @Override
    //initialization
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();

        //getting references
        final int current_pos = intent.getIntExtra("position", 0);

        final EditText nameTxt = (EditText) findViewById(R.id.body1);

        final EditText InitialValue = (EditText) findViewById(R.id.body2);

        final EditText commentTxt = (EditText) findViewById(R.id.body3);

        final Button saveBtn = (Button) findViewById(R.id.saveBtn);

        final Button increaseBtn =  (Button) findViewById(R.id.increase);

        final Button decreaseBtn =   (Button) findViewById(R.id.decrease);

        final Button resetBtn =    (Button) findViewById(R.id.reset);

        final EditText CurrentValue = (EditText) findViewById(R.id.body4);


        // check clicks and handles

        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                int init=0;
                int current = 0;

                //check user input before saving the data for them

                if(!"".equals(InitialValue.getText().toString().trim()) && !"".equals(CurrentValue.getText().toString().trim())) {
                    init = Integer.parseInt(InitialValue.getText().toString());
                    current = Integer.parseInt(CurrentValue.getText().toString());

                    if (!"".equals(nameTxt.getText().toString().trim()) ) {

                        counter.get(current_pos).setCurrentValue(current);

                        counter.get(current_pos).setComment(commentTxt.getText().toString());

                        counter.get(current_pos).setInitialValue(init);

                        counter.get(current_pos).setName(nameTxt.getText().toString());

                        saveInFile();

                        finish();
                    }
                    else {Toast.makeText(getApplicationContext(), "InputError", Toast.LENGTH_SHORT).show();}
                }



                else if (!"".equals(InitialValue.getText().toString().trim()) && "".equals(CurrentValue.getText().toString().trim())) {

                    init = Integer.parseInt(InitialValue.getText().toString());

                    if (!"".equals(nameTxt.getText().toString().trim()) ) {

                        counter.get(current_pos).setCurrentValue(init);

                        counter.get(current_pos).setComment(commentTxt.getText().toString());

                        counter.get(current_pos).setName(nameTxt.getText().toString());

                        counter.get(current_pos).setInitialValue(init);

                        saveInFile();

                        finish();
                    }

                    else {Toast.makeText(getApplicationContext(), "InputError", Toast.LENGTH_SHORT).show();}

                }

                else {Toast.makeText(getApplicationContext(), "InputError", Toast.LENGTH_SHORT).show();}

            }



        });


        increaseBtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                int update = counter.get(current_pos).getCurrentValue() + 1;
                counter.get(current_pos).setCurrentValue(update);

                CurrentValue.setText(String.valueOf(counter.get(current_pos).getCurrentValue()));

                saveInFile();

            }
        });


        decreaseBtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                int update = counter.get(current_pos).getCurrentValue() - 1;
                counter.get(current_pos).setCurrentValue(update);

                CurrentValue.setText(String.valueOf(counter.get(current_pos).getCurrentValue()));

                saveInFile();

            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                int update = counter.get(current_pos).getInitialValue();
                counter.get(current_pos).setCurrentValue(update);

                CurrentValue.setText(String.valueOf(counter.get(current_pos).getCurrentValue()));

                saveInFile();

            }
        });





    }



    protected void onStart(){
        super.onStart();
        Intent intent = getIntent();
        int pos = intent.getIntExtra("position",0);


        //overwrite the edit text from datastructure
        
        final EditText nameTxt = (EditText) findViewById(R.id.body1);

        final EditText InitialValue = (EditText) findViewById(R.id.body2);

        final EditText CurrentValue = (EditText) findViewById(R.id.body4);

        final EditText commentTxt = (EditText) findViewById(R.id.body3);


        nameTxt.setText(counter.get(pos).getName());
        InitialValue.setText(String.valueOf(counter.get(pos).getInitialValue()));
        CurrentValue.setText(String.valueOf(counter.get(pos).getCurrentValue()));
        commentTxt.setText(counter.get(pos).getComment());


    }




}
