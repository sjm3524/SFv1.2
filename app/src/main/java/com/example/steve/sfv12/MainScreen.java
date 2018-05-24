package com.example.steve.sfv12;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {
    User mainUser;
    Dialog popUpAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        popUpAction = new Dialog(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setBackgroundDrawable();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionPopUp(view);
            }
        });

        Intent intent = getIntent();
        mainUser= (User)intent.getSerializableExtra("User");



        //generateFriendList(mainUser);
        //genOtherLists(mainUser);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menInf = getMenuInflater();
        menInf.inflate(R.menu.app_bar_menu, menu);
        return true;

    }


    public void actionPopUp(View v){
        popUpAction.setContentView(R.layout.options_pop_up);
        TextView close = (TextView) popUpAction.findViewById(R.id.close);
        Button newFriend = (Button) popUpAction.findViewById(R.id.friend);
        Button addEvent = (Button) popUpAction.findViewById(R.id.createEvent);
        Button newGroup = (Button) popUpAction.findViewById(R.id.createGroup);

        newFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent startMain = new Intent(MainScreen.this, AddFriends.class);
                startMain.putExtra("User", mainUser);

                MainScreen.this.startActivity(startMain);

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUpAction.dismiss();
            }
        });

        popUpAction.show();
    }

    public void generateFriendList(User user){
        for (User u : user.getFriends()){

            Button myButton = new Button(this);
            myButton.setText(u.getName());

            LinearLayout ll = (LinearLayout)findViewById(R.id.friendFinder);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButton, lp);
        }
    }

    public void genOtherLists(User user){
        for (User u : user.getFriends()){
            for (Event e : u.getEvents()) {
                Button myButton = new Button(this);
                myButton.setText(e.getTitle());
                LinearLayout ll = (LinearLayout) findViewById(R.id.publicEvents);;

                if(e.getType()==EventType.PUBLIC) {
                     ll = (LinearLayout) findViewById(R.id.publicEvents);
                }
                else if (e.getType() == EventType.PRIVATE){
                     ll = (LinearLayout) findViewById(R.id.privateEvents);
                }


                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                ll.addView(myButton, lp);

            }
        }
    }

}
