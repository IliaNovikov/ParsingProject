package com.example.parsingproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Currency> currencyList;

    public CurrencyAdapter(Context context, List<Currency> currencyList) {
        this.inflater = LayoutInflater.from(context);
        this.currencyList = currencyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.currency_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Currency currency = currencyList.get(position);
        holder.currencyName.setText(currency.getName());
        holder.currencyPurchase.setText(currency.getPurchasePrice());
        holder.currencyPurchaseBank.setText(currency.getPurchaseBank());
        holder.currencySale.setText(currency.getSalePrice());
        holder.currencySaleBank.setText(currency.getSaleBank());
        holder.centerBankPrice.setText(currency.getCentralBankPrice());
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView currencyName, currencyPurchase, currencySale, currencyPurchaseBank, currencySaleBank, centerBankPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            currencyName = itemView.findViewById(R.id.CurrencyNameTV);
            currencyPurchase = itemView.findViewById(R.id.CurrencyPurchaseTV);
            currencyPurchaseBank = itemView.findViewById(R.id.CurrencyPurchaseBankTV);
            currencySale = itemView.findViewById(R.id.CurrencySaleTV);
            currencySaleBank = itemView.findViewById(R.id.CurrencySaleBankTV);
            centerBankPrice = itemView.findViewById(R.id.CenterBankPriceTV);
        }
    }
}
