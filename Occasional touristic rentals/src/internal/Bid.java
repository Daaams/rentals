/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package internal;

import userdata.Tenant;

/**
 *
 * @author maud
 */
public class Bid {
    // attributes
    int month;
    int nbPerson;
    int nbNight;
    int bidAmount;
    //class associations
    Tenant tenant;
    Property property;
    Boolean isClose = false;

    Bid (Tenant t, Property property, int month, int p, int n, int m){
        this.tenant = t;
        this.property = property;
        this.month = month;
        nbPerson = p;
        nbNight = n;
        bidAmount = m;

    }

    public int getBidAmount() {
        return bidAmount;
    }

    @Override
    public String toString(){
        return "Bid: " + bidAmount + " for " + nbPerson + " person(s) for " + nbNight + " night(s) in "
                + relatedMonth(month) + " for " + tenant.getName();
    }

    public void setClosed() {
        isClose = true;
    }

    public int getMonth() {
        return month;
    }

    public Property getProperty() {
        return property;
    }

    public String getPriceString() {
        return bidAmount + "";
    }

    public boolean isClosed() {
        return isClose;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public String getNumberOfPeopleString() {
        return nbPerson + "";
    }

    /*
    (p*n*m)/10 , rounded up to the next multiple of ten, where n the number of nights, p the number of occupiers, and m the price per night.
     */
    public int getPrice() {
        return (nbPerson * nbNight * bidAmount) / 10;
    }

    private String relatedMonth(int month){
        this.month = month;
        String monthStr = "";
        switch (month) {
            case 1:
                monthStr = "January";
                break;
            case 2:
                monthStr = "February";
                break;
            case 3:
                monthStr = "March";
                break;
            case 4:
                monthStr = "April";
                break;
            case 5:
                monthStr = "May";
                break;
            case 6:
                monthStr = "June";
                break;
            case 7:
                monthStr = "July";
                break;
            case 8:
                monthStr = "August";
                break;
            case 9:
                monthStr = "September";
                break;
            case 10:
                monthStr = "October";
                break;
            case 11:
                monthStr = "November";
                break;
            case 12:
                monthStr = "December";
                break;
        }
        return monthStr;
    }
}
