/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userData;

/**
 *
 * @author mgenetet
 */
public class Tenant extends User{
    private final TypeAccount typeAccount = TypeAccount.TENANT;
    private int virtualWallet;
    
    public Tenant(String login, String surname, String name, String nick, String email){
        super(login, surname, name, nick, email);
        virtualWallet = 0;
    }

    public TypeAccount getTypeAccount(){
        return typeAccount;
    }
    
    void depositMoney(int money){
        virtualWallet += money;
    }
}
