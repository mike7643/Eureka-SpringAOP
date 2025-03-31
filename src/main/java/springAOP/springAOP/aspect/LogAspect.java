package springAOP.springAOP.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//Logging 을 담당하는 Aspect

@Component // 객체가 필요한 시점에 Spring 이 주입
@Aspect // aspectJ 라이브러리에 의해 AOP로 동작
public class LogAspect {

    // Logging 을 위한 객체 필요
    // trace > debug > info > warn > error 메소드들은 얼마나 더 자세한 혹은 얼마나 더 중요한 로그를 남기느냐에 따라 선택
    // Springboot 의 현재 설정에 따라 로그 출력 결정 default 설정은 info
    // application.properties 에서 설정 가능
    // PointCUt (Join Point (모든 메소드) 중 어떤 메소드에 끼여 들 것인가 표현)
//    @Pointcut(value = "execution(* springAOP.springAOP.aspect.*.*(..))") // 리턴타입, 패키지, 클래스이름, 파라미터
//    @Pointcut(value = "execution(int springAOP.springAOP.aspect.*.*(..))") // 리턴타입, 패키지, 클래스이름, 파라미터
//    @Pointcut(value = "execution(int springAOP.springAOP.aspect.*.*(String, int))") // 리턴타입, 패키지, 클래스이름, 파라미터
//    @Pointcut(value = "execution(* springAOP.springAOP.aspect.sub.*.*(..))") // 리턴타입, 패키지, 클래스이름, 파라미터
    @Pointcut(value = "execution(* springAOP.springAOP.aspect..*(..))") // 리턴타입, 패키지, 클래스이름, 파라미터
    private void logPointcut() {}// 이름이 logPointcut() 인 Pointcut 1개 생성

    private final Logger logger = LoggerFactory.getLogger(this.getClass());// Logback 구현체 (springboot default 구현체)

    // Advise 와 JoinPoint 에 의해 실제 로그 구현
    @Before("logPointcut()")
    public void beforeLog(JoinPoint joinPoint) {//JoinPoint : 실제 호출되는 메소드
//        System.out.println("[sout : before]");
        logger.info("[Logger : before]");
        logger.info(joinPoint.getSignature().getName());
    }
    @After("logPointcut()")
    public void afterLog(JoinPoint joinPoint) {//JoinPoint : 실제 호출되는 메소드
//        System.out.println("[sout : after]");
        logger.info("[Logger : after]");
    }
}