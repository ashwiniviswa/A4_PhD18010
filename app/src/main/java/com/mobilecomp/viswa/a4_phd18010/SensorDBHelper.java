package com.mobilecomp.viswa.a4_phd18010;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;



public class SensorDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "SensorDB";
    // tasks table name


    private static final String accTABLE_NAME = "AccSensorData";
    private static final String gyroTABLE_NAME = "GyroSensorData";
    private static final String orientTABLE_NAME = "OrientSensorData";
    private static final String gpsTABLE_NAME = "GPSSensorData";
    private static final String proxyTABLE_NAME = "ProxySensorData";


    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_X = "XValue";
    private static final String KEY_Y = "YValue";
    private static final String KEY_Z = "ZValue";
    private static final String KEY_LAT = "Latitude";
    private static final String KEY_LON = "Longitude";
    private static final String KEY_TIMESTAMP = "timeStamp";

    private SQLiteDatabase SQLitedb;
    public SensorDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        SQLitedb=db;
        String accsql = "CREATE TABLE IF NOT EXISTS " + accTABLE_NAME + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_X
                + " REAL, "  + KEY_Y
                +  " REAL , " + KEY_Z+ " REAL ," + KEY_TIMESTAMP+ " TEXT)";

        String gyrosql = "CREATE TABLE IF NOT EXISTS " + gyroTABLE_NAME + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_X
                + " REAL, "  + KEY_Y
                +  " REAL , " + KEY_Z+ " REAL ," + KEY_TIMESTAMP+ " TEXT)";

        String orientsql = "CREATE TABLE IF NOT EXISTS " + orientTABLE_NAME + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_X
                + " REAL, "  + KEY_Y
                +  " REAL , " + KEY_Z+ " REAL ," + KEY_TIMESTAMP+ " TEXT)";

        String gpssql = "CREATE TABLE IF NOT EXISTS " + gpsTABLE_NAME + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_LAT
                + " REAL, "  + KEY_LON
                +  " REAL , "  + KEY_TIMESTAMP+ " TEXT)";

        String proxysql = "CREATE TABLE IF NOT EXISTS " + proxyTABLE_NAME + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_X
                + " REAL, " + KEY_Y+ " REAL ,"+ KEY_Z+ " REAL ," + KEY_TIMESTAMP+ " TEXT)";





        SQLitedb.execSQL(accsql);
        SQLitedb.execSQL(gyrosql);
        SQLitedb.execSQL(orientsql);
        SQLitedb.execSQL(gpssql);
        SQLitedb.execSQL(proxysql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
// Drop older table if existed
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
// Create tables again
        onCreate(db);
    }
    // Adding new data
    public void addData(int choice, float x, float y, float z) {
        SQLiteDatabase SQLitedb = this.getWritableDatabase();

        if (choice==1) {
            ContentValues values = new ContentValues();
            values.put(KEY_X, x);
            values.put(KEY_Y, y);
            values.put(KEY_Z, z);
            values.put("timeStamp", System.currentTimeMillis());
// Inserting Row
            SQLitedb.insert(accTABLE_NAME, null, values);
        }
        else if (choice==2) {
            ContentValues values = new ContentValues();
            values.put(KEY_X, x);
            values.put(KEY_Y, y);
            values.put(KEY_Z, z);
            values.put("timeStamp", System.currentTimeMillis());
// Inserting Row
            SQLitedb.insert(gyroTABLE_NAME, null, values);
        }
        else if (choice==3) {
            ContentValues values = new ContentValues();
            values.put(KEY_X, x);
            values.put(KEY_Y, y);
            values.put(KEY_Z, z);
            values.put("timeStamp", System.currentTimeMillis());
// Inserting Row
            SQLitedb.insert(orientTABLE_NAME, null, values);
        }
        else if (choice==4) {
            ContentValues values = new ContentValues();
            values.put(KEY_LAT, x);
            values.put(KEY_LON, y);

            values.put("timeStamp", System.currentTimeMillis());
// Inserting Row
            SQLitedb.insert(gpsTABLE_NAME, null, values);
        }
        else if (choice==5) {
            ContentValues values = new ContentValues();
            values.put(KEY_X, x);
            values.put(KEY_Y, y);
            values.put(KEY_Z, z);
            values.put("timeStamp", System.currentTimeMillis());
// Inserting Row
            SQLitedb.insert(proxyTABLE_NAME, null, values);
        }
    }







}


