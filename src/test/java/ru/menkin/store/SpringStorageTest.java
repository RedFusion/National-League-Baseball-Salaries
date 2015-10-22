package ru.menkin.store;

import org.junit.*;
import org.springframework.context.*;
import org.springframework.context.support.*;

/**
 * @author Menkin
 * @since 16.10.2015
 */
public class SpringStorageTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        FakeStorage fakeStorage = context.getBean(FakeStorage.class);
        fakeStorage.values();
    }
}
