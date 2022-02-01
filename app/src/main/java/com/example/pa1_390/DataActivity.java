package com.example.pa1_390;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DataActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar myToolbar;
    ListView listView;
    ArrayAdapter arrayNames,arrayButtons;
    TextView counter1,counter2,counter3,total;
    SharedPreferencesHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper=new SharedPreferencesHelper(this);
        setContentView(R.layout.activity_data);

        //Toolbar setup
        myToolbar = (Toolbar) findViewById(R.id.datatoolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("Data Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //TextView setup
        counter1=(TextView)findViewById(R.id.counter1);
        counter2=(TextView)findViewById(R.id.counter2);
        counter3=(TextView)findViewById(R.id.counter3);
        total=(TextView)findViewById(R.id.total);


        //Setting up list view/getting arraylists
        listView=(ListView) findViewById(R.id.listview);
        //ArrayList<String> listNames = (ArrayList<String>) getIntent().getSerializableExtra("NameList");
        //ArrayList<String> listButtons = (ArrayList<String>) getIntent().getSerializableExtra("ButtonList");
        ArrayList<String> listNames;
        String[] temp1=helper.getProfileName("names").split(",");
        List<String> names = Arrays.asList(temp1);
        listNames= new ArrayList<String>(names);


        ArrayList<String> listButtons;
        String[] temp2=helper.getProfileName("buttons").split(",");
        List<String> buttons = Arrays.asList(temp2);
        listButtons= new ArrayList<String>(buttons);



        listView.setAdapter(arrayNames);
        arrayNames=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listNames);
        arrayButtons=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listButtons);




        //Setting button default values
        counter1.setText( helper.getProfileName("Comp1")+" : "+helper.getCount("counter1"));
        counter2.setText( helper.getProfileName("Comp2")+" : "+helper.getCount("counter2"));
        counter3.setText( helper.getProfileName("Comp3")+" : "+helper.getCount("counter3"));
        total.setText("Total Events: "+helper.getCount("total"));
        listView.setAdapter(arrayNames);


        //Back Arrow
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override//Setting our action menu
    public boolean onCreateOptionsMenu(Menu menu)//Create action menu
    {
        getMenuInflater().inflate(R.menu.datamenu, menu);
        return true;
    }

    @Override//Switch case with both options
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {//Switch case for different action menu options(doesn't affect listview)
            case R.id.Buttons:
                listView.setAdapter(arrayButtons);
                counter1.setText("Counter 1:"+helper.getCount("counter1"));
                counter2.setText("Counter 2:"+helper.getCount("counter2"));
                counter3.setText("Counter 3:"+helper.getCount("counter3"));
                total.setText("Total Events: "+helper.getCount("total"));
                break;
            case R.id.Names:
                listView.setAdapter(arrayNames);
                counter1.setText( helper.getProfileName("Comp1")+" : "+helper.getCount("counter1"));
                counter2.setText( helper.getProfileName("Comp2")+" : "+helper.getCount("counter2"));
                counter3.setText( helper.getProfileName("Comp3")+" : "+helper.getCount("counter3"));
                total.setText("Total Events: "+helper.getCount("total"));
                break;
        }
        return true;
    }
}