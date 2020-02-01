package com.Archiver.main;

/**
 * @Author: Archiver
 * @Description:
 * @Date: Created in 12:53 2020/1/31
 * @Modified By:
 */
public interface BeanFactory {

    /**
     * 获取容器中的bean实例
     * @return
     */
    Object getBean(String name);

    /**
     * 是否是单例模式
     * @return
     */
    boolean isSingleton(String name);

    /**
     * 是否是多例模式
     * @return
     */
    boolean isPrototype(String name);

    /**
     *
     * 是否包含该bean
     * @return
     */
    boolean containsBean(String name);
}
