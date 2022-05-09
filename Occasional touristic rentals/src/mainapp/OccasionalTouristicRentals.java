/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mainapp;

import java.util.ArrayList;
import java.util.Scanner;

import intenal.Processing;
import userData.TypeAccount;
import userData.User;

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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OccasionalTouristicRentals app = new OccasionalTouristicRentals();
        app.first();
    }

    public OccasionalTouristicRentals(){
        scan = new Scanner(System.in);
        process = new Processing();
        connected = false;
    }

    private void first() {
        waitingForString = false;
        firstPromptAction();
    }

    private void firstPrompt() {
        System.out.println("What do you want to do?");
        System.out.println("0. Quit.");
        System.out.println("1. Create an account");
        System.out.println("2. Connection");
    }

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
                ARR_Connexion();
                break;
            default:
                System.err.println("Error: no such menu item.");
        }
    }

    /**
     * evenement pour la creation de compte
     */
    private void ARR_CreateAccount() {
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
        String tab [] = {"What's your login ? ", "What's your surname ? ", "What's your name ? ", 
        "What's your nickname ? ", "What's your email ? "};
        ArrayList<String> informations = takeinformations(tab);
        boolean valid = process.testValidityAccount(informations, type);
        if (valid){
            process.createAccount(takeinformations(tab), type);
            firstPromptAction();
        }else{
            System.out.println("This account already exists. Please, start again");
            ARR_CreateAccount();
        }
    }

    private void ARR_Connexion() {
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
            ARR_Connexion();
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
        String tab [] = {"What's your nickname ? ", "What's your login ?"};        
        process.connect(takeinformations(tab), type);
    }
    
    private ArrayList<String> takeinformations(String[] tab) {
        ArrayList<String> arrayList = new ArrayList<>();
        String stringRead;
        for (int i = 0; i < tab.length; i++){
            System.out.println(tab[i]);
            stringRead = scan.nextLine();
            while(!stringRead(stringRead)){
                System.out.println("Please, enter a non-null string nor an empty string please.");
                stringRead = scan.nextLine();
            }
            arrayList.add(stringRead);
        }
        return arrayList;
    }
    
    private boolean stringRead(String s){
        return (!s.equals("") && !s.equals(null));
    }

    private void ARR_Quit() {
        quit = true;
    }  
}
