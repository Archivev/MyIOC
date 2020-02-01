package com.Archiver.test;

import com.Archiver.entity.Man;
import com.Archiver.entity.People;
import com.Archiver.entity.User;
import com.Archiver.main.ApplicationContext;
import com.Archiver.main.ClassPathXmlApplicationContext;

/**
 * @Author: Archiver
 * @Description:
 * @Date: Created in 13:06 2020/1/31
 * @Modified By:
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext ct = new ClassPathXmlApplicationContext("resources/applicationContext.xml");
        People people = (People) ct.getBean("people");
        User user = (User) ct.getBean("user");
        Man man = (Man) ct.getBean("man");
        System.out.println(ct.containsBean("man"));
        System.out.println(ct.getBean("people"));
        System.out.println(ct.getBean("people"));
        System.out.println(ct.isSingleton("man"));
        System.out.println(ct.isPrototype("man"));
        System.out.println(people);
        System.out.println(user);
        System.out.println(man);
    }
}
