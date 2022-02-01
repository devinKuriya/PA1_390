package com.example.pa1_390;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//Devin Kuriya 40111954
//COEN/ELEC390 Programming Assignment 1

public class MainActivity extends AppCompatActivity {
    Button buttondata, buttonsettings, buttonA, buttonB, buttonC,reset;
    TextView total_count;
    int limit;
    General generalvalues;
    protected SharedPreferencesHelper helper ;


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generalvalues=new General();//General Class is used to create our array lists-->was used to store data before sharedpreferecnes was implemented


        //Initializing buttons
        buttondata = (Button) findViewById(R.id.buttonData);
        buttonsettings = (Button) findViewById(R.id.buttonSettings);
        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        total_count = (TextView) findViewById(R.id.total_count);
        reset=(Button) findViewById(R.id.Reset);

        //Setting Button Functions
        buttondata.setOnClickListener(DataOnClickListener);
        buttonsettings.setOnClickListener(SettingsClickListener);
        buttonA.setOnClickListener(countAOnClickListener);
        buttonB.setOnClickListener(countBOnClickListener);
        buttonC.setOnClickListener(countCOnClickListener);
        reset.setOnClickListener(ResetClickListener);
        reset.setText("Reset");

        //Initialize helper-->reused in other functions
        helper=new SharedPreferencesHelper(this);

        total_count.setText("The count is " +helper.getCount("total"));


    }

    @Override
    protected void onResume() {
        super.onResume();
        //Set button names and limit after onpause(), or if values are already stored from previous use
        total_count.setText("The count is " +helper.getCount("total"));
        buttonA.setText(helper.getProfileName("Comp1"));
        buttonB.setText(helper.getProfileName("Comp2"));
        buttonC.setText(helper.getProfileName("Comp3"));
        limit=helper.getlimit("limit");
    }

    protected View.OnClickListener DataOnClickListener = new View.OnClickListener() //Creates new Data Activity when called
    {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(MainActivity.this, DataActivity.class);//Create new intent for Data Activity
            intent.putExtra("ButtonList",generalvalues.getListButtons());//Passing ArrayList of Counter Names
            intent.putExtra("NameList",generalvalues.getListNames());//Passing ArrayList of Names
            startActivity(intent);
        }
    };
    protected View.OnClickListener SettingsClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            gotosettings();//function
        }
    };
    protected View.OnClickListener countAOnClickListener = new View.OnClickListener()//Counter function for button A
    {
        @Override
        public void onClick(View view) {
            generalbuttonclick("counter1","Comp1","Counter1");
        }
    };
    protected View.OnClickListener countBOnClickListener = new View.OnClickListener()//Counter function for button B
    {
        @Override
        public void onClick(View view) {
           generalbuttonclick("counter2","Comp2","Counter2");


        }
    };
    protected View.OnClickListener countCOnClickListener = new View.OnClickListener()//Counter function for button C
    {
        @Override
        public void onClick(View view) {
            generalbuttonclick("counter3","Comp3","Counter3");

        }
    };
    private void gotosettings()//Creates new Setting Activity when called
    {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);//Create new Settings Actvity
        startActivity(intent);
    }
    protected View.OnClickListener ResetClickListener = new View.OnClickListener()//Reset Values on click
    {
        @Override
        public void onClick(View view) {
            resetvalues();
            Toast.makeText(MainActivity.this, "Names, counters, and lists have been reset", Toast.LENGTH_SHORT).show();
        }
    };
    private void resetvalues()//Reset all values
    {
        helper.reset();
        buttonA.setText(helper.getProfileName("Comp1"));
        buttonB.setText(helper.getProfileName("Comp2"));
        buttonC.setText(helper.getProfileName("Comp3"));
        total_count.setText("The count is " +helper.getCount("total"));
        limit=helper.getlimit("limit");
        generalvalues.resetlists();

    }

    private void generalbuttonclick(String buttonname,String compname,String counter)
    {

        if(buttonA.getText()==""||buttonB.getText()==""||buttonC.getText()=="")
        {
            gotosettings();
        }
        else
        {
            if(helper.getCount("total")<limit)
            {

                helper.savecount(buttonname,helper.getCount(buttonname)+1);//save individual incremented counter
                helper.savetotal("total");//save total counter
                generalvalues.setList(counter,helper.getProfileName(compname));//add button to list of buttons pushed
                helper.saveProfileName("buttons",generalvalues.getButtonasString());//save list of buttons as a string
                helper.saveProfileName("names",generalvalues.getNameasString());//save list of button names as a string
            }
            total_count.setText("The count is " +helper.getCount("total"));//update textview
        }
    }

}