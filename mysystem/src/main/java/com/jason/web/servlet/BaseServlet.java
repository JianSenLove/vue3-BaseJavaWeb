package com.jason.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {
    //根据请求的最后一次路径进行方法分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求方法
        String requestURI = req.getRequestURI();
        int index = requestURI.lastIndexOf("/");
        String methodname = requestURI.substring(index + 1);

        //执行方法
            //获取方法类
        Class<? extends BaseServlet> cls = this.getClass();

            //获取方法对象
        try {
            Method method = cls.getMethod(methodname, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
