package com.Archiver.parse;

import com.Archiver.config.Bean;
import com.Archiver.config.Property;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Archiver
 * @Description: 解析xml文件（dom4j）
 * @Date: Created in 13:06 2020/1/31
 * @Modified By:
 */
public class ParseXML {

    //模拟IOC容器
    /**
     * map<String,Bean>结构
     * String 用来模拟 bean 中的 id 值
     * Bean 模拟实际 IOC 容器中的 BeanDefinition
     */
    private static Map<String,Bean> map = new HashMap<>();

    /**
     * 解析xml配置文件中的bean并注入到容器中
     * @param pathName
     * @return
     */
    public static Map<String, Bean> getConfig(String pathName) {

        //新建一个dom4j的解析器
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            //根据路径解析xml文件
            document = saxReader.read(pathName);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("请检查您的xml配置文件是否正确");
        }
        //根据Xpath寻找bean节点
        String Xpath = "//bean";

        //寻找根节点下所有bean结构
        List<Element> beans = document.selectNodes(Xpath);

        for (Element element: beans) {

            //将解析到的内容封装到 bean 对象中
            Bean bean = new Bean();
            String name = element.attributeValue("id");
            String className = element.attributeValue("class");
            String scope = element.attributeValue("scope");
            bean.setName(name);
            bean.setClassName(className);

            //如果没有写则默认单例
            if (scope!=null){
                bean.setScope(scope);
            }

            //继续遍历bean节点下的property节点
            List<Element> properties = element.elements("property");

            //如果有该节点则解析内容
            if (properties != null) {
                for (Element e:properties) {
                    Property property = new Property();
                    String pname = e.attributeValue("name");
                    String value = e.attributeValue("value");
                    String ref = e.attributeValue("ref");
                    property.setName(pname);
                    property.setValue(value);
                    property.setRef(ref);
                    /**
                     * 获取list<Property>后向内添加元素
                     * 将解析到的属性封装到property中，并添加到bean中
                     */
                    bean.getProperties().add(property);
                }
            }
            //封装到map中
            map.put(name,bean);
        }

        return map;
    }
}
