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

public class DatabaseHelper extends SQLiteOpenHelper implements IDatabase{
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

    //create table query
    //private static final String CREATE_TABLE_USER = "create table " + TABLE_USER + "(" + USER_TABLE_COLUM_ID + "'INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL'," + USER_TABLE_COLUM_NAME + "'TEXT'," + USER_TABLE_COLUM_EMAIL + "'TEXT NOT NULL'," + USER_TABLE_COLUM_PASSWORD + "'TEXT NOT NULL'," + USER_TABLE_COLUM_PHONE + "'TEXT NOT NULL'," + USER_TABLE_COLUM_GENDER + "'TEXT NOT NULL'," + USER_TABLE_COLUM_TYPE + "'TEXT NOT NULL'," + USER_TABLE_COLUM_PHOTO + "'BLOB'," + USER_TABLE_COLUM_CREATED_TIME + "'default (datetime(current_timestamp))'," + USER_TABLE_COLUM_UPDATE_TIME + "'datetime(current_timestamp)')";
    private static final String CREATE_TABLE_USER="CREATE TABLE " + TABLE_USER + "("
            + USER_TABLE_COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_TABLE_COLUM_NAME + " TEXT,"
            + USER_TABLE_COLUM_EMAIL + " TEXT,"+ USER_TABLE_COLUM_TYPE + " TEXT," + USER_TABLE_COLUM_GENDER + " TEXT,"+ USER_TABLE_COLUM_PHONE + " TEXT,"+ USER_TABLE_COLUM_PHOTO + " BLOB,"+ USER_TABLE_COLUM_CREATED_TIME + "INTEGER ,"+ USER_TABLE_COLUM_UPDATE_TIME+ "INTEGER ," + USER_TABLE_COLUM_PASSWORD + " TEXT" + ")";



    private SQLiteDatabase database;
    private ContentValues values;
    private User user;

