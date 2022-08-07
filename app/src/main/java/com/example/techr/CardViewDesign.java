package com.example.techr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CardViewDesign extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_design);
    }

    public void CPU(View view) {
        String id = "CPU";
        Bundle bundle = new Bundle();
        Intent i = new Intent(CardViewDesign.this,BrowseProducts.class);
        bundle.putString("Category",id);
        i.putExtras(bundle);
        startActivity(i);
    }
    public void CPUCooler(View view) {
        String id = "CPU Cooler";
        Bundle bundle = new Bundle();
        Intent i = new Intent(CardViewDesign.this,BrowseProducts.class);
        bundle.putString("Category",id);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void GPU(View view) {
        String id = "GPU";
        Bundle bundle = new Bundle();
        Intent i = new Intent(CardViewDesign.this,BrowseProducts.class);
        bundle.putString("Category",id);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void Motherboard(View view) {
        String id = "Motherboard";
        Bundle bundle = new Bundle();
        Intent i = new Intent(CardViewDesign.this,BrowseProducts.class);
        bundle.putString("Category",id);
        i.putExtras(bundle);
        startActivity(i);
    }
    public void PSU(View view) {
        String id = "PSU";
        Bundle bundle = new Bundle();
        Intent i = new Intent(CardViewDesign.this,BrowseProducts.class);
        bundle.putString("Category",id);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void Monitor(View view) {
        String id = "Monitor";
        Bundle bundle = new Bundle();
        Intent i = new Intent(CardViewDesign.this,BrowseProducts.class);
        bundle.putString("Category",id);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void Memory(View view) {
        String id = "Memory";
        Bundle bundle = new Bundle();
        Intent i = new Intent(CardViewDesign.this,BrowseProducts.class);
        bundle.putString("Category",id);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void Storage(View view) {
        String id = "Storage";
        Bundle bundle = new Bundle();
        Intent i = new Intent(CardViewDesign.this,BrowseProducts.class);
        bundle.putString("Category",id);
        i.putExtras(bundle);
        startActivity(i);
    }



    public void BackArrow(View view) {
        startActivity(new Intent(getApplicationContext(),Dashboard.class));
    }
}