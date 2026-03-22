package test_Evaluation_machine.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import test_Evaluation_machine.Interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册登录拦截器
        registry.addInterceptor(new LoginInterceptor())
                // 拦截需要登录的接口
                .addPathPatterns("/judge/**", "/user/current", "/user/solved", "/user/logout", "/user/problem-status")
                // 放行的接口
                .excludePathPatterns("/user/register", "/user/login", "/problem/**", "/index.html", "/");
    }
}