/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userData;

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

    public User(String login, String surname, String name, String nick, String email) {
        LOGIN = login;
        this.surname = surname;
        this.name = name;
        nickname = nick;
        mail = email;
    }
}
