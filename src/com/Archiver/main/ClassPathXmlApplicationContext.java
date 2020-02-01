package com.Archiver.main;

import com.Archiver.config.Bean;
import com.Archiver.config.Property;
import com.Archiver.parse.ParseXML;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: Archiver
 * @Description:
 * @Date: Created in 13:00 2020/1/31
 * @Modified By:
 */
public class ClassPathXmlApplicationContext implements ApplicationContext{

    //获取解析到信息的map
    private Map<String, Bean> map;

    //作为IOC容器使用，放置反射生成的对象
    private Map<String,Object> context = new HashMap<>();

    public ClassPathXmlApplicationContext(String pathName){
        //读取配置文件得到初试化的Bean的信息
        map = ParseXML.getConfig(pathName);

        //通过entrySet方法，返回一个储存map映射关系的set集合，分别取 key 和 value
        for (Map.Entry<String,Bean> e: map.entrySet()) {
            String beanName = e.getKey();
            Bean bean = e.getValue();

            Object exsitBean = context.get(beanName);
            //如果容器中没有而且作用域为单例时则创建对象
            if (exsitBean == null && "singleton".equals(bean.getScope())){
                //根据bean信息创建一个实例
                exsitBean = createBean(bean);
                //并将实例放入容器中
                context.put(beanName,exsitBean);
            }
        }
    }

    public Object createBean(Bean bean){
        Class clazz = null;
        try{
            clazz = Class.forName(bean.getClassName());
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("没有找到该类");
        }
        Object object = null;
        try {
            object = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("没有提供无参构造器");
        }
        //BeanUtils注入属性的map
        Map<String,String> pmap = new HashMap<>();

        for (Property p: bean.getProperties()) {
            String name = p.getName();
            String value = p.getValue();
            String ref = p.getRef();
            if (value != null){
                pmap.put(name,value);

                //利用Apache的BeanUtils注入属性
                try{
                    BeanUtils.populate(object,pmap);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("请检查你的" + name + "属性");
                }
            }
            if (ref != null){
                Object exsitBean = context.get(ref);
                /**
                 * 首先寻找容器中是否有 ref 的对象
                 * 如果有则不用再次创建，如果没有则递归的先 ref 的对象创建
                 */
                if (exsitBean == null){
                    //从xml解析的map中取出bean并创建
                    exsitBean = createBean(map.get(ref));
                }
                try {
                    BeanUtils.setProperty(object,name,exsitBean);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("您的bean的属性" + name + "未设置set方法");
                }
            }
        }
        return object;
    }

    @Override
    public Object getBean(String name) {
        Object o = context.get(name);
        //如果为空则证明容器内没有，且为多例模式直接创建
        //也就是说单例模式下，初始化容器时直接创建，多例等到调用时在创建
        if (o == null) {
            o = createBean(map.get(name));
        }
        return o;
    }

    @Override
    public boolean isSingleton(String name) {
        Bean bean = map.get(name);
        return Objects.equals(bean.getScope(),"singleton");
    }

    @Override
    public boolean isPrototype(String name) {
        Bean bean = map.get(name);
        return Objects.equals(bean.getScope(),"prototype");
    }

    @Override
    public boolean containsBean(String name) {
        Object o = map.get(name);
        if (o != null){
            return true;
        }else {
            return false;
        }
    }
}
