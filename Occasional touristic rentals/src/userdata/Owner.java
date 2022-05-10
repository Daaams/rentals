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
    
    public Owner(String login, String surname, String name, String nick, String email){
        super(login, surname, name, nick, email);
        allProperties = new HashSet<Property>();
    }
    
    void addProperty(Property property){
        allProperties.add(property);
    }
    
    void deleteProperty(Property property){
        allProperties.remove(property);
    }
    
    public TypeAccount getTypeAccount(){
        return typeAccount;
    }
}
