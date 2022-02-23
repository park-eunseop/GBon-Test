package kr.co.gb.eunsub.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

/**
 * null 및 빈 문자열 입력 여부 검사
 * @author Park
 * @since 1.0.0
 *
 */

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface NotEmptyString {

}
