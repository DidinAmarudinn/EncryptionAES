package com.example.encryptionanddecrytionaes.Activitites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.encryptionanddecrytionaes.R;

public class MainActivity extends AppCompatActivity {
    private Button btn_mulai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_mulai=findViewById(R.id.btn_mulai);

        btn_mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Encryption_Act.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
