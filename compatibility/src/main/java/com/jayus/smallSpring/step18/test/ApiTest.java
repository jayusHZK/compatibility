package com.jayus.smallSpring.step18.test;

import com.jayus.smallSpring.step18.context.support.ClassPathXmlApplicationContext;
import com.jayus.smallSpring.step18.jdbc.support.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class ApiTest {

    private static JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        init();
        //execeteSqlTest();
        //queryForListTest();
        //queryListWithColumnClassTypeTest();
        //queryListWithColumnCLassTypeWithArgTest();
        //queryListWithArgTest();
        //queryObjectTest();
        //queryMapTest();
        queryMapWithArgTest();
    }

    public static void init() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
    }

    public static void execeteSqlTest() {
       /* ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);*/
        jdbcTemplate.execute("        CREATE TABLE `user` (\n" +
                "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                "  `username` varchar(100) DEFAULT NULL,\n" +
                "        PRIMARY KEY (`id`),\n" +
                "        UNIQUE KEY `user_id_uindex` (`id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin");
    }

    public static void queryForListTest() {
        List<Map<String, Object>> allResult = jdbcTemplate.queryForList("select * from user;");
        for (int i = 0; i < allResult.size(); i++) {
            System.out.printf("第%d行数据", i + 1);
            Map<String, Object> objectMap = allResult.get(i);
            System.out.println(objectMap);
        }
    }

    public static void queryListWithColumnClassTypeTest(){
        List<String> allResult = jdbcTemplate.queryForList("select username from user;", String.class);
        for (int i = 0; i < allResult.size(); i++) {
            System.out.printf("第%d行数据", i + 1);
            String username = allResult.get(i);
            System.out.println(username);
        }
    }

    public static void queryListWithColumnCLassTypeWithArgTest(){
        List<String> allResult = jdbcTemplate.queryForList("select username from user where id = ? or id = 2", String.class, 1);
        for (int i = 0; i < allResult.size(); i++) {
            System.out.printf("第%d行数据", i + 1);
            String username = allResult.get(i);
            System.out.println(username);
        }
    }

    public static void queryListWithArgTest(){
        List<Map<String, Object>> allResult = jdbcTemplate.queryForList("select * from user where id = ?", 1);
        for (int i = 0; i < allResult.size(); i++) {
            System.out.printf("第%d行数据", i + 1);
            Map<String, Object> row = allResult.get(i);
            System.out.println(row);
        }
    }

    public static void queryObjectTest(){
        String username = jdbcTemplate.queryForObject("select username from user ", String.class);
        System.out.println(username);
    }

    public static void queryMapTest(){
        Map<String, Object> row = jdbcTemplate.queryForMap("select * from user where id = 1");
        System.out.println(row);
    }

    public static void queryMapWithArgTest(){
        Map<String, Object> row = jdbcTemplate.queryForMap("select * from user where id = ?", 1);
        System.out.println(row);
    }

}
