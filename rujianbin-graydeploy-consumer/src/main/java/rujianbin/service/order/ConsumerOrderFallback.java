package rujianbin.service.order;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Created by rujianbin on 2018/3/23.
 */
@Component
public class ConsumerOrderFallback implements IConsumerOrderService{

    @Override
    public String orderDetail(@RequestHeader(value = "version", required = false) String version) {
        return "error";
    }
}
