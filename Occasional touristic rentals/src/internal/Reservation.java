package internal;

import userdata.Tenant;

public class Reservation {
    private Tenant tenant;
    private Property property;
    private int month;

    private Bid bid;

    public Reservation(Tenant tenant, Property property, int month, Bid bid) {
        this.tenant = tenant;
        this.property = property;
        this.month = month;
        this.bid = bid;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public Property getProperty() {
        return property;
    }

    public int getMonth() {
        return month;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public void recap() {
        String months[] =  { "January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};
        System.out.println("Reservation: " + " " + property.getName());
        System.out.println("Tenant: " + tenant.getName());
        System.out.println("Adress" + property.getAddressOfTheProperty() + " " +
                property.getTheCity());
        System.out.println("Description " + property.getDescription());
        System.out.println("Month " + months[month-1] + " Number of person " + bid.getNumberOfPeopleString());
    }
}
