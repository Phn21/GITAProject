package com.example.day11last.Recycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day11last.Database.Info1;
import com.example.day11last.DetailInfo;
import com.example.day11last.MainActivity;
import com.example.day11last.R;
import com.example.day11last.UsersActivity;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RViewHolder> {

    Context context;
    List<Info1> info1ListView;

    public RVAdapter(Context context, List<Info1> info1ListView) {
        this.context = context;
        this.info1ListView = info1ListView;
    }

    @NonNull
    @Override
    public RVAdapter.RViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);

        return new RViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.RViewHolder holder, int position) {

        Info1 info1 =info1ListView.get(position);

        holder.name.setText("Name: " + info1.getName());
        holder.number.setText("Number: " + info1.getNumber());
        holder.location.setText("Location: " + info1.getLocation());

    }

    @Override
    public int getItemCount() {
        return info1ListView.size();
    }


    public class RViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, number, location, dateOfDonation, dateOfAbleDonateAgain;
        public RViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameRCYCLER);
            number = itemView.findViewById(R.id.numberRCYCLER);
            location = itemView.findViewById(R.id.locationRCYCLER);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Info1 info1 = info1ListView.get(getAdapterPosition());

            Intent intent = new Intent(context,DetailInfo.class);
            intent.putExtra("INFO", info1);

            context.startActivity(intent);
        }
    }



}
