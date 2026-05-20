package com.Ornexa.filter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.Ornexa.model.User;
import com.Ornexa.utils.SessionUtils;



public class AthenticationFilter implements Filter {
	private static final String LOGIN_SERVLET = "/loginServlet";
	private static final String LOGIN = "/pages/Users/login.jsp";
    private static final String REGISTER_SERVLET = "/registerServlet";
    private static final String REGISTER = "/pages/Users/register.jsp";
    private static final String ContactUs_Servlet="/ContactUsServlet";
    private static final String ContactUs = "/pages/Users/contactUs.jsp";
    private static final String AboutUs_Servlet="/AboutUsServlet";
    private static final String AboutUs = "/pages/Users/about.jsp";
    private static final String Product_Servlet="/ProductServlet";
    private static final String Product = "/pages/Users/product.jsp";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath(); 
       
      
        String path = uri.substring(contextPath.length());


        if (path.startsWith("/resources/") || path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".png") || path.endsWith(".jpg")   || path.startsWith("/WEB-INF")) {
        	
            chain.doFilter(request, response);
            return;
        }

        User user = (User) SessionUtils.getAttribute(req, "user");
        boolean isLoggedIn = (user != null);
        boolean isAdminPage =
        	       path.equals("/UserManagementServlet")
        	    || path.equals("/AddProductServlet")
        	    || path.equals("/AdminDashboardServlet");
        if (isAdminPage && (!isLoggedIn || !"admin".equalsIgnoreCase(user.getRole()))) {
            res.sendRedirect(contextPath + "/Home_pageServlet");
            return;
        }
 
        boolean isPublic = path.equals(LOGIN_SERVLET) ||path.equals(LOGIN) || path.equals(REGISTER_SERVLET)|| path.equals(REGISTER) || path.equals(ContactUs_Servlet) || path.equals(ContactUs) || path.equals(AboutUs_Servlet) ||path.equals(AboutUs) ||path.equals(Product_Servlet) ||path.equals(Product);

        if (!isLoggedIn) {
            if (isPublic) {
                chain.doFilter(request, response);
            } else {
            	res.sendRedirect(contextPath + "/Home_pageServlet");

            }
        } else {
            if (isPublic) {
                res.sendRedirect(contextPath + "/Home_pageServlet");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {}

}
