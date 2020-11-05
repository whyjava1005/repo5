package com.deyuan.service.impl;

import com.deyuan.dao.ISysLogDao;
import com.deyuan.pojo.SysLog;
import com.deyuan.service.ISysLogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("sysLogService")
@Transactional
public class ISysLogServiceImpl implements ISysLogService {
    @Autowired
    private ISysLogDao sysLogDao;
    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(int page,int size) {
        //        配置分页，从第几页开始，显示几条数据
        PageHelper.startPage(page,size);
        return sysLogDao.findAll();
    }
}
