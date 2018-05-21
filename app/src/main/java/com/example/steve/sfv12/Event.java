package com.example.steve.sfv12;


public class Event {

    EventType type;
    String title;
    String desc;
    String address;
    String startTime;
    String endTime;

    public Event(EventType type, String title, String desc, String address, String startTime, String endTime){
        this.type=type;
        this.title = title;
        this.desc =desc;
        this.address = address;
        this.startTime =startTime;
        this.endTime = endTime;
    }

    public EventType getType(){
        return type;
    }

    public String getTitle(){
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getAddress() {
        return address;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
