/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package internal;

/**
 *
 * @author damie
 */
public class Price {
    private int thePrice;

    /**
     * Constructor of the price
     * @param price the nominal price
     */
    public Price(int price){
        thePrice = price;
    }

    /**
     * To get the nominal price
     * @return the nominal price
     */
    public int getThePrice(){return thePrice;}

    /**
     * To change the nominal price
     * @param newPrice the new nominal price
     */
    public void changeThePrice(int newPrice){thePrice = newPrice;}
    
}
