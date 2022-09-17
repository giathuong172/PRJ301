/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuongng.ErrorCLasses;

/**
 *
 * @author Admin
 */
public class AccountUpdateError {
    private String passwordIsEmptyError;
    private String fullnameIsEmptyError;
    private String roleError;

    public AccountUpdateError() {
    }

    public String getPasswordIsEmptyError() {
        return passwordIsEmptyError;
    }

    public void setPasswordIsEmptyError(String passwordIsEmptyError) {
        this.passwordIsEmptyError = passwordIsEmptyError;
    }

    public String getFullnameIsEmptyError() {
        return fullnameIsEmptyError;
    }

    public void setFullnameIsEmptyError(String fullnameIsEmptyError) {
        this.fullnameIsEmptyError = fullnameIsEmptyError;
    }

    public String getRoleError() {
        return roleError;
    }

    public void setRoleError(String roleError) {
        this.roleError = roleError;
    }
    
}
