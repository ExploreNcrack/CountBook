package com.example.zhaozhen_countbook;

import java.text.SimpleDateFormat;
import java.util.Date;


//class for the data structure use in this app

public class Counter {

    // setting up all the attributes that are needed for the app
    private String Name;
    private Date date;
    private String Comment;
    private int InitialValue;
    private int CurrentValue;


    //passing in info when object creates
    public Counter( String Name, String Comment, int InitialValue){
        this.Name = Name;
        this.date = new Date();
        this.Comment = Comment;
        this.InitialValue = InitialValue;
        this.CurrentValue = InitialValue;
    }

    // methods that are needed for operation in the app

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


    //control the display for the listview
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "Name: "+Name + "\nCurrent Count: " + Integer.toString(CurrentValue) + "\nDate:" + df.format(date);
    }

}
