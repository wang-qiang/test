package test.common;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.aspectj.lang.ProceedingJoinPoint;

import test.dto.Result;

public class AopValidate {

	public Object isThrow(ProceedingJoinPoint pjp){
		try {
			return pjp.proceed(pjp.getArgs());
		} catch (Throwable e) {
			if (e instanceof ConstraintViolationException) {
				ConstraintViolationException mve = (ConstraintViolationException) e;
				Set<String> result = new HashSet<>();
				Set<ConstraintViolation<?>> set = mve.getConstraintViolations();
				for (ConstraintViolation<?> m : set) {
					result.add(m.getMessage());
				}
				return new Result(1, result);
			} else {
				e.printStackTrace();
				return null;
			}
		}
	}
}