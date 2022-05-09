/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userData;

/**
 *
 * @author mgenetet
 */
public class Admin extends User{
    private final TypeAccount typeAccount = TypeAccount.ADMINISTRATOR;
    
    public Admin(String login, String surname, String name, String nick, String email){
        super(login, surname, name, nick, email);
    }

    public TypeAccount getTypeAccount(){
        return typeAccount;
    }
    
}
