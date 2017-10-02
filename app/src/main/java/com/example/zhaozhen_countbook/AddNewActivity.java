package com.example.zhaozhen_countbook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


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



                int init = Integer.parseInt(InitialValue.getText().toString() );

                counter.add (new Counter(nameTxt.getText().toString(), commentTxt.getText().toString(), init ));

                saveInFile();

                finish();
/*
                InitialValue.addTextChangedListener(new TextWatcher() {
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        checkInputs();
                    }

                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        checkInputs();
                    }

                    public void afterTextChanged(Editable s) { checkInputs(); }

                    private void checkInputs() {
                        if (nameTxt.getText().toString().matches("") || InitialValue.getText().toString().matches("")) {
                            saveBtn.setEnabled(false);
                        }else{
                            saveBtn.setEnabled(true);
                        }
                    }
                });
*/


               // saveInFile();
            }
        });


    }



}




