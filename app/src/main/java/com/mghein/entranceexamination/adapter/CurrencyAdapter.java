package com.mghein.entranceexamination.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mghein.entranceexamination.R;
import com.mghein.entranceexamination.model.Currency;

import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.MyviewHolder> {
    private Context context;
    List<Currency> currencyList;

    public CurrencyAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Currency> currencyList){
        this.currencyList=currencyList;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.currency_layout,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        Currency currency=currencyList.get(position);
        holder.counteryUnit.setText(currency.getUnit());
        holder.amount.setText(currency.getAmount());

    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView counteryUnit;
        TextView amount;
        public MyviewHolder(@NonNull View itemView) {

            super(itemView);
            counteryUnit=itemView.findViewById(R.id.tv_countery);
            amount=itemView.findViewById(R.id.tv_amount);
        }
    }
}
