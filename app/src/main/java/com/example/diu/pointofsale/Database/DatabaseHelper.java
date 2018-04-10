package com.example.diu.pointofsale.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import com.example.diu.pointofsale.Model.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static android.os.Build.ID;

public class DatabaseHelper extends SQLiteOpenHelper {
    //INITIalize variable for database
    private static final String DATABASE_NAME = "pointOfSale.db";
    private static final String TABLE_USER = "user";
    private static final String USER_TABLE_COLUM_ID = "id";
    private static final String USER_TABLE_COLUM_NAME = "user_name";
    private static final String USER_TABLE_COLUM_EMAIL = "email";
    private static final String USER_TABLE_COLUM_PASSWORD = "password";
    private static final String USER_TABLE_COLUM_PHONE = "phone";
    private static final String USER_TABLE_COLUM_GENDER = "gender";
    private static final String USER_TABLE_COLUM_TYPE = "user_type";
    private static final String USER_TABLE_COLUM_PHOTO = "photo";
    private static final String USER_TABLE_COLUM_CREATED_TIME = "create_time";
    private static final String USER_TABLE_COLUM_UPDATE_TIME = "update_time";


    private static final String TABLE_PRODUCT = "product";
    private static final String PRODUCT_TABLE_COLUM_ID = "id";
    private static final String PRODUCT_TABLE_COLUM_NAME = "name";
    private static final String PRODUCT_TABLE_COLUM_DESCRIPTION = "description";
    private static final String PRODUCT_TABLE_COLUM_BARCODE = "product_barcode";
    private static final String PRODUCT_TABLE_COLUM_SELLING_PRICE = "selling_price";
    private static final String PRODUCT_TABLE_COLUM_BUYING_PRICE = "buying_price";
    private static final String PRODUCT_TABLE_COLUM_IMAGE = "image";
    private static final String PRODUCT_TABLE_COLUM_CATAGORY = "catagory";
    private static final String PRODUCT_TABLE_COLUM_SIZE = "size";
    private static final String PRODUCT_TABLE_COLUM_USER_ID = "user_id";
    private static final String PRODUCT_TABLE_COLUM_QUANTITY = "quantity";
    private static final String PRODUCT_TABLE_COLUM_INSERT_TIME = "INSERT_TIME";
    private static final String PRODUCT_TABLE_COLUM_UPDATE_TIME = "update_time";

    private static final String TABLE_SHOPE = "shope";
    private static final String SHOPE_TABLE_COLUM_ID = "id";
    private static final String SHOPE_TABLE_COLUM_NAME = "shope_name";
    private static final String SHOPE_TABLE_COLUM_EMAIL = "email";
    private static final String SHOPE_TABLE_COLUM_PHONE = "phone";
    private static final String SHOPE_TABLE_COLUM_ADDRESS = "address";
    private static final String SHOPE_TABLE_COLUM_BANNERIMAGE = "banner_image";
    private static final String SHOPE_TABLE_COLUM_CREATED_DATE = "created_date";
    private static final String SHOPE_TABLE_COLUM_UPDATE_DATE = "update_date";
    private static final String SHOPE_TABLE_COLUM_USER_ID = "shope_user_id";

    private static final String TABLE_CUSTOMER = "customer";
    private static final String CUSTOMER_TABLE_COLUM_ID = "id";
    private static final String CUSTOMER_TABLE_COLUM_NAME = "name";
    private static final String CUSTOMER_TABLE_COLUM_EMAIL = "email";
    private static final String CUSTOMER_TABLE_COLUM_PHONE = "phone";
    private static final String CUSTOMER_TABLE_COLUM_ADDRESS = "address";

    /*private static final String TABLE_SALES="sales";
    private static final String SALES_TABLE_COLUM_ID="id";
    private static final String SALES_TABLE_COLUM_PRODUCT_ID="product_id";
    private static final String SALES_TABLE_COLUM_USER_ID="user_id";
    private static final String SALES_TABLE_COLUM_QUANTITY="quantity";
    private static final String SALES_TABLE_COLUM_CUSTOMER_ID="customer_id";
    private static final String SALES_TABLE_COLUM_SALES_DATE="sales_date";

    private static final String TABLE_CART="cart";
    private static final String CART_TABLE_COLUM_ID="id";
    private static final String CART_TABLE_COLUM_PRODUCT_ID="product_id";
    private static final String CART_TABLE_COLUM_USER_ID="user_id";
    private static final String CART_TABLE_COLUM_CART_TIME="cart_time";
    private static final String CART_TABLE_COLUM_QUANTITY="quantity";*/

