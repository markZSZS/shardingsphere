package org.zs.shardingsphere;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zs.shardingsphere.bean.User;
import org.zs.shardingsphere.mapper.UserMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class Test {


    @Autowired
    UserMapper mapper;



    @org.junit.jupiter.api.Test
    void insert() throws ParseException {
        for(long i=22;i<=29;i++){
            User user=new User();
            user.setId(i);
            user.setUsername("李四"+i);
            user.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2027-06-01 10:00:00"));
            mapper.insert(user);
        }
    }
    @org.junit.jupiter.api.Test
    void get(){
        User user = mapper.selectById(4L);
        System.out.println(user.getUsername());
    }

}