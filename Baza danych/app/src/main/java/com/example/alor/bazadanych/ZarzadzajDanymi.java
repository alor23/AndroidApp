package com.example.alor.bazadanych;

/**
 * Created by alor on 30.11.2017.
 */
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;
import android.database.Cursor;

public class ZarzadzajDanymi
{

    private SQLiteDatabase db;

    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_NAME = "name";
    public static final String TABLE_ROW_NAZWISKO = "nazwisko";
    public static final String TABLE_ROW_AGE = "age";
    public static final String TABLE_ROW_PLEC = "plec";
    public static final String TABLE_ROW_PESEL = "pesel";

    private static final String DB_NAME = "address_book_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_N_AND_A = "names_and_addresses";

    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper
    {
        public CustomSQLiteOpenHelper(Context context)
        {
            super(context,DB_NAME,null,DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            String newTableQueryString = "create table "
                    + TABLE_N_AND_A + " ("
                    + TABLE_ROW_ID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_NAME
                    + " text not null,"
                    + TABLE_ROW_NAZWISKO
                    + " text not null,"
                    + TABLE_ROW_AGE
                    + " text not null,"
                    + TABLE_ROW_PESEL
                    + " text not null,"
                    + TABLE_ROW_PLEC
                    + " text not null);";
            db.execSQL(newTableQueryString);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {

        }
    }


    public ZarzadzajDanymi(Context context)
    {
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
        db=helper.getWritableDatabase();
    }

    public void  insert (String name, String nazwisko, String age, String pesel, String plec)
    {
        String query = "INSERT INTO " + TABLE_N_AND_A + " (" + TABLE_ROW_NAME + ", " + TABLE_ROW_NAZWISKO + ", " + TABLE_ROW_AGE + ", " + TABLE_ROW_PESEL + ", " + TABLE_ROW_PLEC + ") " + "VALUES ("
                + "'" + name + "'" + ", " + "'" + nazwisko + "'" + ", " + "'" + age + "'" + ", " + "'" + pesel + "'" + ", " + "'"+ plec + "'" + ");";
        Log.i("insert() - ",query);
        db.execSQL(query);
    }
    public void delete (String name)
    {
        String query ="DELETE FROM " + TABLE_N_AND_A + " WHERE " + TABLE_ROW_NAME + " = '" + name + "';";
        Log.i("delete() = ", query);
        db.execSQL(query);
    }
    public Cursor selectAll()
    {
        Cursor c = db.rawQuery("SELECT *" +" from " + TABLE_N_AND_A,null);
        return c;
    }
    public Cursor searchName(String name)
    {
        String query = "SELECT "+
                TABLE_ROW_ID +
                ", " + TABLE_ROW_NAME +
                ", " + TABLE_ROW_NAZWISKO +
                ", " + TABLE_ROW_AGE +
                ", " + TABLE_ROW_PESEL +
                ", " + TABLE_ROW_PLEC +
                " from " +
                TABLE_N_AND_A + " Where " +
                TABLE_ROW_NAME + " = '" + name + "';";
        Log.i("serchName() = ",query);
        Cursor c = db.rawQuery(query, null);
        return c;
    }
    public Cursor searchNazwisko(String nazwisko)
    {
        String query = "SELECT "+
                TABLE_ROW_ID +
                ", " + TABLE_ROW_NAME +
                ", " + TABLE_ROW_NAZWISKO +
                ", " + TABLE_ROW_AGE +
                ", " + TABLE_ROW_PESEL +
                ", " + TABLE_ROW_PLEC +
                " from " +
                TABLE_N_AND_A + " Where " +
                TABLE_ROW_NAZWISKO + " = '" + nazwisko + "';";
        Log.i("searchNazwisko() = ",query);
        Cursor c = db.rawQuery(query, null);
        return c;
    }
    public Cursor searchAge(String age)
    {
        String query = "SELECT "+
                TABLE_ROW_ID +
                ", " + TABLE_ROW_NAME +
                ", " + TABLE_ROW_NAZWISKO +
                ", " + TABLE_ROW_AGE +
                ", " + TABLE_ROW_PESEL +
                ", " + TABLE_ROW_PLEC +
                " from " +
                TABLE_N_AND_A + " Where " +
                TABLE_ROW_AGE + " = '" + age + "';";
        Log.i("serchName() = ",query);
        Cursor c = db.rawQuery(query, null);
        return c;
    }
    public Cursor serchPlec(String plec)
    {
        String query = "SELECT "+
                TABLE_ROW_ID +
                ", " + TABLE_ROW_NAME +
                ", " + TABLE_ROW_NAZWISKO +
                ", " + TABLE_ROW_AGE +
                ", " + TABLE_ROW_PESEL +
                ", " + TABLE_ROW_PLEC +
                " from " +
                TABLE_N_AND_A + " Where " +
                TABLE_ROW_PLEC + " = '" + plec + "';";
        Log.i("serchName() = ",query);
        Cursor c = db.rawQuery(query, null);
        return c;
    }
}
