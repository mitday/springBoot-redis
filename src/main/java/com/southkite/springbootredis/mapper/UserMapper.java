package com.southkite.springbootredis.mapper;

import com.southkite.springbootredis.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liao xin <liaoxin@mailink.com>
 * @date 2020/4/4 15:57
 */
@Mapper
@Component
public interface UserMapper {

    /**
     * 插入
     * @param u
     */
    @Insert("insert sys_user(id,user_name) values(#{id},#{userName})")
    void insert(User u);

    /**
     * 更新
     * @param u
     */
    @Update("update sys_user set user_name = #{userName} where id=#{id} ")
    void update(User u);

    /**
     * 删除
     * @param id
     */
    @Delete("delete from sys_user where id=#{id} ")
    void delete(@Param("id") String id);

    /**
     * 查询
     * @param id
     * @return
     */
    @Select("select id,user_name from sys_user where id=#{id} ")
    User find(@Param("id") String id);

    /**
     * 注：方法名和要UserMapper.xml中的id一致
     * @param userName
     * @return
     */
    List<User> query(@Param("userName") String userName);

    /**
     * 批量删除
     */
    @Delete("delete from sys_user")
    void deleteAll();
}
