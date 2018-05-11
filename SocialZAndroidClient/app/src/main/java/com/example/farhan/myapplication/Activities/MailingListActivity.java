package com.example.farhan.myapplication.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import com.example.farhan.myapplication.Adapters.PractitionersAdapter;
import com.example.farhan.myapplication.POJO.Hobby;
import com.example.farhan.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MailingListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailing_list);

        TextView hobbytxt = findViewById(R.id.hobby);

        String gsonHobby = getIntent().getStringExtra("hobby");
        Gson gson = new GsonBuilder().create();
        Hobby hobby = gson.fromJson(gsonHobby, Hobby.class);

        hobbytxt.setText("People who likes "+hobby.getNome());

        RecyclerView rvEmails = findViewById(R.id.rvEmails);

        // Create adapter passing in the sample user data
        PractitionersAdapter adapter = new PractitionersAdapter(hobby.getPraticanti(), this);
        // Attach the adapter to the recyclerview to populate items
        rvEmails.setAdapter(adapter);
        // Set layout manager to position the items
        rvEmails.setLayoutManager(new LinearLayoutManager(this));
    }
}
