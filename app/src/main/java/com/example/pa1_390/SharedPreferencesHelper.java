package com.example.pa1_390;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class SharedPreferencesHelper {
    private SharedPreferences sharedPreferences;
    ArrayList<String> listButtons;

    public SharedPreferencesHelper(Context context)//constructor
    {
        sharedPreferences=context.getSharedPreferences("ButtonPreferences",Context.MODE_PRIVATE);
    }

    public void saveProfileName(String key,String name)//save any button name
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key,name);
        editor.commit();
    }
    public String getProfileName(String key)//get any button name
    {
        return sharedPreferences.getString(key,"");
    }
    public void saveLimit(String key,int limit)//save limit
    {
        if(limit>5 && limit<200)
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt(key,limit);
            editor.commit();
        }
    }
    public int getlimit(String key)//Get Limit
    {
        return sharedPreferences.getInt(key,100);
    }
    public void savecount(String key,int count)//Save individual counter values
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(key,count);
        editor.commit();
    }
    public void savetotal(String key)//Save total counter value
    {
        int result=0;
        result=sharedPreferences.getInt("counter1",0)+sharedPreferences.getInt("counter2",0)+sharedPreferences.getInt("counter3",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(key,result);
        editor.commit();
    }
    public int getCount(String key)//Get any counter value
    {
        return sharedPreferences.getInt(key,0);
    }
    public void reset()
    {
        sharedPreferences.edit().clear().commit();
    }




}
