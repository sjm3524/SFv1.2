package com.example.steve.sfv12;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
    List<User> friends;
    List<Event> events;
    int userID;
    List groups;
     String name;
     String username;
     String email;


    public User(int userID){
        this.name = name;

    }

    public User(int userID, String username, String name, String email){
        this.userID = userID;
        this.name = name;
        this.username = username;
        this.email = email;

    }

    public void addEvent(Event e){
        events.add(e);
    }

    public void addFriend(User friend){
        friends.add(friend);
    }

    public void removeFriend(User friend){
        friends.remove(friend);
    }

    public List<User> getFriends(){
        return friends;
    }

    public String getName(){
        return name;
    }

    public List<Event> getEvents(){
        return events;
    }
}
