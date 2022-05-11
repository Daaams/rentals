/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userdata;

/**
 *
 * @author mgenetet
 */
public class Admin extends User{
    private final TypeAccount typeAccount = TypeAccount.ADMINISTRATOR;

    /**
     * constructor of the class Administrator
     * @param login the login of the administrator
     * @param surname the family name of the administrator
     * @param name the name of the administrator
     * @param nick the nickname of the administrator
     * @param email the email of the administrator
     */
    public Admin(String login, String surname, String name, String nick, String email){
        super(login, surname, name, nick, email);
    }
}
