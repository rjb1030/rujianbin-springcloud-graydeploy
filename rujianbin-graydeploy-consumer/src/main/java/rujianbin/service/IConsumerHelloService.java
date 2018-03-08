package rujianbin.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rujianbin.eureka.api.bean.RjbParam;
import rujianbin.eureka.api.bean.UserDto;
import rujianbin.eureka.api.service.IHelloService;

/**
 * Created by rujianbin on 2018/2/7.
 *
 * 利用@FeignClient 以接口形式封装服务提供者的restFul调用
 */

@FeignClient(value = "rujianbin-graydeploy-provider",fallbackFactory = MyFallBackFactory.class)
public interface IConsumerHelloService extends IHelloService {

    @RequestMapping("/hello")
    String hello(@RequestParam(value = "version", required = false) String version);

    @RequestMapping("/say")
    String say(@RequestParam("name") String name, @RequestParam("age") int age);

    @RequestMapping("/talk")
    String talk(@RequestBody RjbParam param);

    @RequestMapping("/sing")
    UserDto sing(@RequestBody RjbParam param);
}
