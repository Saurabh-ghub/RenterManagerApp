package com.example.renter_management;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static MyDatabase sInstance;
    public static synchronized MyDatabase getInstance(Context context){
        if(sInstance == null){
            sInstance = new MyDatabase(context.getApplicationContext());
        }
        return sInstance;
    }

    // Database Info
    private static final String DATABASE_NAME = "RenterManagementDatabase";
    private static final int DATABASE_VERSION = 14;

    // Table Names
    private static final String TABLE_FLAT = "flats";
    private static final String TABLE_RENTERS = "renters";
    private static final String TABLE_PAYMENT = "payments";
    private static final String TABLE_METER = "meters";
    private static final String TABLE_MONTHLYRENT = "monthlyrents";


    // Flat Table Columns
    private static final String KEY_FLAT_ID = "flat_id";
    private static final String KEY_FLAT_NICKNAME = "nick_name";
    private static final String KEY_NO_OF_ROOMS = "no_of_rooms";
    private static final String KEY_FLAT_RENT = "flat_rent";
    private static final String KEY_FLAT_ALLOCATION_DATE = "allocation_date";
    private static final String KEY_FLAT_ADVANCE = "advance";
    private static final String KEY_INITIAL_METER_READING = "initial_meter_reading";
    private static final String KEY_ELECTRICITY_PER_UNIT_COST = "electricity_cost_per_unit";

    // Renter Table Columns
    private static final String KEY_RENTER_ID = "renter_id";
    private static final String KEY_RENTER_NAME = "userName";
    private static final String KEY_RENTER_PHOTO = "photo";
    private static final String KEY_RENTER_PHONE_NUMBER = "phone_number";
    private static final String KEY_RENTER_ADDRES = "addres";
    private static final String KEY_RENTER_FLAT_ID_FK = "flat_id";

    //Payment table columns
    private static final String KEY_PAYMENT_ID = "payment_id";
    private static final String KEY_PAYMENT_PAIDBY = "paid_by";
    private static final String KEY_PAYMENT_AMOUNT = "payment_amount";
    private static final String KEY_ROOM_PAYMENT_AMT = "room_payment_amount";
    private static final String KEY_METER_PAYMENT_AMT = "meter_payment_amount";
    private static final String KEY_PAYMENT_MODE = "payment_mode";
    private static final String KEY_PAYMENT_DATE = "payment_date";
    private static final String KEY_PAYMENT_REMARKS = "remarks";
    private static final String KEY_PAYMENT_FLAT_ID_FK = "flat_id";

    //Meter Table columns
    private static final String KEY_METER_ID = "meter_id";
    private static final String KEY_METER_CURRENT_READING = "current_reading";
    private static final String KEY_METER_COST = "calculated_cost";
    private static final String KEY_METER_READING_DATE = "reading_date";
    private static final String KEY_METER_FLAT_ID_FK = "flat_id";

    //MonthlyRent Table Column
    private static final String KEY_MONTHLYRENT_ID = "montlyRent_id";
    private static final String KEY_MONTH = "month";
    private static final String KEY_MONTHLYRENT_PAIDDATE = "paid_Date";
    private static final String KEY_MONTHLYRENT_RECEIVED_AMOUNT = "recievedAmount";
    private static final String KEY_MONTHLYRENT_DUEAMOUNT = "dueAmount";
    private static final String KEY_MONTHLYRENT_FLATID = "flat_id";

    public MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_FLAT = "Create Table "+TABLE_FLAT+
                " ( "+ KEY_FLAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_NO_OF_ROOMS+
                " INTEGER, "+ KEY_FLAT_NICKNAME + " TEXT, "
                + KEY_FLAT_ALLOCATION_DATE + " TEXT, "+
                KEY_FLAT_ADVANCE + " INTEGER, " +
                KEY_FLAT_RENT + " INTEGER NOT NULL, " +
                 KEY_ELECTRICITY_PER_UNIT_COST + " REAL, "+
                KEY_INITIAL_METER_READING + " REAL)" ;

        String CREATE_TABLE_RENTER = "Create Table " + TABLE_RENTERS +
                "( "+ KEY_RENTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                KEY_RENTER_NAME+" TEXT NOT NULL, "+KEY_RENTER_PHONE_NUMBER+" TEXT, "
                + KEY_RENTER_ADDRES + " TEXT, "
                + KEY_RENTER_PHOTO + " BLOB , "
                + KEY_RENTER_FLAT_ID_FK + " INTEGER REFERENCES "+ TABLE_FLAT + " )";

        String CREATE_TABLE_PAYMENT = "Create TABLE "+ TABLE_PAYMENT+" ( "
                 + KEY_PAYMENT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_PAYMENT_PAIDBY+ " TEXT, " + KEY_PAYMENT_AMOUNT+" INTEGER, " +
                KEY_ROOM_PAYMENT_AMT + " INTEGER, "+ KEY_METER_PAYMENT_AMT + " INTEGER, " +
                KEY_PAYMENT_MODE + " TEXT, " +
                KEY_PAYMENT_DATE + " TEXT, " + KEY_PAYMENT_REMARKS + " TEXT, " +
                KEY_PAYMENT_FLAT_ID_FK+ " INTEGER REFERENCES  " + TABLE_FLAT + " )";

        String CREATE_TABLE_METER = "CREATE TABLE "+ TABLE_METER + " ( "+
                KEY_METER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                KEY_METER_CURRENT_READING + " REAL, " + KEY_METER_COST + " REAL, " +
                KEY_METER_READING_DATE + " TEXT, "+
                KEY_METER_FLAT_ID_FK + " INTEGER REFERENCES " + TABLE_FLAT + " )";

        String CREATE_TABLE_MONTHLYRENT = "CREATE TABLE " + TABLE_MONTHLYRENT + " ( " +
                KEY_MONTHLYRENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_MONTH + " TEXT, " + KEY_MONTHLYRENT_RECEIVED_AMOUNT + " INTEGER, " +
                KEY_MONTHLYRENT_DUEAMOUNT + " INTEGER, " +
                KEY_MONTHLYRENT_PAIDDATE + " TEXT, " +
                KEY_METER_FLAT_ID_FK + " INTEGER REFERENCES " + TABLE_FLAT + " )";

        db.execSQL(CREATE_TABLE_FLAT);
        db.execSQL(CREATE_TABLE_RENTER);
        db.execSQL(CREATE_TABLE_PAYMENT);
        db.execSQL(CREATE_TABLE_METER);
        db.execSQL(CREATE_TABLE_MONTHLYRENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion){
             db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PAYMENT);
             db.execSQL("DROP TABLE IF EXISTS "+ TABLE_RENTERS);
             db.execSQL("DROP TABLE IF EXISTS "+ TABLE_METER);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_FLAT );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONTHLYRENT);

            onCreate(db);
        }
    }

    // CRUD OPERATIONS

    //Insertion a flat into database
    public long addFlat(@NonNull Flat flat){
        long flat_Id = -1;
        SQLiteDatabase db = this.getWritableDatabase();


        try {
            ContentValues cv = new ContentValues();
            cv.put(KEY_NO_OF_ROOMS,flat.getNo_of_rooms());
            cv.put(KEY_FLAT_NICKNAME,flat.getNickName());
            cv.put(KEY_FLAT_ADVANCE,flat.getAdvance());
            cv.put(KEY_FLAT_RENT,flat.getFlat_rent());
            cv.put(KEY_FLAT_ALLOCATION_DATE,flat.getJoining_date());
            cv.put(KEY_INITIAL_METER_READING,flat.getIntial_meter_reading());
            cv.put(KEY_ELECTRICITY_PER_UNIT_COST,flat.getElectricity_rate());

            long sflat = db.insert(TABLE_FLAT,null,cv);
            Log.d("DEBUG","sflat value = " + Long.toString(sflat));
            if (sflat != -1 ){
                Log.d("DEBUG","Inside sflat");
                String getid_query = "SELECT * FROM " + TABLE_FLAT + " WHERE (" + KEY_FLAT_NICKNAME + " = '" + flat.getNickName() + "' AND " + KEY_FLAT_ADVANCE + " = '" + flat.getAdvance() +  "' )";
                Cursor cursor = db.rawQuery(getid_query,null);
                Log.d("DEBUG","CURSOR_SIZE = " + Integer.toString(cursor.getCount()));
                if(cursor.moveToFirst()){
                    Log.d("DEBUG","FLAT ID = " + Integer.toString(cursor.getInt(0)));
                    flat_Id = cursor.getInt(0);
                    return flat_Id;

                }
                cursor.close();
                db.close();
            }
        }
        catch (Exception e){
            Log.d("INSERTION EXCEPTION", String.valueOf(e));
        }
        return flat_Id;
    }

    //GET FLAT DETAILS
    List<Flat> getAllFlats(){
        List<Flat>flats = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql_query = "SELECT * FROM " + TABLE_FLAT;
        Cursor cursor = db.rawQuery(sql_query,null);
        if(cursor.moveToFirst()){
            do{
                Flat flat = new Flat(cursor.getInt(0),cursor.getInt(1), cursor.getString(2), cursor.getString(3),cursor.getInt(4),cursor.getInt(5),cursor.getFloat(6),cursor.getFloat(7) );
                flats.add(flat);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return flats;
    }

    Flat getOneFlat(int flatID){
        Flat flat = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql_query = "SELECT * FROM " + TABLE_FLAT + " WHERE " + KEY_FLAT_ID + " = " +flatID;
        Cursor cursor = db.rawQuery(sql_query,null);
        if(cursor.moveToFirst()){
            flat = new Flat(cursor.getInt(0),cursor.getInt(1), cursor.getString(2), cursor.getString(3),cursor.getInt(4),cursor.getInt(5),cursor.getFloat(6),cursor.getFloat(7) );
        }
        db.close();
        cursor.close();
        return flat;
    }
    // RENTER DETAILS

    // INSERT RENTER into table
     public void addRenter(Renter renter){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_RENTER_NAME,renter.getRenter_name());
        cv.put(KEY_RENTER_PHONE_NUMBER,renter.getPhone());
        cv.put(KEY_RENTER_ADDRES,renter.getAddres());
        cv.put(KEY_RENTER_PHOTO,renter.getPhoto().toString());
        cv.put(KEY_RENTER_FLAT_ID_FK,renter.getFlat_id());
        try {
            long insert = db.insert(TABLE_RENTERS, null, cv);
        }catch (Exception e){
            Log.d("Exception",e.toString());
        }
        db.close();
     }

     //Fetch renters list from database
    public List<Renter> getAllRenters(int flatID){
        List<Renter>renterList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM "+ TABLE_RENTERS + " WHERE " + KEY_RENTER_FLAT_ID_FK + " = " + flatID ;
        Cursor cursor = db.rawQuery(sql, null);
        Log.d("DEBUG","Where FlatID foreign = "+ Integer.toString(cursor.getCount()));
        if(cursor.moveToFirst()){
            do{
                Renter renter = new Renter(cursor.getInt(0),cursor.getString(1),cursor.getString(2), cursor.getString(3), Uri.parse(cursor.getString(4)), cursor.getInt(5));
                renterList.add(renter);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return renterList;
    }

    //get single renter detail
    public Renter getRenter(int id){
        Renter renter = null ;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_RENTERS + " WHERE " + KEY_RENTER_ID + " = " + id;

        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            renter = new Renter(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), Uri.parse(cursor.getString(4)), cursor.getInt(5) );
        }
        cursor.close();
        db.close();
        return renter;
    }


    // PAYMENT TABLE CRUDS

    //Insertion in payment table
    public void addPayment(Payment payment){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_PAYMENT_AMOUNT,payment.getPaid_amount());
        cv.put(KEY_ROOM_PAYMENT_AMT,payment.getRoom_payment());
        cv.put(KEY_METER_PAYMENT_AMT,payment.getMeter_payment());
        cv.put(KEY_PAYMENT_DATE,payment.getPayment_date());
        cv.put(KEY_PAYMENT_MODE,payment.getPayment_mode());
        cv.put(KEY_PAYMENT_PAIDBY,payment.getPaid_by());
        cv.put(KEY_PAYMENT_FLAT_ID_FK,payment.getFlat_id());
        cv.put(KEY_PAYMENT_REMARKS,payment.getRemarks());

        long insert = db.insert(TABLE_PAYMENT, null, cv);
        Log.d("DEBUG","Value of insert "+ Long.toString(insert));
        db.close();
    }

    //Retrieve payment history
    public  List<Payment> getAllPayment(int flaID){
        List<Payment>paymentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PAYMENT + " WHERE " + KEY_PAYMENT_FLAT_ID_FK + " = " + flaID;
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                Payment payment = new Payment(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8) );
                paymentList.add(payment);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return paymentList;
    }

    //Rettrieve particular payment
    public Payment getOnePayment(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Payment payment = null;
        String query = "SELECT * FROM " + TABLE_PAYMENT + " WHERE " + KEY_PAYMENT_ID + " = " + id;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            payment = new Payment(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8) );
        }
        cursor.close();
        db.close();
        return payment;
    }

    // METER TABLE CRUD OPERATION
    public void addMeterReading(Meter meter){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_METER_CURRENT_READING,meter.getCurrent_reading());
        cv.put(KEY_METER_COST,meter.getCost());
        cv.put(KEY_METER_READING_DATE,meter.getReading_date());
        cv.put(KEY_METER_FLAT_ID_FK,meter.getFlat_id());
        long insert = db.insert(TABLE_METER, null, cv);
        if(insert==-1){
            Log.d("DEBUG", "Meter Reading Insertion Error");
        }
        db.close();
    }

    public  List<Meter> getAllMeterReading(int flatID){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Meter>meterList = new ArrayList<>();

        String sqlQuery = "SELECT * FROM "+ TABLE_METER + " WHERE " + KEY_METER_FLAT_ID_FK + " = " + flatID;
        Cursor cursor = db.rawQuery(sqlQuery,null);

        if(cursor.moveToFirst()){
            do{
                Meter meter = new Meter(cursor.getInt(0),cursor.getFloat(1),cursor.getFloat(2), cursor.getString(3), cursor.getInt(4) );
                meterList.add(meter);
            }while(cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return meterList;
    }

    public Meter getOneReading(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Meter meter = null;

        String sqlQuery = "SELECT * FROM "+ TABLE_METER + " WHERE " + KEY_METER_ID + " = " + id;
        Cursor cursor = db.rawQuery(sqlQuery,null);

        if(cursor.moveToFirst()){

                 meter = new Meter(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2), cursor.getString(3), cursor.getInt(4) );

        }
        db.close();
        cursor.close();
        return meter;
    }

    // CRUD OPERATION FOR TABLE MONTHLY RENT

    //Insertion in monthlyRent
    public void addMonthlyRent(MonthlyRent monthlyRent){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_MONTH,monthlyRent.getMonth());
        cv.put(KEY_MONTHLYRENT_RECEIVED_AMOUNT,monthlyRent.getRecvAmount());
        cv.put(KEY_MONTHLYRENT_DUEAMOUNT,monthlyRent.getDueAmount());
        cv.put(KEY_MONTHLYRENT_PAIDDATE,monthlyRent.getPaidDate());
        cv.put(KEY_MONTHLYRENT_FLATID,monthlyRent.getFlat_id());
        db.insert(TABLE_MONTHLYRENT,null,cv);
        db.close();
    }

    //Update
    public void updateMonthlyRent(MonthlyRent monthlyRent){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = " UPDATE " + TABLE_MONTHLYRENT + " SET " + KEY_MONTHLYRENT_RECEIVED_AMOUNT + " = '" + monthlyRent.getRecvAmount() + "',  " + KEY_MONTHLYRENT_DUEAMOUNT + " =  '" + monthlyRent.getDueAmount() + "', "+ KEY_MONTHLYRENT_PAIDDATE + " = " + " '" + monthlyRent.getPaidDate() +"' WHERE " + KEY_MONTHLYRENT_ID + " = " + monthlyRent.getId();
         db.execSQL(sqlQuery);
        db.close();
    }

    //READ DATA
    public List<MonthlyRent> getAllMonthlyRent(int flatId){
        SQLiteDatabase db = this.getReadableDatabase();
        List<MonthlyRent>monthlyRentList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_MONTHLYRENT + " WHERE " + KEY_MONTHLYRENT_FLATID + " = " + Integer.toString(flatId);
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                MonthlyRent monthlyRent = new MonthlyRent(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getInt(5));
                monthlyRentList.add(monthlyRent);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return monthlyRentList;
    }



}
