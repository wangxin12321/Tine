package com.example.administrator.tine.bean;

public class Task {
    public String time;
    public String date;
    public String task;

    public Task(String time, String date, String task) {
        this.time = time;
        this.date = date;
        this.task = task;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
