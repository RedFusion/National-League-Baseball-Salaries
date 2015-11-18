package ru.menkin.store;

import org.junit.*;
import org.springframework.context.*;
import org.springframework.context.support.*;
import org.springframework.orm.hibernate4.*;

/**
 * @author Menkin
 * @since 16.10.2015
 */

public class SpringStorageTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        context.getBean(HibernateTemplate.class);
        ISpringStorage springStorage = context.getBean(ISpringStorage.class);
        springStorage.values();
    }
}
