package by.tms.petstore.interceptor;

import by.tms.petstore.model.UserStatus;
import by.tms.petstore.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    private KeyService keyService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String adminKey = request.getHeader("X-Key");
        UserStatus userStatus = keyService.validKey(adminKey);
        if (userStatus.equals(UserStatus.ADMIN)) {
            return true;
        }
        return false;
    }

    @Autowired
    public void setKeyService(KeyService keyService) {
        this.keyService = keyService;
    }
}
