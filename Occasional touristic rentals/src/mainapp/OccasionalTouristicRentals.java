/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mainapp;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.SplittableRandom;

import internal.Price;
import internal.Processing;
import internal.Property;
import userdata.*;

/**
 *
 * @author damie
 */
public class OccasionalTouristicRentals {

    private boolean waitingForString;
    private int numberRead;
    private Scanner scan;
    private boolean quit;
    private Processing process;
    private boolean someoneConnected;

    /**
     * main method, launch the application
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OccasionalTouristicRentals app = new OccasionalTouristicRentals();
        app.remplissage();
        app.run();
    }

    /**
     * 3 utilisateurs de l'application pour ne pas les créer à chaque fois
     */
    private void remplissage() {
        ArrayList<String> data = new ArrayList<>();
        data.add("login");
        data.add("surname");
        data.add("name");
        data.add("nick");
        data.add("email");
        process.createAccount(data, TypeAccount.TENANT);
        process.createAccount(data, TypeAccount.OWNER);
        process.createAccount(data, TypeAccount.ADMINISTRATOR);
        process.getAllOwners().get(0).addProperty(new Property(TypeProperty.HOUSE, "name", "address",
                "city", "little description", 2), new Price(20));
    }

    /**
     * Constructor of the application
     */
    public OccasionalTouristicRentals() {
        scan = new Scanner(System.in);
        process = new Processing();
        someoneConnected = false;
    }

    /**
     * Call the prompt while the user do not quit
     */
    private void run() {
        waitingForString = false;
        while (!quit) {
            firstPromptAction();
        }
    }

    /**
     * Print actions to select by the user at the beginning
     */
    private void firstPrompt() {
        System.out.println("0. Quit.");
        System.out.println("1. Create an account");
        System.out.println("2. Connection");
        System.out.println("");
        System.out.println("What do you want to do?");
    }

    /**
     * Take the selection of the user and launch the event associated
     */
    private void firstPromptAction() {
        if (!waitingForString) {
            firstPrompt();
        }
        try {
            numberRead = Integer.parseInt(readString());
        } catch (NumberFormatException nfe) {
            System.err.println("Error: please enter an integer.");
            firstPromptAction();
        }
        switch (numberRead) {
            case 0:
                ARR_Quit();
                break;
            case 1:
                ARR_CreateAccounts();
                break;
            case 2:
                ARR_Connection();
                break;
            default:
                System.err.println("Error: no such menu item.");
        }
    }

    /**
     * Event for the account creation
     */
    private void ARR_CreateAccounts() {
        TypeAccount type = askType();
        String[] questions = {"What's your login ? ", "What's your surname ? ", "What's your name ? ",
                "What's your nickname ? ", "What's your email ? "};
        ArrayList<String> data = takeData(questions);
        boolean nonValid = process.testValidityAccount(data, type);
        if (!nonValid) {
            process.createAccount(data, type);
        } else {
            System.err.println("This account already exists. Please, start again");
        }
        firstPromptAction();
    }

    /**
     * Event for the user connection
     */
    private void ARR_Connection() {
        TypeAccount type = askType();
        String[] questions = {"What's your login ?"};
        User userConnected = process.connectUser(takeData(questions),TypeAccount.TENANT);
        
        if (userConnected != null) { someoneConnected = true; }
        else { displayErrorMessageConnection(); }
        
        while (someoneConnected) {
            switch (type){
                case TENANT :
                    ARR_EventsTenant(userConnected);
                    break;
                case OWNER :
                    ARR_EventsOwner(userConnected);
                    break;
                case ADMINISTRATOR : 
                    ARR_EventsAdministrator(userConnected);
                    break;
            }
        }
        firstPromptAction();
    }

    /**
     * displays in the console an error message
     */
    private void displayErrorMessageConnection() {
        System.err.println("Your nickname or your login do not match.");
        System.err.println("Please try again or create an account with the same data.");
    }

