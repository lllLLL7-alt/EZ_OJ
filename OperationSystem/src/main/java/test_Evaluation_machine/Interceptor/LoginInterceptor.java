package test_Evaluation_machine.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import test_Evaluation_machine.Pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            response.setStatus(401);
            response.getWriter().write("未登录，请先登录");
            return false;
        }
        return true;
    }
}