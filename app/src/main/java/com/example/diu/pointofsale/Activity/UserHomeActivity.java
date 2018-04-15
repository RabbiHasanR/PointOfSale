package com.example.diu.pointofsale.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.diu.pointofsale.R;

public class UserHomeActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = UserHomeActivity.this;

    private CardView sales,product,report,customer,stock;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        initViews();
        initListner();
    }

    //initializ views
    public void initViews(){
        sales=(CardView)findViewById(R.id.salesCardView);
        product=(CardView)findViewById(R.id.productCardView);
        customer=(CardView)findViewById(R.id.customerCardView);
        report=(CardView)findViewById(R.id.reportCardView);
        stock=(CardView)findViewById(R.id.stockCardView);
    }

    //initialize listner
    public void initListner(){
        sales.setOnClickListener(this);
        product.setOnClickListener(this);
        customer.setOnClickListener(this);
        report.setOnClickListener(this);
        stock.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.salesCardView:
                intent=new Intent(".Activity.SalesActivity");
                startActivity(intent);
                break;

            case R.id.productCardView:
                intent=new Intent(".Activity.ProductActivity");
                startActivity(intent);
                break;
            case R.id.customerCardView:
                intent=new Intent(".Activity.CustomerActivity");
                startActivity(intent);
                break;
            case R.id.reportCardView:
                intent=new Intent(".Activity.ReportActivity");
                startActivity(intent);
                break;
            case R.id.stockCardView:
                intent=new Intent(".Activity.ProductDetailActivity");
                startActivity(intent);
                break;
        }
    }
}
