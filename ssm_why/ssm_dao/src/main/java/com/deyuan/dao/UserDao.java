package com.deyuan.dao;

import com.deyuan.pojo.Role;
import com.deyuan.pojo.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface UserDao {
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.deyuan.dao.RoleDao.findRoleByUserID")),
    })
    public UserInfo findByUsername(String username);

    @Select("select * from users")
    List<UserInfo> findall();

    @Insert("insert into users(username,password,email,phoneNum,status) values (#{username},#{password},#{email},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.deyuan.dao.RoleDao.findRoleByUserID")),
    })
    UserInfo findById(String id);

    @Select("select * from role where id not in(select roleid from users_role where userid=#{userid})")
    List<Role> findOtherRole(String id);

    @Insert("insert into users_role(userid,roleid) values (#{userid},#{roleid})")
    void addRoleToUser(@Param("userid") String userid, @Param("roleid") String roleid);
}
