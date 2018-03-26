package rujianbin.service.order;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import rujianbin.eureka.api.service.IOrderService;

/**
 * Created by rujianbin on 2018/3/7.
 */
@Component
public class OrderFallBackFactory implements FallbackFactory<IOrderService> {

    private static final Logger log = LoggerFactory.getLogger(OrderFallBackFactory.class);


    @Override
    public IOrderService create(Throwable throwable) {
        log.error("【ERROR】服务降级原因: {} " ,throwable.getMessage());

        return new ConsumerOrderFallback();

    }
}