    //create table query
    //private static final String CREATE_TABLE_USER = "create table " + TABLE_USER + "(" + USER_TABLE_COLUM_ID + "'INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL'," + USER_TABLE_COLUM_NAME + "'TEXT'," + USER_TABLE_COLUM_EMAIL + "'TEXT NOT NULL'," + USER_TABLE_COLUM_PASSWORD + "'TEXT NOT NULL'," + USER_TABLE_COLUM_PHONE + "'TEXT NOT NULL'," + USER_TABLE_COLUM_GENDER + "'TEXT NOT NULL'," + USER_TABLE_COLUM_TYPE + "'TEXT NOT NULL'," + USER_TABLE_COLUM_PHOTO + "'BLOB'," + USER_TABLE_COLUM_CREATED_TIME + "'default (datetime(current_timestamp))'," + USER_TABLE_COLUM_UPDATE_TIME + "'datetime(current_timestamp)')";
    private static final String CREATE_TABLE_USER="CREATE TABLE " + TABLE_USER + "("
            + USER_TABLE_COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_TABLE_COLUM_NAME + " TEXT,"
            + USER_TABLE_COLUM_EMAIL + " TEXT,"+ USER_TABLE_COLUM_TYPE + " TEXT," + USER_TABLE_COLUM_GENDER + " TEXT,"+ USER_TABLE_COLUM_PHONE + " TEXT,"+ USER_TABLE_COLUM_PHOTO + " BLOB,"+ USER_TABLE_COLUM_CREATED_TIME + "INTEGER ,"+ USER_TABLE_COLUM_UPDATE_TIME+ "INTEGER ," + USER_TABLE_COLUM_PASSWORD + " TEXT" + ")";
    private static final String CREATE_TABLE_PRODUCT = "create table " + TABLE_PRODUCT + "(" + PRODUCT_TABLE_COLUM_ID + "'INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL'," + PRODUCT_TABLE_COLUM_NAME + "'TEXT NOT NULL'," + PRODUCT_TABLE_COLUM_DESCRIPTION + "'TEXT NOT NULL'," + PRODUCT_TABLE_COLUM_BARCODE + "'TEXT NOT NULL'," + PRODUCT_TABLE_COLUM_SELLING_PRICE + "'REAL NOT NULL'," + PRODUCT_TABLE_COLUM_BUYING_PRICE + "'REAL NOT NULL'," + PRODUCT_TABLE_COLUM_IMAGE + "'BLOB'," + PRODUCT_TABLE_COLUM_CATAGORY + "'TEXT NOT NULL'," + PRODUCT_TABLE_COLUM_SIZE + "'TEXT NOT NULL'," + PRODUCT_TABLE_COLUM_QUANTITY + "'INTEGER NOT NULL'," + PRODUCT_TABLE_COLUM_INSERT_TIME + "'default (datetime(current_timestamp))'," + PRODUCT_TABLE_COLUM_UPDATE_TIME + "'datetime(current_timestamp)'," + PRODUCT_TABLE_COLUM_USER_ID + "'INTEGER'," + " FOREIGN KEY (" + PRODUCT_TABLE_COLUM_USER_ID + ") REFERENCES " + TABLE_USER + " (" + USER_TABLE_COLUM_ID + "))";
    private static final String CREATE_TABLE_SHOPE = "create table " + TABLE_SHOPE + "(" + SHOPE_TABLE_COLUM_ID + "'INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL'," + SHOPE_TABLE_COLUM_NAME + "'TEXT NOT NULL'," + SHOPE_TABLE_COLUM_EMAIL + "'TEXT NOT NULL'," + SHOPE_TABLE_COLUM_PHONE + "'TEXT NOT NULL'," + SHOPE_TABLE_COLUM_ADDRESS + "'TEXT NOT NULL'," + SHOPE_TABLE_COLUM_BANNERIMAGE + "'BLOB'," + USER_TABLE_COLUM_TYPE + "'TEXT NOT NULL'," + SHOPE_TABLE_COLUM_CREATED_DATE + "'default (datetime(current_timestamp))'," + SHOPE_TABLE_COLUM_UPDATE_DATE + "'datetime(current_timestamp)'," + SHOPE_TABLE_COLUM_USER_ID + "'INTEGER'," + " FOREIGN KEY (" + SHOPE_TABLE_COLUM_USER_ID + ") REFERENCES " + TABLE_USER + " (" + USER_TABLE_COLUM_ID + "))";
    private static final String CREATE_TABLE_CUSTOMER = "create table " + TABLE_CUSTOMER + "(" + CUSTOMER_TABLE_COLUM_ID + "'INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL'," + CUSTOMER_TABLE_COLUM_NAME + "'TEXT NOT NULL'," + CUSTOMER_TABLE_COLUM_EMAIL + "'TEXT NOT NULL'," + CUSTOMER_TABLE_COLUM_PHONE + "'TEXT NOT NULL'," + CUSTOMER_TABLE_COLUM_ADDRESS + "'TEXT NOT NULL')";
    //private static final String CREATE_TABLE_SALES="create table "+TABLE_SALES+"(" +SALES_TABLE_COLUM_ID+ "'INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL',"+SALES_TABLE_COLUM_QUANTITY+"'INTEGER NOT NULL',"+SALES_TABLE_COLUM_SALES_DATE+"'datetime()',"+SALES_TABLE_COLUM_USER_ID+"'INTEGER',"+ " FOREIGN KEY ("+SALES_TABLE_COLUM_USER_ID+") REFERENCES "+TABLE_USER+" ("+USER_TABLE_COLUM_ID+"),"+SALES_TABLE_COLUM_PRODUCT_ID+"'INTEGER',"+ " FOREIGN KEY ("+SALES_TABLE_COLUM_PRODUCT_ID+") REFERENCES "+TABLE_PRODUCT+" ("+PRODUCT_TABLE_COLUM_USER_ID+"),"+SALES_TABLE_COLUM_CUSTOMER_ID+"'INTEGER',"+ " FOREIGN KEY ("+SALES_TABLE_COLUM_CUSTOMER_ID+") REFERENCES "+TABLE_CUSTOMER+" ("+CUSTOMER_TABLE_COLUM_ID+"))";
    //private static final String CREATE_TABLE_CART="create table "+TABLE_CART+"(" +CART_TABLE_COLUM_ID+ "'INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL',"+CART_TABLE_COLUM_QUANTITY+"'INTEGER NOT NULL',"+CART_TABLE_COLUM_CART_TIME+"'datetime()',"+CART_TABLE_COLUM_USER_ID+"'INTEGER',"+ " FOREIGN KEY ("+CART_TABLE_COLUM_USER_ID+") REFERENCES "+TABLE_USER+" ("+USER_TABLE_COLUM_ID+"),"+CART_TABLE_COLUM_PRODUCT_ID+"'INTEGER',"+ " FOREIGN KEY ("+CART_TABLE_COLUM_PRODUCT_ID+") REFERENCES "+TABLE_PRODUCT+" ("+PRODUCT_TABLE_COLUM_USER_ID+"))";


