/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userdata;

/**
 *
 * @author mgenetet
 */
public class Tenant extends User{
    private final TypeAccount typeAccount = TypeAccount.TENANT;
    private int virtualWallet;

    /**
     * constructor of the class Tenant
     * @param login the login of the tenant
     * @param surname the family name of the tenant
     * @param name the name of the tenant
     * @param nick the nickname of the tenant
     * @param email the email of the tenant
     */
    public Tenant(String login, String surname, String name, String nick, String email){
        super(login, surname, name, nick, email);
        virtualWallet = 0;
    }

    /**
     * To get the type of account of the status owner
     * @return TypeAccount.OWNER
     */
    @Override
    public TypeAccount getTypeAccount(){
        return typeAccount;
    }

    /**
     * To add money on the tenant's wallet
     * @param money
     */
    void depositMoney(int money){
        virtualWallet += money;
    }
}
