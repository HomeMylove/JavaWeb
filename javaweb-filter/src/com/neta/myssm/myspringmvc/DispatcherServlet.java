package com.neta.myssm.myspringmvc;

import com.neta.myssm.ioc.BeanFactory;
import com.neta.myssm.ioc.ClassPathXmlApplicationContext;
import com.neta.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

// 拦截所有以 .do 结尾的请求
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {
    private BeanFactory beanFactory;

    // 解析配置文件
    public DispatcherServlet() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
        beanFactory = new ClassPathXmlApplicationContext();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取前缀
        String servletPath = req.getServletPath();

        servletPath = servletPath.substring(1, servletPath.lastIndexOf(".do"));

        Object controllerBeanObj = beanFactory.getBean(servletPath);

        // 获取关键字
        String operate = req.getParameter("operate");
        if (StringUtil.isEmpty(operate))
            operate = "index";

        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(operate)) {
                    // 统一获取参数
                    Parameter[] parameters = method.getParameters();
                    Object[] parameterValues = new Object[parameters.length];

                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        if (parameterName.equals("req")) {
                            parameterValues[i] = req;
                        } else if (parameterName.equals("session")) {
                            parameterValues[i] = req.getSession();
                        } else if (parameterName.equals("resp")) {
                            parameterValues[i] = resp;
                        } else {
                            Class<?> type = parameter.getType();
                            String paramStr = req.getParameter(parameter.getName());
                            Object paramObj = paramStr;
                            if (paramObj != null) {
                                if (type.equals(Integer.class)) {
                                    paramObj = Integer.parseInt(paramStr);
                                }
                            }
                            parameterValues[i] = paramObj;
                        }
                    }

                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, parameterValues);

                    String returnStr = (String) returnObj;
                    if (returnStr.startsWith("redirect:")) {
                        String redirect = returnStr.substring("redirect:".length());
                        resp.sendRedirect(redirect);
                    } else {
                        super.processTemplate(returnStr, req, resp);
                    }
                }
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new DispatcherServletException("Dispatcher Servlet Exception");
        }
    }
}
