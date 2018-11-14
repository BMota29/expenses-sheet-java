package com.everis.security.boundary;

import javax.interceptor.InterceptorBinding;
import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@InterceptorBinding
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface PasswordEncoded {
    String digestAlgorithm() default "SHA-512";
    String secureRandomType() default "SHA1PRNG";
    String secureRandomImpl() default "SUN";
}
