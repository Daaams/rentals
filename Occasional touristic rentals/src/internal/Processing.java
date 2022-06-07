
package internal;

import userdata.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Processing {

    private ArrayList<Tenant> allTenants;
    private ArrayList<Admin> allAdmins;
    private ArrayList<Owner> allOwners;
    private ArrayList<User> allUsers;
    private ArrayList<Bid> allBids;
    private ArrayList<Reservation> allReservations;


    /**
     * constructor of the class
     */
    public Processing() {
        allAdmins = new ArrayList<>();
        allOwners = new ArrayList<>();
        allTenants = new ArrayList<>();
        allUsers = new ArrayList<>();
        allBids = new ArrayList<>();
        allReservations = new ArrayList<>();
    }

    /**
     * To get the list of users
     *
     * @return an ArrayList
     */
    public ArrayList<Tenant> getAllTenants() {
        return allTenants;
    }

    public ArrayList<Owner> getAllOwners() {
        return allOwners;
    }

    public ArrayList<Admin> getAllAdmins() {
        return allAdmins;
    }

    public ArrayList<Reservation> getAllReservations() {return allReservations;}

    /**
     * Create a new account according to the type of the account
     *
     * @param personalInforations informations given by the user
     * @param type                the type of account to create
     */
    public void createAccount(ArrayList<String> personalInforations, TypeAccount type) {
        if (type == TypeAccount.ADMINISTRATOR) {
            Admin u = new Admin(personalInforations.get(0), personalInforations.get(1),
                    personalInforations.get(2), personalInforations.get(3), personalInforations.get(4));
            allAdmins.add(u);
            allUsers.add(u);
        } else if (type == TypeAccount.OWNER) {
            Owner u = new Owner(personalInforations.get(0), personalInforations.get(1),
                    personalInforations.get(2), personalInforations.get(3), personalInforations.get(4));
            allOwners.add(u);
            allUsers.add(u);
        } else {
            Tenant u = new Tenant(personalInforations.get(0), personalInforations.get(1),
                    personalInforations.get(2), personalInforations.get(3), personalInforations.get(4));
            allTenants.add(u);
            allUsers.add(u);
        }
    }

    /**
     * Connect a tenant to the application
     * @param personalData data given by the tenant
     * @param type the type of account
     * @return the connected tenant
     */
    public User connectUser(ArrayList<String> personalData, TypeAccount type) {
        int i = 0;
        User connected = null;
        ArrayList<User> usersList;
        if (type == TypeAccount.ADMINISTRATOR){
            usersList = new ArrayList<>(allAdmins);
        }else if (type == TypeAccount.TENANT){
            usersList = new ArrayList<>(allTenants);
        }else{
            usersList = new ArrayList<>(allOwners);
        }
        while (i < usersList.size() && connected == null) {
            User u = usersList.get(i);
            if (u.getLogin().equals(personalData.get(0))) {
                connected = u;
            }
            i++;
        }
        return connected;
    }

    /**
     * Tests if an account with the same personnal informations has already been created
     *
     * @param personalData data of the user
     * @param type the type of account of the user
     * @return a boolean
     */
    public boolean testValidityAccount(ArrayList<String> personalData, TypeAccount type) {
        boolean nonValid = false;
        int i = 0;
        ArrayList<User> usersList;
        if (type == TypeAccount.ADMINISTRATOR){
            usersList = new ArrayList<>(allAdmins);
        }else if (type == TypeAccount.TENANT){
            usersList = new ArrayList<>(allTenants);
        }else{
            usersList = new ArrayList<>(allOwners);
        }
        while (i < usersList.size() && !nonValid) {
            User u = usersList.get(i);
            nonValid = testLogin(u, personalData, type) && testMail(u, personalData, type);
            i++;
        }
        return nonValid;
    }

    /**
     * Tests if the login given during the account creation already exists
     *
     * @param u the user to compare
     * @param personalData data given by the user wanted to create an account
     * @return a boolean
     */
    private boolean testLogin(User u, ArrayList<String> personalData, TypeAccount type) {
        boolean test = u.getLogin().equals(personalData.get(0)) && u.getType().equals(type);
        if (test) {
            System.out.println("This login already exists, choose another one");
        }
        return test;
    }

    /**
     * Tests if the mail address given during the account creation already exists
     *
     * @param u the user to compare
     * @param personalData data given by the user wanted to create an account
     * @return a boolean
     */
    private boolean testMail(User u, ArrayList<String> personalData, TypeAccount type) {
        boolean test = u.getNickname().equals(personalData.get(4)) && u.getType().equals(type);
        if (test) {
            System.err.println("This mail already exists, choose another one");
        }
        return test;
    }

    /**
     * To add money into the Tenant's wallet
     *
     * @param tenantConnected the tenant
     * @param money           money to add
     */
    public void addMoneyOnWallet(Tenant tenantConnected, int money) {
        tenantConnected.depositMoney(money);
    }

    /**
     * To withdraw money of tenant's wallet
     *
     * @param tenantConnected the tenant
     * @param money           money to withdraw
     */
    public void withdrawMoneyOfWallet(Tenant tenantConnected, int money) {
        tenantConnected.withdrawMoney(money);
    }

    /**
     * Shows the data of the property
     *
     * @param property the wanted property
     */
    public void consultDataOfAProperty(String property) {
        for (Owner o : allOwners) {
            HashMap<Property, Price> p = o.getProperties();
            for (Property pro : p.keySet()) {
                if (pro.getNameProperty().equals(property)) {
                    System.out.println(pro.toString());
                    System.out.println("The type of the property is : " + pro.getTypeProperty());
                    System.out.println("The max quantity of occupiers of the property : " + pro.getMaxOccupiers());
                    System.out.println("The nominal price : " + p.get(pro).getThePrice());
                    System.out.println("The description : " + pro.getDescription());
                    System.out.println("");
                }
            }
        }
    }

    /**
     * Shows data of the user
     *
     * @param userConnected the connected user
     */
    public void seeData(User userConnected) {
        System.out.println("Name : " + userConnected.getName());
        System.out.println("Surname : " + userConnected.getSurname());
        System.out.println("Nickname : " + userConnected.getNickname());
        System.out.println("Mail : " + userConnected.getMail());
    }

    /**
     * Changes the name of the user
     *
     * @param userConnected the connected user
     * @param stringRead    the new name
     */
    public void changeName(User userConnected, String stringRead) {
        userConnected.changeName(stringRead);
    }

    /**
     * Changes the surname of the user
     *
     * @param userConnected the connected user
     * @param stringRead    the new surname
     */
    public void changeSurname(User userConnected, String stringRead) {
        userConnected.changeSurname(stringRead);
    }

    /**
     * Changes the nickname of the user
     *
     * @param userConnected the connected user
     * @param stringRead    the new nickname
     */
    public void changeNickname(User userConnected, String stringRead) {
        userConnected.changeNickname(stringRead);
    }

    /**
     * Changes the email address of the user
     *
     * @param userConnected the connected user
     * @param stringRead    the new email address
     */
    public void changeMail(User userConnected, String stringRead) {
        userConnected.changeMail(stringRead);
    }

    /**
     * Shows all users of the application
     */
    public void seeAllUsers() {
        for (User u : allUsers) {
            System.out.println(u.toString() + "\n");
        }
    }

    /**
     * Deletes an account according to given data
     *
     * @param accountData   an array containing data of the account to delete
     * @param type          the type of the account
     * @param userConnected the administrator connected
     * @return true if the account is well deleted
     */
    public boolean deleteAccount(String accountData, TypeAccount type, User userConnected) {
        switch (type) {
            case TENANT:
                if (allTenants.size() == 0) {
                    System.err.println("There are no tenants.");
                } else {
                    allTenants.remove(searchAccountTenant(accountData));
                    allUsers.remove(searchAccountTenant(accountData));
                    System.out.println("Deleted");
                    return true;
                }
                break;
            case OWNER:
                if (allOwners.size() == 0) {
                    System.err.println("There are no owners");
                } else {
                    allOwners.remove(searchAccountOwner(accountData));
                    allUsers.remove(searchAccountOwner(accountData));
                    System.out.println("Deleted");
                    return true;
                }
                break;
            case ADMINISTRATOR:
                if (searchAccountAdmin(accountData) == userConnected) {
                    System.err.println("You can't delete your account, you are connected");
                    return false;
                } else {
                    allAdmins.remove(searchAccountAdmin(accountData));
                    allUsers.remove(searchAccountAdmin(accountData));
                    System.out.println("Deleted");
                    return true;
                }
        }
        return false;
    }

    /**
     * Searches the account of the tenant among tenants of the application
     *
     * @param login the login if the user
     * @return the corresponding tenant or null
     */
    public Tenant searchAccountTenant(String login) {
        boolean found = false;
        int i = 0;
        Tenant tenant = null;
        while (i < allTenants.size() && !found) {
            Tenant t = allTenants.get(i);
            if (t.getLogin().equals(login)) {
                tenant = t;
                found = true;
            }
            i++;
        }
        return tenant;
    }

    /**
     * Searches the account of the owner among owners of the application
     *
     * @param login the login if the user
     * @return the corresponding owner or null
     */
    public Owner searchAccountOwner(String login) {
        boolean found = false;
        int i = 0;
        Owner owner = null;
        while (i < allOwners.size() && !found) {
            Owner o = allOwners.get(i);
            if (o.getLogin().equals(login)) {
                owner = o;
                found = true;
            }
            i++;
        }
        return owner;
    }

    /**
     * Searches the account of the administrator among administrators of the application
     *
     * @param login the login if the user
     * @return the corresponding administrator or null
     */
    public Admin searchAccountAdmin(String login) {
        boolean found = false;
        int i = 0;
        Admin admin = null;
        while (i < allAdmins.size() && !found) {
            Admin a = allAdmins.get(i);
            if (a.getLogin().equals(login)) {
                admin = a;
                found = true;
            }
            i++;
        }
        return admin;
    }

    /**
     * Shows all the property of the connected owner
     *
     * @param ownerConnected the connected owner
     */
    public void seeMyProperties(Owner ownerConnected) {
        System.out.println("Properties : ");
        ArrayList<Property> myProperties = ownerConnected.getMyProperties();
        myProperties.sort(new PropertiesComparator());
        Iterator<Property> it = new IteratorProperties(myProperties);
        while (it.hasNext()){
            Property p = it.next();
            System.out.println(p.toString());
            System.out.println("Description : " + p.getDescription());
        }
    }

    /**
     * Creates and Adds a property in the portfolio of the owner
     *
     * @param ownerConnected the owner connected
     * @param propertyData   data of the property
     * @param type           the type of the property
     * @param maxOccupiers   the number maximum of occupiers
     * @param nominalPrice   the nominal price
     */
    public void addPropertyToThePortfolio(Owner ownerConnected, ArrayList<String> propertyData, TypeProperty type,
                                          int maxOccupiers, int nominalPrice) {
        if (!testSameAddress(propertyData)) {
            ownerConnected.addProperty(new Property(type, propertyData.get(0), propertyData.get(1), propertyData.get(2),
                    propertyData.get(3), maxOccupiers), new Price(nominalPrice));
        } else {
            System.err.println("A property with the same address already exists.");
            System.err.println("The property has not been added.");
        }
    }

    /**
     * Tests if one of the properties of the application correspond to data given by the user
     *
     * @param propertyData data of the property given by the user
     * @return a boolean
     */
    private boolean testSameAddress(ArrayList<String> propertyData) {
        for (Owner o : allOwners) {
            for (Property p : o.getProperties().keySet()) {
                if (p.getTheCity().equals(propertyData.get(2)) && p.getAddressOfTheProperty().equals(propertyData.get(1))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Calculates the sum of all properties
     *
     * @return the sum of all properties
     */
    public int propertiesSum() {
        int sum = 0;
        for (Owner o : allOwners) {
            sum += o.getProperties().size();
        }
        return sum;
    }

    /**
     * Deletes a property of the portfolio of the owner
     *
     * @param ownerConnected    the connected owner
     * @param dataOfTheProperty data of the property to delete
     */
    public boolean deletePropertyOwner(Owner ownerConnected, ArrayList<String> dataOfTheProperty) {
        Property p = findProperty(ownerConnected, dataOfTheProperty);
        if (p != null) {
            ownerConnected.deleteProperty(p);
            return true;
        }
        return false;
    }

    /**
     * Deletes a property according to given data
     *
     * @param answers data corresponding to the property to delete
     * @return a boolean
     */
    public boolean deletePropertyAdmin(ArrayList<String> answers) {
        for (Owner o : allOwners) {
            boolean deleted = deletePropertyOwner(o, answers);
            if (deleted) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints all properties of the application
     */
    public void seeAllProperties() {
        for (Owner o : allOwners) {
            System.out.println("Owner : " + o.getNickname());
            seeMyProperties(o);
        }
    }

    /**
     * find a property corresponding to the data given by the user
     *
     * @param ownerConnected the connected owner
     * @param data           data of the property
     * @return the corresponding property
     */
    public Property findProperty(Owner ownerConnected, ArrayList data) {
        Property property = null;
        for (Property p : ownerConnected.getProperties().keySet()) {
            if (p.getNameProperty().equals(data.get(0)) &&
                    p.getAddressOfTheProperty().equals(data.get(1)) &&
                    p.getTheCity().equals(data.get(2))) {
                property = p;
            }
        }
        return property;
    }

    /**
     * Changes the description of a property
     *
     * @param answers data of the property given by the user
     */
    public void changeDescription(ArrayList<String> answers) {
        for (Owner o : allOwners) {
            if (o.getNickname().equals(answers.get(3))) {
                Property p = findProperty(o, answers);
                if (p != null) {
                    p.changeDescription(answers.get(answers.size() - 1));
                }
            }
        }
    }

    /**
     * Changes the type of the property
     *
     * @param p       the concerned property
     * @param newType the new type of the property
     */
    public void changeTypeOfTheProperty(Property p, TypeProperty newType) {
        p.changeType(newType);
    }

    /**
     * Changes the name of the property
     *
     * @param p       the concerned property
     * @param newName the new name of the property
     */
    public void changeNameOfTheProperty(Property p, String newName) {
        p.changeName(newName);
    }

    /**
     * Changes the maximum of occupiers of the property
     *
     * @param p               the concerned property
     * @param newMaxOccupiers the new max occupiers of the property
     */
    public void changeTheNumberMaxOfOccupiers(Property p, int newMaxOccupiers) {
        p.changeMaxOccupiers(newMaxOccupiers);
    }

    /**
     * Changes the maximum of occupiers of the property
     *
     * @param ownerConnected  the connected owner
     * @param p               the concerned property
     * @param newNominalPrice the new max occupiers of the property
     */
    public void changeNominalPrice(Owner ownerConnected, Property p, int newNominalPrice) {
        ownerConnected.getProperties().get(p).changeThePrice(newNominalPrice);
    }

    public Property PropertyExist(String stringRead) {
        Property property = null;
        for (Owner o : allOwners) {
            for (Property p : o.getProperties().keySet()) {
                if (p.getNameProperty().equals(stringRead)) {
                    return p;
                }
            }
        }
        return property;
    }

    public Owner findOwner(Property property) {
        Owner owner = null;
        for (Owner o : allOwners) {
            if (o.getProperties().containsKey(property)) {
                owner = o;
            }
        }
        return owner;
    }

    public void createBid(Tenant tenantConnected, Property property, int month, int people, int nights, int bid) {
        Bid theBid = new Bid(tenantConnected, property, month, people, nights, bid);
        property.setBid(theBid);
        tenantConnected.addABid(theBid);
        allBids.add(theBid);
    }

    public int winningBidSum(Tenant tenantConnected) {
        int sum = 0;
        for (Bid b : tenantConnected.getMyBids()) {
            for (Owner o : allOwners) {
                for (Property p : o.getProperties().keySet()) {
                    if (p.getCurrentBid().equals(b)) {
                        sum += p.getCurrentBid().bidAmount;
                    }
                }
            }
        }
        return sum;
    }

    public void seeTheHighestBid() {
        int maxBid = 0;
        if (allBids.size() == 0){
            System.err.println("No bids yet");
        }else{
            Property property = null;
            for (Bid b : allBids) {
                if (b.getBidAmount() > maxBid) {
                    maxBid = b.getBidAmount();
                    property = b.getProperty();
                }
            }
            System.out.println("The highest bid is " + property.getName() + " for " + property.getCurrentBid().getBidAmount()
                    + " euros");
        }
    }

    public void seeTheHighestBidForAMonth(int month) {
        int maxBid = 0;
        if (allBids.size() == 0){
            System.err.println("No bids yet");
        }else{
            Property property = null;
            for (Bid b : allBids) {
                if (b.getBidAmount() > maxBid && b.getMonth() == month) {
                    maxBid = b.getBidAmount();
                    property = b.getProperty();
                }
            }
            System.out.println("The highest bid for the month is " + property.getName()
                    + " with " + property.getCurrentBid().getBidAmount() + " euros");
        }
    }


    public void listAllBids() {
        for (Bid b : allBids) {
            System.out.println("Property : " + b.property.getName() + " bid : " + b.getPriceString());
        }
    }

    public void listAllBidsByMonth(int month) {
        for (Bid b : allBids) {
            if (b.month == month) {
                System.out.println("Property : " + b.property.getName() + " bid : " + b.getPriceString());
            }
        }
    }

    public void listAllBidsByProperty(Property property) {
        for (Bid b : allBids) {
            if (b.property.equals(property)) {
                System.out.println("Property : " + b.property.getName() + " bid : " + b.getPriceString());
            }
        }
    }

    public void listAllBidsByAmount(int numberRead) {
        for (Bid b : allBids) {
            if (b.bidAmount == numberRead) {
                System.out.println("Property : " + b.property.getName() + " bid : " + b.getPriceString());
            }
        }
    }

    public void closeBid(int month) {
        for (Bid b : allBids) {
            if (b.month == month) {
                b.setClosed();
                if (b.getProperty().getCurrentBid().equals(b)) {
                    b.getTenant().depositMoney(-b.getBidAmount());
                    Reservation reservation = new Reservation(b.getTenant(),
                            b.getProperty(), b.getMonth(), b);
                    b.getTenant().createNewReservation(reservation);
                    allReservations.add(reservation);
                    for (Owner o : allOwners) {
                        for (Property p : o.getProperties().keySet()) {
                            if (p.equals(b.getProperty())) {
                                if (b.getBidAmount() >= o.getProperties().get(p).getThePrice()) {
                                    // add 100% of the nominal price + 75% of the rest of the bid
                                    o.addMoney(o.getProperties().get(p).getThePrice()
                                            + (int)((b.getBidAmount()-o.getProperties().get(p).getThePrice())*0.75));
                                }
                                else {
                                    // add only 80% the nominal price
                                    o.addMoney((int)(o.getProperties().get(p).getThePrice()*0.8));
                                }
                            }
                        }
                    }

                }
                else {
                    b.getTenant().depositMoney(-1);
                }
            }
            for (Owner o : allOwners) {
                for (Property p : o.getProperties().keySet()) {
                    Boolean haveBid = false;
                    for (Bid b2 : allBids) {
                        if (b2.getProperty().equals(p)) {
                            haveBid = true;
                        }
                    }
                    if (!haveBid) {
                        o.addMoney(-10);
                    }
                }
            }
        }
    }

        public void seeAllBidClosed (Tenant tenantConnected){
            if (tenantConnected.getMyBids().size() == 0){
                System.err.println("You have not any bids");
            }else{
                for (Bid b : tenantConnected.getMyBids()) {
                    if (b.isClosed()) {
                        if (b.getProperty().getCurrentBid().equals(b.bidAmount)) {
                            System.out.println("Property : " + b.property.getName() + " bid : " + b.getPriceString() + " WIN ");
                        } else {
                            System.out.println("Property : " + b.property.getName() + " bid : " + b.getPriceString() + " LOSE ");
                        }
                    }
                }
            }
        }

        public void seeAllReservations(Tenant tenantConnected){
            for (Reservation r : tenantConnected.getMyReservations()) {
                r.recap();
                System.out.println("");
            }
        }

        public void listAllReservationsOfMyProperties(Owner ownerConnected){
            // à revoir avec les reservations
            if (allReservations.size() == 0){
                System.err.println("No reservation yet");
            }else{
                for (Property p : ownerConnected.getMyProperties()) {
                    for (Reservation r : allReservations) {
                        if (r.getProperty().equals(p)) {
                            r.recap();
                            System.out.println("Mail : " + r.getTenant().getMail());
                            System.out.println("");
                        }
                    }
                }
            }
        }

        public void listAllReservation() {
            for (Reservation r : allReservations) {
                r.recap();
                System.out.println("");
            }
        }
    }


