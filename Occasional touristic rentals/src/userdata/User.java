/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userdata;

/**
 *
 * @author mgenetet
 */
public class User {
    private final String LOGIN;
    private String surname;
    private String name;
    private String nickname;
    private String mail;

    /**
     * constructor of the class User
     * @param login the login of the User
     * @param surname the family name of the user
     * @param name the name of the user
     * @param nick the nickname of the user
     * @param email the email of the user
     */
    public User(String login, String surname, String name, String nick, String email) {
        LOGIN = login;
        this.surname = surname;
        this.name = name;
        nickname = nick;
        mail = email;
    }

    /**
     * To get the login of the user
     * @return a String
     */
    public String getLogin(){
        return LOGIN;
    }

    /**
     * To get the name of the user
     * @return a String
     */
    public String getName(){
        return name;
    }

    /**
     * To get the surname of the user
     * @return a String
     */
    public String getSurname(){
        return surname;
    }

    /**
     * To get the nickname of the user
     * @return a String
     */
    public String getNickname(){return nickname;}

    /**
     * To get the mail of the user
     * @return a String
     */
    public String getMail(){
        return mail;
    }
    /**
     * Assigns the new name
     * @param newName the new name
     */
    public void changeName(String newName){ name = newName;}

    /**
     * Assigns the new surname
     * @param newSurname the new surname
     */
    public void changeSurname(String newSurname){ surname = newSurname;}

    /**
     * Assigns the new nickname
     * @param newNickname the new nickname
     */
    public void changeNickname(String newNickname){ nickname = newNickname;}

    /**
     * Assigns the new email address
     * @param newMail the new email address
     */
    public void changeMail(String newMail){ mail = newMail;}
}
