package com.example.zhaozhen_countbook;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.name;

/**
 * Created by CrackCrack on 2017-10-01.
 */

public class Counter {
    private String Name;
    private Date date;
    private String Comment;
    private int InitialValue;
    private int CurrentValue;

    public Counter( String Name, String Comment, int InitialValue){
        this.Name = Name;
        this.date = new Date();
        this.Comment = Comment;
        this.InitialValue = InitialValue;
        this.CurrentValue = InitialValue;
    }

    public String getName(){
        return Name;
    }

    public String getComment(){
        return Comment;
    }

    public int getInitialValue(){
        return InitialValue;
    }

    public int getCurrentValue(){
        return CurrentValue;
    }

    public void setName(String Name){
        this.Name = Name;
    }

    public void setComment(String Comment){
        this.Comment = Comment;
    }

    public void setInitialValue(int InitialValue){
        this.InitialValue = InitialValue;
    }

    public void  setCurrentValue(int CurrentValue){
        this.CurrentValue = CurrentValue;
    }

    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "Name: "+Name + "\nCurrent Count: " + Integer.toString(InitialValue) + "\nComment: " + getComment() + "\nDate:" + df.format(date);
    }

}
