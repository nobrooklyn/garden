package local.garden.xray.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import local.garden.xray.lib.config.AbstractXrayInterceptor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ApiXrayInterceptor extends AbstractXrayInterceptor {

    @Override
    @Around("execution(public * local.garden.xray.api..*(..))")
    protected Object invoke(ProceedingJoinPoint pjp) throws Throwable {
        log.info("xray tracing");
        return super.invoke(pjp);
    }
}
