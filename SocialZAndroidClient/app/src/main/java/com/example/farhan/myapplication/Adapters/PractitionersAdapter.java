package com.example.farhan.myapplication.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farhan.myapplication.R;

import java.util.List;

public class PractitionersAdapter extends RecyclerView.Adapter<PractitionersAdapter.ViewHolder> {

    private static List<String> practitioners;
    private static Context context;

    public PractitionersAdapter(List<String> practitioners, Context context) {
        this.practitioners = practitioners;
        this.context = context;
    }


    @NonNull
    @Override
    public PractitionersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View emailView = inflater.inflate(R.layout.item_email, parent, false);
        ViewHolder viewHolder = new ViewHolder(emailView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String practitioner = practitioners.get(position);

        TextView email = holder.email;
        email.setText(practitioner);
    }

    @Override
    public int getItemCount() {
        return practitioners.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView email;

        public ViewHolder(View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.email);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            Toast.makeText(context, practitioners.get(position).toString() + " selected.", Toast.LENGTH_SHORT).show();
        }
    }
}
