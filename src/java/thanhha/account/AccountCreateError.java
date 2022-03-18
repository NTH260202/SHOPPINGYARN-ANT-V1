/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thanhha.account;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class AccountCreateError implements Serializable{
    private String usernameIsExisted;
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String firstnameLengthError;
    private String confirmedNotMatched;

    public AccountCreateError(String usernameIsExisted, String usernameLengthErr, String passwordLengthErr, String firstnameLengthError, String confirmedNotMatched) {
        this.usernameIsExisted = usernameIsExisted;
        this.usernameLengthErr = usernameLengthErr;
        this.passwordLengthErr = passwordLengthErr;
        this.firstnameLengthError = firstnameLengthError;
        this.confirmedNotMatched = confirmedNotMatched;
    }

    public AccountCreateError() {
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }

    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getFirstnameLengthError() {
        return firstnameLengthError;
    }

    public void setFirstnameLengthError(String firstnameLengthError) {
        this.firstnameLengthError = firstnameLengthError;
    }

    public String getConfirmedNotMatched() {
        return confirmedNotMatched;
    }

    public void setConfirmedNotMatched(String confirmedNotMatched) {
        this.confirmedNotMatched = confirmedNotMatched;
    }
    
    
}
