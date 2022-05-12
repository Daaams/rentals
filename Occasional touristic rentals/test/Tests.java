/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import internal.Processing;
import userdata.*;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;


/**
 *
 * @author damien
 */
public class Tests {
    
    public Tests() {
    }

    /**
     * Tests if an accounts are well created
     */
    @Test
    public void createAccountTest(){
        Processing process = new Processing();
        ArrayList<Tenant> tenants = process.getAllTenants();
        ArrayList<Owner> owners = process.getAllOwners();
        ArrayList<Admin> admins = process.getAllAdmins();
        tenants.add(new Tenant("login", "surname", "name", "nick","email"));
        owners.add(new Owner("login", "surname", "name", "nick","email"));
        admins.add(new Admin("login", "surname", "name", "nick","email"));
        assertEquals(1, tenants.size());
        assertEquals(1, owners.size());
        assertEquals(1, admins.size());
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
        assertEquals(1, process.getAllTenants().size());
    }

    /**
     * Tests the validity of an account during its creation
     */
    @Test
    public void compareAccounts(){
        Processing process = new Processing();
        ArrayList<Tenant> tenants = process.getAllTenants();
        tenants.add(new Tenant("login", "surname", "name", "nick","email"));
        ArrayList<String> account2 = new ArrayList<String>();
        account2.add("login");
        account2.add("surname");
        account2.add("name");
        account2.add("nickname");
        account2.add("email");
        assertFalse(process.testValidityAccount(account2, TypeAccount.TENANT));
        assertTrue(1 == tenants.size());
    }

    /**
     * Tests if the connection is established
     */
    @Test
    public void connectionTest(){
        Processing process = new Processing();
        ArrayList<Tenant> users = process.getAllTenants();
        users.add(new Tenant("login", "surname", "name", "nick","email"));
        ArrayList<String> connectionInformations = new ArrayList<>();
        connectionInformations.add("nick");
        connectionInformations.add("login");
        ArrayList<String> connectionInformations2 = new ArrayList<>();
        connectionInformations2.add("nick");
        connectionInformations2.add("logins");
        assertTrue(process.connectTenant(connectionInformations) != null);
        assertTrue(process.connectTenant(connectionInformations2) == null);
    }

    /**
     * Tests if money added by a tenant is well added to his wallet
     */
    @Test
    public void depositMoneyTenantAccountTest(){
        Processing process = new Processing();
        ArrayList<Tenant> tenants = process.getAllTenants();
        Tenant t = (new Tenant("login", "surname", "name", "nick","email"));
        tenants.add(t);
        process.addMoneyOnWallet(tenants.get(0), 5);
        assertTrue(t.getVirtualWallet() == 5);
    }

    /**
     * Tests if money withdraw by a tenant is well withdraw to his wallet
     */
    @Test
    public void withdrawMoneyTenantAccountTest(){
        Processing process = new Processing();
        ArrayList<Tenant> tenants = process.getAllTenants();
        Tenant t = (new Tenant("login", "surname", "name", "nick","email"));
        tenants.add(t);
        process.addMoneyOnWallet(tenants.get(0), 10);
        process.withdrawMoneyOfWallet(tenants.get(0), 5);
        assertTrue(t.getVirtualWallet() == 5);
    }

    /**
     * Tests if data is well changed
     */
    @Test
    public void changeDataTest(){
        Processing process = new Processing();
        Tenant t = (new Tenant("login", "surname", "name", "nick","email"));
        process.changeName(t,"NAME");
        process.changeSurname(t, "SURNAME");
        process.changeMail(t, "EMAIL");
        process.changeNickname(t, "NICKNAME");
        assertTrue(t.getName().equals("NAME"));
        assertTrue(t.getSurname().equals("SURNAME"));
        assertTrue(t.getNickname().equals("NICKNAME"));
        assertTrue(t.getMail().equals("EMAIL"));
    }
}
