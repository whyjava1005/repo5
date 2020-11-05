package com.deyuan.dao;

import com.deyuan.pojo.Permission;
import com.deyuan.pojo.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {


    //子查询 根据userid从桥表中先查出对应的roleid,就是这个用户对应几个角色 再根据这个roleid也就是角色查出对应的权限
    @Select("select * from role where id in(select roleid from users_role where userid=#{userid})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(id = true,property = "roleName",column = "roleName"),
            @Result(id = true,property = "roleDesc",column = "roleDesc"),
            @Result(id = true,property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.deyuan.dao.IPermissionDao.findByRoleId")),
    })
   public List<Role> findRoleByUserID(String userid);
    @Select("select * from role")
    List<Role> findAll();
    @Insert("insert into role(roleName,roleDesc) values (#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from permission where id not in (select permissionid from role_permission where roleid=#{roleid})")
    List<Permission> findOtherPermission(String roleid);

    @Insert("insert into role_permission (roleid,permissionid) values (#{roleid},#{permissionid})")
    void addPermissionToRole(@Param("roleid") String roleId, @Param("permissionid") String permissionid);
}
