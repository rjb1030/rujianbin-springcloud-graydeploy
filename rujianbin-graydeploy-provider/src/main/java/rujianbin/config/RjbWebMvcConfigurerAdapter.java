package rujianbin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Created by rujianbin on 2018/2/28.
 */
@Configuration
public class RjbWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(new SpringMVCInterceptor());
        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }


    public static class SpringMVCInterceptor  extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
            Enumeration<String> headerNames =  httpServletRequest.getHeaderNames();
            while(headerNames.hasMoreElements()){
                String headerName = headerNames.nextElement();
                System.out.println("请求中 header["+headerName+"]="+httpServletRequest.getHeader(headerName));
            }
            return true;
        }
    }
}
