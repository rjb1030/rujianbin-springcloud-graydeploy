package rujianbin.controller;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rujianbin.common.utils.RjbStringUtils;
import rujianbin.service.hello.IConsumerHelloService;
import rujianbin.service.order.IConsumerOrderService;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by rujianbin on 2018/2/6.
 */

@RefreshScope  //该注解允许配置中心参数更新后调用/refresh后热更新变量
@RestController
public class HelloConsumerController implements ApplicationContextAware {

    private ApplicationContext ctx;

    @Value("${rujianbin.testName}")
    private String testName;

    @Autowired
    private SpringClientFactory factory;

    @Autowired
    private IConsumerHelloService helloService;
    @Autowired
    private IConsumerOrderService orderService;

    /**
     * 根据RequestVersionExtractor的获取方式，配置灰度方式，当前是header属性gray_version决定灰度
     * @param version
     * @return
     */
    @RequestMapping("/hello-consumer2")
    public Map hello2(@RequestParam(value = "version", required = false) String version){

        System.out.println("==== 输出默认配置：");
        // 获取默认的配置
        ZoneAwareLoadBalancer alb = (ZoneAwareLoadBalancer) factory
                .getLoadBalancer("default");
        ZoneAwareLoadBalancer alb2 = (ZoneAwareLoadBalancer) factory
                .getLoadBalancer("rujianbin-graydeploy-provider");
        ZoneAwareLoadBalancer alb3 = (ZoneAwareLoadBalancer) factory
                .getLoadBalancer("rujianbin-graydeploy-provider2");
        System.out.println("    IClientConfig: "
                + factory.getLoadBalancer("default").getClass().getName());
        System.out.println(" default   IRule: " + alb.getRule().toString());
        System.out.println(" rujianbin-graydeploy-provider   IRule: " + alb2.getRule().toString());
        System.out.println(" rujianbin-graydeploy-provider2   IRule: " + alb3.getRule().toString());

        System.out.println("==== 输出 cloud-provider 配置：");


//        IRule irule = ctx.getBean(IRule.class);
        System.out.println("controller 线程id="+Thread.currentThread().getId());
        Long s1 = System.currentTimeMillis();
        String hello = helloService.hello(version);
        String order = orderService.orderDetail(version);


        Map<String,Object> map = new LinkedHashMap<>();
        map.put("hello",hello);
        map.put("order",order);

        map.put("testName",testName);
        Long s2 = System.currentTimeMillis();
        map.put("耗时",s2-s1);
        System.out.println(RjbStringUtils.ObjectToString(map));
        return map;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }
}
