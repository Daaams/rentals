/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userData;

/**
 *
 * @author mgenetet
 */
class Property {
    private TypeProperty type;
    private String name;
    private final String ADDRESS;
    private final String CITY;
    private String description;
    private int nominalPrice;
    private int maxOccupiers;
    
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
