package ge.exen.configuration;

import ge.exen.models.User;
import ge.exen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class InterceptConfig implements HandlerInterceptor {

    @Autowired
    UserService user;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User currentUser = user.getCurrentUser();
        String url = request.getRequestURL().toString();
        url = url.substring(8);
        url = url.substring(url.indexOf("/"));
        System.out.println(url);
        if(currentUser == null && !url.equals("/login")) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
