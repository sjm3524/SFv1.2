package com.example.steve.sfv12;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setBackgroundDrawable();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        User steve = new User("stejwm", "Steve");
        User jimmy = new User("aismiJimmy", "Jimmy");
        User trevor = new User("spitzy", "trevor");
        User matt = new User("bigDickman", "matt");
        User hoss = new User("bigHoss11", "Hoss");
        User banjamin = new User("sossedBanj", "Banjamin");
        User lost = new User("lostTard", "Lost");
        User cross = new User("chrisCross", "Cross");

        Event banger = new Event(EventType.PRIVATE, "Banger at my place", "It's gonna be lit pull through", "20 rawhide dr", "7", "9");
        Event nasheys = new Event(EventType.PUBLIC, "Nashvilles", "Boolin at nashvilles", "Nashvilles", "11", "2");


        jimmy.addEvent(nasheys);
        cross.addEvent(banger);
        steve.addFriend(jimmy);
        steve.addFriend(trevor);
        steve.addFriend(matt);
        steve.addFriend(hoss);
        steve.addFriend(banjamin);
        steve.addFriend(lost);
        steve.addFriend(cross);


        generateFriendList(steve);
        genOtherLists(steve);

    }
     View.OnClickListener friendButt = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            System.out.println(view.getId());
            Button thisButt = (Button) findViewById(view.getId());
            Snackbar.make(view, thisButt.getText() + "is your friend!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    };

    public void generateFriendList(User user){
        for (User u : user.getFriends()){

            Button myButton = new Button(this);
            myButton.setOnClickListener(friendButt);
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
