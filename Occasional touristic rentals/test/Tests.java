/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import intenal.Processing;
import userdata.Tenant;
import userdata.TypeAccount;
import userdata.User;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;


/**
 *
 * @author damie
 */
public class Tests {
    
    public Tests() {
    }

    /**
     * Tests if an account is well created
     */
    @Test
    public void createAccountTest(){
        Processing process = new Processing();
        ArrayList<User> users = process.getAllUsers();
        users.add(new Tenant("login", "surname", "name", "nick","email"));
        assertEquals(1, users.size());
    }

    /**
     * Tests if an account is well created with personal data
     */
    @Test
    public void createAccountMethodTest(){
        Processing process = new Processing();
        ArrayList<String> account2 = new ArrayList<String>();
        account2.add("login");
        account2.add("surname");
        account2.add("name");
        account2.add("nickname");
        account2.add("email");
        process.createAccount(account2, TypeAccount.TENANT);
        assertEquals(1, process.getAllUsers().size());
    }

    /**
     * Tests the validity of an account during its creation
     */
    @Test
    public void compareAccounts(){
        Processing process = new Processing();
        ArrayList<User> users = process.getAllUsers();
        users.add(new Tenant("login", "surname", "name", "nick","email"));
        ArrayList<String> account2 = new ArrayList<String>();
        account2.add("login");
        account2.add("surname");
        account2.add("name");
        account2.add("nickname");
        account2.add("email");
        assertFalse(process.testValidityAccount(account2, TypeAccount.TENANT));
        assertTrue(1 == users.size());
    }

    /**
     * Tests if the connection is established
     */
    @Test
    public void connectionTest(){
        Processing process = new Processing();
        ArrayList<User> users = process.getAllUsers();
        users.add(new Tenant("login", "surname", "name", "nick","email"));
        ArrayList<String> connectionInformations = new ArrayList<>();
        connectionInformations.add("nick");
        connectionInformations.add("login");
        ArrayList<String> connectionInformations2 = new ArrayList<>();
        connectionInformations2.add("nick");
        connectionInformations2.add("logins");
        assertTrue(process.connect(connectionInformations, TypeAccount.TENANT));
        assertFalse(process.connect(connectionInformations2, TypeAccount.TENANT));
        assertFalse(process.connect(connectionInformations, TypeAccount.ADMINISTRATOR));
    }
}
