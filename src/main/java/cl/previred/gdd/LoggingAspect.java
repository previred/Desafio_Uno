package cl.previred.gdd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Aspecto que se utiliza para escribir logging sin necesidad de hacerlo en cada método de
 * los controladores
 */

@Aspect
@Component
public class LoggingAspect {

  private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  @Before("execution (* cl.previred.gdd.controllers.*.*(..))")
  public void logging(JoinPoint joinPoint) {
    HttpServletRequest request =
            ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                    .getRequest();
    Object obj = joinPoint.getArgs()[0];
    ObjectMapper om = new ObjectMapper();
    try {
      String body = om.writeValueAsString(obj);
      logger.info("Request ({}): {}", request.getServletPath(), body);
    } catch (JsonProcessingException e) {
      logger.warn("Isn't possible body logging.", e);
    }
  }

  @AfterReturning(value = "execution (* cl.previred.gdd.controllers.*.*(..))", returning = "result")
  public void logging(JoinPoint joinPoint, Object result) {
    HttpServletRequest request =
            ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                    .getRequest();
    ObjectMapper om = new ObjectMapper();
    try {
      String body = om.writeValueAsString(result);
      logger.info("Response ({}): {}", request.getServletPath(), body);
    } catch (JsonProcessingException e) {
      logger.warn("Isn't possible body logging.", e);
    }
  }
}
