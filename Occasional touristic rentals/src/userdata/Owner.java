/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userdata;

import java.util.HashSet;

/**
 *
 * @author mgenetet
 */
public class Owner extends User{
    private HashSet<Property> allProperties;
    private final TypeAccount typeAccount = TypeAccount.OWNER;
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
        super(login, surname, name, nick, email);
        allProperties = new HashSet<Property>();
        virtualWallet = 0;
    }

    /**
     * To add a property to the list of this owner
     * @param property
     */
    void addProperty(Property property){
        allProperties.add(property);
    }

    /**
     * To delete a property of the list of this owner
     * @param property
     */
    void deleteProperty(Property property){
        allProperties.remove(property);
    }

    /**
     * To get the type of account of the status owner
     * @return TypeAccount.OWNER
     */
    @Override
    public TypeAccount getTypeAccount(){
        return typeAccount;
    }
}
