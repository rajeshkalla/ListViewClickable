package com.tecnics.listviewclickable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ServicesList extends AppCompatActivity {

    ArrayList<Object> ServicesList1 = new ArrayList<>();

    ArrayList<Object> ServicesList2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_list);

        ServicesList2.add("Hello");



        Intent thisIntent = getIntent();

        Bundle args = thisIntent.getBundleExtra("BUNDLE");

        ArrayList<Object> object = (ArrayList<Object>) args.getSerializable("ARRAYLIST");

        final ListView listView = findViewById(R.id.servicesListView);

        ArrayAdapter<Object> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, object);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Toast.makeText(getApplicationContext(), (String)listView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(ServicesList.this, ServicesList.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)ServicesList2);
                intent.putExtra("BUNDLE",args);
                startActivity(intent);


            }
        });
    }
}
