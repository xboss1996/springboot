package com.example.demo.mapper;

import com.example.demo.model.UserClass;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into USER{name,account_id,token,gmt_create,gmt_modified} values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(UserClass user);
}
