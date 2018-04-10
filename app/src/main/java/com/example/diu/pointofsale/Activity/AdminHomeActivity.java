package com.example.diu.pointofsale.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diu.pointofsale.Adapter.UserRecyclerAdapter;
import com.example.diu.pointofsale.Database.DatabaseHelper;
import com.example.diu.pointofsale.Model.User;
import com.example.diu.pointofsale.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AdminHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,View.OnClickListener{

    private AppCompatActivity activity=AdminHomeActivity.this;
    private AppCompatTextView appCompatTextView;
    private RecyclerView recyclerViewUsers;
    private List<User> listUsers;
    private UserRecyclerAdapter userRecyclerAdapter;
    private DatabaseHelper myDb;
    private DrawerLayout drawerLayout;
    private FloatingActionButton floatingActionButtonInsert,floatingActionButtonCamera;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private Intent intent;
    private static Integer REQUEST_CAMERA=1,SELECT_FILE=0;
    private byte[] byteArray ;
    private ImageView captreImage;
    private String emailFromIntent;
    private Button saveImage;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        initView();
        initObjects();
        initListner();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            System.exit(0);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * this method initializ listner
     */
    public void initListner(){

        floatingActionButtonInsert.setOnClickListener(this);
        floatingActionButtonCamera.setOnClickListener(this);
        saveImage.setOnClickListener(this);
    }

    /**
     * this method initilaiz all views
     */

    public void initView(){
        appCompatTextView=(AppCompatTextView)findViewById(R.id.textViewName);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        floatingActionButtonInsert=(FloatingActionButton)findViewById(R.id.fab);
        //floatingActionButtonCamera=(FloatingActionButton)findViewById(R.id.fab_camera);
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        floatingActionButtonCamera=(FloatingActionButton)header.findViewById(R.id.fab_camera);
        captreImage=(ImageView)header.findViewById(R.id.imageView);
        saveImage=(Button)header.findViewById(R.id.saveImage);
        recyclerViewUsers=(RecyclerView)findViewById(R.id.recyclerViewUsers);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listUsers = new ArrayList<>();
        userRecyclerAdapter = new UserRecyclerAdapter(listUsers,activity);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(userRecyclerAdapter);
        myDb = new DatabaseHelper(activity);
        emailFromIntent = getIntent().getStringExtra("EMAIL");
        appCompatTextView.setText(String.valueOf(emailFromIntent));
        getDataFromSQLite();
    }

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listUsers.clear();
                listUsers.addAll(myDb.getAllUser());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                userRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myProfile) {
            sendData();
            // Handle the camera action
        } else if (id == R.id.nav_add_new_user) {
            intent=new Intent(".Activity.SignUpActivity");
            startActivity(intent);

        }else if (id==R.id.nav_logout){
            finish();
        }
        else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                intent=new Intent(".Activity.SignUpActivity");
                startActivity(intent);
                break;
            case R.id.fab_camera:
                selectImage();
                break;
            case R.id.saveImage:
                setImage();
                break;
            default:
                break;
        }
    }

    //selectImage method
    public void selectImage(){
        final CharSequence[] item={"Camera","Gallery","Cancel"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this.activity);
        builder.setTitle("Add Image");
        builder.setItems(item,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(item[which].equals("Camera")){
                            intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent,REQUEST_CAMERA);
                            //startActivity(intent);

                        }else if (item[which].equals("Gallery")){
                            intent=new Intent(intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent,SELECT_FILE);
                            //startActivity(intent);

                        }else if (item[which].equals("Cancel")){
                            dialog.dismiss();
                        }
                    }
                });
        //show alertDialog box
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== this.RESULT_OK){
            if(requestCode== REQUEST_CAMERA){
                Bundle bundle=data.getExtras();
                Bitmap bmp=(Bitmap)bundle.get("data");
                captreImage.setImageBitmap(bmp);
                //create object ByteArrayOutputStream
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                //convert bitmap to byteArray
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();

            }else if (requestCode==SELECT_FILE){
                Uri selectImageData=data.getData();
                captreImage.setImageURI(selectImageData);

            }
        }
    }

    //set image
    public void setImage(){
         user=new User();
        user.setPhoto(byteArray);
        myDb.insertImage(emailFromIntent,user);


    }

    //pass data for profile
    public void sendData(){
        intent=new Intent(".Activity.ProfileActivity");
        //intent.putExtra("email",emailFromIntent);
        startActivity(intent);
    }



}