    public DatabaseHelper(Context context) {
        super(context, DatabaseContents.DATABASE.toString(), null, 2);
        database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        //execute query
        database.execSQL(CREATE_TABLE_USER);
        /*database.execSQL("CREATE TABLE " + DatabaseContents.TABLE_USER + "("

                + "_id INTEGER PRIMARY KEY,"
                + "user_name TEXT(100),"
                + "email TEXT(100),"
                + "password TEXT(100),"
                + "phone TEXT(100),"
                + "gender TEXT(100),"
                + "type TEXT(100)"

                + ");");
        Log.d("CREATE DATABASE", "Create " + DatabaseContents.TABLE_PRODUCT_CATALOG + " Successfully.");*/
        database.execSQL("CREATE TABLE " + DatabaseContents.TABLE_PRODUCT_CATALOG + "("

                + "_id INTEGER PRIMARY KEY,"
                + "name TEXT(100),"
                + "barcode TEXT(100),"
                + "unit_price DOUBLE,"
                + "status TEXT(10)"

                + ");");
        Log.d("CREATE DATABASE", "Create " + DatabaseContents.TABLE_PRODUCT_CATALOG + " Successfully.");

        database.execSQL("CREATE TABLE "+ DatabaseContents.TABLE_STOCK + "("

                + "_id INTEGER PRIMARY KEY,"
                + "product_id INTEGER,"
                + "quantity INTEGER,"
                + "cost DOUBLE,"
                + "date_added DATETIME"

                + ");");
        Log.d("CREATE DATABASE", "Create " + DatabaseContents.TABLE_STOCK + " Successfully.");

        database.execSQL("CREATE TABLE "+ DatabaseContents.TABLE_SALE + "("

                + "_id INTEGER PRIMARY KEY,"
                + "status TEXT(40),"
                + "payment TEXT(50),"
                + "total DOUBLE,"
                + "start_time DATETIME,"
                + "end_time DATETIME,"
                + "orders INTEGER"

                + ");");
        Log.d("CREATE DATABASE", "Create " + DatabaseContents.TABLE_SALE + " Successfully.");

        database.execSQL("CREATE TABLE "+ DatabaseContents.TABLE_SALE_LINEITEM + "("

                + "_id INTEGER PRIMARY KEY,"
                + "sale_id INTEGER,"
                + "product_id INTEGER,"
                + "quantity INTEGER,"
                + "unit_price DOUBLE"

                + ");");
        Log.d("CREATE DATABASE", "Create " + DatabaseContents.TABLE_SALE_LINEITEM + " Successfully.");


        // this _id is product_id but for update method, it is easier to use name _id
        database.execSQL("CREATE TABLE " + DatabaseContents.TABLE_STOCK_SUM + "("

                + "_id INTEGER PRIMARY KEY,"
                + "quantity INTEGER"

                + ");");
        Log.d("CREATE DATABASE", "Create " + DatabaseContents.TABLE_STOCK_SUM + " Successfully.");

        Log.d("CREATE DATABASE", "Create Database Successfully.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // on upgrade drop older tables
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPE);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALES);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);

        // create new tables
        onCreate(db);
    }


    //save user information
    public long insertUserInfo(User user) {
        long a = 0;
        try {
            database = this.getReadableDatabase();
             values = new ContentValues();
            values.put(USER_TABLE_COLUM_NAME, user.getUserName());
            values.put(USER_TABLE_COLUM_EMAIL, user.getEmail());
            values.put(USER_TABLE_COLUM_PASSWORD, user.getPassword());
            values.put(USER_TABLE_COLUM_PHONE, user.getPhone());
            values.put(USER_TABLE_COLUM_GENDER, user.getGender());
            values.put(USER_TABLE_COLUM_TYPE, user.getType());
            values.put(USER_TABLE_COLUM_CREATED_TIME,System.currentTimeMillis());
            a = database.insert(TABLE_USER, null, values);

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
         database = this.getWritableDatabase();

         values = new ContentValues();
        values.put(USER_TABLE_COLUM_NAME, user.getUserName());
        values.put(USER_TABLE_COLUM_EMAIL, user.getEmail());
        values.put(USER_TABLE_COLUM_PASSWORD, user.getPassword());
        values.put(USER_TABLE_COLUM_PHONE, user.getPhone());
        values.put(USER_TABLE_COLUM_GENDER, user.getGender());
        values.put(USER_TABLE_COLUM_TYPE, user.getType());
        values.put(USER_TABLE_COLUM_UPDATE_TIME,System.currentTimeMillis());

        // updating row
        database.update(TABLE_USER, values, USER_TABLE_COLUM_ID+ " = ?",
                new String[]{String.valueOf(user.getUserId())});
        database.close();
    }
    /**
     * fetch specific raw using id from sqlite database and get data
     */

    public Cursor getData(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM user WHERE email='"+email+"'";
        Cursor res =  db.rawQuery( query,null );

        if(res != null ) {
            /*user.setUserName(res.getString(res.getColumnIndex(USER_TABLE_COLUM_NAME)));
            user.setPhone(res.getString(res.getColumnIndex(USER_TABLE_COLUM_PHONE)));
            user.setEmail(res.getString(res.getColumnIndex(USER_TABLE_COLUM_EMAIL)));
            user.setGender(res.getString(res.getColumnIndex(USER_TABLE_COLUM_GENDER)));
            user.setType(res.getString(res.getColumnIndex(USER_TABLE_COLUM_TYPE)));
            user.setPhoto(res.getBlob(res.getColumnIndex(USER_TABLE_COLUM_PHOTO)));*/
            res.moveToFirst();
        }
        return res;

    }

    /**
     * This method is to delete user record
     *
     */
    public boolean deleteUse(String email){
         database = getWritableDatabase();
         database.delete(TABLE_USER, USER_TABLE_COLUM_EMAIL + "=?"  ,new  String[]{String.valueOf(email)});
         database.close();
        return true;
    }

    /**
     * this method insert image
     */
     public long insertImage(String email,User user){
         database = getWritableDatabase();
         long id=0;
         try{
             values=new ContentValues();
             values.put(USER_TABLE_COLUM_PHOTO,user.getPhoto());
             String[] selectionArgs={String.valueOf(email)};
             id=database.update(TABLE_USER,values,USER_TABLE_COLUM_EMAIL +"=?",selectionArgs);
         }
         catch (SQLException exception){
             Log.d("UserInfo Insert:", exception.toString());
         }

         return id;

     }


    /**
     * product inser,update,select,execute
     */

    @Override
    public List<Object> select(String queryString) {
        try {
            database = this.getWritableDatabase();
            List<Object> list = new ArrayList<Object>();
            Cursor cursor = database.rawQuery(queryString, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        ContentValues content = new ContentValues();
                        String[] columnNames = cursor.getColumnNames();
                        for (String columnName : columnNames) {
                            content.put(columnName, cursor.getString(cursor
                                    .getColumnIndex(columnName)));
                        }
                        list.add(content);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            database.close();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insert(String tableName, Object content) {
        try {
             database = this.getWritableDatabase();
            int id = (int) database.insert(tableName, null,
                    (ContentValues) content);
            database.close();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public boolean update(String tableName, Object content) {
        try {
             database = this.getWritableDatabase();
            ContentValues cont = (ContentValues) content;
            // this array will always contains only one element.
            String[] array = new String[]{cont.get("_id")+""};
            database.update(tableName, cont, " _id = ?", array);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String tableName, int id) {
        try {
             database = this.getWritableDatabase();
            database.delete(tableName, " _id = ?", new String[]{id+""});
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean execute(String query) {
        try{
             database = this.getWritableDatabase();
            database.execSQL(query);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
