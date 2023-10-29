package no.booking.logic;

import java.util.UUID;

public class Booking {
    private String username;
    private UUID tourId;
    private int adultTicketAmount;
    private int childTicketAmount;
    private int infantTicketAmount;
    private int totalCost;
    private String date;

    public Booking(String username, UUID tourId, int adultTicketAmount, int childTicketAmount, int infantTicketAmount, int totalCost, String date) {
        this.username = username;
        this.tourId = tourId;
        this.adultTicketAmount = adultTicketAmount;
        this.childTicketAmount = childTicketAmount;
        this.infantTicketAmount = infantTicketAmount;
        this.totalCost = totalCost;
        this.date = date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTourId(UUID id) {
        tourId = id;
    }

    public void setAdultTicketAmount(int amount) {
        adultTicketAmount = Math.max(amount, 0);
    }

    public void setChildTicketAmount(int amount) {
        childTicketAmount = Math.max(amount, 0);
    }

    public void setInfantTicketAmount(int amount) {
        infantTicketAmount = Math.max(amount, 0);
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = Math.max(totalCost, 0);
    }

    public String getUsername() {
        return username;
    }

    public UUID getTourId() {
        return tourId;
    }

    public int getTotalTicketAmount() {
        return (adultTicketAmount + childTicketAmount + infantTicketAmount);
    }

    public int getAdultTicketAmount() {
        return adultTicketAmount;
    }

    public int getChildTicketAmount() {
        return childTicketAmount;
    }

    public int getInfantTicketAmount() {
        return infantTicketAmount;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public String getDate() {
        return date;
    }
}
