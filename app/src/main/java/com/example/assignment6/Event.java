package com.example.assignment6;
//stores data
public class Event {
    private String type;
    private String date;
    private String time;

    public Event(String type, String date, String time){
        this.type = type;
        this.date = date;
        this.time = time;
    }

    public String getType(){ return type; }
    public String getDate(){ return date; }
    public String getTime(){ return time; }
}