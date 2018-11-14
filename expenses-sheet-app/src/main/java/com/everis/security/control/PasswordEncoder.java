package com.everis.security.control;

import com.everis.login.boundary.LoginService;
import com.everis.login.entity.User;
import com.everis.security.boundary.PasswordEncoded;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;

@PasswordEncoded
@Interceptor
@Priority(value = Interceptor.Priority.APPLICATION)
public class PasswordEncoder {

    @Inject
    private LoginService loginService;

    @AroundInvoke
    public Object encode(InvocationContext ctx) throws Exception {
        Method method = ctx.getMethod();
        //get fields from @interface
        PasswordEncoded userPasswordHashed = method.getDeclaredAnnotation(PasswordEncoded.class);

        final String digestAlgorithm = userPasswordHashed.digestAlgorithm();
        final String secureRandomType = userPasswordHashed.secureRandomType();
        final String secureRandomImpl = userPasswordHashed.secureRandomImpl();

        Arrays.asList(ctx.getParameters()).forEach((obj) -> {
            if(obj instanceof User) {
                User user = (User) obj;
                byte[] userSalt = this.loginService.getUserSalt(user.getUsername());
                if(userSalt != null) {
                    user.setSalt(userSalt);
                } else {
                    user.setSalt(generateSalt(secureRandomType, secureRandomImpl));
                }
                user.setPassword(getSecurePassword(user.getPassword(), user.getSalt(), digestAlgorithm));
            }
        });

        return ctx.proceed();
    }

    private byte[] generateSalt(String secureRandomType, String secureRandomImpl) {
        byte[] salt = new byte[64];

        SecureRandom sr;
        try {
            sr = SecureRandom.getInstance(secureRandomType,secureRandomImpl);
            sr.nextBytes(salt);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            //log error
        }
        return salt;
    }

    private String getSecurePassword(String passwordToHash, byte[] salt, String digestAlgorithm) {
        String generatedPassword = passwordToHash;
        try {
            MessageDigest md = MessageDigest.getInstance(digestAlgorithm);
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(byte b : bytes) {
                sb.append(Integer.toString((b & 0xFF) + 0x100, 16).substring(0));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            //log error
        }
        return generatedPassword;
    }

}
