package com.tecnics.listviewclickable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    private String service;

    String URL_TO_REQUEST_SERVICES = "http://192.241.244.177/Give/GetServices.php?service_id=0";


    public ArrayList<Object> object1 = new ArrayList<>();


    public ArrayList<Object> object2 = new ArrayList<>();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button servicesBtn = findViewById(R.id.servicesButton);

        servicesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                object1.add("Testing");
                object2 = getServices();
//                object2 = (ArrayList<Object>) object1.clone();
                Log.d("Testing", object2.toString());
                getServices();

            }
        });


    }


    public ArrayList<Object> getServices() {


        final ArrayList<Object> servicesList = new ArrayList<>();


        RequestParams requestParams = new RequestParams();

        requestParams.put("service_id", 0);


        AsyncHttpClient client = new AsyncHttpClient();

        client.get(URL_TO_REQUEST_SERVICES, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray responseString) {


                super.onSuccess(statusCode, headers, responseString);


                try {
                    for (int counter = 0; counter < responseString.length(); counter++)
                    {

                        service = responseString.get(counter).toString();
                        JSONObject json = new JSONObject(service);
                        servicesList.add(json.get("serviceName").toString());
                    }

                    Intent intent = new Intent(MainActivity.this, ServicesList.class);
                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST", servicesList);
                    intent.putExtra("BUNDLE", args);
                    startActivity(intent);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {

                Log.e("Give", "Fail" + e.toString());
                Log.d("Give", "Status code " +   statusCode);
                Toast.makeText(getApplicationContext(), "Poor internet connection!", Toast.LENGTH_SHORT).show();

            }

        });

        return servicesList;
    }

}
