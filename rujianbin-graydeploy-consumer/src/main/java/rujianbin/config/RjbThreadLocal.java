package rujianbin.config;

import com.fm.cloud.bamboo.BambooRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;

/**
 * Created by rujianbin on 2018/2/28.
 */
public class RjbThreadLocal {

    public static String GRAY_FLAG = "gray_version";

    public static ThreadLocal threadLocal = new ThreadLocal();

    public static final HystrixRequestVariableDefault<String> version_context = new HystrixRequestVariableDefault<String>();
}
