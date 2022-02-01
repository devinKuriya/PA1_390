package com.example.pa1_390;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar myToolbar;
    EditText Comp1Name, Comp2Name, Comp3Name, MaxCount;
    Button savebutton;
    protected SharedPreferencesHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_activity);

        //Setting up the tool bar
        myToolbar = (Toolbar) findViewById(R.id.settingtoolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Getting each edittext field to be able to change button/limit values
        Comp1Name = (EditText) findViewById(R.id.Comp1_inputname);
        Comp2Name = (EditText) findViewById(R.id.Comp2_inputname);
        Comp3Name = (EditText) findViewById(R.id.Comp3_inputname);
        MaxCount = (EditText) findViewById(R.id.Maxcount_input);
        savebutton = (Button) findViewById(R.id.save_button);
        savebutton.setOnClickListener(SaveOnClickListener);

        //Setting up shared preferences
        helper = new SharedPreferencesHelper(this);

        DefaultMode(); //setting display mode

        //Back Arrow
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }


    protected View.OnClickListener SaveOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean temp=SaveData();
            if(temp==true)//Pass if values entered pass checks
            {
                DefaultMode();//Function to set fields back to default
            }
        }
    };

    private boolean SaveData() {
        if(Checkfields()==true)//Save if values entered pass checks
        {
            helper.saveProfileName("Comp1", Comp1Name.getText().toString());
            helper.saveProfileName("Comp2", Comp2Name.getText().toString());
            helper.saveProfileName("Comp3", Comp3Name.getText().toString());
            helper.saveLimit("limit", Integer.parseInt(MaxCount.getText().toString()));
        }

        return Checkfields();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {//Switch case for different options in the action menu
            case R.id.Edit:
                Comp1Name.setEnabled(true);
                Comp2Name.setEnabled(true);
                Comp3Name.setEnabled(true);
                MaxCount.setEnabled(true);
                savebutton.setVisibility(savebutton.VISIBLE);
                break;
            case R.id.View:
                Comp1Name.setEnabled(false);
                Comp2Name.setEnabled(false);
                Comp3Name.setEnabled(false);
                MaxCount.setEnabled(false);
                savebutton.setVisibility(savebutton.INVISIBLE);
                break;
        }
        return true;
    }

    private void DefaultMode()//Default mode is Display mode
    {
        Comp1Name.setEnabled(false);
        Comp2Name.setEnabled(false);
        Comp3Name.setEnabled(false);
        MaxCount.setEnabled(false);
        savebutton.setVisibility(savebutton.INVISIBLE);
    }

    private boolean Checkfields()//Check if values entered meet given limits
    {
        int a=0;
        //Try catch because app will crash if value entered for limit is not a number without it
        try {
            if(Integer.parseInt(MaxCount.getText().toString())<5 || Integer.parseInt(MaxCount.getText().toString())>200)
            {
                Toast.makeText(this,"Error MaxCount: Must be a number between 5 and 200(Info not saved)",Toast.LENGTH_SHORT).show();
                a=1;

            }
        }
        catch(Exception e)
        {
            Toast.makeText(this,"Error MaxCount: Must be a number between 5 and 200(Info not saved)",Toast.LENGTH_SHORT).show();
            a=1;
        }

        //Check that names are only letters and spaces-->Otherwise toast message appears
       if(onlyLettersSpaces(Comp1Name.getText().toString())==false || Comp1Name.getText().length()>20)
       {
           Toast.makeText(this,"Error Comp1: Must be alphabetical characters and spaces with 20 char max(Info not saved)",Toast.LENGTH_SHORT).show();
           a=1;

       }
        if(onlyLettersSpaces(Comp2Name.getText().toString())==false || Comp2Name.getText().length()>20)
        {
            Toast.makeText(this,"Error Comp2:Must be alphabetical characters and spaces with 20 char max(Info not saved)",Toast.LENGTH_SHORT).show();
            a=1;

        }
        if(onlyLettersSpaces(Comp3Name.getText().toString())==false || Comp3Name.getText().length()>20)
        {
            Toast.makeText(this,"Error Comp3:Must be alphabetical characters and spaces with 20 char max(Info not saved)",Toast.LENGTH_SHORT).show();
            a=1;

        }

        //Our return so we know if Check was passed or not
        if(a==0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    public static boolean onlyLettersSpaces(String name)//Check names are only letters or spaces
    {
        int length=name.length();//String length
        char temp;//temp value
        for(int i=0;i<length;i++)//for loop to go through the name given
        {
            temp= name.charAt(i);
            if (Character.isLetter(temp) || temp == ' ')
            {
                continue;
            }
            return false;//will only reach if if statement is not true
        }
        return true;
    }

}