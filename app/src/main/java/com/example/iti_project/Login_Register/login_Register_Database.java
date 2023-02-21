package com.example.iti_project.Login_Register;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class login_Register_Database extends SQLiteOpenHelper {
    public static final String TABLE_NAME="users";
    public static final String TABLE_COL_USERNAME="username";
    public static final String TABLE_COL_PASSWORD="password";
    public static final String TABLE_COL_CONFIRMPASSWORD="confirmPassword";
    public static final int VERSION=1;

    public login_Register_Database(Context c){
        super(c,TABLE_NAME,null,VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE  "+TABLE_NAME+"("+TABLE_COL_USERNAME+" TEXT primary key ,"+TABLE_COL_PASSWORD+" TEXT ,"+TABLE_COL_CONFIRMPASSWORD+" TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  "+TABLE_NAME+" ");
        onCreate(sqLiteDatabase);


    }
    public boolean insertUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",user.getUsername());
        values.put("password", user.getPassword());
        values.put("confirmPassword",user.getConfirmPassword());
        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }
    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[]args ={username};
        Cursor cursor = db.rawQuery("Select * from "+TABLE_NAME+" where "+TABLE_COL_USERNAME+" = ?",args);
        return cursor.getCount() > 0;
    }


    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args= {username,password};
        Cursor cursor = db.rawQuery("Select * from "+TABLE_NAME+" where username = ? and password = ?",args);
        return cursor.getCount() > 0;

    }
//    public Boolean checkusernamepassword(String username, String password){
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
//        if(cursor.getCount()>0)
//            return true;
//        else
//            return false;
    }

