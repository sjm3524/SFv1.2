package com.example.steve.sfv12;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.steve.sfv12.server_requests.FriendRequest;
import com.example.steve.sfv12.server_requests.SearchRequest;

import org.json.JSONObject;

import java.util.regex.Pattern;

public class AddFriends extends AppCompatActivity {

    User mainUser;
    Dialog friendUpAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        final EditText searchUser = (EditText)findViewById(R.id.editText4);
        ImageView search = (ImageView)findViewById(R.id.imageView);
        final LinearLayout ll = (LinearLayout)findViewById(R.id.llFriends);

        friendUpAction = new Dialog(this);

        Intent intent = getIntent();
        mainUser= (User)intent.getSerializableExtra("User");

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = searchUser.getText().toString();

                ll.removeAllViews();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse (String response){
                        try {


                            String[] results = response.split(Pattern.quote("}"));

                            //System.out.println(results[0]);

                            for (int i = 0; i < results.length; i++) {
                                results[i]=results[i]+"}";
                                JSONObject jsonResponse = new JSONObject(results[i]);
                                boolean success = jsonResponse.getBoolean("success");


                                if (success) {

                                    final String name = jsonResponse.getString("name");
                                    final String username = jsonResponse.getString("username");
                                    final String friendID = jsonResponse.getString("userID");


                                    Button myButton = new Button(AddFriends.this);
                                    myButton.setText(name + ": " + username);

                                    myButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            friendPopUp(view, name, username);

                                        }
                                    });


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

    public Response.Listener<String> sendRequest(final String friendName){


        Response.Listener<String> responseListener =  new Response.Listener<String>(){
            @Override
            public void onResponse (String response){
                try {





                    if (response.equals("true")) {


                        Toast.makeText(AddFriends.this, "Friend request sent to "+friendName, Toast.LENGTH_LONG).show();



                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddFriends.this);
                        builder.setMessage("Failed to send request")
                                .setNegativeButton("Retry", null)
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

        return responseListener;
    }

    public void friendPopUp(View v, String uName, final String uUsername){
        friendUpAction.setContentView(R.layout.friend_layout);
        TextView close = (TextView) friendUpAction.findViewById(R.id.closex);
        TextView name = (TextView) friendUpAction.findViewById(R.id.textView11);
        TextView username = (TextView) friendUpAction.findViewById(R.id.textView12);

        name.setText(uName);
        username.setText("@"+uUsername);

        ImageView add = (ImageView)friendUpAction.findViewById(R.id.imageView4);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FriendRequest search = new FriendRequest(mainUser.username, uUsername, sendRequest(uUsername));

                RequestQueue queue = Volley.newRequestQueue(AddFriends.this);
                queue.add(search);


            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friendUpAction.dismiss();
            }
        });

        friendUpAction.show();
    }
}
