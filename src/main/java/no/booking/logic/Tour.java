package no.booking.logic;

import no.booking.persistence.DataHandler;

import java.util.List;
import java.util.UUID;

public class Tour {
    private UUID id;
    private String ownerUsername;
    private String title;
    private String city;
    private String country;
    private String description;
    private String date;
    private int adultTicketPrice;
    private int childTicketPrice;
    private int infantTicketPrice;
    private String meetingPoint;
    private int maxTicketAmount;
    private int availableTicketsCount;

    public Tour(String ownerUsername, String title, String country, String city, String description, String date, int adultTicketPrice, int childTicketPrice, int infantTicketPrice, String meetingPoint, int maxTicketAmount) {
        this.id = UUID.randomUUID();
        this.ownerUsername = ownerUsername;
        this.title = title;
        this.country = country;
        this.city = city;
        this.description = description;
        this.date = date;
        this.adultTicketPrice = adultTicketPrice;
        this.childTicketPrice = childTicketPrice;
        this.infantTicketPrice = infantTicketPrice;
        this.meetingPoint = meetingPoint;

        this.maxTicketAmount = Math.max(maxTicketAmount, 0);
        this.availableTicketsCount = maxTicketAmount;
    }

    public boolean book(DataHandler dataHandler, String username, int adultTicketAmount, int childTicketAmount, int infantTicketAmount, String date) {
        int totalTicketAmount = adultTicketAmount + childTicketAmount + infantTicketAmount;
        if (!decreaseTicketCount(totalTicketAmount))
            return false;

        int calculatedTotalCost = (adultTicketPrice * adultTicketAmount) + (childTicketPrice * adultTicketAmount) + (infantTicketPrice * infantTicketAmount);
        Booking booking = new Booking(username, id, adultTicketAmount, childTicketAmount, infantTicketAmount, calculatedTotalCost, date);

        // Check that the user hasn't already booked this tour
        List<Booking> bookings = dataHandler.getBookingsByTourId(id);
        boolean hasBookingAlready = !bookings.isEmpty();
        if (hasBookingAlready) {
            increaseTicketCount(totalTicketAmount);
            return false;
        }

        dataHandler.addBooking(booking);
        return true;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String username) {
        ownerUsername = username;
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

    public void setAdultTicketPrice(int price) {
        adultTicketPrice = Math.max(price, 0);
    }

    public int getAdultTicketPrice() {
        return adultTicketPrice;
    }

    public void setChildTicketPrice(int price) {
        childTicketPrice = Math.max(price, 0);
    }

    public int getChildTicketPrice() {
        return childTicketPrice;
    }

    public void setInfantTicketPrice(int price) {
        infantTicketPrice = Math.max(price, 0);
    }

    public int getInfantTicketPrice() {
        return infantTicketPrice;
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

    public int getMaxTicketAmount() {
        return maxTicketAmount;
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
