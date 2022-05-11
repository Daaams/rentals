/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mainapp;

import java.util.ArrayList;
import java.util.Scanner;

import intenal.Processing;
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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OccasionalTouristicRentals app = new OccasionalTouristicRentals();
        app.run();
    }

    /**
     * Constructor of the application
     */
    public OccasionalTouristicRentals(){
        scan = new Scanner(System.in);
        process = new Processing();
        someoneConnected = false;
    }

    /**
     * Call the prompt while the user do not quit 
     */
    private void run() {
        waitingForString = false;
        while(!quit){
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
                ARR_CreateAccounts(0);
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
    private void ARR_CreateAccounts(int i) {
        TypeAccount type = askType();
        String questions [] = {"What's your login ? ", "What's your surname ? ", "What's your name ? ",
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
        String questions [] = {"What's your nickname ? ", "What's your login ?"};
        switch (type){
            case TENANT:
                Tenant tenantConnected = process.connectTenant(takeData(questions));
                if (tenantConnected != null){
                    someoneConnected = true;
                    while (someoneConnected){
                        ARR_EventsTenant(tenantConnected);
                    }
                }else{
                    displayErrorMessageConnection();
                }
                break;
            case OWNER:
                Owner ownerConnected = process.connectOwner(takeData(questions));
                if (ownerConnected != null){
                    someoneConnected = true;
                    while (someoneConnected){
                        ARR_EventsOwner(ownerConnected);
                    }
                }else{
                    displayErrorMessageConnection();
                }
                break;
            case ADMINISTRATOR:
                Admin adminConnected = process.connectAdmin(takeData(questions));
                if (adminConnected != null){
                    someoneConnected = true;
                    while (someoneConnected){
                        ARR_EventsAdministrator(adminConnected);
                    }
                }else{
                    displayErrorMessageConnection();
                }
                break;
        }
    }

    /**
     * displays in the console an error message
     */
    private void displayErrorMessageConnection(){
        System.err.println("Your nickname or your login do not match.");
        System.err.println("Please try again or create an account with the same data.");
    }

    /**
     * Ask some questions to the user and take his personal data
     * @param questions an array containing questions to ask
     * @return the data answered by the user
     */
    private ArrayList<String> takeData(String[] questions) {
        ArrayList<String> arrayList = new ArrayList<>();
        String stringRead;
        for (int i = 0; i < questions.length; i++){
            System.out.println(questions[i]);
            stringRead = scan.nextLine();
            while(!stringRead(stringRead)){
                System.err.println("Please, enter a non-null string nor an empty string please.");
                stringRead = scan.nextLine();
            }
            arrayList.add(stringRead);
        }
        return arrayList;
    }

    /**
     * Tests if the answer given by the user is not null or empty
     * @param s the answer of the user
     * @return a boolean
     */
    private boolean stringRead(String s){
        return (!s.equals("") && !s.equals(null));
    }

    /**
     * Asks the user for his status for the connection or the account creation
     * @return the type of account
     */
    private TypeAccount askType() {
        TypeAccount type = null;
        System.out.println("Which type of account ?");
        System.out.println("0. Administrator.");
        System.out.println("1. Owner.");
        System.out.println("2. Tenant.");
        stringRead = scan.nextLine();
        try {
            numberRead = Integer.parseInt(stringRead);
        } catch (NumberFormatException nfe) {
            System.err.println("Error: please enter an integer.");
            ARR_Connection();
        }
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
    private void ARR_EventsTenant(Tenant userConnected) {
        System.out.println("What do you want to do ?");
        System.out.println("1. Add money on my virtual account.");
        System.out.println("2. See all properties on the application.");
        System.out.println("3. Consult data of a property.");
        System.out.println("4. See my wallet.");
        System.out.println("5. Log out.");
        askForEventTenants(userConnected);
    }

    /**
     * displays the actions that an owner can do
     */
    private void ARR_EventsOwner(User ownerConnected) {
        //add a property to their portfolio
        //delete a property of their portfolio
        //change data of a property
    }

    /**
     * displays the actions that an administrator can do
     */
    private void ARR_EventsAdministrator(User adminConnected) {
        //view all user
        //delete an account
        //delete a property
        //change the description of a property
    }

    /**
     * Triggers the event selected by the tenant
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
                process.seeAllProperties();
                break;
            case 3:
                ARR_ConsultDataOfAProperty();
                break;
            case 4:
                process.seeMyWallet(tenantConnected);
                break;
            case 5:
                someoneConnected = false;
                break;
            default:
                System.err.println("Error: no such menu item.");
        }
    }

    /**
     * Asks the tenant for the quantity of money to add into his wallet
     * @return
     */
    private int askForMoney() {
        System.out.println("How much do you want to add to you wallet ?");
        System.out.println("Integer must be multiple of 5.");
        stringRead = scan.nextLine();
        try {
            numberRead = Integer.parseInt(stringRead);
            if (Integer.parseInt(stringRead) % 5 == 0){
                System.out.println("Your money will be added to your wallet.");
            }else{
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
        } else if (stringRead.equals("no")) {
            System.out.println("Which property do you want to consult ?");
            stringRead = scan.nextLine();
            process.consultDataOfAProperty(stringRead);
        } else {
            System.err.println("I did not understand your answer.");
        }
    }

    /**
     * Quit event
     */
    private void ARR_Quit() {
        quit = true;
    }
}
