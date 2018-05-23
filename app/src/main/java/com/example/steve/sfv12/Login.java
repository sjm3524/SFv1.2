package com.example.steve.sfv12;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button button = findViewById(R.id.login);

        final EditText iUsername = (EditText) findViewById(R.id.editText);
        final EditText iPassword = (EditText) findViewById(R.id.editText2);

        // LOGIN ATTEMPT FROM SAVED DATA
            SharedPreferences userData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

            String username = userData.getString("username", "");
            String password = userData.getString("password", "");

            Response.Listener<String> responseListener = login();

            LoginRequest login = new LoginRequest(username, password, responseListener);

            RequestQueue queue = Volley.newRequestQueue(Login.this);
            queue.add(login);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {








                String username = iUsername.getText().toString();
                String pass = iPassword.getText().toString();

                Response.Listener<String> responseListener = login();

                LoginRequest login = new LoginRequest(username, pass, responseListener);

                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(login);
            }
        });


    }


    public void register(View view){
        Intent register =  new Intent(this, Register.class);
        startActivity(register);
    }

    public Response.Listener<String> login(){
        Response.Listener<String> responseListener =  new Response.Listener<String>(){
            @Override
            public void onResponse (String response){
                try {


                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");


                    if (success) {

                        String name = jsonResponse.getString("name");
                        String username = jsonResponse.getString("username");
                        String email = jsonResponse.getString("email");
                        String pass = jsonResponse.getString("password");

                        User mainUser = new User(username, name);

                        SharedPreferences userData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = userData.edit();

                        editor.putString("username", username);
                        editor.putString("password", pass);
                        editor.apply();

                        Intent startMain = new Intent(Login.this, MainScreen.class);

                        startMain.putExtra("User", mainUser);
                        Login.this.startActivity(startMain);

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                        builder.setMessage("Failed to login")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                }
                catch(Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setMessage(e.getMessage())
                            .setNegativeButton(":(", null)
                            .create()
                            .show();
                }

            }
        };

        return responseListener;
    }


}
