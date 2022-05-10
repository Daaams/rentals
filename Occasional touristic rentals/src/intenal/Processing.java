package intenal;

import java.util.ArrayList;

import userdata.Admin;
import userdata.Owner;
import userdata.Tenant;
import userdata.TypeAccount;
import userdata.User;

public class Processing {

    private ArrayList<User> allUsers;

    /**
     * constructor of the class
     */
    public Processing() {
        allUsers = new ArrayList<>();
    }

    /**
     * To get the list of users
     * @return an ArrayList
     */
    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    /**
     * Create a user account
     * @param personalInforations informations given by the user
     * @param type the type of account to create
     */
    public void createAccount(ArrayList<String> personalInforations, TypeAccount type) {
        User u;
        if (type == TypeAccount.ADMINISTRATOR){
            u = new Admin(personalInforations.get(0), personalInforations.get(1), personalInforations.get(2), personalInforations.get(3), personalInforations.get(4));
        }else if (type == TypeAccount.OWNER){
            u = new Owner(personalInforations.get(0), personalInforations.get(1), personalInforations.get(2), personalInforations.get(3), personalInforations.get(4));
        }else{
            u = new Tenant(personalInforations.get(0), personalInforations.get(1), personalInforations.get(2), personalInforations.get(3), personalInforations.get(4));
        }
        allUsers.add(u);
    }

    /**
     * Connect a user to the application
     * @param personalInforations informations given by the user
     * @param type the type of account of the user
     */
    public void connect(ArrayList<String> personalInforations, TypeAccount type) {
        for(User u : allUsers){
        }
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
        while (i < allUsers.size() && !nonValid){
            User u = allUsers.get(i);
            nonValid = testNickname(u, personalInformations) || testMail(u, personalInformations);
            i++;
        }
        return nonValid;
    }

    /**
     *
     * To test if the nickname given during the account creation already exists
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
     * To test if the mail address given during the account creation already exists
     * @param u the user to compare
     * @param personalInformations informations given by the user wanted to create an account
     * @return a boolean
     */
    private boolean testMail(User u, ArrayList<String> personalInformations){
        boolean test = u.getNickname().equals(personalInformations.get(4));
        if (test){
            System.out.println("This mail already exists, choose another one");
        }
        return test;
    }

}
