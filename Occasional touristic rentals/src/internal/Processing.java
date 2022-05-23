
package internal;

import java.util.ArrayList;
import java.util.HashMap;

import userdata.*;

public class Processing {

    private ArrayList<Tenant> allTenants;
    private ArrayList<Admin> allAdmins;
    private ArrayList<Owner> allOwners;
    private ArrayList<User> allUsers;


    /**
     * constructor of the class
     */
    public Processing() {
        allAdmins = new ArrayList<>();
        allOwners = new ArrayList<>();
        allTenants = new ArrayList<>();
        allUsers = new ArrayList<>();
    }

    /**
     * To get the list of users
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

    /**
     * Create a new account according to the type of the account
     * @param personalInforations informations given by the user
     * @param type the type of account to create
     */
    public void createAccount(ArrayList<String> personalInforations, TypeAccount type) {
        if (type == TypeAccount.ADMINISTRATOR){
            Admin u = new Admin(personalInforations.get(0), personalInforations.get(1), personalInforations.get(2), personalInforations.get(3), personalInforations.get(4));
            allAdmins.add(u);
            allUsers.add(u);
        }else if (type == TypeAccount.OWNER){
            Owner u = new Owner(personalInforations.get(0), personalInforations.get(1), personalInforations.get(2), personalInforations.get(3), personalInforations.get(4));
            allOwners.add(u);
            allUsers.add(u);
        }else{
            Tenant u = new Tenant(personalInforations.get(0), personalInforations.get(1), personalInforations.get(2), personalInforations.get(3), personalInforations.get(4));
            allTenants.add(u);
            allUsers.add(u);
        }
    }

    /**
     * Connect a tenant to the application
     * @param personalData data given by the tenant
     * @return the connected tenant
     */
    public Tenant connectTenant(ArrayList<String> personalData) {
        int i = 0;
        Tenant connected = null;
        while (i < allTenants.size() && connected == null){
            Tenant t = allTenants.get(i);
            if (t.getLogin().equals(personalData.get(0)))
            {
                connected = t;
            }
            i++;
        }
        return connected;
    }

    /**
     * Connect an owner to the application
     * @param personalData datd given by the owner
     * @return the connected owner
     */
    public Owner connectOwner(ArrayList<String> personalData) {
        int i = 0;
        Owner connected = null;
        
        while (i < allOwners.size() && connected == null){
            
            Owner t = allOwners.get(i);
            
            if (t.getLogin().equals(personalData.get(0)))
            {
                connected = t;
            }
            i++;
        }
        return connected;
    }
    
    

    /**
     * Connect an administrator to the application
     * @param personalData data given by the administrator
     * @return the connected administrator
     */
    public Admin connectAdmin(ArrayList<String> personalData) {
        int i = 0;
        Admin connected = null;
        while (i < allAdmins.size() && connected == null){
            Admin t = allAdmins.get(i);
            if (t.getLogin().equals(personalData.get(0)))
            {
                connected = t;
            }
            i++;
        }
        return connected;
    }

    /**
     * Tests if an account with the same personnal informations has already been created
     * @param personalData data of the user
     * @param type the type of account of the user
     * @return a boolean
     */
    public boolean testValidityAccount(ArrayList<String> personalData, TypeAccount type) {
        boolean nonValid = false;
        int i = 0;
        switch (type){
            case OWNER :
                ArrayList<Owner> listO = new ArrayList<>(allOwners);
                while (i < listO.size() && !nonValid){
                    Owner o = listO.get(i);
                    nonValid = testLogin(o, personalData) && testMail(o, personalData);
                    i++;
                }
                break;
            case ADMINISTRATOR:
                ArrayList<Admin> listA = new ArrayList<>(allAdmins);
                while (i < listA.size() && !nonValid){
                    Admin a = listA.get(i);
                    nonValid = testLogin(a, personalData) && testMail(a, personalData);
                    i++;
                }
                break;
            case TENANT:
                ArrayList<Tenant> listT = new ArrayList<>(allTenants);
                while (i < listT.size() && !nonValid){
                    Tenant t = listT.get(i);
                    nonValid = testLogin(t, personalData) && testMail(t, personalData);
                    i++;
                }
                break;
        }
        return nonValid;
    }

    /**
     *
     * Tests if the login given during the account creation already exists
     * @param u the user to compare
     * @param personalData data given by the user wanted to create an account
     * @return a boolean
     */
    private boolean testLogin(User u, ArrayList<String> personalData){
        boolean test = u.getLogin().equals(personalData.get(0));
        if (test){
            System.out.println("This login already exists, choose another one");
        }
        return test;
    }

    /**
     *
     * Tests if the mail address given during the account creation already exists
     * @param u the user to compare
     * @param personalData data given by the user wanted to create an account
     * @return a boolean
     */
    private boolean testMail(User u, ArrayList<String> personalData){
        boolean test = u.getNickname().equals(personalData.get(4));
        if (test){
            System.err.println("This mail already exists, choose another one");
        }
        return test;
    }