    /**
     * Reads a string entered by one user in the console
     * @return the string entered
     */
    private String readString(){
        String str = scan.nextLine();
        while (stringReadInConsole(str) == false){
            System.err.println("Your entry is null or empty, please, enter something");
            str = scan.nextLine();
        }
        return str;
    }

    /**
     * Ask some questions to the user and take his personal data
     *
     * @param questions an array containing questions to ask
     * @return the data answered by the user
     */
    private ArrayList<String> takeData(String[] questions) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String question : questions) {
            System.out.println(question);
            arrayList.add(readString());
        }
        return arrayList;
    }

    /**
     * Tests if the answer given by the user is not null or empty
     *
     * @param s the answer of the user
     * @return a boolean
     */
    private boolean stringReadInConsole(String s) {
        return (!s.equals("") && !s.equals(null));
    }

    /**
     * Asks the user for his status for the connection or the account creation
     *
     * @return the type of account
     */
    private TypeAccount askType() {
        TypeAccount type = null;
        System.out.println("0. Administrator.");
        System.out.println("1. Owner.");
        System.out.println("2. Tenant.");
        askForInt("Which type of account ?");
        switch (numberRead) {
            case 0:
                type = TypeAccount.ADMINISTRATOR;
                break;
            case 1:
                type = TypeAccount.OWNER;
                break;
            case 2:
                type = TypeAccount.TENANT;
                break;
            default:
                System.err.println("Error: no such menu item.");
        }
        return type;
    }

    /**
     * displays the actions that a tenant can do
     */
    private void ARR_EventsTenant(User userConnected) {
        Tenant tenantConnected = process.searchAccountTenant(userConnected.getLogin());
        System.out.println("");
        System.out.println("What do you want to do ?");
        System.out.println("");
        System.out.println("1. Add money on my virtual account.");
        System.out.println("2. withdraw money of my virtual account.");
        System.out.println("3. See my data.");
        System.out.println("4. Change my data.");
        System.out.println("5. See all properties on the application.");
        System.out.println("6. Consult data of a property.");
        System.out.println("7. See my wallet.");
        System.out.println("8. Bid on a property");
        System.out.println("9. See my bids"); // à faire
        System.out.println("10. See the highest bid among all properties"); // à faire
        System.out.println("11. See the highest bid for a given month"); // à faire
        System.out.println("12. Log out.");
        System.out.println("");
        askForEventTenants(tenantConnected);
    }

    /**
     * displays the actions that an owner can do
     */
    private void ARR_EventsOwner(User userConnected) {
        Owner ownerConnected = process.searchAccountOwner(userConnected.getLogin());
        System.out.println("");
        System.out.println("What do you want to do ?");
        System.out.println("");
        System.out.println("1. See my data");
        System.out.println("2. Change my data");
        System.out.println("3. See all my properties.");
        System.out.println("4. Add a property to my portfolio.");
        System.out.println("5. Delete a property of my portfolio.");
        System.out.println("6. Change data of a property.");
        System.out.println("7. See my wallet.");
        System.out.println("8. List bids on my properties");// à faire
        System.out.println("9. Log out.");
        System.out.println("");
        askForEventOwners(ownerConnected);
    }

    /**
     * displays the actions that an administrator can do
     */
    private void ARR_EventsAdministrator(User userConnected) {
        Admin adminConnected = process.searchAccountAdmin(userConnected.getLogin());
        System.out.println("");
        System.out.println("What do you want to do ?");
        System.out.println("");
        System.out.println("1. See my data");
        System.out.println("2. Change my data");
        System.out.println("3. View all users of the application.");
        System.out.println("4. Delete an account.");
        System.out.println("5. Delete a property.");
        System.out.println("6. Change the description of a property.");
        System.out.println("7. Log out.");
        System.out.println("");
        askForEventAdmins(adminConnected);
    }

    /**
     * Triggers the event selected by the user connected as tenant
     *
     * @param tenantConnected the tenant
     */
    private void askForEventTenants(Tenant tenantConnected) {
        try {
            numberRead = Integer.parseInt(readString());
        } catch (NumberFormatException nfe) {
            System.err.println("Error: please enter an integer.");
            askForEventTenants(tenantConnected);
        }
        switch (numberRead) {
            case 1:
                process.addMoneyOnWallet(tenantConnected, askForMoney());
                System.out.println("It has been added");
                break;
            case 2:
                process.withdrawMoneyOfWallet(tenantConnected, askForMoney());
                break;
            case 3:
                process.seeData(tenantConnected);
                break;
            case 4:
                ARR_AskDataToChange(tenantConnected);
                break;
            case 5:
                process.seeAllProperties();
                break;
            case 6:
                ARR_ConsultDataOfAProperty();
                break;
            case 7:
                tenantConnected.seeMyWallet();
                break;
            case 8:
                ARR_BidOnAProperty(tenantConnected);
                break;
            case 9:
                // à faire --> see my bids
                System.out.println("nothing for the moment");
                break;
            case 10:
                System.err.println(tenantConnected.getNickname() + ", you have been disconnected");
                someoneConnected = false;
                break;
            default:
                System.err.println("Error: no such menu item.");
        }
    }

    /**
     * Asks the tenant for the quantity of money to add into his wallet
     *
     * @return the integer enter by the user
     */
    private int askForMoney() {
        System.out.println("How much money ?");
        System.out.println("Integer must be multiple of 5.");
        String str = readString();
        try {
            numberRead = Integer.parseInt(str);
            if (Integer.parseInt(str) % 5 != 0) {
                System.err.println("You must enter an integer multiple of 5");
                askForMoney();
            }
            if (numberRead == 0){
                System.err.println("You must enter an integer multiple of 5 greater than 0");
            }
        } catch (NumberFormatException nfe) {
            System.err.println("Error: please enter an integer.");
            askForMoney();
        }
        return numberRead;
    }

    /**
     * Event for consulting data of a property
     */
    private void ARR_ConsultDataOfAProperty() {
        System.out.println("Do you want to see all properties before ? (yes / no)");
        String str = readString();
        if (str.equals("yes")){
            process.seeAllProperties();
            System.out.println("");
            askForAproperty();
        } else if (str.equals("no")) {
            askForAproperty();
        } else {
            System.err.println("I did not understand your answer.");
            ARR_ConsultDataOfAProperty();
        }
    }

    /**
     * Asks the user for a property to see
     */
    private void askForAproperty(){
        System.out.println("Which property do you want to consult ?");
        process.consultDataOfAProperty(readString());
    }

    /**
     * Triggers the event selected by the user connected as owner
     * @param ownerConnected the connected owner
     */
    private void askForEventOwners(Owner ownerConnected) {
        try {
            numberRead = Integer.parseInt(readString());
        } catch (NumberFormatException nfe) {
            System.err.println("Error: please enter an integer.");
            askForEventOwners(ownerConnected);
        }
        switch (numberRead) {
            case 1:
                process.seeData(ownerConnected);
                break;
            case 2:
                ARR_AskDataToChange(ownerConnected);
                break;
            case 3:
                process.seeMyProperties(ownerConnected);
                break;
            case 4:
                ArrayList<String> questions = new ArrayList<>();
                questions.add("The name of the property :");
                questions.add("The address of the property :");
                questions.add("The city :");
                questions.add("Description :");
                process.addPropertyToThePortfolio(ownerConnected, ARR_AskDataForProperty(questions),
                        askTypeOfTheProperty(), askForInt("What is the number max of occupiers ?"),
                        askForInt("What is the number nominal price ?"));
                break;
            case 5:
                ArrayList<String> questions2 = new ArrayList<>();
                questions2.add("The name of the property :");
                questions2.add("The address of the property :");
                questions2.add("The city :");
                if (ownerConnected.getProperties().size() == 0){
                    System.out.println("There is no properties in my portfolio.");
                }else{
                    boolean deleted = process.deletePropertyOwner(ownerConnected, ARR_AskDataForProperty(questions2));
                    if (deleted){
                        System.out.println("It has been deleted");
                    }else{
                        System.out.println("Wrong data, it has not been deleted");
                    }
                }
                break;
            case 6:
                ARR_ChangeDataOfAProperty(ownerConnected);
                break;
            case 7:
                ownerConnected.seeMyWallet();
                break;
            case 8:
                // à faire --> see bids on my properties
                System.out.println("nothing for the moment");
                break;
            case 9:
                System.err.println(ownerConnected.getNickname() + ", you have been disconnected");
                someoneConnected = false;
                break;
            default:
                System.err.println("Error: no such menu item.");
        }
    }

    /**
     * Event for changing data of a property
     * @param ownerConnected the connected owner
     */
    private void ARR_ChangeDataOfAProperty(Owner ownerConnected) {
        System.out.println("Do you want to see data of all your properties before ? (yes / no)");
        String str = readString();
        if (str.equals("yes")) {
            process.seeMyProperties(ownerConnected);
            changeDataOfAProperty(ownerConnected);
        } else if (str.equals("no")) {
            changeDataOfAProperty(ownerConnected);
        }else{
            System.err.println("I did not understand your answer");
            ARR_ChangeDataOfAProperty(ownerConnected);
        }
    }

    /**
     * Retrieves data concerning the property
     * @param ownerConnected the connected owner
     */
    private void changeDataOfAProperty(Owner ownerConnected) {
        ArrayList<String> questions2 = new ArrayList<>();
        questions2.add("The name of the property :");
        questions2.add("The address of the property :");
        questions2.add("The city :");
        dataToChange(ownerConnected, ARR_AskDataForProperty(questions2));
    }

    /**
     * Asks the user for the data he wants to change
     * @param ownerConnected
     * @param data
     */
    private void dataToChange(Owner ownerConnected, ArrayList<String> data) {
        Property p = process.findProperty(ownerConnected, data);
        if (p == null){
            System.err.println("No property matches");
        }else{
            System.out.println("What do you want to change ?");
            System.out.println("");
            System.out.println("1. The type of the property.");
            System.out.println("2. The name of the property.");
            System.out.println("3. The description of the property.");
            System.out.println("4. The number max of occupiers.");
            System.out.println("5. The nominal price.");
            System.out.println("");
            int intAnswer = askForInt("What is your choice ?");
            switch (intAnswer) {
                case 1:
                    process.changeTypeOfTheProperty(p, askTypeOfTheProperty());
                    break;
                case 2:
                    System.out.println("What is the new name of the property ?");
                    String newName = readString();
                    process.changeNameOfTheProperty(p, newName);
                    break;
                case 3:
                    ArrayList<String> dataAndDesc = new ArrayList<>();
                    dataAndDesc.add(p.getNameProperty());
                    dataAndDesc.add(p.getAddressOfTheProperty());
                    dataAndDesc.add(p.getTheCity());
                    dataAndDesc.add(ownerConnected.getNickname());
                    System.out.println("What is the new description ?");
                    dataAndDesc.add(readString());
                    process.changeDescription(dataAndDesc);
                    break;
                case 4:
                    changeNumberoccupiers(p);
                    break;
                case 5:
                    changeNominalPrice(ownerConnected, p);
                    break;
                default:
                    System.err.println("Error: no such menu item.");
            }
        }
    }

    /**
     * Method for changing the nominal price
     * @param ownerConnected the connected owner
     * @param p the current property
     */
    private void changeNominalPrice(Owner ownerConnected, Property p) {
        System.out.println("Do you want to see the current nominal price before ? (yes / no)");
        String str = readString();
        if (str.equals("yes")) {
            System.out.println("The current nominal price is " + p.getMaxOccupiers());
            process.changeNominalPrice(ownerConnected, p, askForInt("What is the new nominal price ?"));
        } else if (str.equals("no")) {
            process.changeNominalPrice(ownerConnected, p, askForInt("What is the new nominal price ?"));
        }else{
            System.err.println("I did not understand your answer");
            changeNominalPrice(ownerConnected, p);
        }
    }

    /**
     * Method for changing the number max of occupiers
     * @param p the current property
     */
    private void changeNumberoccupiers(Property p) {
        System.out.println("Do you want to see the number max of occupiers before ? (yes / no)");
        String str = readString();
        if (str.equals("yes")) {
            System.out.println("The number max of occupiers is " + p.getMaxOccupiers());
            process.changeTheNumberMaxOfOccupiers(p, askForInt("What is the new max of occupiers ?"));
        } else if (str.equals("no")) {
            process.changeTheNumberMaxOfOccupiers(p, askForInt("What is the new max of occupiers ?"));
        }else{
            System.err.println("I did not understand your answer");
            changeNumberoccupiers(p);
        }
    }

    /**
     * Asks the user for an integer
     * @param question the question to ask depending on the context
     * @return the integer enter by the user
     */
    private int askForInt(String question) {
        int number = 0;
        System.out.println(question);
        System.out.println("");
        try {
            number = Integer.parseInt(readString());
        } catch (NumberFormatException nfe) {
            System.err.println("Error: please enter an integer.");
            askForInt(question);
        }
        return number;
    }

    /**
     * Asks the user to choose the type of property
     * @return the type of property chosen
     */
    private TypeProperty askTypeOfTheProperty() {
        System.out.println("What is your property ?");
        System.out.println("");
        System.out.println("1. House");
        System.out.println("2. Estate");
        System.out.println("3. Apartment");
        System.out.println("4. Room");
        System.out.println("5. Homestead");
        System.out.println("");
        TypeProperty type = null;
        try {
            numberRead = Integer.parseInt(readString());
        } catch (NumberFormatException nfe) {
            System.err.println("Error: please enter an integer.");
            askTypeOfTheProperty();
        }
        switch (numberRead) {
            case 1:
                type = TypeProperty.HOUSE;
                break;
            case 2:
                type = TypeProperty.ESTATE;
                break;
            case 3:
                type = TypeProperty.APARTMENT;
                break;
            case 4:
                type = TypeProperty.ROOM;
                break;
            case 5:
                type = TypeProperty.HOMESTEAD;
                break;
            default:
                System.err.println("Error: no such menu item.");
        }
        return type;
    }

    /**
     * Event for craation of a property
     * @param questions questions to ask for collecting data
     * @return an Arraylist containing data entered
     */
    private ArrayList<String> ARR_AskDataForProperty(ArrayList<String> questions) {
        ArrayList<String> propertyData = new ArrayList<>();
        for (int i = 0; i < questions.size(); i ++){
            System.out.println(questions.get(i));
            propertyData.add(readString());
        }
        return propertyData;
    }

    /**
     * Triggers the event selected by the user connected as administrator
     * @param adminConnected the connected administrator
     */
    private void askForEventAdmins(Admin adminConnected) {
        try {
            numberRead = Integer.parseInt(readString());
        } catch (NumberFormatException nfe) {
            System.err.println("Error: please enter an integer.");
            askForEventAdmins(adminConnected);
        }
        switch (numberRead) {
            case 1:
                process.seeData(adminConnected);
                break;
            case 2:
                ARR_AskDataToChange(adminConnected);
                break;
            case 3:
                process.seeAllUsers();
                break;
            case 4:
                ARR_DeleteAccount(adminConnected);
                break;
            case 5:
                ARR_DeleteProperty();
                break;
            case 6:
                ARR_ChangeDescriptionOfProperty();
                break;
            case 7:
                System.err.println(adminConnected.getNickname() + ", you have been disconnected");
                someoneConnected = false;
                break;
            default:
                System.err.println("Error: no such menu item.");
        }
    }

    /**
     * Event for changing the description of a property
     */
    private void ARR_ChangeDescriptionOfProperty() {
        System.out.println("Do you want to see data of all properties before ? (yes / no)");
        String str = readString();
        String [] questions = {"What is the name of the property ?", "What is the address of the property ?",
                "In which city is located the property ?", "What is the nickname of the owner ?", "What is the new description ?"};
        if (str.equals("yes")) {
            process.seeAllProperties();
            changeDescription(takeData(questions));
        } else if (str.equals("no")) {
            changeDescription(takeData(questions));
        }else{
            System.err.println("I did not understand your answer");
            ARR_ChangeDescriptionOfProperty();
        }
    }

    /**
     * Changes the description of a property corresponding to data asked
     * @param answers data of the property with the new description
     */
    private void changeDescription(ArrayList<String> answers) {
        process.changeDescription(answers);
    }

    /**
     * Event for deleting a property
     */
    private void ARR_DeleteProperty() {
        int sumProperties = process.propertiesSum();
        if (sumProperties == 0){
            System.out.println("There is no properties in the application");
        }else{
            System.out.println("Do you want to see all properties before ? (yes / no)");
            String str = readString();
            if (str.equals("yes")) {
                process.seeAllProperties();
                deleteProperty();
            } else if (str.equals("no")) {
                deleteProperty();
            }else{
                System.err.println("I did not understand your answer");
                ARR_DeleteProperty();
            }
        }
    }

    /**
     * Deletes a property corresponding to data entered by the user
     */
    private void deleteProperty() {
        String [] questions = {"What is the name of the property ?", "What is the address of the property ?",
        "In which city is located the property ?"};
        ArrayList<String> answers = new ArrayList<>();
        for (int i = 0; i < questions.length; i ++){
            System.out.println(questions[i]);
            answers.add(readString());
        }
        boolean deleted = process.deletePropertyAdmin(answers);
        if (deleted){
            System.out.println("It has been deleted");
        }else{
            System.out.println("It has not been deleted");
        }
    }

    /**
     * Asks the connected user if he wants to see his data before changing them
     * @param userConnected the connected user
     */
    private void ARR_AskDataToChange(User userConnected) {
        System.out.println("Do you want to see your data before ? (yes / no)");
        String str = readString();
        if (str.equals("yes")) {
            process.seeData(userConnected);
            changeData(userConnected);
        } else if (str.equals("no")) {
            changeData(userConnected);
        }else{
            System.err.println("I did not understand your answer");
            ARR_AskDataToChange(userConnected);
        }
    }

    /**
     * Asks the connected user which data he wants to change
     * @param userConnected the connected user
     */
    private void changeData(User userConnected) {
        System.out.println("");
        System.out.println("Which data do you want to change ?");
        System.out.println("");
        System.out.println("1. My name.");
        System.out.println("2. My surname.");
        System.out.println("3. My nickname.");
        System.out.println("4. My mail.");
        System.out.println("");
        try {
            numberRead = Integer.parseInt(readString());
        } catch (NumberFormatException nfe) {
            System.err.println("Error: please enter an integer.");
            changeData(userConnected);
        }
        switch (numberRead){
            case 1:
                System.out.println("What is your name ?");
                process.changeName(userConnected, readString());
                System.out.println("It has been changed.");
                break;
            case 2:
                System.out.println("What is your surname ?");
                process.changeSurname(userConnected, readString());
                System.out.println("It has been changed.");
                break;
            case 3:
                System.out.println("What is your nickname ?");
                process.changeNickname(userConnected, readString());
                System.out.println("It has been changed.");
                break;
            case 4:
                System.out.println("What is your email address ?");
                process.changeMail(userConnected, readString());
                System.out.println("It has been changed.");
                break;
            default:
                System.err.println("Choose a number between 1 and 4.");
                changeData(userConnected);
                break;
        }
    }

    /**
     * Event fot deleting an account
     * @param userConnected the connected user
     */
    private void ARR_DeleteAccount(User userConnected) {
        boolean deleted = process.deleteAccount(askForAccount(), askType(), userConnected);
        if (!deleted){
            System.err.println("You enter wrong data.");
            ARR_DeleteAccount(userConnected);
        };
    }

    /**
     * Asks data about a person
     * @return an array containing the data
     */
    private String askForAccount() {
        String question = "The login of the person :";
        System.out.println(question);
        String account = readString();
        return account;
    }

    /**
     * Quit event
     */
    private void ARR_Quit() {
        quit = true;
    }
    
    private void ARR_BidOnAProperty(Tenant tenantConnected) {
        System.out.println("Do you want to see all properties before ? (yes / no)");
        String str = readString();
        if (str.equals("yes")){
            process.seeAllProperties();
            System.out.println("");
            makeABid(tenantConnected);
        } else if (str.equals("no")) {
            makeABid(tenantConnected);
        } else {
            System.err.println("I did not understand your answer.");
            ARR_BidOnAProperty(tenantConnected);
        }
    }

    private void makeABid(Tenant tenantConnected){
        Property property = null;
        System.out.println("Enter the name of the property");
        property = process.PropertyExist(readString());
        int bid = 0;
        int month = 0;
        int people = 0;
        int nights = 0;
        if (property==null){
            System.err.println("Error: no property has this name, try again.");
        }else{
            month = chooseMonth();
        }
        if (property.getCurrentBid() != null && tenantConnected.getVirtualWallet() >= property.getCurrentBid().getBidAmount() + 10 + process.winningBidSum(tenantConnected)){
            while (!checksBid(property.getCurrentBid().getBidAmount() + 10, bid)){
                System.out.println("The bid must be greater than 0 and greater than the amount of the current bid");
                bid = askForInt("How much do you want to bid ? (" + property.getCurrentBid().getBidAmount() + 10 + "and more.");
            }
            process.createBid(tenantConnected, property, month, people, nights, bid);
        }else if (property.getCurrentBid() == null){
            people = askForInt("How many People ? (between 0 and" + property.getMaxOccupiers() + ").");
            if (people < 0 && people > property.getMaxOccupiers()){
                people = askForInt("How many People ? (between 0 and" + property.getMaxOccupiers() + ").");
            }
            nights = askForInt("How many nights ? (between 1 and 10).");
            if (nights < 1 || nights > 10){
                System.err.println("Please for legal reasons the number of night is between 1 and 10.");
                nights = askForInt("How many nights ?");
            }
            Owner o = process.findOwner(property);
            System.out.println("No current bid. The amount is : " + (people*nights*o.getProperties().get(property).getThePrice())/10);
            if (checksBid((people*nights*o.getProperties().get(property).getThePrice()/10), tenantConnected.getVirtualWallet())){
                bid = askForInt("Enter this bid to confirm");
                if (bid == (people*nights*o.getProperties().get(property).getThePrice()/10)){
                    process.createBid(tenantConnected, property, month, people, nights, bid);
                }else{
                    System.err.println("This bid has not been created");
                }
            }
        }else{
            System.err.println("You have not enough money. Please, put some money into your virtual wallet.");
        }
    }

    private boolean checksBid(int amountOfTheBid, int theBid){
        return theBid > 0 && theBid > amountOfTheBid;
    }

    private int chooseMonth(){
        int month = 0;
        System.out.println("Choose the month desired");
        System.out.println("1. January");
        System.out.println("2. February");
        System.out.println("3. March");
        System.out.println("4. April");
        System.out.println("5. May");
        System.out.println("6. June");
        System.out.println("7. July");
        System.out.println("8. August");
        System.out.println("9. September");
        System.out.println("10. October");
        System.out.println("11. November");
        System.out.println("12. December");
        month = askForInt("What is your choice ?");
        if (month <= 0 && month > 12){
            System.err.println("Choose a month between 1 and 12");
            chooseMonth();
        }
        return month;
    }
}
