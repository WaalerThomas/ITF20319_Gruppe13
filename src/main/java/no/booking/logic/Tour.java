package no.booking.logic;

import java.util.UUID;

public class Tour {
    private UUID id;
    private String title;
    private String city;
    private String country;
    private String description;
    private String date;
    private int price_Per_Type_Ticket;
    private String meetingPoint;
    private int maxTicketAmount;
    private int availableTicketsCount;

    public Tour(String title, String country, String city, String description, String date, int price_Per_Type_Ticket, String meetingPoint, int maxTicketAmount) {
        this.id = UUID.randomUUID();

        this.title = title;
        this.country = country;
        this.city = city;
        this.description = description;
        this.date = date;
        this.price_Per_Type_Ticket = price_Per_Type_Ticket;
        this.meetingPoint = meetingPoint;
        this.maxTicketAmount = maxTicketAmount;
        this.availableTicketsCount = maxTicketAmount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public int getAvailableTicketsCount() {
        return availableTicketsCount;
    }

    public boolean increaseTicketCount() {
        return increaseTicketCount(1);
    }

    public boolean increaseTicketCount(int amount) {
        if ((availableTicketsCount + amount) > maxTicketAmount)
            return false;

        availableTicketsCount += amount;
        return true;
    }

    public boolean decreaseTicketCount() {
        return decreaseTicketCount(1);
    }

    public boolean decreaseTicketCount(int amount) {
        if (availableTicketsCount - amount < 0)
            return false;

        availableTicketsCount -= amount;
        return true;
    }
}
