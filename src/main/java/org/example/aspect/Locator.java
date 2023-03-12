package org.example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@EnableAspectJAutoProxy
public class Locator {

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void isServiceAnnotation(){}

    @Pointcut("within(org..*Service)")
    public void isServiceByName(){}

    @Pointcut("within(org.example.service.*)")
    public void isServiceByPackage(){}

    @Pointcut("execution(public void org.example.service.CustomerService.get(*))")
    public void isMethodByName(){}

    @Pointcut("isServiceAnnotation() && isServiceByName() || isServiceByPackage()")
    public void isService(){}

    @Pointcut("@within(org.springframework.stereotype.Repository)")
    public void isRepositoryAnnotated(){}


    @Before("isServiceAnnotation()")
    public void annotatedServiceStartingLog(JoinPoint point){
        log.info(">>> BEFORE ANNOTATED SERVICE {}", point.toShortString());
    }

    @AfterThrowing("isRepositoryAnnotated()")
    public void annotatedRepositoryExceptionThrowingLog(JoinPoint point){
        log.warn("### WHEN REPOSITORY THROWS EXCEPTION {}", point.toLongString());
    }

    @AfterReturning(value = "isRepositoryAnnotated() && target(repo) && args(id)", argNames = "point, repo, id")
    public void annotatedRepositoryWhenDbRerunsSomething(JoinPoint point, Object repo, Object id){
        log.warn("<<< WHEN ARGUMENTS EXISTS {}, {}, {}", point.toShortString(), repo.toString(), id.toString());
    }


}
