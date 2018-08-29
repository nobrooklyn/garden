package local.garden.xray.lib.config;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Subsegment;
import com.amazonaws.xray.spring.aop.XRayInterceptorUtils;

import org.aspectj.lang.ProceedingJoinPoint;

public abstract class AbstractXrayInterceptor {
    protected Object invoke(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Subsegment subsegment = AWSXRay.beginSubsegment(pjp.getSignature().getName());
            subsegment.setMetadata(XRayInterceptorUtils.generateMetadata(pjp, subsegment));
            return XRayInterceptorUtils.conditionalProceed(pjp);
        } catch (Throwable t) {
            AWSXRay.getCurrentSegment().addException(t);
            throw t;
        } finally {
            AWSXRay.endSubsegment();
        }
    }
}
