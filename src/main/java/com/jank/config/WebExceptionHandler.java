package com.jank.config;

import com.jank.common.LogManager;
import com.jank.common.Result;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理
 */
@ControllerAdvice
public class WebExceptionHandler {
    private static Logger logger = LogManager.getLogger(WebExceptionHandler.class);

    /**
     * 未知异常
     *
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception e, HttpServletRequest request) {
        logger.error(e.getLocalizedMessage(), e);
        ModelAndView model = new ModelAndView();
		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			model.setView(new MappingJackson2JsonView());
            model.addObject(Result.newError().withMsg("系统繁忙，请稍候再试"));
		} else {
		    model.setViewName("error/500");
		}
		return model;
    }

    /**
     * 请求方法错误异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Result httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        String[] supportedMethods = e.getSupportedMethods();
        if (supportedMethods != null && supportedMethods.length > 0) {
            return Result.newError()
                    .withCode(HttpStatus.METHOD_NOT_ALLOWED.value())
                    .withMsg("请使用" + supportedMethods[0] + "请求");
        }
        return Result.newError().withCode(HttpStatus.METHOD_NOT_ALLOWED.value()).withMsg("不支持当前请求方法");
    }

    /**
     * 业务错误异常
     */
    @ExceptionHandler({
            IllegalArgumentException.class,
            IllegalStateException.class
    })
    @ResponseBody
    public Result illegalExceptionHandler(Exception e) {
        return Result.newError().withMsg(e.getMessage());
    }

    /**
     * 前端参数校验异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result bindExceptionHandler() {
        return Result.newError().withMsg("参数校验出错，请检查参数");
    }

    /**
     * shiro权限异常
     */
    @ExceptionHandler({
            AuthenticationException.class,
            AuthorizationException.class
    })
    public ModelAndView authorizationExceptionHandler(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			model.setView(new MappingJackson2JsonView());
            model.addObject(Result.newError().withCode(HttpStatus.FORBIDDEN.value()).withMsg("没有执行此操作的权限"));
		} else {
		    model.setViewName("error/403");
		}
		return model;
    }
    
}
