package org.zs.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.zs.shardingsphere.bean.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}