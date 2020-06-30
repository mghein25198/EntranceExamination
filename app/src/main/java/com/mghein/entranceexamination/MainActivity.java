package com.mghein.entranceexamination;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.TextView;

import com.mghein.entranceexamination.adapter.CurrencyAdapter;
import com.mghein.entranceexamination.model.Currency;
import com.mghein.entranceexamination.model.DataResponse;
import com.mghein.entranceexamination.model.Header;
import com.mghein.entranceexamination.model.Rate;
import com.mghein.entranceexamination.network.RetrofitClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvCurrency;
    DatabaseHelper helper;
    TextView info,description,time;
    List<Currency> currencyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info=findViewById(R.id.tv_info);
        description=findViewById(R.id.tv_description);
        time=findViewById(R.id.tv_time);
        currencyList=new ArrayList<>();

        helper=new DatabaseHelper(MainActivity.this);
        rvCurrency=findViewById(R.id.currency_rv);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
        rvCurrency.setLayoutManager(linearLayoutManager);

        CurrencyAdapter adapter=new CurrencyAdapter(MainActivity.this);

        if (isConnected()) {
            RetrofitClient.getInstance().getApi().getLatest().enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                    helper.deleteCurrecy();
                    helper.deleteHeader();
                    Calendar cal=Calendar.getInstance(Locale.ENGLISH);
                    cal.setTimeInMillis(Long.valueOf(response.body().getTimestamp())*1000);
                    String date= DateFormat.format("dd-MM-yyyy",cal).toString();
                    info.setText(response.body().getInfo());
                    description.setText(response.body().getDescription());
                    time.setText(date);

                    Rate rate=response.body().getRate();

                    currencyList.add(new Currency("1 USD",rate.getUsd()+" Kyats"));

                    currencyList.add(new Currency("1 ZAR",rate.getZar()+" Kyats"));

                    currencyList.add(new Currency("1 NPR",rate.getNpr()+" Kyats"));

                    currencyList.add(new Currency("1 EGP",rate.getEgp()+" Kyats"));

                    currencyList.add(new Currency("1 BDT",rate.getBdt()+" Kyats"));

                    currencyList.add(new Currency("1 THB",rate.getThb()+" Kyats"));

                    currencyList.add(new Currency("1 PKR",rate.getPkr()+" Kyats"));

                    currencyList.add(new Currency("1 KES",rate.getKes()+" Kyats"));

                    currencyList.add(new Currency("1 IDR",rate.getIdr()+" Kyats"));

                    currencyList.add(new Currency("1 KHR",rate.getKhr()+" Kyats"));

                    currencyList.add(new Currency("1 SGD",rate.getSgd()+" Kyats"));

                    currencyList.add(new Currency("1 LAK",rate.getLak()+" Kyats"));

                    currencyList.add(new Currency("1 SAR",rate.getSar()+" Kyats"));

                    currencyList.add(new Currency("1 CZK",rate.getCzk()+" Kyats"));

                    currencyList.add(new Currency("1 JPY",rate.getJpy()+" Kyats"));

                    currencyList.add(new Currency("1 LKR",rate.getLkr()+" Kyats"));

                    currencyList.add(new Currency("1 NZD",rate.getNzd()+" Kyats"));

                    currencyList.add(new Currency("1 HKD",rate.getHkd()+" Kyats"));

                    currencyList.add(new Currency("1 BRL",rate.getBrl()+" Kyats"));

                    currencyList.add(new Currency("1 VND",rate.getVnd()+" Kyats"));

                    currencyList.add(new Currency("1 PHP",rate.getPhp()+" Kyats"));

                    currencyList.add(new Currency("1 KRW",rate.getKrw()+" Kyats"));

                    currencyList.add(new Currency("1 GBP",rate.getGbp()+" Kyats"));

                    currencyList.add(new Currency("1 CAD",rate.getCad()+" Kyats"));

                    currencyList.add(new Currency("1 RSD",rate.getRsd()+" Kyats"));

                    currencyList.add(new Currency("1 MYR",rate.getMyr()+" Kyats"));

                    currencyList.add(new Currency("1 DKK",rate.getDkk()+" Kyats"));

                    currencyList.add(new Currency("1 AUD",rate.getAud()+" Kyats"));

                    currencyList.add(new Currency("1 SEK",rate.getSek()+" Kyats"));

                    currencyList.add(new Currency("1 NOK",rate.getNok()+" Kyats"));

                    currencyList.add(new Currency("1 ILS",rate.getIls()+" Kyats"));

                    currencyList.add(new Currency("1 INR",rate.getInr()+" Kyats"));

                    currencyList.add(new Currency("1 BND",rate.getBnd()+" Kyats"));

                    currencyList.add(new Currency("1 EUR",rate.getEur()+" Kyats"));

                    currencyList.add(new Currency("1 KWD",rate.getKwd()+" Kyats"));

                    currencyList.add(new Currency("1 RUB",rate.getRub()+" Kyats"));

                    currencyList.add(new Currency("1 CNY",rate.getCny()+" Kyats"));

                    currencyList.add(new Currency("1 CHF",rate.getChf()+" Kyats"));

                    Log.d("logdata",currencyList.toString());

                    adapter.setData(currencyList);
                    rvCurrency.setAdapter(adapter);

                    helper.setHeader(info.getText().toString(),description.getText().toString(),time.getText().toString());
                    for (int i=0;i<currencyList.size();i++){
                        Currency currency=currencyList.get(i);
                        helper.setCurrency(currency.getUnit(),currency.getAmount());
                        Log.d("logdata",currency.getUnit());
                    }
                }

                @Override
                public void onFailure(Call<DataResponse> call, Throwable t) {

                }
            });
        }else {
            Log.d("logdata","NO Internet");
            Header header=helper.getHeader();
            info.setText(header.getInfo());
            description.setText(header.getDescription());
            time.setText(header.getTime());

            currencyList=helper.getCurrencyList();
            adapter.setData(currencyList);
            rvCurrency.setAdapter(adapter);

        }
    }

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }
}
