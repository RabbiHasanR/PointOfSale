package com.example.diu.pointofsale.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.diu.pointofsale.Activity.UpdateUserActivity;
import com.example.diu.pointofsale.Database.DatabaseHelper;
import com.example.diu.pointofsale.Model.User;
import com.example.diu.pointofsale.R;

import java.util.ArrayList;
import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>  {
    private List<User> listUsers;
    private User user;
    private Context context;
    private DatabaseHelper myDb;
    private Intent intent;


    public UserRecyclerAdapter(List<User> listUsers,Context context) {
        this.listUsers = listUsers;
        this.context=context;
        myDb = new DatabaseHelper(this.context);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.users_profile_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, final int position) {
            holder.textViewName.setText(listUsers.get(position).getUserName());
            holder.textViewEmail.setText(listUsers.get(position).getEmail());
            holder.textViewType.setText(listUsers.get(position).getType());
            holder.textViewId.setText(String.valueOf(listUsers.get(position).getUserId()));

            //popup menu for card
        holder.textOption.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //display option menu
                        PopupMenu popupMenu=new PopupMenu(context,holder.textOption);
                        popupMenu.inflate(R.menu.popup_menu);
                        popupMenu.setOnMenuItemClickListener(
                                new PopupMenu.OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem item) {
                                        switch (item.getItemId()){
                                            case R.id.delete_menu:
                                                Log.d("Positoin",String.valueOf(position));
                                               deleteUser(position);
                                                //Toast.makeText(context, "Delete User", Toast.LENGTH_SHORT).show();
                                                break;
                                            case R.id.update_menu:
                                                Log.d("Positoin",String.valueOf(position));

                                                   updateUser(position);
                                                break;
                                            default:
                                                break;
                                        }
                                        return false;
                                    }
                                }
                        );
                        popupMenu.show();
                    }
                }
        );


    }

    //this method for delete user
    public void deleteUser(int position){
        String email=listUsers.get(position).getEmail();
        Log.d("ID",String.valueOf(email));
        boolean isDelete=myDb.deleteUse(email);
        this.listUsers.remove(position);
        if(isDelete){
            notifyDataSetChanged();
            notifyItemRemoved(position);
            Toast.makeText(context, "Delete User", Toast.LENGTH_SHORT).show();
        }


    }

    //this  method for update user
    public void updateUser(int position){
        intent = new Intent(context, UpdateUserActivity.class);
        intent.putExtra("name",listUsers.get(position).getUserName());
        intent.putExtra("email",listUsers.get(position).getEmail());
        intent.putExtra("password",listUsers.get(position).getPassword());
        intent.putExtra("phone",listUsers.get(position).getPhone());
        intent.putExtra("gender",listUsers.get(position).getGender());
        intent.putExtra("type",listUsers.get(position).getType());
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        Log.v(UserRecyclerAdapter.class.getSimpleName(),"Total item:"+listUsers.size());
        Log.v("Data:",listUsers.toString());
        return listUsers.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewType;
        public AppCompatTextView textViewId;
        public AppCompatTextView textOption;
        public UserViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail);
            textViewType= (AppCompatTextView) view.findViewById(R.id.textViewType);
            textViewId=(AppCompatTextView)view.findViewById(R.id.textViewId);
            textOption=(AppCompatTextView)view.findViewById(R.id.textOption);
        }
    }


}
