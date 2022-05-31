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
                + month + " for " + tenant.getName();
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
}
