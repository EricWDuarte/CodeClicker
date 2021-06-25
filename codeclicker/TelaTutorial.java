package com.example.codeclicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class TelaTutorial extends AppCompatActivity {
    ConstraintLayout click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.telatutorial);
        getSupportActionBar().hide();
        click = findViewById(R.id.layoutClickTutorial);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proxTela();
            }
        });
    }

    @Override
    public void onBackPressed() {
    }

    public void proxTela(){
        startActivity(new Intent(TelaTutorial.this,TelaJogo.class));
    }
}