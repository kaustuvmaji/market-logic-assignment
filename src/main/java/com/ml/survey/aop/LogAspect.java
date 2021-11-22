/**
 * 
 */
package com.ml.survey.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ml.survey.common.LogMethodExecution;

/**
 * This Class enable logging for all the methods annotated with 
 * {@LogMethodExecution} annotation.
 * 
 * @author Kaustuv Maji
 */
@Component
@Aspect
public class LogAspect {

	private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

	public LogAspect() {
	}

	/**
	 * This method log the execution timeline of business methods which are
	 * annotated with {@link LogMethodExecution}
	 * 
	 * @param joinPoint this provides the entry point of this rest app.
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(com.ml.survey.common.LogMethodExecution)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		Signature signature = joinPoint.getSignature();

		LOG.info("[---] ## > Entering " + signature.getDeclaringTypeName() + "::" + signature.getName() + "(...)");

		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;

		LOG.info("[---] ## > Exiting " + signature.getDeclaringTypeName() + "::" + signature.getName() + "(...)");

		if (LOG.isTraceEnabled()) {
			LOG.trace("## > " + signature.getDeclaringTypeName() + "::" + signature.getName() + "(...) executed in "
					+ executionTime + "ms");
		}
		return proceed;
	}
}
