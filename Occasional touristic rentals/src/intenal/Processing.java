package intenal;

import java.util.ArrayList;

import userData.Admin;
import userData.Owner;
import userData.Tenant;
import userData.TypeAccount;
import userData.User;

public class Processing {

    private ArrayList<User> array;

    /**
     * constructor of the class
     */
    public Processing() {
        array = new ArrayList<>();
    }

    /**
     * Create a user account
     * @param personnalInforations informations given by the user
     * @param type the type of account to create
     */
    public void createAccount(ArrayList<String> personnalInforations, TypeAccount type) {
        User u;
        if (type == TypeAccount.ADMINISTRATOR){
            u = new Admin(personnalInforations.get(0), personnalInforations.get(1), personnalInforations.get(2), personnalInforations.get(3), personnalInforations.get(4));
        }else if (type == TypeAccount.OWNER){
            u = new Owner(personnalInforations.get(0), personnalInforations.get(1), personnalInforations.get(2), personnalInforations.get(3), personnalInforations.get(4));
        }else{
            u = new Tenant(personnalInforations.get(0), personnalInforations.get(1), personnalInforations.get(2), personnalInforations.get(3), personnalInforations.get(4));
        }
        array.add(u);
    }

    /**
     * Connect a user to the application
     * @param personnalInforations informations given by the user
     * @param type the type of account of the user
     */
    public void connect(ArrayList<String> personnalInforations, TypeAccount type) {
        for(User u : array){
        }
    }

    /**
     * Tests if an account with the same personnal informations has already been created
     * @param personnalInformations informations of the user 
     * @param type the type of account of the user
     * @return a boolean
     */
    public boolean testValidityAccount(ArrayList<String> informations, TypeAccount type) {
        return true;
    }
}
