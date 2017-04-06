package com.qyluo.dao;

import com.qyluo.meta.Trx;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by qy_lu on 2017/4/4.
 */
public interface TrxDao {
    @Select("select * from trx")
    public List<Trx> getTrxs();
}
