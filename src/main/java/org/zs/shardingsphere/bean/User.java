package org.zs.shardingsphere.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_2026")
public class User {

    @TableId
    private Long id;


    private String username;

    private Date createTime;

}