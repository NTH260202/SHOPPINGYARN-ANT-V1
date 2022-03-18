package thanhha.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ParsingUtils {
    public static String hashString(String inputString) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(inputString.getBytes());
        byte[] digest = md.digest();
        String hashedString = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hashedString;
    }
}
