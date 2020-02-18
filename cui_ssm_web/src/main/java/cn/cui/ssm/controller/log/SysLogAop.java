package cn.cui.ssm.controller.log;

import cn.cui.ssm.domain.SysLog;
import cn.cui.ssm.service.ISysLogService;
import cn.cui.ssm.utils.UUIDUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Auther cui
 * @Date 2020/2/14
 * @Desc
 */
public class SysLogAop {
    //因为在web.xml文件里配置了一个监听器，所以可以注入
    @Autowired
    private ServletRequest request;
    @Autowired
    private ISysLogService sysLogService;

    private Class<?> clazz;
    private Method method;
    private Date visitTime;
    private String username;
    private String ip;
    private String url;
    private Long executionTime;
    private String methodName;
    private String exception;

    //前置
    public void doBeforeLog(JoinPoint jp) throws Exception{
        clazz = jp.getTarget().getClass();          //获得被代理对象
        methodName = jp.getSignature().getName();   //获得方法名字
        Method[] methods = clazz.getMethods();
        for (Method m : methods){
            if(m.getName().equals(methodName)){
                method = m;                         //获得被代理的方法
                break;
            }
        }
        RequestMapping ClassAnno = clazz.getAnnotation(RequestMapping.class);   //获得requestMapping注解
        RequestMapping MethodAnno = method.getAnnotation(RequestMapping.class); //获得requestMapping注解
        String[] classValue = ClassAnno.value();    //获得url
        String[] methodValue = MethodAnno.value();  //获得url
        if(classValue!=null && methodValue!=null){
            url = classValue[0]+methodValue[0];
        }
        ip = request.getRemoteAddr();   //获得ip
        SecurityContext context = SecurityContextHolder.getContext();   //获得springsecurity上下文
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User)
                        context.getAuthentication().getPrincipal();     //通过springsecurity获得当前用户
        username = user.getUsername();
        visitTime = new Date();     //访问时间
    }

    //后置
    public void doAfterReturningLog(JoinPoint jp) throws Exception{
        executionTime = new Date().getTime()-visitTime.getTime();   //获得执行时间
    }

    //异常
    //<aop:after-throwing throwing="ex"/>
    public void doAfterThrowingLog(JoinPoint jp, Throwable ex) throws Exception{
        //出现异常时拿到异常对象
        String exType = ex.getCause().getClass().getName();
        String exMsg = ex.getCause().getMessage();
        exception = exType+":"+exMsg;
        if(exception.length()>255){
            exception = exception.substring(0, 255);
        }
    }

    //最终
    public void doAfterLog(JoinPoint jp) throws Exception{
        SysLog sysLog = new SysLog();
        sysLog.setException(exception);
        sysLog.setExecutionTime(executionTime);
        sysLog.setIp(ip);
        sysLog.setMethod(methodName);
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);
        sysLog.setId(UUIDUtils.randomUUIDString());
        sysLogService.save(sysLog);
        exception = null;
    }
}