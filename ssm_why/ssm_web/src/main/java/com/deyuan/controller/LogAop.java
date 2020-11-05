package com.deyuan.controller;

import com.deyuan.pojo.SysLog;
import com.deyuan.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;//    开始时间
    private Class claszz; //初始化访问的类
    private Method method; //初始化访问的方法
    @Before("execution(* com.deyuan.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {

        visitTime=new Date();//        设置开始时间
        claszz=jp.getTarget().getClass(); //通过反射获取具体的类的信息，可以指向任何类对象
        String methodName = jp.getSignature().getName();//获取要执行的方法的名称

        Object[] args = jp.getArgs();//获取有参数的方法的参数
        if (args==null || args.length==0){
            method = claszz.getMethod(methodName);//通过类对象调用方法名获取要访问的没参数的方法
        }else {
//                将有参数的方法的参数，封装成一个class数组对象，不知道长度，就使用已经获取到的对象类型方法参数的数组的长度
            Class[] classargs=new Class[args.length];
            for (int i = 0; i <args.length ; i++) {
//                循环遍历classargs把里面的参数封装到classargs里面
                    classargs[i]=args[i].getClass();
            }
            //封装参数获取到有参数的方法
           method = claszz.getMethod(methodName, classargs);
        }



    }


//    后置通知
    @After("execution(* com.deyuan.controller.*.*(..))")
    public void doAfter(){
//        获取访问时长
       long time= new Date().getTime()-visitTime.getTime();
       //获取url 通过反射的方式获取
        String url ="";
        if (claszz!=null && method!=null && claszz!=LogAop.class){
            //获取类上的requestmapping注解
            RequestMapping classAnnotation = (RequestMapping) claszz.getAnnotation(RequestMapping.class);
            if (classAnnotation!=null){
                String[] classvalue = classAnnotation.value(); //获取到类上的注解的值
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);//获取到方法上的requestmapping注解
                String[] methodvalue = methodAnnotation.value();//获取到方法上的注解的值
                url=classvalue[0]+methodvalue[0];//拼出url
                //获取请求的ip地址
                String ip = request.getRemoteAddr();
                //获取当前操作的用户
                SecurityContext context = SecurityContextHolder.getContext();
                User user = (User) context.getAuthentication().getPrincipal();
                //获取当前操作的用户名
                String username = user.getUsername();
                SysLog sysLog = new SysLog();
                sysLog.setIp(ip);
                sysLog.setUsername(username);
                sysLog.setExecutionTime(time);
                sysLog.setMethod("[类名]"+claszz.getName()+"[方法名]"+method.getName());
                sysLog.setUrl(url);
                sysLog.setVisitTime(visitTime);//访问开始的时间
                sysLogService.save(sysLog);
            }
        }


    }

}
