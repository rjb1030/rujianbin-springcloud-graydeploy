package rujianbin;

import com.graydeploy.springcloud.versioning.config.FeignClientConfigduration;
import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import rujianbin.common.utils.RjbStringUtils;
import rujianbin.common.utils.YamlPropertySourceFactory;

import javax.security.auth.message.config.AuthConfigFactory;

/**
 * Created by rujianbin on 2018/2/12.
 * 配置：
 *  1.引入springcloud-starter-versioning.jar
 *  2.指定versioning.server-version.metadata.xxx=1.0配置
 *  3.@RibbonClients指定rule配置
 *
 * 测试请求
 * 127.0.0.1:8010/api-graydeploy-hello/hello
 * 127.0.0.1:8010/api-graydeploy-order/orderDetail
 */

@EnableZuulProxy
@SpringBootApplication
@RibbonClients(value = {
        @RibbonClient(name = "rujianbin-graydeploy-provider",configuration = FeignClientConfigduration.class),
        @RibbonClient(name = "rujianbin-graydeploy-provider2",configuration = FeignClientConfigduration.class)
})
@PropertySource(value={"classpath:application-eureka-zuul-config.yml"},factory=YamlPropertySourceFactory.class)
public class EurekaZuulApplication {

    private static final Logger log = LoggerFactory.getLogger(EurekaZuulApplication.class);

    public static void main(String[] args) {
        //解决tomcat 版本太高，org.apache.catalina.authenticator.AuthenticatorBase.getJaspicProvider报错问题
        if (AuthConfigFactory.getFactory() == null) {
            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
        }
        Environment env = new SpringApplicationBuilder().sources(
                EurekaZuulApplication.class
                ).web(true).run(args).getEnvironment();
        log.info(RjbStringUtils.startupLog(env));
    }
}
