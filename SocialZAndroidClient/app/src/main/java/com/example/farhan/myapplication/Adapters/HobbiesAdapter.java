package com.example.farhan.myapplication.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farhan.myapplication.Activities.MailingListActivity;
import com.example.farhan.myapplication.POJO.Hobby;
import com.example.farhan.myapplication.R;
import com.google.gson.Gson;

import java.util.List;

public class HobbiesAdapter extends RecyclerView.Adapter<HobbiesAdapter.ViewHolder> {

    private static List<Hobby> hobbies;
    private static Context context;

    public HobbiesAdapter(List<Hobby> hobbies, Context context) {
        this.hobbies = hobbies;
        this.context = context;
    }

    @NonNull
    @Override
    public HobbiesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View hobbyView = inflater.inflate(R.layout.item_hobby, parent, false);
        ViewHolder viewHolder = new ViewHolder(hobbyView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hobby hobby = hobbies.get(position);

        TextView hobbyName = holder.hobbyName;
        hobbyName.setText(hobby.getNome());

        Button mailingList = holder.mailingList;
        mailingList.setText(R.string.mailing_list);
        mailingList.setOnClickListener((View v) -> {
            showPractitioners(hobby);
        });

    }

    private void showPractitioners(Hobby hobby) {
        Intent i = new Intent(context, MailingListActivity.class);
        Gson gson = new Gson();
        String jsonHobby = gson.toJson(hobby);
        i.putExtra("hobby", jsonHobby);
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return hobbies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView hobbyName;
        public Button mailingList;


        public ViewHolder(View itemView) {
            super(itemView);
            hobbyName = itemView.findViewById(R.id.hobby_name);
            mailingList = itemView.findViewById(R.id.seeMailsBtn);

            mailingList.setOnClickListener((View v) -> {
                int position = getLayoutPosition();
                Hobby hobby = hobbies.get(position);
                Toast.makeText(context, "Loading " + hobby.getNome() + "'s practitioners...", Toast.LENGTH_SHORT).show();
            });
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            Hobby hobby = hobbies.get(position);
            Toast.makeText(context, hobby.getNome() + " selected.", Toast.LENGTH_SHORT).show();

        }
    }
}