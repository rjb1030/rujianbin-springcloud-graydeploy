package rujianbin.service.order;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import rujianbin.eureka.api.service.IOrderService;

/**
 * Created by rujianbin on 2018/3/23.
 */

@FeignClient(value = "rujianbin-graydeploy-provider2",fallbackFactory = OrderFallBackFactory.class)
public interface IConsumerOrderService extends IOrderService {

    @Override
    @RequestMapping("/orderDetail")
    String orderDetail(@RequestHeader(value = "version", required = false) String version);
}
