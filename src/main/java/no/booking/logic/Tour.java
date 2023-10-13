package no.booking.logic;

import java.util.Date;

public class Tour {

    public String title;
    public String city;
    public String description;
    public String date;
    public String price_Per_Type_Ticket;
    public String meetingPoint;

    public Tour(String title, String city) {
        this(title, city, "", "", "", "");
    }

    public Tour(String title, String city, String description, String date, String price_Per_Type_Ticket, String meetingPoint) {
        this.title = title;
        this.city = city;
        this.description = description;
        this.date = date;
        this.price_Per_Type_Ticket = price_Per_Type_Ticket;
        this.meetingPoint = meetingPoint;
    }


}
