package rujianbin;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import rujianbin.common.utils.RjbStringUtils;
import rujianbin.common.utils.YamlPropertySourceFactory;

import javax.security.auth.message.config.AuthConfigFactory;

/**
 * Hello world!
 *
 */
//@EnableGrayBunny
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@PropertySource(value={"classpath:application-eureka-consumer-config.yml"},factory=YamlPropertySourceFactory.class)
public class AppConsumer
{
    private static final Logger log = LoggerFactory.getLogger(AppConsumer.class);

    public static void main( String[] args )
    {
        //解决tomcat 版本太高，org.apache.catalina.authenticator.AuthenticatorBase.getJaspicProvider报错问题
        if (AuthConfigFactory.getFactory() == null) {
            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
        }
        Environment env  = new SpringApplicationBuilder().sources(
                AppConsumer.class).web(true).run(args).getEnvironment();
        log.info(RjbStringUtils.startupLog(env));
    }
}
