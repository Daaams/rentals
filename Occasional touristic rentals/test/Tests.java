/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import internal.*;
import org.junit.Test;
import userdata.*;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 *
 * @author damien
 */
public class Tests {
    Processing process = new Processing();

    public Tests() {
    }

    /**
     * Tests if an accounts are well created
     */
    @Test
    public void createAccountTest(){
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
        ArrayList<String> account2 = new ArrayList<>();
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
        ArrayList<String> account2 = new ArrayList<>();
        account2.add("login");
        account2.add("surname");
        account2.add("name");
        account2.add("nickname");
        account2.add("email");

        process.createAccount(account2, TypeAccount.TENANT);
        ArrayList<String> connectionData = new ArrayList<>();
        connectionData.add("login");
        ArrayList<String> connectionData2 = new ArrayList<>();
        connectionData2.add("logins");
        assertTrue(process.connectUser(connectionData, TypeAccount.TENANT) != null);
        assertTrue(process.connectUser(connectionData2, TypeAccount.TENANT) == null);
    }

    /**
     * Tests if money added by a tenant is well added to his wallet
     */
    @Test
    public void depositMoneyTenantAccountTest(){
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

    /**
     * Tests if an account is well deleted
     */
    @Test
    public void deleteAccountTest(){
        ArrayList<String> account2 = new ArrayList<>();
        account2.add("login");
        account2.add("surname");
        account2.add("name");
        account2.add("nick");
        account2.add("email");
        process.createAccount(account2, TypeAccount.TENANT);
        process.createAccount(account2, TypeAccount.ADMINISTRATOR);
        assertEquals(1, process.getAllTenants().size());
        String login = "login";
        process.deleteAccount(login, TypeAccount.TENANT, process.getAllAdmins().get(0));
        assertTrue(process.getAllTenants().size() == 0);
    }

    /**
     * Tests if a property is well added to the owner's portfolio
     */
    @Test
    public void ropertyTest(){
        ArrayList<Owner> owners = process.getAllOwners();
        owners.add(new Owner("login", "surname", "name", "nick","email"));
        owners.get(0).addProperty(new Property(TypeProperty.HOMESTEAD, "TheProperty", "TheAddress", "TheCity", "TheDesc", 10), new Price(200));
        assertTrue(owners.get(0).getProperties().size() == 1);
        owners.get(0).addProperty(new Property(TypeProperty.HOMESTEAD, "TheProperty", "TheAddress", "TheCity", "TheDesc", 10), new Price(200));
        assertFalse(owners.get(0).getProperties().size() == 1);
    }

    /**
     * Tests if a property is well deleted of the owner's portfolio
     */
    @Test
    public void deleteApropertyTest(){
        ArrayList<Owner> owners = process.getAllOwners();
        owners.add(new Owner("login", "surname", "name", "nick","email"));
        owners.get(0).addProperty(new Property(TypeProperty.HOMESTEAD, "TheProperty", "TheAddress", "TheCity", "TheDesc", 10), new Price(200));
        assertTrue(owners.get(0).getProperties().size() == 1);
        ArrayList<String> propertyToDelete = new ArrayList<>();
        propertyToDelete.add("TheProperty");
        propertyToDelete.add("TheAddress");
        propertyToDelete.add("TheCity");
        assertTrue(process.deletePropertyOwner(owners.get(0), propertyToDelete));
    }

    /**
     * Tests if the description of an owner's property is well changed
     */
    @Test
    public void changeDescription(){
        ArrayList<Owner> owners = process.getAllOwners();
        owners.add(new Owner("login", "surname", "name", "nick","email"));
        Property p = new Property(TypeProperty.HOMESTEAD, "TheProperty", "TheAddress", "TheCity", "TheDesc", 10);
        owners.get(0).addProperty(p, new Price(200));
        assertTrue(p.getDescription().equals("TheDesc"));
        ArrayList<String> data = new ArrayList<>();
        data.add("TheProperty");
        data.add("TheAddress");
        data.add("TheCity");
        data.add("nick");
        data.add("theNewDesc");
        process.changeDescription(data);
        assertTrue(p.getDescription().equals("theNewDesc"));
    }

    /**
     * Tests if a property exists
     */
    @Test
    public void propertyExistsTest(){
        ArrayList<Owner> owners = process.getAllOwners();
        owners.add(new Owner("login", "surname", "name", "nick","email"));
        Property p = new Property(TypeProperty.HOMESTEAD, "TheProperty", "TheAddress", "TheCity", "TheDesc", 10);
        owners.get(0).addProperty(p, new Price(200));
        assertTrue(process.propertyExist("TheProperty") == p);
        assertFalse(process.propertyExist("theProperty") == p);
    }

    /**
     * Tests if a bid has been well created
     */
    @Test
    public void createBidTest(){
        Tenant t = (new Tenant("login", "surname", "name", "nick","email"));
        process.getAllTenants().add(t);
        t.depositMoney(200);
        ArrayList<Owner> owners = process.getAllOwners();
        owners.add(new Owner("login", "surname", "name", "nick","email"));
        Property p = new Property(TypeProperty.HOMESTEAD, "TheProperty", "TheAddress", "TheCity", "TheDesc", 10);
        owners.get(0).addProperty(p, new Price(2));
        process.createBid(t, p, 12, 2, 2, 20);
        assertTrue(process.getAllbids().size() == 1);
    }

    /**
     * Tests if an owner is found thanks to a Property
     */
    @Test
    public void findOwnerTest(){
        ArrayList<Owner> owners = process.getAllOwners();
        Owner o = new Owner("login", "surname", "name", "nick","email");
        owners.add(o);
        Property p = new Property(TypeProperty.HOMESTEAD, "TheProperty", "TheAddress", "TheCity", "TheDesc", 10);
        owners.get(0).addProperty(p, new Price(2));
        Owner o2 = new Owner("login2", "surname2", "name2", "nick2","email2");
        owners.add(o2);
        Property p2 = new Property(TypeProperty.HOMESTEAD, "TheProperty2", "TheAddress2", "TheCity2", "TheDesc2", 10);
        owners.get(1).addProperty(p2, new Price(2));
        assertTrue(process.findOwner(p2).equals(o2));
    }

    /**
     * Tests if a tenant is found thanks to the name of the owner
     */
    @Test
    public void searchAccountTenantTest(){
        ArrayList<String> account1 = new ArrayList<>();
        account1.add("login");
        account1.add("surname");
        account1.add("name");
        account1.add("nick");
        account1.add("email");

        ArrayList<String> account2 = new ArrayList<>();
        account2.add("login2");
        account2.add("surname2");
        account2.add("name2");
        account2.add("nick2");
        account2.add("email2");

        process.createAccount(account1, TypeAccount.TENANT);
        process.createAccount(account2,TypeAccount.TENANT);

        assertTrue( process.searchAccountTenant("login") == process.getAllTenants().get(0));
    }

    /**
     * Tests if an owner is found thanks to the name of the owner
     */
    @Test
    public void searchAccountOwnerTest(){
        ArrayList<String> account1 = new ArrayList<>();
        account1.add("login");
        account1.add("surname");
        account1.add("name");
        account1.add("nick");
        account1.add("email");

        ArrayList<String> account2 = new ArrayList<>();
        account2.add("login2");
        account2.add("surname2");
        account2.add("name2");
        account2.add("nick2");
        account2.add("email2");

        process.createAccount(account1, TypeAccount.OWNER);
        process.createAccount(account2,TypeAccount.OWNER);

        assertTrue( process.searchAccountOwner("login2") == process.getAllOwners().get(1));
        assertTrue( process.searchAccountOwner("login3") == null);
    }

    /**
     * Tests if an admin is found thanks to the name of the owner
     */
    @Test
    public void searchAccountAdminTest(){
        ArrayList<String> account1 = new ArrayList<>();
        account1.add("login");
        account1.add("surname");
        account1.add("name");
        account1.add("nick");
        account1.add("email");

        ArrayList<String> account2 = new ArrayList<>();
        account2.add("login2");
        account2.add("surname2");
        account2.add("name2");
        account2.add("nick2");
        account2.add("email2");

        process.createAccount(account1, TypeAccount.ADMINISTRATOR);
        process.createAccount(account2,TypeAccount.ADMINISTRATOR);

        assertTrue( process.searchAccountAdmin("login") == process.getAllAdmins().get(0));
    }

    /**
     * Tests if a property is available for the rent
     */
    @Test
    public void setAvailablePropertyTest(){
        ArrayList<Owner> owners = process.getAllOwners();
        Owner o = new Owner("login", "surname", "name", "nick","email");
        owners.add(o);
        Property p = new Property(TypeProperty.HOMESTEAD, "TheProperty", "TheAddress", "TheCity", "TheDesc", 10);
        owners.get(0).addProperty(p, new Price(2));
        assertTrue(p.isAvailable());
        p.setAvailable(false);
        assertFalse(p.isAvailable());
    }

    /**
     * Tests if a reservation is well created
     */
    @Test
    public void createReservationTest(){
        Tenant t = (new Tenant("login", "surname", "name", "nick","email"));
        Property p = new Property(TypeProperty.HOMESTEAD, "TheProperty", "TheAddress", "TheCity", "TheDesc", 10);
        process.createBid(t, p, 12, 2, 2, 20);
        assertTrue(process.getAllReservations().size() == 0);
        process.getAllReservations().add(new Reservation(t, p, 1, process.getAllbids().get(0)));
        assertTrue(process.getAllReservations().size() == 1);
    }

}
