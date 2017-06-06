package ua.training.entity;

import java.io.Serializable;

/**
 * Created by vitaliy on 21.05.17.
 */
public class Ticket implements Serializable{
    private Integer id;
    private Integer place;
    private Integer flightId;
    private Integer userId;
    private Integer baggage;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private Boolean priorityRegistration;
    private Boolean priorityBoarding;

    public Integer getId() {
        return id;
    }

    public Ticket() {
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Integer getBaggage() {
        return baggage;
    }

    public void setBaggage(Integer baggage) {
        this.baggage = baggage;
    }

    public Boolean getPriorityRegistration() {
        return priorityRegistration;
    }

    public void setPriorityRegistration(Boolean priorityRegistration) {
        this.priorityRegistration = priorityRegistration;
    }

    public Boolean getPriorityBoarding() {
        return priorityBoarding;
    }

    public void setPriorityBoarding(Boolean priorityBoarding) {
        this.priorityBoarding = priorityBoarding;
    }

    public double getTotalPrice(){
        return 100;
    }
}
