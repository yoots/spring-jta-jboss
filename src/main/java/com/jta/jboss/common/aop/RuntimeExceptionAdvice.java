package com.jta.jboss.common.aop;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.ThrowsAdvice;

public class RuntimeExceptionAdvice implements ThrowsAdvice{
    protected final Log logger = LogFactory.getLog(getClass());

    public void afterThrowing(RuntimeException ex) throws Throwable {
        if (logger.isDebugEnabled()) {
            logger.error("RuntimeExceptionAdvice ##########################################################################");            
            logger.error("Exception : " + ex.getClass().getName());
        }
        logger.error(ExceptionUtils.getFullStackTrace(ex));        

        if (logger.isDebugEnabled()) {
            logger.error("########################################################################## RuntimeExceptionAdvice");
        }
    }    
    
    public static String readStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
