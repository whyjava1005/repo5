package com.deyuan.dao;

import com.deyuan.pojo.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface Memberdao {
    @Select("select * from member where id = #{id}")
    Member findById(String id)throws Exception;
}
