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
}
