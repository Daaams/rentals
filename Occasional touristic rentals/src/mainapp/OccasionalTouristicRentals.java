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
    private String stringRead;
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
        stringRead = scan.nextLine();
        try {
            numberRead = Integer.parseInt(stringRead);
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
        String[] questions = {"What's your nickname ? ", "What's your login ?"};
        switch (type) {
            case TENANT:
                Tenant tenantConnected = process.connectTenant(takeData(questions));
                if (tenantConnected != null) {
                    someoneConnected = true;
                    while (someoneConnected) {
                        ARR_EventsTenant(tenantConnected);
                    }
                } else {
                    displayErrorMessageConnection();
                }
                break;
            case OWNER:
                Owner ownerConnected = process.connectOwner(takeData(questions));
                if (ownerConnected != null) {
                    someoneConnected = true;
                    while (someoneConnected) {
                        ARR_EventsOwner(ownerConnected);
                    }
                } else {
                    displayErrorMessageConnection();
                }
                break;
            case ADMINISTRATOR:
                Admin adminConnected = process.connectAdmin(takeData(questions));
                if (adminConnected != null) {
                    someoneConnected = true;
                    while (someoneConnected) {
                        ARR_EventsAdministrator(adminConnected);
                    }
                } else {
                    displayErrorMessageConnection();
                }
                break;
        }
    }

    /**
     * displays in the console an error message
     */
    private void displayErrorMessageConnection() {
        System.err.println("Your nickname or your login do not match.");
        System.err.println("Please try again or create an account with the same data.");
    }

    /**
     * Ask some questions to the user and take his personal data
     *
     * @param questions an array containing questions to ask
     * @return the data answered by the user
     */
    private ArrayList<String> takeData(String[] questions) {
        ArrayList<String> arrayList = new ArrayList<>();
        String stringRead;
        for (String question : questions) {
            System.out.println(question);
            stringRead = scan.nextLine();
            while (!stringRead(stringRead)) {
                System.err.println("Please, enter a non-null string nor an empty string please.");
                stringRead = scan.nextLine();
            }
            arrayList.add(stringRead);
        }
        return arrayList;
    }

    /**
     * Tests if the answer given by the user is not null or empty
     *
     * @param s the answer of the user
     * @return a boolean
     */
    private boolean stringRead(String s) {
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
    private void ARR_EventsTenant(Tenant tenantConnected) {
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
        System.out.println("8. Log out.");
        System.out.println("");
        askForEventTenants(tenantConnected);
    }

    /**
     * displays the actions that an owner can do
     */
    private void ARR_EventsOwner(Owner ownerConnected) {
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
        System.out.println("8. Log out.");
        System.out.println("");
        askForEventOwners(ownerConnected);
    }

    /**
     * displays the actions that an administrator can do
     */
    private void ARR_EventsAdministrator(Admin adminConnected) {
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
        stringRead = scan.nextLine();
        try {
            numberRead = Integer.parseInt(stringRead);
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
                process.seeMyWallet(tenantConnected);
                break;
            case 8:
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
        stringRead = scan.nextLine();
        try {
            numberRead = Integer.parseInt(stringRead);
            if (Integer.parseInt(stringRead) % 5 != 0) {
                System.err.println("You must enter an integer multiple of 5");
                askForMoney();
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
        stringRead = scan.nextLine();
        if (stringRead.equals("yes")){
            process.seeAllProperties();
            System.out.println("");
            askForAproperty();
        } else if (stringRead.equals("no")) {
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
        stringRead = scan.nextLine();
        while (!stringRead(stringRead)) {
            System.err.println("Please, enter a non-null string nor an empty string please.");
            stringRead = scan.nextLine();
        }
        process.consultDataOfAProperty(stringRead);
    }

    /**
     * Triggers the event selected by the user connected as owner
     * @param ownerConnected the connected owner
     */
    private void askForEventOwners(Owner ownerConnected) {
        stringRead = scan.nextLine();
        try {
            numberRead = Integer.parseInt(stringRead);
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
                process.addPropertyToThePortfolio(ownerConnected, ARR_AskDataForProperty(ownerConnected, questions),
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
                    boolean deleted = process.deletePropertyOwner(ownerConnected, ARR_AskDataForProperty(ownerConnected, questions2));
                    if (deleted){
                        System.out.println("It has been deleted");
                    }else{
                        System.out.println("Wrong data, it has not been deleted");
                    }
                }
                break;
            case 6:
                System.out.println("nothing for the moment");
                break;
            case 7:
                process.seeMyWalletOwner(ownerConnected);
                break;
            case 8:
                System.err.println(ownerConnected.getNickname() + ", you have been disconnected");
                someoneConnected = false;
                break;
            default:
                System.err.println("Error: no such menu item.");
        }
    }

    /**
     * Asks the user for an integer
     * @param question the question to ask depending on the context
     * @return the integer enter by the user
     */
    private int askForInt(String question) {
        System.out.println(question);
        System.out.println("");
        stringRead = scan.nextLine();
        try {
            numberRead = Integer.parseInt(stringRead);
        } catch (NumberFormatException nfe) {
            System.err.println("Error: please enter an integer.");
            askForInt(question);
        }
        return numberRead;
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
        System.out.println("5. Estate");
        System.out.println("");
        TypeProperty type = null;
        stringRead = scan.nextLine();
        try {
            numberRead = Integer.parseInt(stringRead);
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
     * @param ownerConnected the connected owner
     * @param questions questions to ask for collecting data
     * @return an Arraylist containing data entered
     */
    private ArrayList<String> ARR_AskDataForProperty(Owner ownerConnected, ArrayList<String> questions) {
        ArrayList<String> propertyData = new ArrayList<>();
        for (int i = 0; i < questions.size(); i ++){
            System.out.println(questions.get(i));
            stringRead = scan.nextLine();
            while (!stringRead(stringRead)) {
                System.err.println("Please, enter a non-null string nor an empty string please.");
                stringRead = scan.nextLine();
            }
            propertyData.add(stringRead);
        }
        return propertyData;
    }

    /**
     * Triggers the event selected by the user connected as administrator
     * @param adminConnected the connected administrator
     */
    private void askForEventAdmins(Admin adminConnected) {
        stringRead = scan.nextLine();
        try {
            numberRead = Integer.parseInt(stringRead);
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
        stringRead = scan.nextLine();
        if (stringRead.equals("yes")) {
            process.seeAllProperties();
            changeDescription();
        } else if (stringRead.equals("no")) {
            changeDescription();
        }else{
            System.err.println("I did not understand your answer");
            ARR_ChangeDescriptionOfProperty();
        }
    }

    /**
     * Changes the description of a property corresponding to data asked
     */
    private void changeDescription() {
        String [] questions = {"What is the name of the property ?", "What is the address of the property ?",
                "In which city is located the property ?", "What is the nickname of the owner ?", "What is the new description ?"};
        ArrayList<String> answers = new ArrayList<>();
        for (int i = 0; i < questions.length; i ++) {
            System.out.println(questions[i]);
            stringRead = scan.nextLine();
            while (!stringRead(stringRead)) {
                System.err.println("Please, enter a non-null string nor an empty string please.");
                stringRead = scan.nextLine();
            }
            answers.add(stringRead);
        }
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
            stringRead = scan.nextLine();
            if (stringRead.equals("yes")) {
                process.seeAllProperties();
                deleteProperty();
            } else if (stringRead.equals("no")) {
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
            stringRead = scan.nextLine();
            while (!stringRead(stringRead)) {
                System.err.println("Please, enter a non-null string nor an empty string please.");
                stringRead = scan.nextLine();
            }
            answers.add(stringRead);
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
        stringRead = scan.nextLine();
        if (stringRead.equals("yes")) {
            process.seeData(userConnected);
            changeData(userConnected);
        } else if (stringRead.equals("no")) {
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
        stringRead = scan.nextLine();
        try {
            numberRead = Integer.parseInt(stringRead);
        } catch (NumberFormatException nfe) {
            System.err.println("Error: please enter an integer.");
            changeData(userConnected);
        }
        switch (numberRead){
            case 1:
                System.out.println("What is your name ?");
                stringRead = scan.nextLine();
                while (stringRead(stringRead) == false){
                    System.err.println("Your is null or empty, please, enter something");
                    stringRead = scan.nextLine();
                }
                process.changeName(userConnected, stringRead);
                System.out.println("It has been changed.");
                break;
            case 2:
                System.out.println("What is your surname ?");
                stringRead = scan.nextLine();
                while (stringRead(stringRead) == false){
                    System.err.println("Your is null or empty, please, enter something");
                    stringRead = scan.nextLine();
                }
                process.changeSurname(userConnected, stringRead);
                System.out.println("It has been changed.");
                break;
            case 3:
                System.out.println("What is your nickname ?");
                stringRead = scan.nextLine();
                while (stringRead(stringRead) == false){
                    System.err.println("Your is null or empty, please, enter something");
                    stringRead = scan.nextLine();
                }
                process.changeNickname(userConnected, stringRead);
                System.out.println("It has been changed.");
                break;
            case 4:
                System.out.println("What is your email address ?");
                stringRead = scan.nextLine();
                while (stringRead(stringRead) == false){
                    System.err.println("Your is null or empty, please, enter something");
                    stringRead = scan.nextLine();
                }
                process.changeMail(userConnected, stringRead);
                System.out.println("It has been changed.");
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
    private String[] askForAccount() {
        String [] account = new String[3];
        String [] questions = {"The name of the person :", "The surname of the person :",
                "The nickname of the person :"};
        for (int i = 0; i < questions.length; i ++){
            System.out.println(questions[i]);
            stringRead = scan.nextLine();
            while (!stringRead(stringRead)) {
                System.err.println("Please, enter a non-null string nor an empty string please.");
                stringRead = scan.nextLine();
            }
            account[i] = stringRead;
        }
        return account;
    }

    /**
     * Quit event
     */
    private void ARR_Quit() {
        quit = true;
    }
}
