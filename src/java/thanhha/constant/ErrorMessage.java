package thanhha.constant;

public class ErrorMessage {
    public static class ACCOUNT {
        public final static String USERNAME_LENGTH_ERROR = "Username is required with 6-20 characters";
        public final static String PASSWORD_LENGTH_ERROR = "Password is required with 6-30 characters";
        public final static String CONFIRMED_PASSWORD_FAILED = "Password is not matched!";
        public final static String FIRST_NAME_LENGTH_ERROR = "First Name is required with 6-10 characters";
        public final static String LAST_NAME_LENGTH_ERROR = "Last Name is required with 6-40 characters";
        public final static String USERNAME_EXISTED = "Username is existed!";
    }

    public static class PRODUCT {
        public static boolean IS_QUANTITY_ERROR = false;
        public final static String QUANTITY_IS_INVALID = "Some items are not enough for your order";

    }
}
