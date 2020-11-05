package com.deyuan.service;

import com.deyuan.pojo.Permission;
import com.deyuan.pojo.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll(int page,int size);

    void save(Role role);

    List<Permission> findOtherPermission(String roleid);

    void addPermissionToRole(String roleId, String[] ids);
}
