package local.garden.xray.lib.config;

import java.net.URL;

import javax.servlet.Filter;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import com.amazonaws.xray.plugins.ECSPlugin;
import com.amazonaws.xray.strategy.sampling.LocalizedSamplingStrategy;

public abstract class AbstractXrayWebConfig {

    static {
        AWSXRayRecorderBuilder builder = AWSXRayRecorderBuilder.standard().withPlugin(new ECSPlugin());

        URL ruleFile = Thread.currentThread().getContextClassLoader().getResource("/sampling-rules.json");
        builder.withSamplingStrategy(new LocalizedSamplingStrategy(ruleFile));

        AWSXRay.setGlobalRecorder(builder.build());
    }

    protected Filter tracingFilter(String segmentName) {
        return new AWSXRayServletFilter(segmentName);
    }
}