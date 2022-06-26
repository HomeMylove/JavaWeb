package com.neta.myssm.ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlApplicationContext implements BeanFactory{
    private final Map<String,Object> beanMap = new HashMap<>();

    public ClassPathXmlApplicationContext(){
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
                    String beanClassName = beanElement.getAttribute("class");
                    Class<?> beanClass = Class.forName(beanClassName);
                    Object beanObj = beanClass.newInstance();
                    beanMap.put(beanId, beanObj);
                }
            }
            // 建立 bean 之间的依赖关系
            for(int i = 0; i < beanNodeList.getLength();i++){
                Node beanNode = beanNodeList.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    NodeList beanChildNodes = beanElement.getChildNodes();
                    for(int j = 0 ; j <beanChildNodes.getLength();j++){
                        Node beanChildNode = beanChildNodes.item(j);
                        if(beanChildNode.getNodeType() == Node.ELEMENT_NODE && beanChildNode.getNodeName().equals("property")){
                            Element beanChildElement = (Element) beanChildNode;
                            String propertyName = beanChildElement.getAttribute("name");
                            String propertyRef = beanChildElement.getAttribute("ref");

                            // 找到 ref 对应的实例
                            Object refObj = beanMap.get(propertyRef);
                            // 外层 bean 对象
                            Object beanObj = beanMap.get(beanId);

                            Field field = beanObj.getClass().getDeclaredField(propertyName);
                            field.setAccessible(true);
                            field.set(beanObj,refObj);
                        }
                    }
                }
            }


        } catch (ParserConfigurationException | SAXException | IOException | ClassNotFoundException |
                 InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
