package com.deyuan.controller;

import com.deyuan.pojo.SysLog;
import com.deyuan.service.ISysLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") Integer size){
        ModelAndView mv =new ModelAndView();
       List<SysLog> list= sysLogService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(list);
       mv.addObject("pageInfo",pageInfo);
       mv.setViewName("syslog-list");
       return mv;

    }
}
