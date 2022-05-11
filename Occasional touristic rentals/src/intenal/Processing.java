package intenal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import userdata.Admin;
import userdata.Owner;
import userdata.Tenant;
import userdata.TypeAccount;
import userdata.User;

public class Processing {

    private ArrayList<Tenant> allTenants;
    private ArrayList<Admin> allAdmins;
    private ArrayList<Owner> allOwners;


    /**
     * constructor of the class
     */
    public Processing() {
        allAdmins = new ArrayList<>();
        allOwners = new ArrayList<>();
        allTenants = new ArrayList<>();
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
        }else if (type == TypeAccount.OWNER){
            Owner u = new Owner(personalInforations.get(0), personalInforations.get(1), personalInforations.get(2), personalInforations.get(3), personalInforations.get(4));
            allOwners.add(u);
        }else{
            Tenant u = new Tenant(personalInforations.get(0), personalInforations.get(1), personalInforations.get(2), personalInforations.get(3), personalInforations.get(4));
            allTenants.add(u);
        }
    }

    /**
     * Connect a tenant to the application
     * @param personalInforations informations given by the tenant
     * @return the connected tenant
     */
    public Tenant connectTenant(ArrayList<String> personalInforations) {
        int i = 0;
        Tenant connected = null;
        while (i < allTenants.size() && connected == null){
            Tenant t = allTenants.get(i);
            if (t.getNickname().equals(personalInforations.get(0))
            && t.getLogin().equals(personalInforations.get(1)))
            {
                connected = t;
            }
            i++;
        }
        return connected;
    }

    /**
     * Connect an owner to the application
     * @param personalInforations informations given by the owner
     * @return the connected owner
     */
    public Owner connectOwner(ArrayList<String> personalInforations) {
        int i = 0;
        Owner connected = null;
        while (i < allOwners.size() && connected == null){
            Owner t = allOwners.get(i);
            if (t.getNickname().equals(personalInforations.get(0))
                    && t.getLogin().equals(personalInforations.get(1)))
            {
                connected = t;
            }
            i++;
        }
        return connected;
    }

    /**
     * Connect an administrator to the application
     * @param personalInforations informations given by the administrator
     * @return the connected administrator
     */
    public Admin connectAdmin(ArrayList<String> personalInforations) {
        int i = 0;
        Admin connected = null;
        while (i < allAdmins.size() && connected == null){
            Admin t = allAdmins.get(i);
            if (t.getNickname().equals(personalInforations.get(0))
                    && t.getLogin().equals(personalInforations.get(1)))
            {
                connected = t;
            }
            i++;
        }
        return connected;
    }

    /**
     * Tests if an account with the same personnal informations has already been created
     * @param personalInformations informations of the user
     * @param type the type of account of the user
     * @return a boolean
     */
    public boolean testValidityAccount(ArrayList<String> personalInformations, TypeAccount type) {
        boolean nonValid = false;
        int i = 0;
        switch (type){
            case OWNER :
                ArrayList<Owner> listO = new ArrayList<>(allOwners);
                while (i < listO.size() && !nonValid){
                    Owner o = listO.get(i);
                    nonValid = testNickname(o, personalInformations) && testMail(o, personalInformations);
                    i++;
                }
                break;
            case ADMINISTRATOR:
                ArrayList<Admin> listA = new ArrayList<>(allAdmins);
                while (i < listA.size() && !nonValid){
                    Admin a = listA.get(i);
                    nonValid = testNickname(a, personalInformations) && testMail(a, personalInformations);
                    i++;
                }
                break;
            case TENANT:
                ArrayList<Tenant> listT = new ArrayList<>(allTenants);
                while (i < listT.size() && !nonValid){
                    Tenant t = listT.get(i);
                    nonValid = testNickname(t, personalInformations) && testMail(t, personalInformations);
                    i++;
                }
                break;
        }
        return nonValid;
    }

    /**
     *
     * Tests if the nickname given during the account creation already exists
     * @param u the user to compare
     * @param personalInformations informations given by the user wanted to create an account
     * @return a boolean
     */
    private boolean testNickname(User u, ArrayList<String> personalInformations){
        boolean test = u.getNickname().equals(personalInformations.get(3));
        if (test){
            System.out.println("This nickname already exists, choose another one");
        }
        return test;
    }

    /**
     *
     * Tests if the mail address given during the account creation already exists
     * @param u the user to compare
     * @param personalInformations informations given by the user wanted to create an account
     * @return a boolean
     */
    private boolean testMail(User u, ArrayList<String> personalInformations){
        boolean test = u.getNickname().equals(personalInformations.get(4));
        if (test){
            System.err.println("This mail already exists, choose another one");
        }
        return test;
    }

    /**
     * To add money into the Tenant's wallet
     * @param userConnected the tenant
     * @param money the money to add
     */
    public void addMoneyOnWallet(Tenant userConnected, int money) {
        userConnected.depositMoney(money);
    }

    public void seeAllProperties() {
    }

    public void consultDataOfAProperty(String property) {
    }

    /**
     * To see the content of the tenant wallet
     * @param userConnected the tenant
     */
    public void seeMyWallet(Tenant userConnected) {
        System.out.println(userConnected.getVirtualWallet());
    }
}
