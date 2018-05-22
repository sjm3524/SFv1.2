package com.example.steve.sfv12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button button = findViewById(R.id.login);

    }

    public void startMain(View view){
        Intent startMain =  new Intent(this, MainScreen.class);
        startActivity(startMain);
    }

    public void register(View view){
        Intent register =  new Intent(this, Register.class);
        startActivity(register);
    }


}
