package com.aerium.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        new TaskAsyncTask().execute();
    }

    class TaskAsyncTask extends AsyncTask{
        ProgressDialog progressDialog;
        Recyclerview recycleradapter;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            String json= null;
            StringBuffer response = new StringBuffer();
            try {
                URL url = new URL("http://jasminjasani.com/sub_domain/student/usersdata.json");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader bufferReader = new BufferedReader(reader);

                String inputLine;
                while ((inputLine = bufferReader.readLine()) != null){
                    Log.i("inputBuffer", inputLine);
                    response.append(inputLine);
                }
                bufferReader.close();
                reader.close();

                json = new String(response);
                Log.i("json_aving", json);
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("users");
                Log.i("json_array",jsonArray.getString(1));
                recycleradapter = new Recyclerview(jsonArray);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o){
            super.onPostExecute(o);
            recyclerview.setAdapter(recycleradapter);
            if (progressDialog.isShowing())
                progressDialog.dismiss();
        }
    }


}