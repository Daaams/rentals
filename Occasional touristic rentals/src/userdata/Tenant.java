/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userdata;

import internal.Bid;
import internal.Reservation;

import java.util.HashSet;

/**
 *
 * @author mgenetet
 */
public class Tenant extends User{
    HashSet<Bid> myBids;
    HashSet<Reservation> myReservation;
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
        super(login, surname, name, nick, email, TypeAccount.TENANT);
        virtualWallet = 0;
        myBids = new HashSet<>();
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
    
    /**
     * To see the content of the tenant wallet
     */
    public void seeMyWallet() { System.out.println(virtualWallet); }

    /**
     * Add a bid to the list myBids
     * @param bid Bid
     */
    public void addABid(Bid bid){myBids.add(bid);}

    /**
     * Return all the bids of the tenant
     * @return HashSet<Bid>
     */
    public HashSet<Bid> getMyBids(){return new HashSet<>(myBids);}

    /**
     * Add a new reservation to all the reservation of the tenant
     * @param reservation
     */
    public void createNewReservation(Reservation reservation) {
        myReservation.add(reservation);
    }

    /**
     * Return all the reservation of the tenant
     * @return HashSet<Reservation>
     */
    public HashSet<Reservation> getMyReservations() {
        return myReservation;
    }
}
