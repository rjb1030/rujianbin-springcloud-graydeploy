package rujianbin.graydeploy.provider2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import rujianbin.eureka.api.service.IOrderService;

/**
 * Created by rujianbin on 2018/2/6.
 */
@RestController
public class OrderController implements IOrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private DiscoveryClient client;

    @Autowired
    Environment env;

    @Override
    public String orderDetail(@RequestHeader(value = "version", required = false) String version) {
        return "orderDetail method reponse----> version="+version+",port="+env.getProperty("server.port");
    }




}
