package com.kuang.mapper;

import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public User queryUserByName(String name);
}
