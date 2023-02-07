package com.example.parsingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Document doc;
    private Thread secThread;
    private Runnable runnable;
    private RecyclerView recyclerView;
    private Button button;
    private List<Currency> currencyList;
    private TextView name, purchase, sale, centerBank;
    CurrencyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currencyList = new ArrayList<>();
        //currencyList.add(new Currency("DLR", "68.98", "VTB", "68.5", "Sber", "68.7"));
        recyclerView = findViewById(R.id.CurrencyRecyclerView);

        if(!hasConnection(this)){
            Toast.makeText(this, "Подключитесь к интернету", Toast.LENGTH_SHORT).show();
        }

        startNewThread();
        adapter = new CurrencyAdapter(this, currencyList);
        recyclerView.setAdapter(adapter);

        name = findViewById(R.id.NameTV);
        purchase = findViewById(R.id.PurchaseTV);
        sale = findViewById(R.id.SaleTV);
        centerBank = findViewById(R.id.CenterBankTV);

        button = findViewById(R.id.RefreshButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.recreate();
            }
        });


    }
    private void getWebDocument(){
        try {
            doc = Jsoup.connect("https://mainfin.ru/currency/ufa").get();

            Elements tablesElements = doc.getElementsByTag("tbody");
            Elements banks = doc.getElementsByClass("fs-12 text-gray");
            Log.e("size", "banks size " + banks.size());
            Element table = tablesElements.get(0);
            Elements tableElements = table.children();
            Element tableElement = tableElements.get(0);
            Elements columnNames = tableElement.children();
            Element columnName = columnNames.get(0);
            Log.e( "size", "Size" + tableElements.size());
            Log.e("size", "cursize" + tableElements.get(0).children().get(1).children().size());
            int j = 0;
            for(int i = 1; i < tableElements.size(); i++){
                String n = tableElements.get(i).children().get(0).text();
                String p = tableElements.get(i).children().get(1).text().substring(0,5);
                String pb = banks.get(j).text();
                j++;
                String s = tableElements.get(i).children().get(2).text().substring(0,5);
                String sb = banks.get(j).text();
                j++;
                String c = tableElements.get(i).children().get(3).text();
                currencyList.add(new Currency(n, p, pb, s, sb, c));
                Log.e("a", tableElements.get(i).children().get(1).text());
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    name.setText(columnNames.get(0).text());
                    purchase.setText(columnNames.get(1).text());
                    sale.setText(columnNames.get(2).text());
                    centerBank.setText(columnNames.get(3).text());
                    adapter.notifyDataSetChanged();
                    Log.e("list", currencyList.toString());
                }
            });


            Log.e("html", "column" + tableElement.text());
        } catch (IOException e) {
            Log.e("ex", "exception");
            e.printStackTrace();
        }
    }
    private void startNewThread(){
        runnable = new Runnable() {
            @Override
            public void run() {
               getWebDocument();
            }
        };
        secThread = new Thread(runnable);
        secThread.start();
    }
    private boolean hasConnection(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        wifiInfo = cm.getActiveNetworkInfo();
        if(wifiInfo != null && wifiInfo.isConnected()){
            return true;
        }
        return false;
    }

}