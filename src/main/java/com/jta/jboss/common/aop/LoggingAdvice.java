package com.jta.jboss.common.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @FileName : LoggingAdvice.java
 * @Project : jbts
 * @Date : 2013. 9. 12. 
 * @author : 황의환
 */

public class LoggingAdvice implements MethodInterceptor {
    protected final Log logger = LogFactory.getLog(getClass());

    public Object invoke(MethodInvocation invo) throws Throwable {
        String className = invo.getThis().getClass().getName();
        
        if (logger.isDebugEnabled()) {
            if(className.indexOf("BO")> 0){
                logger.debug("");
                logger.debug("-------------------------------------------------------------------------------------");
                logger.debug(className + "." + invo.getMethod().getName() + "()" + " start");
                logger.debug("");
                Object[] args = invo.getArguments();
                if ((args != null) && (args.length > 0)) {
                    for (int i = 0; i < args.length; i++) {
                        logger.debug("Argument[" + i + "] : " + args[i]);
                    }
                }
            }else if(className.indexOf("DAO")> 0){
                logger.debug(className + "." + invo.getMethod().getName() + "()" + " start");
                Object[] args = invo.getArguments();
                if ((args != null) && (args.length > 0)) {
                    for (int i = 0; i < args.length; i++) {
                        logger.debug("Argument[" + i + "] : " + args[i]);
                    }
                }   
            }else if(className.indexOf("Proxy")> 0 && className.length() < 10){
               
            }else{
                logger.debug(className + "." + invo.getMethod().getName() + "()" + " start");
                Object[] args = invo.getArguments();
                if ((args != null) && (args.length > 0)) {
                    for (int i = 0; i < args.length; i++) {
                        logger.debug("Argument[" + i + "] : " + args[i]);
                    }
                } 
            }
        }
        Object retVal = invo.proceed();

        if (logger.isDebugEnabled()) {
            if (className.indexOf("BO")> 0) {
                logger.debug("");
                logger.debug(className + "." + invo.getMethod().getName() + "()" + " end ");
                logger.debug("-------------------------------------------------------------------------------------");                
            }else if(className.indexOf("DAO")> 0){
                logger.debug(className + "." + invo.getMethod().getName() + "()" + " end ");  
            }else if(className.indexOf("Proxy")> 0 && className.length() < 10){    
            }else{
                logger.debug(className + "." + invo.getMethod().getName() + "()" + " end ");
            }
        }

        return retVal;
    }
}