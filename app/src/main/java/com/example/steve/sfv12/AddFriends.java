package com.example.steve.sfv12;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class AddFriends extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        final EditText searchUser = (EditText)findViewById(R.id.editText4);
        ImageView search = (ImageView)findViewById(R.id.imageView);
        final LinearLayout ll = (LinearLayout)findViewById(R.id.llFriends);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = searchUser.getText().toString();

                ll.removeAllViews();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse (String response){
                        try {


                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");


                            if (success) {

                                String name = jsonResponse.getString("name");
                                String username = jsonResponse.getString("username");



                                Button myButton = new Button(AddFriends.this);
                                myButton.setText(name + ": " +username);


                                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                ll.addView(myButton, lp);


                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddFriends.this);
                                builder.setMessage("Could not find user")
                                        .setNegativeButton("Okay", null)
                                        .create()
                                        .show();
                            }
                        }
                        catch(Exception e){
                            AlertDialog.Builder builder = new AlertDialog.Builder(AddFriends.this);
                            builder.setMessage(e.getMessage())
                                    .setNegativeButton(":(", null)
                                    .create()
                                    .show();
                        }

                    }
                };

                SearchRequest search = new SearchRequest(username, responseListener);

                RequestQueue queue = Volley.newRequestQueue(AddFriends.this);
                queue.add(search);
            }

        });
    }
}
