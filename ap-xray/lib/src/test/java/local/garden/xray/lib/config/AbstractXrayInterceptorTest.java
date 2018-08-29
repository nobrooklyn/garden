package local.garden.xray.lib.config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.amazonaws.xray.AWSXRay;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Test;

public class AbstractXrayInterceptorTest extends AbstractXrayInterceptor {
    @Test
    public void test_invoke() throws Throwable {
        ProceedingJoinPoint pjp = mock(ProceedingJoinPoint.class);
        Signature signature = mock(Signature.class);

        when(signature.getDeclaringTypeName()).thenReturn("TestSub");
        when(signature.getName()).thenReturn("test");
        when(pjp.getSignature()).thenReturn(signature);
        when(pjp.getArgs()).thenReturn(new Object[] {});
        when(pjp.getTarget()).thenReturn(new Object());

        try {
            AWSXRay.beginSegment("Test");
            super.invoke(pjp);
        } finally {
            AWSXRay.endSegment();
        }
    }
}
