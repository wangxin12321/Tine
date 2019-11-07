package com.example.administrator.tine.bean;

public class Databean {

    private String time;
    private String date;
    private String content;

    public Databean(String time, String content) {
        this.time = time;
        this.content = content;
    }

    public Databean() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
