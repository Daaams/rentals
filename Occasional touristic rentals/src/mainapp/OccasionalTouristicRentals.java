/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mainapp;

import java.util.ArrayList;
import java.util.Scanner;

import intenal.Processing;
import userdata.TypeAccount;

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
    boolean connected;
    private Processing process;
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
        connected = false;
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
        System.out.println("What do you want to do?");
        System.out.println("0. Quit.");
        System.out.println("1. Create an account");
        System.out.println("2. Connection");
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
                ARR_CreateAccount();
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
    private void ARR_CreateAccount() {
        TypeAccount type = askType();
        String questions [] = {"What's your login ? ", "What's your surname ? ", "What's your name ? ", 
        "What's your nickname ? ", "What's your email ? "};
        ArrayList<String> informations = takeinformations(questions);
        boolean valid = process.testValidityAccount(informations, type);
        if (valid){
            process.createAccount(informations, type);
            firstPromptAction();
        }else{
            System.out.println("This account already exists. Please, start again");
            ARR_CreateAccount();
        }
    }

    /**
     * Event for the user connection
     */
    private void ARR_Connection() {
        TypeAccount type = askType();
        String questions [] = {"What's your nickname ? ", "What's your login ?"};
        boolean connected = process.connect(takeinformations(questions), type);
    }

    /**
     * Quit event 
     */
    private void ARR_Quit() {
        quit = true;
    } 

    /**
     * Ask the user for his status for the connection or the account creation
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
     * Ask some questions to the user and take his informations
     * @param questions an arraay containing questions to ask
     * @return the informations answered by the user
     */
    private ArrayList<String> takeinformations(String[] questions) {
        ArrayList<String> arrayList = new ArrayList<>();
        String stringRead;
        for (int i = 0; i < questions.length; i++){
            System.out.println(questions[i]);
            stringRead = scan.nextLine();
            while(!stringRead(stringRead)){
                System.out.println("Please, enter a non-null string nor an empty string please.");
                stringRead = scan.nextLine();
            }
            arrayList.add(stringRead);
        }
        return arrayList;
    }
    
    /**
     * Test if the answer given by the user is not null or empty
     * @param s the answer of the user
     * @return a boolean
     */
    private boolean stringRead(String s){
        return (!s.equals("") && !s.equals(null));
    }
 
}
