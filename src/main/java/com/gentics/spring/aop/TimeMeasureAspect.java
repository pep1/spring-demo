package com.gentics.spring.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Scope("singleton")
public class TimeMeasureAspect {

	private static Logger log = Logger.getLogger(TimeMeasureAspect.class);

	private Long findTotalTime = new Long(0);

	private Long createTotalTime = new Long(0);

	@Around("execution(* com.gentics.spring.controller.BugController.find*(..))")
	private Object measureFindTime(ProceedingJoinPoint pjp) throws Throwable {

		log.debug("starting time measurement for find bug. Signature name: " + pjp.getSignature().getName());
		StopWatch findWatch = new StopWatch("Measure time of method " + pjp.getSignature().getName());
		findWatch.start();

		Object returnValue = null;

		try {
			returnValue = pjp.proceed();
		} catch (Exception e) {
			// we catch all exceptions here in order to stop the watch in any
			// case
			log.error("Oooops, something went wrong with the method " + pjp.getSignature().getName(), e);
			throw e;
		} finally {
			findWatch.stop();
			log.debug("Execution of method " + pjp.getSignature().getName() + " took " + findWatch.getLastTaskTimeMillis());
			findTotalTime += findWatch.getLastTaskTimeMillis();
		}

		return returnValue;
	}

	@Around("execution(* com.gentics.spring.controller.BugController.create(..))")
	private Object measureCreateTime(ProceedingJoinPoint pjp) throws Throwable {

		log.debug("starting time measurement for create bug. Signature name: " + pjp.getSignature().getName());
		StopWatch createWatch = new StopWatch("Measure time of method " + pjp.getSignature().getName());
		createWatch.start();

		Object returnValue = null;

		try {
			returnValue = pjp.proceed();
		} catch (Exception e) {
			// we catch all exceptions here in order to stop the watch in any
			// case
			log.error("Oooops, something went wrong with the method " + pjp.getSignature().getName(), e);
			throw e;
		} finally {
			createWatch.stop();
			log.debug("Execution of method " + pjp.getSignature().getName() + " took " + createWatch.getLastTaskTimeMillis());
			createTotalTime += createWatch.getLastTaskTimeMillis();
		}

		return returnValue;
	}

	public TimeMeasurement getTimeDetails() {
		return new TimeMeasurement(findTotalTime, createTotalTime);
	}

}
