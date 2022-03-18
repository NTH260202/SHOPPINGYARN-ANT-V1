package thanhha.listener;

import java.io.File;
import java.io.IOException;
import thanhha.util.PropertiesFileHelper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.log4j.PropertyConfigurator;

@WebListener
public class ContextListener implements ServletContextListener{

    public ContextListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext context = sce.getServletContext();
            String siteMapLocation =
                    context.getInitParameter("SITEMAP_PROPERTIES_FILE_LOCATION");
            String userAccess =
                    context.getInitParameter("AUTHENTICATION_PROPERTIES_USER_ACCESS");
            String userRole =
                    context.getInitParameter("AUTHORIZATION_PROPERTIES_USER_ROLE");
            
            Properties siteMapProperty = PropertiesFileHelper.getProperties(context, siteMapLocation);
            Properties accessRightProperty = PropertiesFileHelper.getProperties(context, userAccess);
            Properties userRoleProperty = PropertiesFileHelper.getProperties(context, userRole);
            
            context.setAttribute("SITE_MAP", siteMapProperty);
            context.setAttribute("USER_ACCESS", accessRightProperty);
            context.setAttribute("USER_ROLE", userRoleProperty);
            
            String log4jConfigFile = context.getInitParameter("log4j-config-location");
            String fullPath = context.getRealPath("/") + File.separator + log4jConfigFile;
            
            System.setProperty("PATH", context.getRealPath("/"));
            
            PropertyConfigurator.configure(fullPath);
        } catch (IOException ex) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
