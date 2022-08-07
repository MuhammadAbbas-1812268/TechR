package com.example.techr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PopActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        DisplayMetrics DM = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(DM);

        int width = DM.widthPixels;
        int height = DM.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x=0;
        params.y=100;

        getWindow().setAttributes(params);
        Button btnsrch = (Button) findViewById(R.id.BtnSearchFilter);

        EditText Cpu,Motherboard,Gpu;
        Cpu = findViewById(R.id.CPUName);
        Motherboard = findViewById(R.id.BoardName);
        Gpu = findViewById(R.id.CardName);


        btnsrch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cpu = Cpu.getText().toString();
                String motherboard = Motherboard.getText().toString();
                String gpu = Gpu.getText().toString();
                if (cpu == "")
                    cpu = null;
                if (motherboard == "")
                    motherboard = null;
                if (gpu == "")
                    gpu = null;
                Bundle bundle = new Bundle();
                Intent i = new Intent(PopActivity.this,SystemBuilder.class);
                bundle.putString("CPU",cpu);
                bundle.putString("Motherboard",motherboard);
                bundle.putString("GPU",gpu);
                i.putExtras(bundle);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"come"+cpu,Toast.LENGTH_SHORT).show();

            }
        });


    }


}