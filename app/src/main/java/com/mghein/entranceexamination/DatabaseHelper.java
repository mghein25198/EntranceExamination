package com.mghein.entranceexamination;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mghein.entranceexamination.model.Currency;
import com.mghein.entranceexamination.model.Header;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CURRENCY";
    private static int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String table = "CREATE TABLE ExchangeRate(id INTEGER PRIMARY KEY,c_unit TEXT,c_amount TEXT)";
        sqLiteDatabase.execSQL(table);
        String table1 = "CREATE TABLE Header(id INTEGER PRIMARY KEY,info TEXT,description TEXT,time TEXT)";
        sqLiteDatabase.execSQL(table1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ExchanceRate");
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Header");
        onCreate(sqLiteDatabase);

    }

    public void setCurrency(String unit, String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("c_unit", unit);
        values.put("c_amount", amount);
        db.insert("ExchangeRate", null, values);
        db.close();
    }

    public void deleteCurrecy() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM ExchangeRate");
        db.close();
    }

    public List<Currency> getCurrencyList() {
        SQLiteDatabase db = this.getWritableDatabase();

        List<Currency> currencyList = new ArrayList<>();
        String table = "SELECT * FROM ExchangeRate";
        Cursor cursor = db.rawQuery(table, null);
        if (cursor.moveToFirst()) {
            do {
                Currency currency = new Currency();
                currency.setUnit(cursor.getString(1));
                currency.setAmount(cursor.getString(2));
                currencyList.add(currency);

            } while (cursor.moveToNext());
        }
        return currencyList;

    }

    public void setHeader(String info, String description,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("info", info);
        values.put("description", description);
        values.put("time",time);
        db.insert("Header", null, values);
        db.close();
    }

    public void deleteHeader() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Header");
        db.close();
    }

    public Header getHeader() {
        SQLiteDatabase db = this.getWritableDatabase();
        Header header = new Header();
        String table = "SELECT * FROM Header";
        Cursor cursor = db.rawQuery(table, null);
        if (cursor.moveToFirst()) {
            do {
                header.setInfo(cursor.getString(1));
                header.setDescription(cursor.getString(2));
                header.setTime(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        return header;

    }

}
