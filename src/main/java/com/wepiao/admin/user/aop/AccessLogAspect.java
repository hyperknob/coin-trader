package com.wepiao.admin.user.aop;

/**
 * Created by JinSong on 2017/3/4.
 * Controller AccessLog的AOP执行类
 */
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.alibaba.fastjson.JSON;
import com.wepiao.user.common.constant.Constants;
import com.wepiao.user.common.enumeration.LogType;
import com.wepiao.user.common.util.StringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Aspect
@Component
public  class AccessLogAspect {

    private static final Logger logUserInfo = LoggerFactory.getLogger(Constants.LOG_USER_INFO);
    private static final Logger logUserTag  = LoggerFactory.getLogger(Constants.LOG_USER_TAG);
    private static final String logBody     = "HTTP Body:{}";

    public  static  final String ASPECT_SERVICE_EXECUTION = "execution(* com.wepiao.admin.user.rest.controller..*ControllerImpl.*(..))";

    /**
     * 后置通知 用于拦截Controller层记录accesslog
     * @param joinPoint 切点
     * @param retVal
     */
    @AfterReturning(value = ASPECT_SERVICE_EXECUTION,argNames = "retVal",returning = "retVal")
    public  void doAfter(JoinPoint joinPoint, Object retVal) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AccessLog log = method.getAnnotation(AccessLog.class);
        // 仅仅log enable的Controller method才记录日志
        if (null != log && log.enable()) {
            String reqBody = null;
            String requestId = null;
            String url = null;
            Response respBody = (Response) retVal;
            long startMillis = System.currentTimeMillis();

            Object[] args = joinPoint.getArgs();
            if (null != args) {
                for (Object arg: args) {
                    if (arg instanceof String) {
                        reqBody = (String) arg;
                    } else if (arg instanceof HttpHeaders){
                        requestId = ((HttpHeaders) arg).getHeaderString(Constants.SHORT_REQ_ID);
                    } else if (arg instanceof HttpServletRequest){
                        requestId = ((HttpServletRequest) arg).getHeader(Constants.SHORT_REQ_ID);
                        url = ((HttpServletRequest) arg).getRequestURL().toString();
                    }
                }
            }
//            DubboxRestServiceContext context = ResteasyProviderFactory.getContextData(DubboxRestServiceContext.class);
//            String requestId = context.getHttpHeaders().getFirst("requestId");
//
//            String url = context.getUri().getAbsolutePath().toString();
//            String reqBody = new String(context.getBody());

            logMsg(requestId, reqBody, (String) respBody.getEntity(), startMillis, url, LogType.USERTAG);
        }
    }

    private void logMsg(String requestId, String reqBody, String respBody, long startMillis, String url, LogType logType) {
        JSONObject logMsg = new JSONObject();
        logMsg.put("requestId", requestId);
        if (StringUtil.isEmptyCheckTrim(reqBody)) {
            logMsg.put("requestBody", new JSONObject());
        } else {
            JSONObject requestObj = JSON.parseObject(reqBody);
            logMsg.put("requestBody", requestObj);
        }
        if (StringUtil.isEmptyCheckTrim(respBody)) {
            logMsg.put("responseBody", new JSONObject());
        } else {
            JSONObject responseObj = JSON.parseObject(respBody);
            logMsg.put("responseBody", responseObj);

        }
        logMsg.put("requestUrl", url);
        logMsg.put("requestTime", String.valueOf(startMillis));
        long endMillis = System.currentTimeMillis();
        logMsg.put("runtime", endMillis - startMillis);
        if (logType == LogType.USERTAG) {
            logUserTag.info(logBody, logMsg);
        } else if (logType == LogType.USERINFO) {
            logUserInfo.info(logBody, logMsg);
        }

    }
}
