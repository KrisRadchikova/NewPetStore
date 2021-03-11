package by.tms.petstore.configuration;

import by.tms.petstore.interceptor.AdminInterceptor;
import by.tms.petstore.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private AdminInterceptor adminInterceptor;
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(adminInterceptor)
                .addPathPatterns("/user/getAll")
                .addPathPatterns("/user/getUser/{id}")
                .addPathPatterns("/store/getAll")
                .addPathPatterns("/store/order/{orderId}")
                .addPathPatterns("/store/inventory/{petId}")
                .addPathPatterns("/pet")
                .excludePathPatterns("/pet/all")
                .excludePathPatterns("/pet/findByStatus")
                .excludePathPatterns("/user/auth");

        registry
                .addInterceptor(userInterceptor)
                .addPathPatterns("/pet/getAll")
                .addPathPatterns("/pet/find/{petId}")
                .addPathPatterns("/store/order/{orderId}");
    }

    //

    @Autowired
    public void setInterceptors(AdminInterceptor adminInterceptor, UserInterceptor userInterceptor) {
        this.adminInterceptor = adminInterceptor;
        this.userInterceptor = userInterceptor;
    }
}

