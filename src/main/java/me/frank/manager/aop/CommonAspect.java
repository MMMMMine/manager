package me.frank.manager.aop;

import com.alibaba.fastjson.JSONObject;
import me.frank.manager.aop.annotation.NotEmpty;
import me.frank.manager.aop.annotation.Regx;
import me.frank.manager.controller.vo.Result;
import me.frank.manager.logger.BaseLogger;
import me.frank.manager.server.constant.RegexConstant;
import me.frank.manager.server.util.ValidateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by frank on 2017/8/6.
 */
@Aspect
@Component
public class CommonAspect {

    /**
     * @Author Frank
     * @Date 2017/8/6 下午4:28
     * @Description 记录请求参数, 添加服务器时间, 记录返回结果
     */
    @Around("execution(* me.frank.manager.controller.*Controller.*(..))")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        String errorMsg = "";

        Map<String, Object> data = new HashMap<>();

        Result result = new Result(Result.failCode, Result.failMsg, data);

        String methodName = joinPoint.getSignature().getName();

        String log = "api - [" + methodName + "] 收到请求 : ";

        Signature signature = joinPoint.getSignature();

        MethodSignature methodSignature = (MethodSignature) signature;

        Parameter[] parameters = methodSignature.getMethod().getParameters();

        String[] keys = methodSignature.getParameterNames();

        Object[] values = joinPoint.getArgs();

        for (int i = 0; i < keys.length; i++) {

            Parameter item = parameters[i];

            boolean hasNotEmpty = item.isAnnotationPresent(NotEmpty.class);

            boolean hasRegx = item.isAnnotationPresent(Regx.class);

            if (hasNotEmpty) {

                NotEmpty annotation = item.getAnnotation(NotEmpty.class);

                if (StringUtils.isEmpty(values[i])) {

                    errorMsg += annotation.name() + "不能为空 ";

                }

            }

            if (hasRegx) {

                boolean regResult = true;

                Regx annotation = item.getAnnotation(Regx.class);

                String regRule = annotation.type();

                switch (regRule) {

                    case RegexConstant.REG_PHONE:

                        regResult = ValidateUtil.isMobile((String) values[i]);
                        break;

                    case RegexConstant.REG_INDENTITY:

                        regResult = ValidateUtil.isIDCard((String) values[i]);
                        break;
                    case RegexConstant.REG_POSTIVE_INTEGER:

                        regResult = ValidateUtil.isPostiveInteger((String) values[i]);
                        break;


                    //新的正则

                }

                if (!regResult) {

                    errorMsg += "请输入正确的" + annotation.name() + " ";

                }


            }

            log += "[" + keys[i] + "]" + "=" + values[i] + ",";

        }

        BaseLogger.info(log);

        if (!StringUtils.isEmpty(errorMsg)) {

            result.setMsg(errorMsg);

            BaseLogger.info("api - [" + methodName + "] 返回结果 : " + JSONObject.toJSONString(result));

            return result;

        }


        Object obj = joinPoint.proceed();

        result = (Result) obj;

        result.setTimeStamp(DateTime.now().getMillis());

        BaseLogger.info("api - [" + methodName + "] 返回结果 : " + JSONObject.toJSONString(result));

        return result;

    }
}
