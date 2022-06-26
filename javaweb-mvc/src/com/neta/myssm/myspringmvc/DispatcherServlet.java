package com.neta.myssm.myspringmvc;

import com.neta.myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

// 拦截所有以 .do 结尾的请求
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {
    private final Map<String, Object> beanMap = new HashMap<>();


    // 解析配置文件
    public DispatcherServlet() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            // 解析配置文件
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            // 创建 DocumentBuilderFactory 对象
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newDefaultInstance();
            // 创建 DocumentBuilder 对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            // Document 对象
            Document document = documentBuilder.parse(inputStream);
            // 获取 bean 标签
            NodeList beanNodeList = document.getElementsByTagName("bean");

//            循环
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String beanClass = beanElement.getAttribute("class");
                    Class<?> controllerClass = Class.forName(beanClass);
                    Object beanObj = controllerClass.newInstance();
                    beanMap.put(beanId, beanObj);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException | ClassNotFoundException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");

        // 获取前缀
        String servletPath = req.getServletPath();

        servletPath = servletPath.substring(1, servletPath.lastIndexOf(".do"));

        Object controllerBeanObj = beanMap.get(servletPath);

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
            throw new RuntimeException(e);
        }
    }
}
