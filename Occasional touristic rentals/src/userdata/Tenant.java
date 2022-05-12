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
     * To add money on the tenant's wallet
     * @param money the money to add into the wallet
     */
    public void depositMoney(int money){
        virtualWallet += money;
    }

    /**
     * To withdraw money of the tenant's wallet
     * @param money the money to withraw of the wallet
     */
    public void withdrawMoney(int money) { virtualWallet -= money;}

    /**
     * To get the content of the Tenant user
     * @return an integer corresponding to the money of the Tenant
     */
    public int getVirtualWallet(){return virtualWallet;}

}
