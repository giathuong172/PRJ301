/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thuongng.registration;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class RegistrationDTO implements Serializable {
    protected String username;
    protected String password;
    protected String lastName;
    protected boolean role;   

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username, String password,
        String lastName, boolean role) {
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.role = role;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the lastname
     */
    public String getlastName() {
        return lastName;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the role
     */
    public boolean isRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(boolean role) {
        this.role = role;
    }

}
