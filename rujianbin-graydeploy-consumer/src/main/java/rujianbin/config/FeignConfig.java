package rujianbin.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by rujianbin on 2018/2/28.
 */

@Configuration
public class FeignConfig {

    /**
     * logging.level.<FeignClient>=DEBUG 开启debu FeignClient是feign接口的全路径
     * feign请求的日志输出，同时需要配置文件制定@FeignClient制定的客户端类为DEBUG
     * 默认是Logger.Level.NONE 不记录任何日志，故需要调整级别
     * NONE: 不记录日志
     * BASIC：仅记录请求方法，URL以及响应状态码和执行时间
     * HEADERS: 记录除了BASIC的信息之外还会加上HEADER
     * FULL 全部信息
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }



//    @Bean
//    public VersioningConfiguration.UnUseVersioningIRule unUseVersioningIRule(){
//        return new VersioningConfiguration.UnUseVersioningIRule();
//    }


//    @Bean
//    public RequestVersionExtractor requestVersionExtractor(){
//        return new RequestVersionExtractor.RequestHeaderVersionExtractor();
//    }






}
