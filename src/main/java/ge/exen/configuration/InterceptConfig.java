package ge.exen.configuration;

import ge.exen.DAO.ExamDao;
import ge.exen.models.StudentExam;
import ge.exen.models.User;
import ge.exen.services.IExamService;
import ge.exen.services.IUserService;
import ge.exen.services.Zipper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;


public class InterceptConfig implements HandlerInterceptor {

    @Autowired
    IUserService userService;
    @Autowired
    IExamService examService;
    @Autowired
    ExamDao examDAO;
    @Autowired
    Zipper zip;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if(GlobalConstants.DEBUG)return true;
        User currentUser = userService.getCurrentUser();
        String url = request.getRequestURL().toString();
        url = url.substring(8);
        url = url.substring(url.indexOf("/"));
        if(url.startsWith("/resources/")){
            return true;
        }
        if(url.startsWith("/getSidebar")){
            return true;
        }

        if(currentUser == null && !url.equals("/login")) {
            response.sendRedirect("/login");
            return false;
        }
        if(currentUser == null) {
            request.setAttribute("loggedin", "0");
            request.setAttribute("username", "");
            return true;
        }
        request.setAttribute("loggedin", "1");
        request.setAttribute("userId", currentUser.getId());
        request.setAttribute("username", currentUser.getName() + " " + currentUser.getLastName());
        switch (currentUser.getStatus()) {
            case User.ADMIN :
            break;

            case User.LECTURER:
            break;

            case User.STUDENT:

                if(url.startsWith("/admin") || url.startsWith("/lecturer")) {
                    response.sendRedirect("/accessDenied");
                    return false;
                }

                StudentExam ex = examService.getLiveExamForCurrentStudent();
                if(ex != null) {
                    int mn = examDAO.get(ex.getExamId()).getDurationInMinutes();
                    Date time = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(examDAO.get(ex.getExamId()).getStartDate());
                    long ONE_MINUTE_IN_MILLIS = 60000;
                    long t = time.getTime() + ONE_MINUTE_IN_MILLIS * mn;

                    request.setAttribute("time", t);
                }
                else
                    if(!url.startsWith("/eh") && !url.startsWith("/login") && !url.startsWith("/logout"))response.sendRedirect("/eh");
                    request.setAttribute("sidebar", "sidebar.jsp");
            break;



        }
        return true;
    }

}
