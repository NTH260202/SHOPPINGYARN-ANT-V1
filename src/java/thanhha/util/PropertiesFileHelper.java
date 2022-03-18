package thanhha.util;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileHelper {
    
    public static Properties getProperties(ServletContext context, String fileRelativePath) throws IOException{
        InputStream input = context.getResourceAsStream(fileRelativePath);
        Properties property = null;
        property = new Properties();
        property.load(input);
        return property;
    }
}
