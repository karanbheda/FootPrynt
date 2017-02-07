package com.footprynt.footprynt;


public class Offers {
    private String offer,date,type;

    public Offers() {
    }

    public Offers(String offer,String date, String type)
    {
        this.offer = offer;
        this.date = date;
        this.type = type;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) { this.date = date; }

    public String getType() {
        return type;
    }

    public void setType(String type) { this.type = type; }



}