package com.fastexpo.recyclerviewexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fastexpo.recyclerviewexample.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;
    String API = "https://api.androidhive.info/contacts/";
    String s1,s2,s3,s4,s5,s6,s7,s8;
    List<DemoModel> values ;//= new ArrayList<>();
    DemoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.rvDemos.setLayoutManager(new LinearLayoutManager(this));
        values = new ArrayList<>();
        MyServiceclas myServiceclas = new MyServiceclas();
        myServiceclas.execute();


    }

    private List<DemoModel> getValues() {
        return values;
    }


    private class MyServiceclas extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Object doInBackground(Object[] objects) {
            GetNames getNames = new GetNames();

            String resut = getNames.makeServiceCall(API);

                try {
                    JSONObject wholeobj = new JSONObject(resut);
                    JSONArray jsarr = wholeobj.getJSONArray("contacts");

                    for(int  i = 0;i< jsarr.length();i++)
                    {
                        JSONObject arrobj2 = jsarr.getJSONObject(i);

                        s1 =arrobj2.getString("id");
                        s2 = arrobj2.getString("name");
                        s3 = arrobj2.getString("email");
                        s4 = arrobj2.getString("address");
                        s5 = arrobj2.getString("gender");

                        JSONObject phobj2 = arrobj2.getJSONObject("phone");
                        s6 = phobj2.getString("mobile");
                        s7 = phobj2.getString("home");
                        s8 = phobj2.getString("office");

                        values.add(new DemoModel(s1,s2,s3,s4,s5,s6,s7,s8));
                    }


                    } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            DemoOnClickListener listener = new DemoOnClickListener() {
                @Override
                public void OnTestClick(int pos) {
                    Toast.makeText(MainActivity.this, "Clicked value " + pos, Toast.LENGTH_SHORT).show();
                }
            };

            adapter = new DemoAdapter(listener);

            mBinding.rvDemos.setAdapter(adapter);

            adapter.setData(getValues());
        }
    }
}