    /**
     * To add money into the Tenant's wallet
     * @param tenantConnected the tenant
     * @param money money to add
     */
    public void addMoneyOnWallet(Tenant tenantConnected, int money) {
        tenantConnected.depositMoney(money);
    }

    /**
     * To withdraw money of tenant's wallet
     * @param tenantConnected the tenant
     * @param money money to withdraw
     */
    public void withdrawMoneyOfWallet(Tenant tenantConnected, int money) {tenantConnected.withdrawMoney(money);}

    /**
     * Shows the data of the property
     * @param property the wanted property
     */
    public void consultDataOfAProperty(String property) {
        for (Owner o: allOwners) {
            HashMap<Property, Price> p = o.getProperties();
            for (Property pro: p.keySet()) {
                if(pro.getNameProperty().equals(property)){
                    System.out.println(pro.toString());
                    System.out.println("The type of the property is : "+pro.getTypeProperty());
                    System.out.println("The max quantity of occupiers of the property : "+ pro.getMaxOccupiers());
                    System.out.println("The nominal price : "+ p.get(pro).getThePrice());
                    System.out.println("The description : "+pro.getDescription());
                    System.out.println("");
                }
            }
        }
    }

    /**
     * To see the content of the tenant wallet
     * @param userConnected the tenant
     */
    public void seeMyWallet(Tenant userConnected) {
        System.out.println(userConnected.getVirtualWallet());
    }

    /**
     * Shows data of the user
     * @param userConnected the connected user
     */
    public void seeData(User userConnected) {
        System.out.println("Name : "+ userConnected.getName());
        System.out.println("Surname : "+ userConnected.getSurname());
        System.out.println("Nickname : "+ userConnected.getNickname());
        System.out.println("Mail : "+ userConnected.getMail());
    }

    /**
     * Changes the name of the user
     * @param userConnected the connected user
     * @param stringRead the new name
     */
    public void changeName(User userConnected, String stringRead) {
        userConnected.changeName(stringRead);
    }

    /**
     * Changes the surname of the user
     * @param userConnected the connected user
     * @param stringRead the new surname
     */
    public void changeSurname(User userConnected, String stringRead) {
        userConnected.changeSurname(stringRead);
    }

    /**
     * Changes the nickname of the user
     * @param userConnected the connected user
     * @param stringRead the new nickname
     */
    public void changeNickname(User userConnected, String stringRead) {
        userConnected.changeNickname(stringRead);
    }

    /**
     * Changes the email address of the user
     * @param userConnected the connected user
     * @param stringRead the new email address
     */
    public void changeMail(User userConnected, String stringRead) {
        userConnected.changeMail(stringRead);
    }

    /**
     * Shows all users of the application
     */
    public void seeAllUsers() {
        for (Admin a: allAdmins) {System.out.println(a.toString() + "\n");}
        for (Owner o: allOwners) {System.out.println(o.toString() + "\n");}
        for (Tenant t: allTenants) {System.out.println(t.toString() + "\n");}
    }

