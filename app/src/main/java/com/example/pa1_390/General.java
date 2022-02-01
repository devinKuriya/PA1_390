package com.example.pa1_390;

import android.text.TextUtils;

import java.util.ArrayList;

public class General {
    ArrayList<String> listNames,listButtons;


    public General()//Class was created to help keep Arraylists together
    {

        listNames=new ArrayList<>();
        listButtons=new ArrayList<>();

    }
    public ArrayList<String> getListNames() {
        return listNames;
    }

    public ArrayList<String> getListButtons() {
        return listButtons;
    }

    public void setList(String button, String name) //Called and then adds to list everytime a button is pressed
    {
        listButtons.add(button);
        listNames.add(name);

        }
    public void resetlists()//Delete the lists
    {
        listNames.clear();
        listButtons.clear();
    }
    public String getButtonasString()//Returns the list of buttons pressed in the form of a string
    {
        String joined= TextUtils.join(",",listButtons);
        return joined;
    }
    public String getNameasString()//Returns the list of button names of buttons pressed in the form of a string
    {
        String joined= TextUtils.join(",",listNames);
        return joined;
    }

}
