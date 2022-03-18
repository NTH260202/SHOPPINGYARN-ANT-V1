/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package thanhha.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import thanhha.account.AccountDTO;
import static thanhha.constant.ResourceUrl.PathName.PRODUCT_PAGE;
import static thanhha.constant.ResourceUrl.PathName.LOAD_USER_INFOR;
import static thanhha.constant.ResourceUrl.PathName.LOGIN;
import static thanhha.constant.ResourceUrl.PathName.SEARCH_PAGE;

/**
 *
 * @author DELL
 */
@WebFilter(filterName = "AuthorizeFilter1", urlPatterns = {"/*"})
public class AuthorizeFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AuthorizeFilter.class);
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public AuthorizeFilter() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthorizeFilter1:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthorizeFilter1:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        try {
            HttpSession session = servletRequest.getSession(false);
            ServletContext context = request.getServletContext();

            Properties userAccessMap = (Properties) context.getAttribute("USER_ROLE");

            String servletPath = servletRequest.getServletPath();
            String resource = servletPath.substring(1);
            String role = userAccessMap.getProperty(resource);

            String userRole = "guest";

            if (session != null) {
                AccountDTO validUser = (AccountDTO) session.getAttribute("USER");
                if (validUser != null) {
                    if (validUser.isAdmin()) {
                        userRole = "admin";
                    } else {
                        userRole = "user";
                    }
                }
            }
            System.out.println(role + " + " + resource + " " + session );
            if (resource.equals("") && (session != null)) {
                servletResponse.sendRedirect(LOGIN);
            } else {
            
//            if ((resource.equals("") || resource.equals("login")) && userRole.equals("guest")) {
////                servletResponse.sendRedirect(PRODUCT_PAGE);
                
//                servletResponse.sendRedirect(LOAD_USER_INFOR);
//            }
            
//            if ((resource.equals("") || resource.equals("login")) && userRole.equals("user")) {
////                servletResponse.sendRedirect(PRODUCT_PAGE);
//                
//                servletResponse.sendRedirect(PRODUCT_PAGE);
//            }
                

                if (role != null) {
                    if (role.contains(userRole)) {
                        System.out.println("Hello Im pass filter 1");
                        chain.doFilter(request, response);
                    } else {
                        servletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                } else {
                    if (resource.contains(".jpg")) {
                        chain.doFilter(request, response);
                    } else {
                        servletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                }
            }
        } catch (Throwable t) {
            LOGGER.error(t.getMessage());
//            servletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("AuthorizeFilter1:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthorizeFilter1()");
        }
        StringBuffer sb = new StringBuffer("AuthorizeFilter1(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
