package com.example.farhan.myapplication.Activities;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.farhan.myapplication.Adapters.HobbiesAdapter;
import com.example.farhan.myapplication.POJO.Hobby;
import com.example.farhan.myapplication.POJO.Pojo;
import com.example.farhan.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.List;

public class HobbiesListActivity extends AppCompatActivity {

    private static final String SOAP_ACTION = "";
    private static final String METHOD_NAME = "getHobbiesWithPractitioners";
    private static final String NAMESPACE = "http://SocialZWebService/";
    private static final String URL = "http://10.0.2.2:8080/SocialZWebService/HobbiesAndUsers?WSDL";

    private Context context;
    private RecyclerView rvHobbies;
    private HobbiesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hobbies_list);
        context = this;

        rvHobbies = findViewById(R.id.rvHobbies);

        new AsyncTaskRunner().execute();

    }


    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                envelope.setOutputSoapObject(request);

                HttpTransportSE ht = new HttpTransportSE(URL);
                try {
                    ht.call(SOAP_ACTION, envelope);
                    SoapObject response = (SoapObject) envelope.bodyIn;

                    return response.getProperty("return").toString();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            Gson gson = new GsonBuilder().create();
            Pojo pojo = gson.fromJson(result, Pojo.class);
            List<Hobby> hobbies = pojo.getHobbies();


            adapter = new HobbiesAdapter(hobbies, context);

            rvHobbies.setAdapter(adapter);

            rvHobbies.setLayoutManager(new LinearLayoutManager(context));
        }

    }
}
