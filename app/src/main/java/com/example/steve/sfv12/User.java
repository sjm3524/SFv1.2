package com.example.steve.sfv12;

import java.util.ArrayList;
import java.util.List;

public class User {
    List<User> friends;
    List<Event> events;
    List groups;
     String name;
     String userName;


    public User(String userName, String name){
        this.name = name;
        this.userName = userName;
        friends = new ArrayList();
        events = new ArrayList<>();
    }

    public User(String userName, String name, List friends, List groups){
        this.name = name;
        this.userName = userName;
        this.friends = friends;
        this.groups = groups;
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
