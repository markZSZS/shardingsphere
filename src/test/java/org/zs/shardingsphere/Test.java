package org.zs.shardingsphere;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zs.shardingsphere.bean.User;
import org.zs.shardingsphere.mapper.UserMapper;

@SpringBootTest
public class Test {


    @Autowired
    UserMapper mapper;



    @org.junit.jupiter.api.Test
    void insert(){
        for(long i=22;i<=29;i++){
            User user=new User();
            user.setId(i);
            user.setUsername("李四"+i);
            mapper.insert(user);
        }
    }
    @org.junit.jupiter.api.Test
    void get(){
        User user = mapper.selectById(4L);
        System.out.println(user.getUsername());
    }

}