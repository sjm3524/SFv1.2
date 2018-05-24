package com.example.steve.sfv12;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.steve.sfv12.server_requests.RegisterRequest;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText iName = (EditText)findViewById(R.id.name);
        final EditText iUsername = (EditText)findViewById(R.id.username);
        final EditText iPassword = (EditText)findViewById(R.id.password);
        final EditText iEmail = (EditText)findViewById(R.id.editText7);

        Button register = (Button)findViewById(R.id.button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = iName.getText().toString();
                String username = iUsername.getText().toString();
                String password = iPassword.getText().toString();
                String email = iEmail.getText().toString();

                Response.Listener<String> respList = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            if (response.equals("true")){
                                Intent intent = new Intent(Register.this, Login.class);
                                Register.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                builder.setMessage("Failed to register")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        }
                        catch(Exception e){
                            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                            builder.setMessage("Exception " +e.getMessage())
                                    .setNegativeButton("Close", null)
                                    .create()
                                    .show();
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest reg = new RegisterRequest(name, username, email, password, respList);
                RequestQueue queue = Volley.newRequestQueue(Register.this);
                queue.add(reg);

            }
        });
    }
}
