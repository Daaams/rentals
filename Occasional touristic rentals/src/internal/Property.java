/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package internal;

import userdata.TypeProperty;

/**
 *
 * @author mgenetet
 */
public class Property {
    //class attributes
    private String name;
    private final String ADDRESS;
    private final String CITY;
    private String description;
    private int maxOccupiers;
    private boolean isRented;

    // associations
    private TypeProperty type;
    private Bid currentBid;
    private boolean inAvailableList;



    /**
     * Constructor of the class Property
     * @param type the type of this property
     * @param name the name of this property
     * @param address the address of this property
     * @param city the city of this property
     * @param desc the description of this property
     * @param maxOccupiers the max quantity of people that can sleep per night
     */
    public Property(TypeProperty type, String name, String address, 
            String city, String desc, int maxOccupiers){
        this.type = type;
        this.name = name;
        ADDRESS = address;
        CITY = city;
        description = desc;
        this.maxOccupiers = maxOccupiers;
        isRented = false;
        currentBid = null;
        inAvailableList = true;
    }

    /**
     * To get the name of the property
     * @return the name
     */
    public String getNameProperty(){
        return name;
    }
    /**
     * To get the address of the property
     * @return the address
     */
    public String getAddressOfTheProperty(){
        return ADDRESS;
    }
    /**
     * To get the city of the property
     * @return the city
     */
    public String getTheCity(){
        return CITY;
    }
    /**
     * To get the number max of occupiers of the property
     * @return the number max of occupiers
     */
    public int getMaxOccupiers(){
        return maxOccupiers;
    }
    /**
     * To get the type of the property
     * @return the type of the property
     */
    public TypeProperty getTypeProperty(){return type;}
    /**
     * To get the description of a property
     * @return the description
     */
    public String getDescription(){return description;}
    /**
     * To get the current bid on the property
     * @return the bid
     */
    public Bid getCurrentBid(){return currentBid;}
    /**
     * To change the description of the property
     * @param newDescription
     */
    public void changeDescription(String newDescription){description = newDescription;}
    /**
     * To change the type of the property
     * @param type the new type
     */
    public void changeType(TypeProperty type){this.type = type;}
    /**
     * To change the name of the property
     * @param name the new name
     */
    public void changeName(String name){this.name = name;}
    /**
     * To change the maxOccupiers of the property
     * @param maxOccupiers the new type
     */
    public void changeMaxOccupiers(int maxOccupiers){this.maxOccupiers = maxOccupiers;}

    /**
     * Put the new bid
     * @param theBid Bid
     */
    public void setBid(Bid theBid){currentBid = theBid;}

    /**
     * Return the name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Put True if this available else false
     * @param b
     */
    public void setAvailable(boolean b) {
        inAvailableList = b;
    }

    /**
     * Return if the property is available
     * @return boolean
     */
    public boolean isAvailable(){return inAvailableList;}

    /**
     * Methode toString of Property
     * @return String
     */
    @Override
    public String toString(){
        return "Property : "+name+" located in "+CITY+" at the address "+ADDRESS+" is a "+ type+".";
    }

}
