package com.example.chanti.text;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



/**
 * Created by chanti on 30-Jan-16.
 **/
public class Home extends AppCompatActivity {

    TextView displayText, displayWeather, displayTempMax, displayTempMin, displayPressure, displayHumidity;
    String response,temp, temp_min, pressure, temp_max, humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        //displayText = (TextView) findViewById(R.id.display);
        displayWeather = (TextView) findViewById(R.id.display2);
        displayTempMax = (TextView) findViewById(R.id.display3);
        displayTempMin = (TextView) findViewById(R.id.display4);
        displayPressure = (TextView) findViewById(R.id.display5);
        displayHumidity= (TextView) findViewById(R.id.display6);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_logout){
            Intent i = new Intent(Home.this, MainActivity.class);
            startActivity(i);
            finish();
        }else if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public String kelvinToFahreinheit(Integer temperature)
    {
        String s = null;
        Double i;
            i = ((temperature*9)/5 - 459.67);
        s = String.format("%.3f",i).toString();
        return s;
    }

    public void weather(View v) {
        if (v.getId() == R.id.weather) {
            String zipCode = ((EditText) findViewById(R.id.zip)).getText().toString();
            final String API_KEY = "a441269177776e803e0aa5df30b2748e";
            final String urlText = "http://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + ",us&appid=" + API_KEY;

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(urlText);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setDoInput(true);
                        urlConnection.setDoOutput(true);
                        StringBuilder stringBuilder = new StringBuilder();
                        InputStream is;
                        is = urlConnection.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String line;
                        while ((line = br.readLine()) != null) {
                            stringBuilder.append(line);
                        }
                        response = stringBuilder.toString();
                        System.out.println(stringBuilder.toString());

                        is.close();
                        urlConnection.disconnect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try
                    {
                        JSONObject jobject = new JSONObject(response);
                        JSONArray jarray = jobject.getJSONArray("weather");
                        JSONObject temp1 = jobject.getJSONObject("main");
                        temp = "Temperature : " + kelvinToFahreinheit(temp1.getInt("temp")) + " F";
                        temp_max = "Maximum Temperature: " + kelvinToFahreinheit(temp1.getInt("temp_max")) + " F";
                        temp_min = "Minimum Temperature: " + kelvinToFahreinheit(temp1.getInt("temp_min")) + " F";
                        humidity = "Humidity: " + temp1.getString("humidity");
                        pressure = "Pressure: " + temp1.getString("pressure");
                        System.out.println(temp);
                        Log.d("temp", temp);
                        System.out.println(jarray);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });

            Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    displayWeather.setText(temp);

                }
            }, 1500);
/*
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    displayText.setText(response);
                }
            }, 1500);*/

        }
    }
}

