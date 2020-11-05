package com.deyuan.service;

import com.deyuan.pojo.SysLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ISysLogService {



    public void save(SysLog sysLog);

    List<SysLog> findAll(int page,int size);
}
