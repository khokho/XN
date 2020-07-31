package ge.exen.configuration;

import ge.exen.models.User;
import ge.exen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptConfig implements HandlerInterceptor {

    @Autowired
    UserService user;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
        User currentUser = user.getCurrentUser();
        String url = request.getRequestURL().toString();
        url = url.substring(8);
        url = url.substring(url.indexOf("/"));
        System.out.println(url);
        if(currentUser == null && !url.equals("/login")) {

            response.sendRedirect("/login");
            return false;
        }
        if(currentUser == null) {
            request.setAttribute("sidebar", "adminSidebar.jsp");
            return true;
        }

        switch (currentUser.getStatus()) {
            case User.ADMIN :
                if(!url.startsWith("/admin")) {
                    response.sendRedirect("/accessDenied");
                    return false;
                }
                request.setAttribute("sidebar", "adminSidebar.jsp");
            break;

            case User.LECTURER:
                if(!url.startsWith("/lecturer")) {
                    response.sendRedirect("/accessDenied");
                    return false;
                }
                request.setAttribute("sidebar", "adminsidebar.jsp");
            break;

            case User.STUDENT:
                if(url.startsWith("/admin") || url.startsWith("/lecturer")) {
                    response.sendRedirect("/accessDenied");
                    return false;
                }
                request.setAttribute("sidebar", "adminsidebar.jsp");
            break;



        }

         */
        return true;
    }
}
