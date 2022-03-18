package thanhha.account;

import java.io.Serializable;

public class AccountRegisterError implements Serializable {
    private String usernameLengthErr;
    private String usernameIsExisted;
    private String passwordLengthErr;
    private String confirmPasswordNotMatched;
    private String firstnameLengthErr;
    private String lastnameLengthErr;



    public AccountRegisterError(String usernameLengthErr, String usernameIsExisted, String passwordLengthErr, String confirmPasswordNotMatched,
                                String firstnameLengthErr, String lastnameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
        this.usernameIsExisted = usernameIsExisted;
        this.passwordLengthErr = passwordLengthErr;
        this.confirmPasswordNotMatched = confirmPasswordNotMatched;
        this.firstnameLengthErr = firstnameLengthErr;
        this.lastnameLengthErr = lastnameLengthErr;
    }

    public AccountRegisterError() {
    }

    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getConfirmPasswordNotMatched() {
        return confirmPasswordNotMatched;
    }

    public void setConfirmPasswordNotMatched(String confirmPasswordNotMatched) {
        this.confirmPasswordNotMatched = confirmPasswordNotMatched;
    }

    public String getFirstnameLengthErr() {
        return firstnameLengthErr;
    }

    public void setFirstnameLengthErr(String firstnameLengthErr) {
        this.firstnameLengthErr = firstnameLengthErr;
    }

    public String getLastnameLengthErr() {
        return lastnameLengthErr;
    }

    public void setLastnameLengthErr(String lastnameLengthErr) {
        this.lastnameLengthErr = lastnameLengthErr;
    }

}
