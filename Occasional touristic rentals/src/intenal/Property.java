/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package intenal;

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
    private int nominalPrice;
    private int maxOccupiers;

    /**
     * Constructor of the class Property
     * @param type the type of this property
     * @param name the name of this property
     * @param address the address of this property
     * @param city the city of this property
     * @param desc the description of this property
     * @param minPrice the price for one person
     * @param maxOccupiers the max quantity of people that can sleep per night
     */
    public Property(TypeProperty type, String name, String address, 
            String city, String desc, int minPrice, int maxOccupiers){
        this.type = type;
        this.name = name;
        ADDRESS = address;
        CITY = city;
        description = desc;
        nominalPrice = minPrice;
        this.maxOccupiers = maxOccupiers;
    }
}
