package org.demoexm.web;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
//配置了@ContextConfiguration注解并使用该注解的locations属�?指明spring和配置文件之后，
@ContextConfiguration(locations = {"classpath:dubbo.xml","classpath:spring.xml","classpath:spring-redis.xml"})
public class BaseTest {

}
