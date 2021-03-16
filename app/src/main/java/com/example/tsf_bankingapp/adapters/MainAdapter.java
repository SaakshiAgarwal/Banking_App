package com.example.tsf_bankingapp.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tsf_bankingapp.models.Model;
import com.example.tsf_bankingapp.R;
import com.example.tsf_bankingapp.TransferMoney;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<Model> dataList;
    private Activity context;
//    private RoomDB database;

    int lastpos = -1;
    public MainAdapter(Activity context, List<Model> dataList){
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {

        Model data = dataList.get(position);
//        database = RoomDB.getInstance(context);

        holder.name.setText("Name : "+data.getName());
        holder.balance.setText("Current Balance : "+data.getBalance()+"");
        holder.email.setText("Email : "+data.getEmail());

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, TransferMoney.class);

                i.putExtra("name_from", data.getName());
                i.putExtra("balance_from", data.getBalance());
                i.putExtra("email_from", data.getEmail());
                i.putExtra("accnumber_from", data.getAccountnumber());

                context.startActivity(i);
                /*context.overridePendingTransition(R.anim.toptonormal, R.anim.fixed);*/
            }
        });
        holder.balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, TransferMoney.class);

                i.putExtra("name_from", data.getName());
                i.putExtra("balance_from", data.getBalance());
                i.putExtra("email_from", data.getEmail());
                i.putExtra("accnumber_from", data.getAccountnumber());
                context.startActivity(i);
                /*context.overridePendingTransition(R.anim.toptonormal, R.anim.fixed);*/
            }
        });
        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, TransferMoney.class);

                i.putExtra("name_from", data.getName());
                i.putExtra("balance_from", data.getBalance());
                i.putExtra("email_from", data.getEmail());
                i.putExtra("accnumber_from", data.getAccountnumber());

                context.startActivity(i);
                /*context.overridePendingTransition(R.anim.toptonormal, R.anim.fixed);*/
            }
        });

        holder.layout.setBackgroundResource(R.color.white);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, balance;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            layout = itemView.findViewById(R.id.linearlayout);
            balance = itemView.findViewById(R.id.balance);

        }
    }
}
