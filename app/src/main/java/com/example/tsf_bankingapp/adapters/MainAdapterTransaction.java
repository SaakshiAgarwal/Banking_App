package com.example.tsf_bankingapp.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tsf_bankingapp.models.ModelTransaction;
import com.example.tsf_bankingapp.R;

import java.util.List;

public class MainAdapterTransaction extends RecyclerView.Adapter<MainAdapterTransaction.ViewHolder> {
    private List<ModelTransaction> dataList;
    private Activity context;
//    private RoomDB database;

    int lastpos = -1;
    public MainAdapterTransaction(Activity context, List<ModelTransaction> dataList){
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapterTransaction.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MainAdapterTransaction.ViewHolder holder, int position) {

        ModelTransaction data = dataList.get(position);
//        database = RoomDB.getInstance(context);

        holder.from.setText(data.getFrom());
        holder.to.setText(data.getTo());
        holder.amount.setText(data.getAmount()+"");

        holder.layout.setBackgroundResource(R.color.white);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView from, to, amount;
        ImageView delete, edit;
        LinearLayout layout;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            layout = itemView.findViewById(R.id.linearlayout);
            amount = itemView.findViewById(R.id.amount);

        }
    }
}
