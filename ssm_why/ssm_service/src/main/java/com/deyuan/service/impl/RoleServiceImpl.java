package com.deyuan.service.impl;

import com.deyuan.dao.RoleDao;
import com.deyuan.pojo.Permission;
import com.deyuan.pojo.Role;
import com.deyuan.service.IRoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> findAll(int page,int size) {
        //        配置分页，从第几页开始，显示几条数据
        PageHelper.startPage(page,size);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Permission> findOtherPermission(String roleid) {
        return roleDao.findOtherPermission(roleid);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) {
        for (String permissionid : ids) {
            roleDao.addPermissionToRole(roleId,permissionid);
        }
    }
}