    /**
     * Deletes an account according to given data
     * @param accountData an array containing data of the account to delete
     * @param type the type of the account
     * @param userConnected the administrator connected
     * @return true if the account is well deleted
     */
    public boolean deleteAccount(String[] accountData, TypeAccount type, User userConnected) {
        switch (type){
            case TENANT:
                if (allTenants.size() == 0){
                    System.err.println("There are no tenants.");
                }else{
                    Tenant t = searchAccountTenant(allTenants, accountData);
                    if (t != null) {
                        allTenants.remove(t);
                        System.out.println("Deleted");
                        return true;
                    }
                }
                break;
            case OWNER:
                if (allOwners.size() == 0){
                    System.err.println("There are no owners");
                }else{
                    Owner o = searchAccountOwner(allOwners, accountData);
                    if (o != null){
                        allOwners.remove(o);
                        System.out.println("Deleted");
                        return true;
                    }
                }
                break;
            case ADMINISTRATOR:
                if (allAdmins.size() == 0){
                    System.err.println("There are no admins");
                }else{
                    Admin a = searchAccountAdmin(allAdmins, accountData);
                    if (a == userConnected){
                        System.err.println("You can't delete your account, you are connected");
                        return false;
                    }else if (a != null && a != userConnected){
                        allAdmins.remove(a);
                        System.out.println("Deleted");
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    /**
     * Searches the account of the tenant among tenants of the application
     * @param tenants an arraylist of Tenant
     * @param accountData the data of the account
     * @return the corresponding tenant or null
     */
    private Tenant searchAccountTenant(ArrayList<Tenant> tenants, String[] accountData) {
        boolean found = false;
        int i = 0;
        Tenant tenant = null;
        while (i < tenants.size() && !found){
            Tenant t = tenants.get(i);
            if (t.getName().equals(accountData[0])
            && t.getSurname().equals(accountData[1])
            && t.getNickname().equals(accountData[2])){
                tenant = t;
                found = true;
            }
            i ++;
        }
        return tenant;
    }

    /**
     * Searches the account of the owner among owners of the application
     * @param owners an arraylist of Owner
     * @param accountData the data of the account
     * @return the corresponding owner or null
     */
    private Owner searchAccountOwner(ArrayList<Owner> owners, String[] accountData) {
        boolean found = false;
        int i = 0;
        Owner owner = null;
        while (i < owners.size() && !found){
            Owner o = owners.get(i);
            if (o.getName().equals(accountData[0])
                    && o.getSurname().equals(accountData[1])
                    && o.getNickname().equals(accountData[2])){
                owner = o;
                found = true;
            }
            i ++;
        }
        return owner;
    }

    /**
     * Searches the account of the administrator among administrators of the application
     * @param admins an arraylist of Admin
     * @param accountData the data of the account
     * @return the corresponding administrator or null
     */
    private Admin searchAccountAdmin(ArrayList<Admin> admins, String[] accountData) {
        boolean found = false;
        int i = 0;
        Admin admin = null;
        while (i < admins.size() && !found){
            Admin a = admins.get(i);
            if (a.getName().equals(accountData[0])
                    && a.getSurname().equals(accountData[1])
                    && a.getNickname().equals(accountData[2])){
                admin = a;
                found = true;
            }
            i ++;
        }
        return admin;
    }

    /**
     * Shows all the property of the connected owner
     * @param ownerConnected the connected owner
     */
    public void seeMyProperties(Owner ownerConnected) {
        System.out.println("Properties : ");
        for (Property p: ownerConnected.getProperties().keySet()) {
            System.out.println(p.toString());
            System.out.println(p.getDescription());
        }
    }

    /**
     * Creates and Adds a property in the portfolio of the owner
     * @param ownerConnected the owner connected
     * @param propertyData data of the property
     * @param type the type of the property
     * @param maxOccupiers the number maximum of occupiers
     * @param nominalPrice the nominal price
     */
    public void addPropertyToThePortfolio(Owner ownerConnected, ArrayList<String> propertyData, TypeProperty type,
                                          int maxOccupiers, int nominalPrice) {
        if (!testSameAddress(propertyData)){
            ownerConnected.addProperty(new Property(type, propertyData.get(0), propertyData.get(1), propertyData.get(2),
                    propertyData.get(3), maxOccupiers), new Price(nominalPrice));
        }else{
            System.err.println("A property with the same address already exists.");
            System.err.println("The property has not been added.");
        }
    }

    /**
     * Tests if one of the properties of the application correspond to data given by the user
     * @param propertyData data of the property given by the user
     * @return a boolean
     */
    private boolean testSameAddress(ArrayList<String> propertyData){
        for (Owner o: allOwners) {
            for (Property p: o.getProperties().keySet()) {
                if (p.getTheCity().equals(propertyData.get(2)) && p.getAddressOfTheProperty().equals(propertyData.get(1))){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Calculates the sum of all properties
     * @return the sum of all properties
     */
    public int propertiesSum(){
        int sum = 0;
        for (Owner o: allOwners) {
            sum += o.getProperties().size();
        }
        return sum;
    }
    /**
     * Deletes a property of the portfolio of the owner
     * @param ownerConnected the connected owner
     * @param dataOfTheProperty data of the property to delete
     */
    public boolean deletePropertyOwner(Owner ownerConnected, ArrayList<String> dataOfTheProperty) {
        for (Property p: ownerConnected.getProperties().keySet()) {
            if (p.getNameProperty().equals(dataOfTheProperty.get(0)) &&
                    p.getAddressOfTheProperty().equals(dataOfTheProperty.get(1)) &&
                    p.getTheCity().equals(dataOfTheProperty.get(2))){
                ownerConnected.deleteProperty(p);
                return true;
            }
        }
        return false;
    }

    /**
     * Prints the content of the owner's wallet
     * @param ownerConnected the connected owner
     */
    public void seeMyWalletOwner(Owner ownerConnected) {
        System.out.println(ownerConnected.getVirtualWallet());
    }

    /**
     * Deletes a property according to given data
     * @param answers data corresponding to the property to delete
     * @return a boolean
     */
    public boolean deletePropertyAdmin(ArrayList<String> answers) {
        for (Owner o: allOwners) {
            boolean deleted = deletePropertyOwner(o, answers);
            if (deleted){
                return true;
            }
        }
        return false;
    }

    /**
     * Prints all properties of the application
     */
    public void seeAllProperties() {
        for (Owner o: allOwners) {
            System.out.println("Owner : " + o.getNickname());
            seeMyProperties(o);
        }
    }

    /**
     * Changes the description of a property
     * @param answers data of the property given by the user
     */
    public void changeDescription(ArrayList<String> answers) {
        for (Owner o: allOwners) {
            if (o.getNickname().equals(answers.get(3))){
                for (Property p: o.getProperties().keySet()) {
                    if (p.getNameProperty().equals(answers.get(0))&&
                    p.getAddressOfTheProperty().equals(answers.get(1))&&
                    p.getTheCity().equals(answers.get(2))){
                        p.changeDescription(answers.get(4));
                    }
                }
            }
        }
    }
}
