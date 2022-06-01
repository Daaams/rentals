/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userdata;

import internal.Price;
import internal.Property;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mgenetet
 */
public class Owner extends User{
    private HashMap<Property, Price> allProperties;
    private int virtualWallet;

    /**
     * constructor of the class Owner
     * @param login the login of the Owner
     * @param surname the family name of the Owner
     * @param name the name of the Owner
     * @param nick the nickname of the Owner
     * @param email the email of the Owner
     */
    public Owner(String login, String surname, String name, String nick, String email){
        super(login, surname, name, nick, email, TypeAccount.OWNER);
        allProperties = new HashMap<>();
        virtualWallet = 0;
    }

    /**
     * To add a property to the list of this owner
     * @param property the property to add
     * @param price the price of the property
     */
    public void addProperty(Property property, Price price){
        allProperties.put(property, price);
    }

    public HashMap<Property, Price> getProperties(){return new HashMap<>(allProperties);}
    /**
     * To delete a property of the list of this owner
     * @param property the property to add
     */
    public void deleteProperty(Property property){
        allProperties.remove(property);
    }
    
    /**
     * Prints the content of the owner's wallet
     */
    public void seeMyWallet() {
        System.out.println(virtualWallet);
    }

    public ArrayList<Property> getMyProperties() {
        ArrayList<Property> myProperties = new ArrayList<>();
        for (Property property : allProperties.keySet()) {
            myProperties.add(property);
        }
        return myProperties;
    }

    public void addMoney(int bidAmount) {
        virtualWallet += bidAmount;
    }
}
