package internal;

import userdata.Tenant;

public class Reservation {
    private Tenant tenant;
    private Property property;
    private int month;

    private Bid bid;

    /**
     * Constructor
     * @param tenant Tenant
     * @param property Property
     * @param month int
     * @param bid Bid
     */
    public Reservation(Tenant tenant, Property property, int month, Bid bid) {
        this.tenant = tenant;
        this.property = property;
        this.month = month;
        this.bid = bid;
    }

    /**
     * Return the tenant
     * @return Tenant
     */
    public Tenant getTenant() {
        return tenant;
    }

    /**
     * Return the property
     * @return Property
     */
    public Property getProperty() {
        return property;
    }

    /**
     * Do the recap of the reservation
     */
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
