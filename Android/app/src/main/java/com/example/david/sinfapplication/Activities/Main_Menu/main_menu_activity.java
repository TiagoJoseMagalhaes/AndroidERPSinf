package com.example.david.sinfapplication.Activities.Main_Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.david.sinfapplication.Activities.product_catalog.product_catalog_activity;
import com.example.david.sinfapplication.R;

public class main_menu_activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }

    public void toCatalog(View view)
    {
        Intent intent = new Intent(this,product_catalog_activity.class);
        this.startActivity(intent);
    }

}