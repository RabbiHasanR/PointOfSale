package com.example.diu.pointofsale.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.diu.pointofsale.Adapter.ButtonAdapter;
import com.example.diu.pointofsale.Database.DatabaseExecutor;
import com.example.diu.pointofsale.Database.NoDaoSetException;
import com.example.diu.pointofsale.Model.Inventory.Inventory;
import com.example.diu.pointofsale.Model.Inventory.Product;
import com.example.diu.pointofsale.Model.Inventory.ProductCatalog;
import com.example.diu.pointofsale.Model.Sale.Register;
import com.example.diu.pointofsale.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductActivity extends AppCompatActivity  {
    private final AppCompatActivity activity =ProductActivity.this;

    protected static final int SEARCH_LIMIT = 0;
    private ListView inventoryListView;
    private ProductCatalog productCatalog;
    private List<Map<String, String>> inventoryList;
    private Button addProductButton;
    private EditText searchBox;
    private Button scanButton;
    private Register register;
    //private Button productDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        try {
            productCatalog = Inventory.getInstance().getProductCatalog();
            register = Register.getInstance();
        } catch (NoDaoSetException e) {
            e.printStackTrace();
        }

        inventoryListView = (ListView) findViewById(R.id.productListView);
        addProductButton = (Button) findViewById(R.id.addProductButton);
        scanButton = (Button) findViewById(R.id.scanButton);
        searchBox = (EditText) findViewById(R.id.searchBox);

        addProductButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(".Activity.AddProductActivity");
                        startActivity(intent);
                    }
                }
        );

    }

    /**
     * Initiate this UI.
     */
    private void initUI() {

        addProductButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //showPopup(v);
            }
        });

        searchBox.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                if (s.length() >= SEARCH_LIMIT) {
                    //search();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        inventoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {
                //int id = Integer.parseInt(inventoryList.get(position).get("id").toString());

               // register.addItem(productCatalog.getProductById(id), 1);
                //saleFragment.update();
                //viewPager.setCurrentItem(1);
            }
        });

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //IntentIntegratorSupportV4 scanIntegrator = new IntentIntegratorSupportV4(activity);
                //scanIntegrator.initiateScan();
            }
        });

    }

    /**
     * Show list.
     * @param list
     */
  /*  private void showList(List<Product> list) {

        inventoryList = new ArrayList<Map<String, String>>();
        for(Product product : list) {
            inventoryList.add(product.toMap());
        }

        ButtonAdapter sAdap = new ButtonAdapter(activity.getBaseContext(), inventoryList,
                R.layout.listview_inventory, new String[]{"name"}, new int[] {R.id.name}, R.id, "id");
        inventoryListView.setAdapter(sAdap);
    }

    /**
     * Search.
     */
   /* private void search() {
        String search = searchBox.getText().toString();

        if (search.equals("/demo")) {
            //testAddProduct();
            searchBox.setText("");
        } else if (search.equals("/clear")) {
            DatabaseExecutor.getInstance().dropAllData();
            searchBox.setText("");
        }
        else if (search.equals("")) {
            showList(productCatalog.getAllProduct());
        } else {
            List<Product> result = productCatalog.searchProduct(search);
            showList(result);
            if (result.isEmpty()) {

            }
        }
    }

    @Override
   /* public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, intent);

        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            searchBox.setText(scanContent);
        } else {
            Toast.makeText(activity.getBaseContext(), "fail",
                    Toast.LENGTH_SHORT).show();
        }
    }*/



}
