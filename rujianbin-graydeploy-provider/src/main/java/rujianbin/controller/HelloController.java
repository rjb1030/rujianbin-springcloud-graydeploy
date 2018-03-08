package rujianbin.controller;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rujianbin.eureka.api.bean.RjbParam;
import rujianbin.eureka.api.bean.UserDto;
import rujianbin.eureka.api.service.IHelloService;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by rujianbin on 2018/2/6.
 */
@RestController
public class HelloController implements IHelloService {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient client;

    @Autowired
    Environment env;

    @Override
    public String header(@RequestHeader(value = "version", required = false) String version) {
        return "header method reponse----> version="+version+",port="+env.getProperty("server.port");
    }

    @Override
    public String hello(@RequestParam(value = "version", required = false) String version){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/hello host:{}.service_id:{}",instance.getHost(),instance.getServiceId());
        int sleepTime = new Random().nextInt(3000);
        logger.info("hello睡眠"+sleepTime);

        return "hello! i am provider port="+env.getProperty("server.port")+",version="+version;
    }

    @Override
    public String say(@RequestParam String name,@RequestParam int age){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/say host:{}.service_id:{}",instance.getHost(),instance.getServiceId());
        int sleepTime = new Random().nextInt(3000);
        logger.info("say睡眠"+sleepTime);
//        try {
//            Thread.sleep(sleepTime);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "hello "+name+"("+age+")! i am provider port="+env.getProperty("server.port");
    }

    @Override
    public String talk(@RequestBody RjbParam param){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/talk host:{}.service_id:{}",instance.getHost(),instance.getServiceId());
        int sleepTime = new Random().nextInt(3000);
        logger.info("talk睡眠"+sleepTime);
//        try {
//            Thread.sleep(sleepTime);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "hello,"+param.getSearchName()+","+param.getSearchPageNo()+","+ ArrayUtils.toString(param.getProductId().toArray())+" port="+env.getProperty("server.port");
    }

    @Override
    public UserDto sing(@RequestBody RjbParam param){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/sing host:{}.service_id:{}",instance.getHost(),instance.getServiceId());
        int sleepTime = new Random().nextInt(3000);
        logger.info("sing睡眠"+sleepTime);
//        try {
//            Thread.sleep(sleepTime);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        UserDto dto = new UserDto();
        dto.setName(param.getSearchName()+" port="+env.getProperty("server.port"));
        dto.setAge(param.getSearchPageNo());
        dto.setAmount(new BigDecimal(param.getProductId().size()));
        return dto;
    }


}