    private SQLiteDatabase db;
    private ContentValues values;
    private User user;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //execute query
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_SHOPE);
        db.execSQL(CREATE_TABLE_CUSTOMER);
        //db.execSQL(CREATE_TABLE_SALES);
        //db.execSQL(CREATE_TABLE_CART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // on upgrade drop older tables
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALES);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);

        // create new tables
        onCreate(db);
    }


    //save user information
    public long insertUserInfo(User user) {
        long a = 0;
        try {
            db = this.getReadableDatabase();
             values = new ContentValues();
            values.put(USER_TABLE_COLUM_NAME, user.getUserName());
            values.put(USER_TABLE_COLUM_EMAIL, user.getEmail());
            values.put(USER_TABLE_COLUM_PASSWORD, user.getPassword());
            values.put(USER_TABLE_COLUM_PHONE, user.getPhone());
            values.put(USER_TABLE_COLUM_GENDER, user.getGender());
            values.put(USER_TABLE_COLUM_TYPE, user.getType());
            values.put(USER_TABLE_COLUM_CREATED_TIME,System.currentTimeMillis());
            a = db.insert(TABLE_USER, null, values);

        } catch (SQLException exception) {
            Log.d("UserInfo Insert:", exception.toString());
        }
        return a;
    }

    //save product information

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                USER_TABLE_COLUM_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = USER_TABLE_COLUM_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);
        //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {

            return true;
        }

        return false;
    }

    //login
    public boolean verifyLogin(String email, String password, String userType) {

        // array of columns to fetch
        String[] columns = {
                USER_TABLE_COLUM_ID,
                USER_TABLE_COLUM_NAME
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = USER_TABLE_COLUM_EMAIL + " = ?" + " AND " + USER_TABLE_COLUM_PASSWORD + " = ?" + " AND " + USER_TABLE_COLUM_TYPE + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password, userType};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);
        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                USER_TABLE_COLUM_ID,
                USER_TABLE_COLUM_NAME,
                USER_TABLE_COLUM_EMAIL,
                USER_TABLE_COLUM_PASSWORD,
                USER_TABLE_COLUM_PHONE,
                USER_TABLE_COLUM_GENDER,
                USER_TABLE_COLUM_TYPE
        };
        // sorting orders
        String sortOrder =
                USER_TABLE_COLUM_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                 user = new User();
                    user.setUserId(cursor.getInt(cursor.getColumnIndex(USER_TABLE_COLUM_ID)));
                    user.setUserName(cursor.getString(cursor.getColumnIndex(USER_TABLE_COLUM_NAME)));
                    user.setEmail(cursor.getString(cursor.getColumnIndex(USER_TABLE_COLUM_EMAIL)));
                    user.setType(cursor.getString(cursor.getColumnIndex(USER_TABLE_COLUM_TYPE)));
                    user.setPassword(cursor.getString(cursor.getColumnIndex(USER_TABLE_COLUM_PASSWORD)));
                    user.setPhone(cursor.getString(cursor.getColumnIndex(USER_TABLE_COLUM_PHONE)));
                    user.setGender(cursor.getString(cursor.getColumnIndex(USER_TABLE_COLUM_GENDER)));
                    // Adding user record to list
                    userList.add(user);



            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
         db = this.getWritableDatabase();

         values = new ContentValues();
        values.put(USER_TABLE_COLUM_NAME, user.getUserName());
        values.put(USER_TABLE_COLUM_EMAIL, user.getEmail());
        values.put(USER_TABLE_COLUM_PASSWORD, user.getPassword());
        values.put(USER_TABLE_COLUM_PHONE, user.getPhone());
        values.put(USER_TABLE_COLUM_GENDER, user.getGender());
        values.put(USER_TABLE_COLUM_TYPE, user.getType());
        values.put(USER_TABLE_COLUM_UPDATE_TIME,System.currentTimeMillis());

        // updating row
        db.update(TABLE_USER, values, USER_TABLE_COLUM_ID+ " = ?",
                new String[]{String.valueOf(user.getUserId())});
        db.close();
    }
    /**
     * fetch specific raw using id from sqlite database and get data
     */

    public Cursor getData(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM user WHERE email='"+email+"'";
        Cursor res =  db.rawQuery( query,null );

        if(res != null && res.moveToFirst()) {
            user.setUserName(res.getString(res.getColumnIndex(USER_TABLE_COLUM_NAME)));
            user.setPhone(res.getString(res.getColumnIndex(USER_TABLE_COLUM_PHONE)));
            user.setEmail(res.getString(res.getColumnIndex(USER_TABLE_COLUM_EMAIL)));
            user.setGender(res.getString(res.getColumnIndex(USER_TABLE_COLUM_GENDER)));
            user.setType(res.getString(res.getColumnIndex(USER_TABLE_COLUM_TYPE)));
            user.setPhoto(res.getBlob(res.getColumnIndex(USER_TABLE_COLUM_PHOTO)));
            return res;
        }
        return res;

    }

    /**
     * This method is to delete user record
     *
     */
    public boolean deleteUse(String email){
         db = getWritableDatabase();
         db.delete(TABLE_USER, USER_TABLE_COLUM_EMAIL + "=?"  ,new  String[]{String.valueOf(email)});
         db.close();
        return true;
    }

    /**
     * this method insert image
     */
     public long insertImage(String email,User user){
         db = getWritableDatabase();
         long id=0;
         try{
             values=new ContentValues();
             values.put(USER_TABLE_COLUM_PHOTO,user.getPhoto());
             String[] selectionArgs={String.valueOf(email)};
             id=db.update(TABLE_USER,values,USER_TABLE_COLUM_EMAIL +"=?",selectionArgs);
         }
         catch (SQLException exception){
             Log.d("UserInfo Insert:", exception.toString());
         }

         return id;

     }

}
