package ua.training.entity;

import java.sql.Date;

/**
 * Created by vitaliy on 21.05.17.
 */
public class Flight {
    private Integer id;
    private String departure;
    private String destination;
    private Date date;
    private int ticketCount;
    private Double startCost;

    public Flight() {
    }


    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getStartCost() {
        return startCost;
    }

    public void setStartCost(Double startCost) {
        this.startCost = startCost;
    }

}
