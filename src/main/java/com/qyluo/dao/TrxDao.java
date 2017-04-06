package com.qyluo.dao;

import com.qyluo.meta.Trx;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by qy_lu on 2017/4/4.
 */
@Repository
public interface TrxDao {
    @Select("select * from trx")
    public List<Trx> getTrxs();
}
