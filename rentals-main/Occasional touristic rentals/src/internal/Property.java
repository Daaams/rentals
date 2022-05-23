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
    private TypeProperty type;
    private String name;
    private final String ADDRESS;
    private final String CITY;
    private String description;
    private int maxOccupiers;

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
     * To change the description of the property
     * @param newDescription
     */
    public void changeDescription(String newDescription){description = newDescription;}

    @Override
    public String toString(){
        return "Property : "+name+" located in "+CITY+" at the address "+ADDRESS+".";
    }
}
