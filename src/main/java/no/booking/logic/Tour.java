package no.booking.logic;

import java.util.Date;

public class Tour {

    public int tourId;
    public String title;
    public String city;
    public String country;
    public String description;
    public String date;
    public int price_Per_Type_Ticket;
    public String meetingPoint;

    public Tour(int tourId, String title, String country, String city, String description, String date, int price_Per_Type_Ticket, String meetingPoint) {
        this.tourId = tourId;
        this.title = title;
        this.country = country;
        this.city = city;
        this.description = description;
        this.date = date;
        this.price_Per_Type_Ticket = price_Per_Type_Ticket;
        this.meetingPoint = meetingPoint;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice_Per_Type_Ticket() {
        return price_Per_Type_Ticket;
    }

    public void setPrice_Per_Type_Ticket(int price_Per_Type_Ticket) {
        this.price_Per_Type_Ticket = price_Per_Type_Ticket;
    }

    public String getMeetingPoint() {
        return meetingPoint;
    }

    public void setMeetingPoint(String meetingPoint) {
        this.meetingPoint = meetingPoint;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
