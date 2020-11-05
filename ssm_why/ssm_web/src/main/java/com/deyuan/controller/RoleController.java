package com.deyuan.controller;


import com.deyuan.pojo.Orders;
import com.deyuan.pojo.Permission;
import com.deyuan.pojo.Role;
import com.deyuan.service.IRoleService;
import com.deyuan.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @RequestMapping("/findAll")
    public ModelAndView  findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                 @RequestParam(name = "size",required = true,defaultValue = "4") Integer size){
        ModelAndView mv =new ModelAndView();
        List<Role> list  =roleService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll.do";
    }


    @RequestMapping("/findRoleByIdAndPermission")
    public ModelAndView findRoleByIdAndPermission(@RequestParam(name = "id",required = true) String roleid){
        ModelAndView mv = new ModelAndView();
//        直接获取role对象
        mv.addObject("roleId",roleid);
       List<Permission> list= roleService.findOtherPermission(roleid);
       mv.addObject("permissionList",list);
       mv.setViewName("role-permission-add");
       return mv;

    }
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "ids",required = true) String[] ids){
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:findAll.do";
    }





}
