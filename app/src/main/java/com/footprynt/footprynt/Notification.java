package com.footprynt.footprynt;


public class Notification {
    private String notification,date;

    public Notification() {
    }

    public Notification(String notification,String date)
    {
        this.notification = notification;
        this.date = date;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) { this.date = date; }


}